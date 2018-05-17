package org.barbasman.jimmazev2;
import org.barbasman.framework.*;
import java.util.List;
import java.util.Random;
import android.util.Log;

public class MenuJim extends DynamicGameObject
{
	float direcction=0;
	float walkTime=0;
	float speed=50.0f;
	int lookat=0;
	int r;
	boolean atascado,libreX,libreY;
	float tickAtascado=0;
	Vector2 ultima,current,nueva;
	Random rand;
	GameObject fjimX,fjimY;
	String text;
	
	public MenuJim(float x,float y, float width,float height,float direcction){
		super(x,y,width,height);
		this.direcction=direcction;
		ultima=new Vector2(0,0);
		current=new Vector2(1,1);
		nueva=new Vector2(2,2);
		fjimX=new GameObject(0,0,32,32);
		fjimY=new GameObject(0,0,32,32);
		libreX=false;
		libreY=false;
		
		rand=new Random();
		r=1;
	}
	public void update(float deltaTime){
		position.add(velocity.x,velocity.y);
		if(velocity.x==0&&velocity.y==0){
			walkTime=0;
		}else{
			walkTime+=deltaTime;
		}
		bounds.lowerLeft.set(position.x-bounds.width/2,position.y-bounds.height/2);
	}
	public float getDirecction(Vector2 velocity){
		return velocity.angle();
	}
	public void mUp(){
		this.velocity.y=1*speed;
	}
	public void mDown(){
		this.velocity.y=-1*speed;
	}
	public void mLeft(){
		this.velocity.x=-1*speed;
	}
	public void mRight(){
		this.velocity.x=1*speed;
	}
	public void mStop(){
		this.velocity.set(0,0);
	}
	public int getLookAt(Vector2 velocity){
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
	
	public boolean isAtascado(float deltaTime){
		atascado=false;
		tickAtascado+=deltaTime;

		while(tickAtascado>0.05){
			ultima.set(current);
			current.set(nueva);
			nueva.set(this.position);

			if((int)ultima.x==(int)current.x&&(int)ultima.y==(int)current.y){
				if((int)current.x==(int)nueva.x&&(int)current.y==(int)nueva.y){
					atascado=true;

					if(atascado){
						r=rand.nextInt(3)-1;
						//text="vel"+r;
						//Log.d("org.barbasman",text);
						velocity.x=speed*(rand.nextInt(3)-1);
						velocity.y=speed*(rand.nextInt(3)-1);
						while(velocity.x==0&&velocity.y==0){
							velocity.x=speed*(rand.nextInt(3)-1);
							velocity.y=speed*(rand.nextInt(3)-1);
						}
					}
				}
			}
			tickAtascado=0;
		}
		return atascado;
	}
	
	public void moveJim(SpatialHashGrid grid,float deltaTime){
		
		fjimX.position.set(position.x,position.y);
		fjimY.position.set(position.x,position.y);

		fjimX.position.add(velocity.x*deltaTime,0);
		fjimX.bounds.lowerLeft.set(fjimX.position.x-fjimX.bounds.width/2,fjimX.position.y-fjimX.bounds.height/2);
		boolean libreX=true;
		List<GameObject>collidersX=grid.getPotentialColliders(fjimX);
		int lenx=collidersX.size();
		for(int i=0;i<lenx;i++){
			GameObject collider=collidersX.get(i);
			collider.bounds.width=32;
			collider.bounds.height=32;
			collider.bounds.lowerLeft.set(collider.position.x-collider.bounds.width/2,collider.position.y-collider.bounds.height/2);
			if(OverlapTester.overlapRectangles(collider.bounds,fjimX.bounds)){
				libreX=false;

				//text="momiaX px="+fmomiaX.position.x+" py="+fmomiaX.position.y+" colX="+collider.position.x+"colY"+collider.position.y+"if"+OverlapTester.overlapRectangles(collider.bounds,fmomiaY.bounds);
				//Log.d("org.barbasman",text);
			}
		}
		fjimY.position.add(0,velocity.y*deltaTime);
		fjimY.bounds.lowerLeft.set(fjimY.position.x-fjimY.bounds.width/2,fjimY.position.y-fjimY.bounds.height/2);
		boolean libreY=true;
		List<GameObject>collidersY=grid.getPotentialColliders(fjimY);
		int leny=collidersY.size();
		for(int j=0;j<leny;j++){
			GameObject collider=collidersY.get(j);
			collider.bounds.width=32;
			collider.bounds.height=32;
			collider.bounds.lowerLeft.set(collider.position.x-collider.bounds.width/2,collider.position.y-collider.bounds.height/2);
			if(OverlapTester.overlapRectangles(collider.bounds,fjimY.bounds)){
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
		if(velocity.x==0&&velocity.y==0){
			walkTime=0;
		}else{
			walkTime+=deltaTime;
		}
	}
}
