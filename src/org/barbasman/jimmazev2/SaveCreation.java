package org.barbasman.jimmazev2;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import android.os.Environment;
import java.io.OutputStreamWriter;

import org.barbasman.framework.FileIO;
import org.barbasman.framework.*;

public class SaveCreation {
	static int mapNumber=16;
	static int mapWidthCreate=16;
	static int mapHeightCreate=16;
	static File folder;
	static World world;
	//String pathGame=Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"JimMaze"+File.separator;
	static int sw=0;
	
	
	public static void saveMap(FileIO files,String nameMap,int x,int y,World world) {
		folder =new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"JimMaze"+File.separator);
		folder.mkdirs();
		mapWidthCreate=world.map.widthOfMap;
		mapHeightCreate=world.map.heightOfMap;
        BufferedWriter out = null;
        try {
			
            out = new BufferedWriter(new OutputStreamWriter(files.writeFile(nameMap+x+'x'+y+".map.txt")));
            out.write(""+mapWidthCreate);
            out.write("\n");
			out.write(""+mapHeightCreate);
			out.write("\n");
			/*for(int i=0;i<mapWidthCreate;i++){
				out.write('x');
			}*/
			
			for(int j=(mapHeightCreate-1);j>=0;j--){
				//out.write('x');
				for(int k=0;k<(mapWidthCreate);k++){
						if(world.map.cmap[k][j].getBlock()==false){
							sw=0;
								for(int nm=0;nm<world.cMomias.size();nm++){
									if(world.cMomias.get(nm).position.x/32==k&&world.cMomias.get(nm).position.y/32==j){
										//out.write('m');
										sw=1;
									}
								}
							
							
								if(world.jim.position.x/32==k&&world.jim.position.y/32==j){
									//out.write('j');
									sw=2;
								}
								
							if(world.win.position.x/32==k&&world.win.position.y/32==j){
								//out.write('e');
								sw=3;
							}
								switch(sw){
									case 0:
										out.write('o');
									break;
									case 1:
										out.write('m');
									break;
									case 2:
										out.write('j');
									break;
									case 3:
										out.write('e');
									break;
									
								}
								
							}
							else{
								out.write('x');
							}
						
					}
				/*out.write('x');*/
				out.write("\n");
			}
			/*for(int i=0;i<mapWidthCreate;i++){
			out.write('x');
			}*/
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

}
