package org.barbasman.jimmazev2;
import java.util.List;
import org.barbasman.framework.*;
import android.util.Log;
import android.util.FloatMath;
import java.util.ArrayList;
import java.util.*;

public class World
{
	Jim jim;
	Momia[] momias;
	ArrayList<Momia> cMomias;
	int numMomias;
	
	Vector2 nPos;
	int tail=10;
	Jim[] tailJim;
	Mapa map;
	LoadingMapa lam;
	boolean finish=false;
	boolean winner=false;
	float distX,distY;
	SpatialHashGrid grid;
	List<GameObject> blocks;
	Rectangle toMove=new Rectangle(0,0,24,24);
	Libre libre;
	DynamicGameObject falseObj1,falseObj2;
	String text;
	GameObject win;
	
	
	public World(int numMap){
		winner=false;
		finish=false;
		map=new Mapa(numMap,lam.map[numMap].widthOfMap,lam.map[numMap].heightOfMap,lam.map[numMap].jimStartX,lam.map[numMap].jimStartY,lam.map[numMap].jimStartD,lam.map[numMap].startDistX,lam.map[numMap].startDistY,lam.map[numMap].cmap,lam.map[numMap].numDeMomias,lam.map[numMap].momia);
		distX=map.getStartDistX();
		distY=map.getStartDistY();
		
		jim=new Jim(map.getJimStartX(),map.getJimStartY(),24,24,map.jimStartD);
		numMomias=map.numDeMomias;
		momias=new Momia[map.getWidthOfMap()*map.getHeightOfMap()];
		cMomias=new ArrayList<Momia>();
		
		
		
		
		grid=new SpatialHashGrid(map.widthOfMap*32,map.heightOfMap*32,192);
		for(int i=0;i<map.widthOfMap;i++){
			for(int j=0;j<map.heightOfMap;j++){
				if(map.cmap[i][j].getBlock()==true){
					grid.insertStaticObject(map.cmap[i][j]);
				}
				if(map.cmap[i][j].getDraw()==Assets.end){
					//text="i="+i+" j="+j+" lowerX"+map.cmap[i][j].bounds.lowerLeft.x+" lowerY"+map.cmap[i][j].bounds.lowerLeft.y;
					//Log.d("org.barbasman",text);
					win=new GameObject(i*32,j*32,32,32);
					
				}
				
			}
		}
		
		/*for(int nm=0;nm<momias.length;nm++){
			momias[nm]=new Momia(map.momia[nm].position.x,map.momia[nm].position.y,32,32);
		}*/
		for(int nm=0;nm<numMomias;nm++){
			momias[nm]=new Momia(map.momia[nm].position.x,map.momia[nm].position.y,32,32);
			cMomias.add(momias[nm]);
		}
	
		libre=new Libre();
		nPos=new Vector2(0,0);
		tailJim=new Jim[tail];
		for(int w=0;w<tail;w++){
			tailJim[w]=new Jim(jim.position.x,jim.position.y,24,24,0);
		}
	}
	
	public void update(float deltaTime){
		
		jim.update(deltaTime);
		collisionBlock(jim,deltaTime);
		jimTailPos();
		for(int y=0;y<numMomias;y++){
			momias[y].update(this,deltaTime);
			gameover(momias[y],jim);
		}
		gameWiner(jim);
		

	}
	
	public void pause(){
		for(int y=0;y<numMomias;y++){
			momias[y].velocity.x=0;
			momias[y].velocity.y=0;
		}
	}
	
