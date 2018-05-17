package org.barbasman.jimmazev2;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.util.Log;


import org.barbasman.framework.FileIO;

public class Settings
{	
	public static int saveNumMap=0;
	public static float saveDistX=0;
	public static float saveDistY=0;
	public static float saveJimX=1;
	public static float saveJimY=1;
	public static float saveJimD=3;
	
	
	public static String[] buts=new String[]{"right","up","down","left","temp"};
	public static int[] numdir=new int[4];
	public static int keepor=0;
	
	
	
	public static void load(FileIO files) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(files.readFile(".jimmaze")));
			saveNumMap=Integer.parseInt(in.readLine());
			saveDistX=Float.parseFloat(in.readLine());
			saveDistY=Float.parseFloat(in.readLine());
			saveJimX=Float.parseFloat(in.readLine());
			saveJimY=Float.parseFloat(in.readLine());
			saveJimD=Float.parseFloat(in.readLine());
			
          
        } catch (IOException e) {
            // :( It's ok we have defaults
        } catch (NumberFormatException e) {
            // :/ It's ok, defaults save our day
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
            }
        }
    }
    public static void save(FileIO files) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    files.writeFile(".jimmaze")));
			out.write(Integer.toString(saveNumMap));
			out.write("\n");
            out.write(Float.toString(saveDistX));
            out.write("\n");
            out.write(Float.toString(saveDistY));
            out.write("\n");
			out.write(Float.toString(saveJimX));
			out.write("\n");
			out.write(Float.toString(saveJimY));
			out.write("\n");
			out.write(Float.toString(saveJimD));
			out.write("\n");
        } catch (IOException e) {
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
            }
        }
    }
	public static void addSavePosition(int sMap,float sDistX,float sDistY,float sJimX,float sJimY,float sJimDirection){
		saveNumMap=sMap;
		saveDistX=sDistX;
		saveDistY=sDistY;
		saveJimX=sJimX;
		saveJimY=sJimY;
		saveJimD=sJimDirection;
		
	}
	public static void saveButtons(FileIO files) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
										 files.writeFile("Buttons.jimmaze")));
										 
			for(int sbi=0;sbi<4;sbi++){
				if(buts[sbi]=="up"){
					out.write(Integer.toString(0));
					out.write("\n");
				}
				if(buts[sbi]=="left"){
					out.write(Integer.toString(1));
					out.write("\n");
				}
				if(buts[sbi]=="down"){
					out.write(Integer.toString(2));
					out.write("\n");
				}
				if(buts[sbi]=="right"){
					out.write(Integer.toString(3));
					out.write("\n");
				}
			}
			out.write(Integer.toString(keepor));
			out.write("\n");
			
        } catch (IOException e) {
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
            }
        }
    }
	public static void loadButtons(FileIO files) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(files.readFile("Buttons.jimmaze")));
			
			for(int lbi=0;lbi<4;lbi++){
				numdir[lbi]=Integer.parseInt(in.readLine());
				if(numdir[lbi]==0){
					buts[lbi]="up";
				}
				if(numdir[lbi]==1){
					buts[lbi]="left";
				}
				if(numdir[lbi]==2){
					buts[lbi]="down";
				}
				if(numdir[lbi]==3){
					buts[lbi]="right";
				}
			}
			keepor=Integer.parseInt(in.readLine());
			
			String text;
			text=""+buts[0]+" "+buts[1]+" "+" "+buts[2]+" "+buts[3];
			Log.d("buts",text);
			
			

        } catch (IOException e) {
            // :( It's ok we have defaults
			
        } catch (NumberFormatException e) {
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
