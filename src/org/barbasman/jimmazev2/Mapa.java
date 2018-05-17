package org.barbasman.jimmazev2;
import org.barbasman.framework.Vector2;

public class Mapa
{
	  	
		int numMapa;
		int widthOfMap;
		int heightOfMap;
		float jimStartX;
		float jimStartY;
		float jimStartD;
		float startDistX;
		float startDistY;
		Casilla[][] cmap;
		 String linea;
		 int numDeMomias=0;
		 Momia[] momia;
		 GameObject[] tesoros;
		 

	
	public Mapa(int numMapa,int lwidhtOfMap,int lheightOfMap,float ljimStartX,float ljimStartY,float ljimSatartD,float lstartDistX, float lstartDistY, Casilla[][] lcmap,int lnumDeMomias,Momia[] lmomia){
		this.numMapa=numMapa;
		this.widthOfMap=lwidhtOfMap;
		this.heightOfMap=lheightOfMap;
		this.jimStartX=ljimStartX;
		this.jimStartY=ljimStartY;
		this.jimStartD=ljimSatartD;
		this.startDistX=lstartDistX;
		this.startDistY=lstartDistY;
		
		this.cmap=lcmap;
		this.numDeMomias=lnumDeMomias;
		this.momia=lmomia;
		
	}
	
	public int getWidthOfMap(){
		return this.widthOfMap;
	}
	public int getHeightOfMap(){
		return this.heightOfMap;
	}
	public float getJimStartX(){
		return this.jimStartX;
	}
	public float getJimStartY(){
		return this.jimStartY;
	}
	public float getStartDistX(){
		return this.startDistX;
	}
	public float getStartDistY(){
		return this.startDistY;
	}
	public Casilla[][] getCmap(){
		return this.cmap;
	}
	public int getNumMomias(){
		return this.numDeMomias;
	}
	public Momia[] getMomia(){
		return this.momia;
	}
	
}