	public void jimTailPos(){
		
		nPos.set(jim.position);
		if(nPos.dist(tailJim[0].position)>32){
			for(int t=tail-1;t>0;t--){
				tailJim[t].position.set(tailJim[t-1].position);
				tailJim[t].bounds.lowerLeft.set(tailJim[t].position);
				tailJim[t].bounds.width=24;
				tailJim[t].bounds.height=24;
			}
			tailJim[0].position.set(nPos);
			tailJim[0].bounds.lowerLeft.set(tailJim[0].position);
			tailJim[0].bounds.width=24;
			tailJim[0].bounds.height=24;
		}
		
		for(int c=1;c<tail;c++){
			tailJim[c].position.x-=tailJim[c].position.x%32;
			if(tailJim[c].position.x%32>16){
				tailJim[c].position.x-=tailJim[c].position.x%32;
				tailJim[c].position.x+=32;
			}else{
				tailJim[c].position.x-=tailJim[c].position.x%32;
			}
			
			if(tailJim[c].position.y%32>16){
				tailJim[c].position.y-=tailJim[c].position.y%32;
				tailJim[c].position.y+=32;
			}else{
				tailJim[c].position.y-=tailJim[c].position.y%32;
			}
			
		}
		
	}
	
	
	public void moveJim(Jim jim,int direcction,float deltaTime){
		
		boolean rigth=false;
		boolean up=false;
		boolean left=false;
		boolean down=false;
		boolean uprigth=false;
		boolean upleft=false;
		boolean downrigth=false;
		boolean downleft=false;
		List<GameObject> colliders=grid.getPotentialColliders(jim);
		int len=colliders.size();
		
		switch(direcction){
			case 0:
			toMove.lowerLeft.set(((jim.position.x)-toMove.width/2),(jim.position.y-toMove.height/2)+jim.speed*deltaTime);
			for(int i=0;i<len;i++){
				GameObject collider=colliders.get(i);
				collider.bounds.width=32;
				collider.bounds.height=32;
				collider.bounds.lowerLeft.set(collider.position.x-collider.bounds.width/2,collider.position.y-collider.bounds.height/2);
				if(OverlapTester.overlapRectangles(toMove,collider.bounds)){
					up=true;
				}
			}
			if(up==false)
				jim.mUp();
			break;
			case 1:
				toMove.lowerLeft.set(((jim.position.x)-toMove.width/2)+(jim.speed*deltaTime)/2,(jim.position.y-toMove.height/2)+(jim.speed*deltaTime)/2);
			for(int i=0;i<len;i++){
				GameObject collider=colliders.get(i);
				collider.bounds.width=32;
				collider.bounds.height=32;
				collider.bounds.lowerLeft.set(collider.position.x-collider.bounds.width/2,collider.position.y-collider.bounds.height/2);
				if(OverlapTester.overlapRectangles(toMove,collider.bounds)){
					uprigth=true;
				}
			}
			if(uprigth==false)
				jim.mUprigth();
			break;
			case 2:
				toMove.lowerLeft.set(((jim.position.x)-toMove.width/2)+jim.speed*deltaTime,jim.position.y-toMove.height/2);
			for(int i=0;i<len;i++){
				GameObject collider=colliders.get(i);
				collider.bounds.width=32;
				collider.bounds.height=32;
				collider.bounds.lowerLeft.set(collider.position.x-collider.bounds.width/2,collider.position.y-collider.bounds.height/2);
				if(OverlapTester.overlapRectangles(toMove,collider.bounds)){
					rigth=true;
				}
			}
			if(rigth==false)
				jim.mRight();
			break;
			case 3:
				toMove.lowerLeft.set(((jim.position.x)-toMove.width/2)+jim.speed*deltaTime,(jim.position.y-toMove.height/2)-(jim.speed*deltaTime));
			for(int i=0;i<len;i++){
				GameObject collider=colliders.get(i);
				collider.bounds.width=32;
				collider.bounds.height=32;
				collider.bounds.lowerLeft.set(collider.position.x-collider.bounds.width/2,collider.position.y-collider.bounds.height/2);
				if(OverlapTester.overlapRectangles(toMove,collider.bounds)){
					downrigth=true;
				}
			}
			if(downrigth==false)
				jim.mDownrigth();
			break;
			case 4:
				toMove.lowerLeft.set(((jim.position.x)-toMove.width/2),(jim.position.y-toMove.height/2)-(jim.speed*deltaTime));
				for(int i=0;i<len;i++){
					GameObject collider=colliders.get(i);
					collider.bounds.width=32;
					collider.bounds.height=32;
					collider.bounds.lowerLeft.set(collider.position.x-collider.bounds.width/2,collider.position.y-collider.bounds.height/2);
					if(OverlapTester.overlapRectangles(toMove,collider.bounds)){
						down=true;
					}
				}
				if(down==false)
					jim.mDown();
			break;
			case 5:
				toMove.lowerLeft.set(((jim.position.x)-toMove.width/2)-jim.speed*deltaTime,(jim.position.y-toMove.height/2)-(jim.speed*deltaTime));
				for(int i=0;i<len;i++){
					GameObject collider=colliders.get(i);
					collider.bounds.width=32;
					collider.bounds.height=32;
					collider.bounds.lowerLeft.set(collider.position.x-collider.bounds.width/2,collider.position.y-collider.bounds.height/2);
					if(OverlapTester.overlapRectangles(toMove,collider.bounds)){
						downleft=true;
					}
				}
				if(downleft==false)
					jim.mDownleft();
			break;
			case 6:
				toMove.lowerLeft.set(((jim.position.x)-toMove.width/2)-jim.speed*deltaTime,(jim.position.y-toMove.height/2));
				for(int i=0;i<len;i++){
					GameObject collider=colliders.get(i);
					collider.bounds.width=32;
					collider.bounds.height=32;
					collider.bounds.lowerLeft.set(collider.position.x-collider.bounds.width/2,collider.position.y-collider.bounds.height/2);
					if(OverlapTester.overlapRectangles(toMove,collider.bounds)){
						left=true;
					}
				}
				if(left==false)
					jim.mLeft();
			break;
			case 7:
				toMove.lowerLeft.set(((jim.position.x)-toMove.width/2)-jim.speed*deltaTime,(jim.position.y-toMove.height/2)+jim.speed*deltaTime);
				for(int i=0;i<len;i++){
					GameObject collider=colliders.get(i);
					collider.bounds.width=32;
					collider.bounds.height=32;
					collider.bounds.lowerLeft.set(collider.position.x-collider.bounds.width/2,collider.position.y-collider.bounds.height/2);
					if(OverlapTester.overlapRectangles(toMove,collider.bounds)){
						upleft=true;
					}
				}
				if(upleft==false)
					jim.mUpleft();
			break;
		}
		
	}
	public void collisionBlock(DynamicGameObject bipedo,float deltaTime){
		
		if(bipedo.velocity.x>0){
			toMove.lowerLeft.set((bipedo.position.x-bipedo.bounds.width/2)+bipedo.velocity.x*deltaTime,bipedo.position.y-bipedo.bounds.height/2);
			List<GameObject> colliders=grid.getPotentialColliders(bipedo);
			int len=colliders.size();
			for(int i=0;i<len;i++){
				GameObject collider=colliders.get(i);
				collider.bounds.lowerLeft.set(collider.position.x-collider.bounds.width/2,collider.position.y-collider.bounds.height/2);
				if(OverlapTester.overlapRectangles(toMove,collider.bounds)){
					bipedo.position.add((bipedo.velocity.x*deltaTime*-1),0);
					bipedo.bounds.lowerLeft.set(bipedo.position.x-bipedo.bounds.width/2,bipedo.position.y-bipedo.bounds.height/2);
				}
			}
		}
		
		if(bipedo.velocity.y>0){
			toMove.lowerLeft.set(bipedo.position.x-bipedo.bounds.width/2,(bipedo.position.y-bipedo.bounds.height/2)+bipedo.velocity.y*deltaTime);
			List<GameObject> colliders=grid.getPotentialColliders(bipedo);
			int len=colliders.size();
			for(int i=0;i<len;i++){
				GameObject collider=colliders.get(i);
				collider.bounds.lowerLeft.set(collider.position.x-collider.bounds.width/2,collider.position.y-collider.bounds.height/2);
				if(OverlapTester.overlapRectangles(toMove,collider.bounds)){
					bipedo.position.add(0,(bipedo.velocity.y*deltaTime*-1));
					bipedo.bounds.lowerLeft.set(bipedo.position.x-bipedo.bounds.width/2,bipedo.position.y-bipedo.bounds.height/2);
				}
			}
		}
		if(bipedo.velocity.x<0){
			toMove.lowerLeft.set((bipedo.position.x-bipedo.bounds.width/2)+bipedo.velocity.x*deltaTime,bipedo.position.y-bipedo.bounds.height/2);
			List<GameObject> colliders=grid.getPotentialColliders(bipedo);
			int len=colliders.size();
			for(int i=0;i<len;i++){
				GameObject collider=colliders.get(i);
				collider.bounds.lowerLeft.set(collider.position.x-collider.bounds.width/2,collider.position.y-collider.bounds.height/2);
				if(OverlapTester.overlapRectangles(toMove,collider.bounds)){
					bipedo.position.add((bipedo.velocity.x*deltaTime*-1),0);
					bipedo.bounds.lowerLeft.set(bipedo.position.x-bipedo.bounds.width/2,bipedo.position.y-bipedo.bounds.height/2);
				}
			}
		}
		if(bipedo.velocity.y<0){
			toMove.lowerLeft.set(bipedo.position.x-bipedo.bounds.width/2,(bipedo.position.y-bipedo.bounds.height/2)+bipedo.velocity.y*deltaTime);
			List<GameObject> colliders=grid.getPotentialColliders(bipedo);
			int len=colliders.size();
			for(int i=0;i<len;i++){
				GameObject collider=colliders.get(i);
				collider.bounds.lowerLeft.set(collider.position.x-collider.bounds.width/2,collider.position.y-collider.bounds.height/2);
				if(OverlapTester.overlapRectangles(toMove,collider.bounds)){
					bipedo.position.add(0,(bipedo.velocity.y*deltaTime*-1));
					bipedo.bounds.lowerLeft.set(bipedo.position.x-bipedo.bounds.width/2,bipedo.position.y-bipedo.bounds.height/2);
				}
			}
		}
	}
	
