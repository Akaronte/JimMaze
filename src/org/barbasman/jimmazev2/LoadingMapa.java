package org.barbasman.jimmazev2;
import org.barbasman.framework.*;

public class LoadingMapa
{
	static LoadAssetsMap lam;
	static Mapa[] map=new Mapa[10];
	
	public LoadingMapa (){
		
	}
	public static void load(int numMap,FileIO files,String mapa){
		map[numMap]=null;
		LoadAssetsMap.load(files,mapa);
		map[numMap]=new Mapa(numMap,lam.widthOfMap,lam.heightOfMap,lam.jimStartX,lam.jimStartY,lam.jimStartD,lam.startDistX,lam.startDistY,lam.cmap,lam.numDeMomias,lam.momia);
	}
	
	public static void cload(int numMap,FileIO files,String mapa){
		//map[numMap]=null;
		LoadAssetsMap.cload(files,mapa);
		map[numMap]=new Mapa(numMap,lam.widthOfMap,lam.heightOfMap,lam.jimStartX,lam.jimStartY,lam.jimStartD,lam.startDistX,lam.startDistY,lam.cmap,lam.numDeMomias,lam.momia);
	}
	
}
