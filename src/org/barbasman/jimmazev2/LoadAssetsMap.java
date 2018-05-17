
package org.barbasman.jimmazev2;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import android.util.Log;
import org.barbasman.framework.FileIO;
import org.barbasman.framework.*;


public class LoadAssetsMap
{		
		static int widthOfMap;
		static int heightOfMap;
		static float jimStartX;
		static float jimStartY;
		static float jimStartD;
		static float startDistX;
		static float startDistY;
		static Casilla[][] cmap;
		static String linea;
		static int numDeMomias=0;
		static Momia[] momia;
		
		
		
	 	
	public LoadAssetsMap(){
		
	}
	
	public static void load(FileIO files,String mapa) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(files.readAsset(mapa)));
			widthOfMap=Integer.parseInt(in.readLine());
			heightOfMap=Integer.parseInt(in.readLine());
			jimStartX=0;
			jimStartY=0.0f;
			jimStartD=0.0f;
			startDistX=0.0f;
			startDistY=0.0f;
			cmap=new Casilla[widthOfMap][heightOfMap];
			
			momia=new Momia[300];
		
			numDeMomias=0;
			for(int j=widthOfMap-1;j>-1;j--){
				linea=in.readLine();
				for(int i=0;i<heightOfMap;i++){
					char letra=linea.charAt(i);
					
					if(letra=='x')
						cmap[i][j]=new Casilla(i*32,j*32,32,32,Assets.block,true);
					if(letra=='o')
						cmap[i][j]=new Casilla(i*32,j*32,32,32,Assets.casilla,false);
					if(letra=='j'){
						cmap[i][j]=new Casilla(i*32,j*32,32,32,Assets.casilla,false);
						jimStartX=i*32;
						jimStartY=j*32;
						jimStartD=0;
						startDistX=i*32;
						startDistY=j*32;
						}
					if(letra=='t')
						cmap[i][j]=new Casilla(i*32,j*32,32,32,Assets.tesoro,false);
						
					if(letra=='e')
						cmap[i][j]=new Casilla(i*32,j*32,32,32,Assets.end,false);
					if(letra=='m'){
						cmap[i][j]=new Casilla(i*32,j*32,32,32,Assets.casilla,false);
						momia[numDeMomias]=new  Momia(i*32,j*32,32,32);
						
						numDeMomias+=1;
					}
					if(letra!='o'&&letra!='x'&&letra!='j'&&letra!='t'&&letra!='e'&&letra!='m'){
						cmap[i][j]=new Casilla(i*32,j*32,32,32,Assets.ball,true);
					}
				}
			}

        } catch (IOException e) {
            // :( It's ok we have defaults
        } catch (NumberFormatException e){
            // :/ It's ok, defaults save our day
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
            }
        }
    }
	
	public static void cload(FileIO files,String mapa) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(files.readFile(mapa)));
			widthOfMap=Integer.parseInt(in.readLine());
			heightOfMap=Integer.parseInt(in.readLine());
			//widthOfMap=Integer.parseInt(in.readLine());
			jimStartX=0;
			jimStartY=0.0f;
			jimStartD=0.0f;
			startDistX=0.0f;
			startDistY=0.0f;
			cmap=new Casilla[widthOfMap][heightOfMap];
			//cmap=new Casilla[heightOfMap][widthOfMap];
			momia=new Momia[300];

			numDeMomias=0;
			for(int j=heightOfMap-1;j>-1;j--){
				linea=in.readLine();
				for(int i=0;i<widthOfMap;i++){
					char letra=linea.charAt(i);

					if(letra=='x')
						cmap[i][j]=new Casilla(i*32,j*32,32,32,Assets.block,true);
					if(letra=='o')
						cmap[i][j]=new Casilla(i*32,j*32,32,32,Assets.casilla,false);
					if(letra=='j'){
						cmap[i][j]=new Casilla(i*32,j*32,32,32,Assets.casilla,false);
						jimStartX=i*32;
						jimStartY=j*32;
						jimStartD=0;
						startDistX=i*32;
						startDistY=j*32;
					}
					if(letra=='t')
						cmap[i][j]=new Casilla(i*32,j*32,32,32,Assets.tesoro,false);

					if(letra=='e')
						cmap[i][j]=new Casilla(i*32,j*32,32,32,Assets.end,false);
					if(letra=='m'){
						cmap[i][j]=new Casilla(i*32,j*32,32,32,Assets.casilla,false);
						momia[numDeMomias]=new  Momia(i*32,j*32,32,32);

						numDeMomias+=1;
					}
					if(letra!='o'&&letra!='x'&&letra!='j'&&letra!='t'&&letra!='e'&&letra!='m'){
						cmap[i][j]=new Casilla(i*32,j*32,32,32,Assets.ball,true);
					}
				}
			}

        } catch (IOException e) {
            // :( It's ok we have defaults
        } catch (NumberFormatException e){
            // :/ It's ok, defaults save our day
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
            }
        }
    }
}