	public void gameover(Momia momia, Jim jim){
		if(momia.position.dist(jim.position)<64){
			Rectangle mr=new Rectangle(momia.position.x-8,momia.position.y-8,16,16);
			//momia.bounds.lowerLeft.set(momia.position.x-momia.bounds.width/2,momia.position.y-momia.bounds.height/2);
			Rectangle jr=new Rectangle(jim.position.x-8,jim.position.y-8,16,16);
			//jim.bounds.lowerLeft.set(jim.position.x-jim.bounds.width/2,jim.position.y-jim.bounds.height/2);
			if(OverlapTester.overlapRectangles(mr,jr)){
				finish=true;
			}
		}
	}
	
	public void gameWiner(Jim jim){
		Rectangle jr=new Rectangle(jim.position.x-8,jim.position.y-8,16,16);
		if(OverlapTester.overlapRectangles(win.bounds,jr)){
			winner=true;
		}
	}
	
	
	public void moveCam(float metricsX,float metricsY){
		distX+=(metricsX);
		distY+=(metricsY);
	}
	
	public void addMomias(float x,float y){
		//numMomias+=1;
		//momias[numMomias-1]=new Momia(x,y,32,32);
		boolean ocupado=false;
		if(cMomias.size()<100){
			for(int cm=0;cm<cMomias.size();cm++){
				if(cMomias.get(cm).position.x==x&&cMomias.get(cm).position.y==y){
					ocupado=true;
				}
			}
			if(ocupado==false){
				cMomias.add(new Momia(x,y,32,32));
				}
		
		}
	}
	
	
	public void dispose(){
			
	}
	/*
	}*/
	
	
	
	/*
	}*/		
}
