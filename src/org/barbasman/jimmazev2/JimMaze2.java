package org.barbasman.jimmazev2;

import android.util.Log;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.barbasman.framework.Screen;
import org.barbasman.framework.GLGame;

public class JimMaze2 extends GLGame {
    boolean firstTimeCreate = true;
	String text="nulo";
    
    @Override
    public Screen getStartScreen() {
        return new LogoScreen(this);
		//return new GameScreen(this);
		
    }
    
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {         
        super.onSurfaceCreated(gl, config);
        if(firstTimeCreate) {
            //Settings.load(getFileIO());
            Assets.load(this);
            firstTimeCreate = false;            
        } else {
            Assets.reload();
        }
    }     
    
    @Override
    public void onPause() {
        super.onPause();
		this.getCurrentScreen().pause();
        /*if(Settings.soundEnabled)
            Assets.music.pause();*/
		
		
    }
	
	public void onResume(){
		super.onResume();
	}
}
