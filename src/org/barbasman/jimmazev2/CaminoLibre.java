package org.barbasman.jimmazev2;
import org.barbasman.framework.*;
import java.util.List;

public class CaminoLibre
{
	
	int speed=1;
	boolean check=false;
	boolean isFree=true;
	float nx=0;
	float ny=0;
	
	public CaminoLibre(){}
	
	public boolean esLibre(DynamicGameObject obj1,DynamicGameObject obj2,SpatialHashGrid grid){
		 nx=obj2.position.x-obj1.position.x;
		 ny=obj2.position.y-obj1.position.y;
		
		if(nx>0){
			obj1.velocity.x=1*speed;
		}
		if(nx<0){
			obj1.velocity.x=-1*speed;
		}
		if(ny>0){
			obj1.velocity.y=1*speed;
		}
		if(nx>0){
			obj1.velocity.y=-1*speed;
		}
		check=true;
		while(check){
			List<GameObject>colliders=grid.getPotentialColliders(obj1);
			int len=colliders.size();
			for(int i=0;i<len;i++){
				GameObject collider=colliders.get(i);
				if(OverlapTester.overlapRectangles(collider.bounds,obj1.bounds)){
					isFree=false;
					check=false;
				}
			}
			if(OverlapTester.overlapRectangles(obj1.bounds,obj2.bounds)){
				check=false;
			}
			obj1.position.add(obj1.velocity);
			obj1.bounds.lowerLeft.set(obj1.position);
		}
		
		return isFree;
	}
		
}
