package org.barbasman.jimmazev2;

import org.barbasman.framework.Vector2;
import android.util.FloatMath;
import android.util.Log;
import java.util.List;
import java.util.ArrayList;
import org.barbasman.framework.*;
import android.widget.*;
import java.util.Random;


public class Momia extends DynamicGameObject
{
	float walkTime=0;
	public static float speedMomia=40.0f;
	float enzona=500;
	DynamicGameObject ball;
	DynamicGameObject ballL;
	int d=0;
	GameObject falsejim;
	GameObject falsem;
	public GameObject fmomiaX,fmomiaY,fmomia;
	float ballSpeed=32;
	String text;
	
	boolean vio=false;
	float	tick=0;
	int lookat=0;
	float tickFollow;
	DynamicGameObject fjim;
	Vector2 ultima,current,nueva;
	boolean atascado=false;
	float tickAtascado=0;
	Random rand;
	Libre libre;
	List<GameObject> testigos;
	List<GameObject> testigosBall;
	
	
	
	
	public Momia(float x,float y, float width,float height){
		super(x,y,width,height);
		ball=new DynamicGameObject(position.x,position.y,24,24);
		ball.velocity.set(0,0);
		ballL=new DynamicGameObject(position.x,position.y,2,2);
		libre=new Libre();
		
		ultima=new Vector2(0,0);
		current=new Vector2(1,1);
		nueva=new Vector2(2,2);
		rand=new Random();
		fjim=new DynamicGameObject(0,0,28,28);
		fmomia=new GameObject(0,0,28,28);
		fmomiaX=new GameObject(0,0,28,28);
		fmomiaY=new GameObject(0,0,28,28);
		
		testigos=new ArrayList<GameObject>(10);
		testigosBall=new ArrayList<GameObject>(1000);
	}
	
	public void update(World world,float deltaTime){
		bounds.lowerLeft.set(position.x-bounds.width/2,position.y-bounds.height/2);
		if(this.position.dist(world.jim.position)<enzona){
			tickFollow+=deltaTime;
			while(tickFollow>0.2f){
			//world.followTail(this,world.tailJim,world.grid);
		 followJim(deltaTime,world.jim,world.tailJim,world.grid);
			tickFollow=0;
			}
		}
		isAtascado(deltaTime,this);
		moveMomia(world.grid,deltaTime);
		
		//followJim2(deltaTime,world.tailJim,world.grid);
		if(velocity.x!=0||velocity.y!=0)
		walkTime+=deltaTime;
	}
	
	
	
	public void followJim(float deltaTime,Jim jim,Jim[] tailJim,SpatialHashGrid grid){
		for(int tfl=0;tfl<tailJim.length;tfl++){
			testigos.clear();
			testigosBall.clear();
			falsejim=new GameObject(tailJim[tfl].position.x,tailJim[tfl].position.y,28,28);
			falsem=new GameObject(position.x,position.y,28,28);
			ball.position.set(falsem.position);
			ball.bounds.lowerLeft.set(ball.position.x-ball.bounds.width/2,ball.position.y-ball.bounds.height/2);
			
	
			float angle=falsejim.position.sub(falsem.position).angle();
			float radians=angle*Vector2.TO_RADIANS;

			ball.velocity.x=(float)Math.cos(radians)*24;
			ball.velocity.y=(float)Math.sin(radians)*24;
			boolean check=true;
			while(check){
				List<GameObject>colliders=grid.getPotentialColliders(ball);
				int len=colliders.size();
				for(int i=0;i<len;i++){
					GameObject collider=colliders.get(i);
					collider.bounds.width=32;
					collider.bounds.height=32;
					collider.bounds.lowerLeft.set(collider.position.x-12,collider.position.y-12);
					if(OverlapTester.overlapRectangles(collider.bounds,ball.bounds)){
						check=false;
						testigos.add(new GameObject(collider.position.x,collider.position.y,collider.bounds.width,collider.bounds.height));
						vio=false;
						
					}
				}
				if(OverlapTester.overlapRectangles(tailJim[tfl].bounds,ball.bounds)){
					check=false;
					d=tfl;
					
					tfl=tailJim.length;
					vio=true;
				}
				testigosBall.add(new GameObject(ball.position.x,ball.position.y,24,24));
				ball.position.add(ball.velocity.x,ball.velocity.y);
				ball.bounds.lowerLeft.set(ball.position.x-ball.bounds.width/2,ball.position.y-ball.bounds.height/2);

			}
		}
		if(vio){
			if(d==0){
				libre.goTo(this,jim.position);
			}else{
				if((position.dist(tailJim[d].position)<16)){
					d-=1;}
			libre.goTo(this,tailJim[d].position);
			}
			
		}
		
	}
	
	public int getLookAt(Vector2 velocity){
		int lookat=0;
			if(velocity.angle()>45&&velocity.angle()<=135){
				lookat=1;
			}
			if(velocity.angle()>135&&velocity.angle()<=225){
				lookat=2;
			}
			if(velocity.angle()>225&&velocity.angle()<=315){
				lookat=3;
			}
			if(velocity.angle()<=45||velocity.angle()>315){
				if(velocity.x!=0){
					lookat=0;
				}
			}
		return lookat;
	}
	
