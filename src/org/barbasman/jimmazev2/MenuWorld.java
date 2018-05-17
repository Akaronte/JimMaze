package org.barbasman.jimmazev2;
import java.util.List;
import org.barbasman.framework.*;
import android.util.Log;
import android.util.FloatMath;
import java.util.ArrayList;
import java.util.*;

public class MenuWorld
{
	MenuJim jim;
	Momia[] momias;
	int numMomias;

	Vector2 nPos;
	int tail=10;
	Jim[] tailJim;
	Mapa map;
	LoadingMapa lam;
	
	float distX,distY;
	SpatialHashGrid grid;
	List<GameObject> blocks;
	Rectangle toMove=new Rectangle(0,0,28,28);
	Libre libre;
	DynamicGameObject falseObj1,falseObj2;
	String text;
	
	
	public MenuWorld(int numMap){
		
		map=new Mapa(numMap,lam.map[numMap].widthOfMap,lam.map[numMap].heightOfMap,lam.map[numMap].jimStartX,lam.map[numMap].jimStartY,lam.map[numMap].jimStartD,lam.map[numMap].startDistX,lam.map[numMap].startDistY,lam.map[numMap].cmap,lam.map[numMap].numDeMomias,lam.map[numMap].momia);
		distX=map.getStartDistX();
		distY=map.getStartDistY();
		jim=new MenuJim(map.jimStartX,map.jimStartY,28,28,map.jimStartD);
		//jim=new MenuJim(map.getJimStartX(),map.getJimStartY(),20,20,map.jimStartD);
		numMomias=map.numDeMomias;
		momias=null;
		momias=new Momia[numMomias];
		grid=new SpatialHashGrid(map.widthOfMap*32,map.heightOfMap*32,192);
		for(int i=0;i<map.widthOfMap;i++){
			for(int j=0;j<map.heightOfMap;j++){
				if(map.cmap[i][j].getBlock()==true){
					grid.insertStaticObject(map.cmap[i][j]);
				}
				
			}
		}
		for(int nm=0;nm<momias.length;nm++){
			momias[nm]=new Momia(map.momia[nm].position.x,map.momia[nm].position.y,16,16);
		}
	
		libre=new Libre();
		nPos=new Vector2(0,0);
		tailJim=new Jim[tail];
		for(int w=0;w<tail;w++){
			tailJim[w]=new Jim(jim.position.x,jim.position.y,24,24,0);
		}
	}
	
	public void update(float deltaTime){
		jim.isAtascado(deltaTime);
		jim.moveJim(grid,deltaTime);
		//jim.update(deltaTime);
		//collisionBlock(jim,deltaTime);
		//jimTailPos();
		for(int y=0;y<numMomias;y++){
			//momias[y].update(this,deltaTime);
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
		
		for(int c=0;c<tail;c++){
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
		List<GameObject> colliders=grid.getPotentialColliders(jim);
		int len=colliders.size();
		
		switch(direcction){
			case 0:
			toMove.lowerLeft.set(jim.position.x+1*jim.speed*deltaTime,jim.position.y);
			for(int i=0;i<len;i++){
				GameObject collider=colliders.get(i);
				collider.bounds.width=32;
				collider.bounds.height=32;
				collider.bounds.lowerLeft.set(collider.position.x,collider.position.y);
				if(OverlapTester.overlapRectangles(toMove,collider.bounds)){
					rigth=true;
				}
			}
			if(rigth==false)
				jim.mRight();
			break;
			case 1:
			toMove.lowerLeft.set(jim.position.x,jim.position.y+1*jim.speed*deltaTime);
			for(int i=0;i<len;i++){
				GameObject collider=colliders.get(i);
				collider.bounds.width=32;
				collider.bounds.height=32;
				collider.bounds.lowerLeft.set(collider.position.x,collider.position.y);
				if(OverlapTester.overlapRectangles(toMove,collider.bounds)){
					up=true;
				}
			}
			if(up==false)
				jim.mUp();
			break;
			case 2:
			toMove.lowerLeft.set(jim.position.x-1*jim.speed*deltaTime,jim.position.y);
			for(int i=0;i<len;i++){
				GameObject collider=colliders.get(i);
				collider.bounds.width=32;
				collider.bounds.height=32;
				collider.bounds.lowerLeft.set(collider.position.x,collider.position.y);
				if(OverlapTester.overlapRectangles(toMove,collider.bounds)){
					left=true;
				}
			}
			if(left==false)
				jim.mLeft();
			break;
			case 3:
			toMove.lowerLeft.set(jim.position.x,jim.position.y-1*jim.speed*deltaTime);
			for(int i=0;i<len;i++){
				GameObject collider=colliders.get(i);
				collider.bounds.width=32;
				collider.bounds.height=32;
				if(OverlapTester.overlapRectangles(toMove,collider.bounds)){
					down=false;
				}
			}
			if(down==false)
				jim.mDown();
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
	
	public void followTail(Momia momia,Jim[] tailjim,SpatialHashGrid grid){
		momia.d=0;
		momia.vio=false;
		for(int i=tailjim.length-1;i>-1;i--){
				if(libre.sinObs(tailjim[i],momia,grid)){
					momia.d=i;
					momia.vio=true;
				}
		}
		
		/*
		}*/
		if(momia.vio)
		libre.goTo(momia,tailjim[momia.d].position);
		
		/*
		}*/
		
		
	}
	
	public void dispose(){
			
	}
	/*
	}*/
	
	
	
	/*
	}*/		
}
