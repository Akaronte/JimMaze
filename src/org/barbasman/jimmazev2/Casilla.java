package org.barbasman.jimmazev2;
import org.barbasman.framework.TextureRegion;

public class Casilla extends GameObject
{
	 private boolean block=false;
	 private TextureRegion draw;
	
	public Casilla(float x, float y, float width, float height,TextureRegion draw,boolean block){
		super(x,y,width,height);
		this.draw=draw;
		this.block=block;
	}
	public boolean getBlock(){
		return block;
	}
	public void setBlock(boolean block){
		this.block=block;
	}
	public TextureRegion getDraw(){
		return this.draw;
	}
	public void setDraw(TextureRegion draw){
		this.draw=draw;
	}
}
