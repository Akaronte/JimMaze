package org.barbasman.jimmazev2;
import org.barbasman.framework.*;

public class Jim extends DynamicGameObject
{
	float direcction=0;
	float walkTime=0;
	float speed=75.0f;
	int lookat=0;
	float factR=1.50f;
	
	public Jim(float x,float y, float width,float height,float direcction){
		super(x,y,width,height);
		this.direcction=direcction;
	}
	public void update(float deltaTime){
		position.add(velocity.x*deltaTime,velocity.y*deltaTime);
		if(velocity.x==0&&velocity.y==0){
			walkTime=0;
		}else{
			walkTime+=deltaTime;
		}
		bounds.lowerLeft.set(position.x,position.y);
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
	public void mUprigth(){
		this.velocity.x=1*(speed/factR);
		this.velocity.y=1*(speed/factR);
	}
	public void mUpleft(){
		this.velocity.x=-1*(speed/factR);
		this.velocity.y=1*(speed/factR);
	}
	public void mDownrigth(){
		this.velocity.x=1*(speed/factR);
		this.velocity.y=-1*(speed/factR);
	}
	public void mDownleft(){
		this.velocity.x=-1*(speed/factR);
		this.velocity.y=-1*(speed/factR);
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
}
