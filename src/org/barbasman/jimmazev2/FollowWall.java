package org.barbasman.jimmazev2;
import org.barbasman.framework.*;
import java.util.List;
import android.util.Log;

public class FollowWall
{	

	DynamicGameObject ballblue;
	GameObject col;
	String text;
	boolean draw;
	
	FollowWall(World world,DynamicGameObject ball,int x,int y){
		ballblue=new DynamicGameObject(ball.position.x,ball.position.y,32,32);
		ballblue.bounds.lowerLeft.set(ballblue.position);
		ballblue.velocity.set(x,y);
		draw=false;
		
		
		while(ballblue.velocity.x!=0||ballblue.velocity.y!=0){
		List<GameObject>colliders=world.grid.getPotentialColliders(ballblue);
		int len=colliders.size();
		
		//Log.d("org.barbasman",text);
				for(int i=0;i<len;i++){
				GameObject	col=colliders.get(i);
				if(OverlapTester.overlapRectangles(col.bounds,ballblue.bounds)){
					ballblue.position.add(ballblue.velocity);
					ballblue.bounds.lowerLeft.set(ballblue.position);
					//colliders.clear();
				}else{
					ballblue.velocity.set(0,0);
					draw=true;
						//text="bb"+draw;
						//Log.d("org.barbasman",text);
				}
				
			}
		}
	}
}