	public boolean isAtascado(float deltaTime,Momia momia){
		atascado=false;
		tickAtascado+=deltaTime;
		
		while(tickAtascado>0.05){
		ultima.set(current);
		current.set(nueva);
		nueva.set(momia.position);

		if((int)ultima.x==(int)current.x&&(int)ultima.y==(int)current.y){
			if((int)current.x==(int)nueva.x&&(int)current.y==(int)nueva.y){
				atascado=true;
				if(atascado){
					if(rand.nextBoolean()){
						velocity.x=0;
					}else{
						if(rand.nextBoolean()){
							velocity.x=1*speedMomia;
						}else{
							velocity.x=-1*speedMomia;
						}
					}
					if(rand.nextBoolean()){
						velocity.y=0;
					}else{
						if(rand.nextBoolean()){
							velocity.y=1*speedMomia;
						}else{
							velocity.y=-1*speedMomia;
						}
					}
					if(velocity.x==0&&velocity.y==0){
						if(rand.nextBoolean()){
							if(rand.nextBoolean()){
								velocity.x=1*speedMomia;
							}else{
								velocity.x=-1*speedMomia;
							}
						}else{
							if(rand.nextBoolean()){
								velocity.y=1*speedMomia;
							}else{
								velocity.y=-1*speedMomia;
							}
						}
					}
				}
			}
		}
		tickAtascado=0;
		}
		return atascado;
	}
	
	public void moveMomia(SpatialHashGrid grid,float deltaTime){
		fmomiaX.position.set(position.x,position.y);
		fmomiaY.position.set(position.x,position.y);
		
		fmomiaX.position.add(velocity.x*deltaTime,0);
		fmomiaX.bounds.lowerLeft.set(fmomiaX.position.x-fmomiaX.bounds.width/2,fmomiaX.position.y-fmomiaX.bounds.height/2);
		boolean libreX=true;
		List<GameObject>collidersX=grid.getPotentialColliders(fmomiaX);
		int lenx=collidersX.size();
		for(int i=0;i<lenx;i++){
			GameObject collider=collidersX.get(i);
			collider.bounds.width=32;
			collider.bounds.height=32;
			collider.bounds.lowerLeft.set(collider.position.x-collider.bounds.width/2,collider.position.y-collider.bounds.height/2);
			if(OverlapTester.overlapRectangles(collider.bounds,fmomiaX.bounds)){
				libreX=false;
				
				//text="momiaX px="+fmomiaX.position.x+" py="+fmomiaX.position.y+" colX="+collider.position.x+"colY"+collider.position.y+"if"+OverlapTester.overlapRectangles(collider.bounds,fmomiaY.bounds);
				//Log.d("org.barbasman",text);
			}
		}
		fmomiaY.position.add(0,velocity.y*deltaTime);
		fmomiaY.bounds.lowerLeft.set(fmomiaY.position.x-fmomiaY.bounds.width/2,fmomiaY.position.y-fmomiaY.bounds.height/2);
		boolean libreY=true;
		List<GameObject>collidersY=grid.getPotentialColliders(fmomiaY);
		int leny=collidersY.size();
		for(int j=0;j<leny;j++){
			GameObject collider=collidersY.get(j);
			collider.bounds.width=32;
			collider.bounds.height=32;
			collider.bounds.lowerLeft.set(collider.position.x-collider.bounds.width/2,collider.position.y-collider.bounds.height/2);
			if(OverlapTester.overlapRectangles(collider.bounds,fmomiaY.bounds)){
				libreY=false;
				
				//text="momiaY px="+fmomiaY.position.x+" py="+fmomiaY.position.y+" colX="+collider.position.x+"colY"+collider.position.y+" if"+OverlapTester.overlapRectangles(collider.bounds,fmomiaY.bounds);
				//+¡Log.d("org.barbasman",text);
				
			}
		}
		
		if(libreX){
			position.add(velocity.x*deltaTime,0);
			
		}
		if(libreY){
			position.add(0,velocity.y*deltaTime);
		}
	}	
	
	public void moveMomia2(SpatialHashGrid grid,float deltaTime){
		
		
		boolean libreX=true;
		List<GameObject>collidersX=grid.getPotentialColliders(this);
		int lenx=collidersX.size();
		for(int i=0;i<lenx;i++){
			GameObject collider=collidersX.get(i);
			collider.bounds.width=32;
			collider.bounds.height=32;
			collider.bounds.lowerLeft.set(collider.position.x-collider.bounds.width/2,collider.position.y-collider.bounds.height/2);
			if(OverlapTester.overlapRectangles(collider.bounds,this.bounds)){
				libreX=false;

			}
		}
		fmomiaY.position.add(0,velocity.y*deltaTime);
		fmomiaY.bounds.lowerLeft.set(fmomiaY.position.x-fmomiaY.bounds.width/2,fmomiaY.position.y-fmomiaY.bounds.height/2);
		boolean libreY=true;
		List<GameObject>collidersY=grid.getPotentialColliders(fmomiaY);
		int leny=collidersY.size();
		for(int j=0;j<leny;j++){
			GameObject collider=collidersY.get(j);
			collider.bounds.width=32;
			collider.bounds.height=32;
			collider.bounds.lowerLeft.set(collider.position.x-collider.bounds.width/2,collider.position.y-collider.bounds.height/2);
			if(OverlapTester.overlapRectangles(collider.bounds,fmomiaY.bounds)){
				libreY=false;

				//text="momiaY px="+fmomiaY.position.x+" py="+fmomiaY.position.y+" colX="+collider.position.x+"colY"+collider.position.y+" if"+OverlapTester.overlapRectangles(collider.bounds,fmomiaY.bounds);
				//+¡Log.d("org.barbasman",text);

			}
		}

		if(libreX){
			position.add(velocity.x*deltaTime,0);

		}
		if(libreY){
			position.add(0,velocity.y*deltaTime);
		}
	}
}
