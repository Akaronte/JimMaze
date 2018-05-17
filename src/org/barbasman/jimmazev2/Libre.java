package org.barbasman.jimmazev2;
import org.barbasman.framework.*;
import java.util.List;
import android.util.FloatMath;
import android.util.Log;

public class Libre
{
	
	int speed=8;
	GameObject falsejim,falsem;
	boolean libre=true;
	DynamicGameObject falseObj1,falseObj2,ball;
	
	
	public Libre(){
	falseObj1=new DynamicGameObject(0,0,32,32);
	falseObj2=new DynamicGameObject(0,0,32,32);
	ball=new DynamicGameObject(0,0,2,2);
	}
	
	public boolean sinObs(DynamicGameObject falsem,DynamicGameObject falsejim,SpatialHashGrid grid){
		falsejim.bounds.width=32;
		falsejim.bounds.height=32;
		falsejim.bounds.lowerLeft.set(falsejim.position.x-falsejim.bounds.width/2,falsejim.position.y-falsejim.bounds.height/2);
		
		falseObj1.position.set(falsem.position.x,falsem.position.y);
		falseObj2.position.set(falsejim.position.x,falsejim.position.y);

		ball.position.set(falseObj1.position.x,falseObj1.position.y);
		ball.bounds.lowerLeft.set(ball.position.x-ball.bounds.width/2,ball.position.y-ball.bounds.height/2);
		float angle=falseObj2.position.sub(falseObj1.position).angle();
		float radians=angle*Vector2.TO_RADIANS;
		
		ball.velocity.x=(float)Math.cos(radians)*speed;
		ball.velocity.y=(float)Math.sin(radians)*speed;
		
		boolean check=true;
		while(check){
			if(OverlapTester.overlapRectangles(ball.bounds,falsejim.bounds)){
				check=false;
				libre=true;
			}
			List<GameObject> colliders=grid.getPotentialColliders(ball);
			int len=colliders.size();
			for(int i=0;i<len;i++){
				GameObject collider=colliders.get(i);
				collider.bounds.width=32;
				collider.bounds.height=32;
				collider.bounds.lowerLeft.set(collider.position.x-collider.bounds.width/2,collider.position.y-collider.bounds.height/2);
				if(OverlapTester.overlapRectangles(ball.bounds,collider.bounds)){
					check=false;
					libre=false;
				}
			}
			
			ball.position.add(ball.velocity);
			ball.bounds.lowerLeft.set(ball.position.x-ball.bounds.width/2,ball.position.y-ball.bounds.height/2);
		}
		
		return libre;
		
	}	
	
	public void goTo(DynamicGameObject momia,Vector2 destino){
		momia.velocity.x=0;
		momia.velocity.y=0;
	//	float nvx=(destino.x-(destino.x%32))-momia.position.x-(momia.position.x%32);
	//	float nvy=(destino.y-(destino.y%32))-momia.position.y-(momia.position.y%32);
		float nvx=destino.x-momia.position.x;
		float nvy=destino.y-momia.position.y;
		
		if(nvx>0){
			momia.velocity.x=1*Momia.speedMomia;
		}
		if(nvx<0){
			momia.velocity.x=-1*Momia.speedMomia;
		}
		if(nvy>0){
			momia.velocity.y=1*Momia.speedMomia;
		}
		if(nvy<0){
			momia.velocity.y=-1*Momia.speedMomia;
		}
		
	}
}
