package org.barbasman.framework;

import org.barbasman.framework.Vector2;
import org.barbasman.framework.Rectangle;

public class GameObject
{
	Vector2 position;
	Rectangle bounds;
	
	public GameObject(float x,float y,float width,float height){
		this.position=new Vector2(x,y);
		this.bounds=new Rectangle(x-width/2,y-height/2,width,height);
	}
}
