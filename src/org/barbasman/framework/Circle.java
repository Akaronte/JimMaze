package org.barbasman.framework;

public class Circle
{
	Vector2 center=new Vector2();
	float radius;
	
	public Circle(float x,float y,float radius){
		this.center.set(x,y);
		this.radius=radius;
	}
}
