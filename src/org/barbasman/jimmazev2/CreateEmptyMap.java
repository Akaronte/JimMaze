package org.barbasman.jimmazev2;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import android.os.Environment;
import java.io.OutputStreamWriter;

import org.barbasman.framework.FileIO;
import org.barbasman.framework.*;

public class CreateEmptyMap {
	static int mapNumber=16;
	static int mapWidthCreate=16;
	static int mapHeightCreate=16;
	static File folder;
	static File map;
	//String pathGame=Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"JimMaze"+File.separator;
	
	
	public static void saveMap(FileIO files,String nameMap,int width,int heigth) {
		if(width<8){
			width=8;
		}
		if(heigth<8){
			heigth=8;
		}
		folder =new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"JimMaze"+File.separator);
		folder.mkdirs();
		mapWidthCreate=width;
		mapHeightCreate=heigth;
		boolean endcreated=false;
		boolean jimCreated=false;
        BufferedWriter out = null;
        try {
			
            out = new BufferedWriter(new OutputStreamWriter(files.writeFile(nameMap+".map.txt")));
            out.write(""+mapWidthCreate);
            out.write("\n");
			out.write(""+mapHeightCreate);
			out.write("\n");
			for(int i=0;i<mapWidthCreate;i++){
				out.write('x');
			}
			out.write("\n");
			for(int j=1;j<(mapHeightCreate-1);j++){
				out.write('x');
					for(int k=1;k<(mapWidthCreate-1);k++){
						
						if(!endcreated){
							out.write('e');
							endcreated=true;
						}else if(jimCreated==false&&j==7){
							out.write('j');
							jimCreated=true;
						}else{
							out.write('o');
						}
					}
				out.write('x');
				out.write("\n");
			}
			for(int i=0;i<mapWidthCreate;i++){
			out.write('x');
			}
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
	
	public static boolean existMap(FileIO files,String nameMap) {
		map =new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"JimMaze"+File.separator,nameMap+".map.txt");
		return map.exists();
		
        /*BufferedWriter out = null;
        try {

            out = new BufferedWriter(new OutputStreamWriter(files.writeFile(nameMap+width+'x'+heigth+".map.txt")));
			
            out.write(""+mapWidthCreate);
            out.write("\n");
			out.write(""+mapHeightCreate);
			out.write("\n");
			for(int i=0;i<mapWidthCreate;i++){
				out.write('x');
			}
			out.write("\n");
			for(int j=1;j<(mapHeightCreate-1);j++){
				out.write('x');
				for(int k=1;k<(mapWidthCreate-1);k++){
					out.write('o');
				}
				out.write('x');
				out.write("\n");
			}
			for(int i=0;i<mapWidthCreate;i++){
				out.write('x');
			}
			out.write("\n");


        } catch (IOException e) {
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
            }
        }*/
    }

}
