package org.barbasman.jimmazev2;
import org.barbasman.framework.GLScreen;
import org.barbasman.framework.Camera2D;
import org.barbasman.framework.*;
import javax.microedition.khronos.opengles.GL10;
public class LogoScreen extends GLScreen
{

	SpriteBatcher batcher;
	Camera2D guiCam;
	float tick=0;
	
	
	public LogoScreen(GLGame game){
		super(game);
		guiCam = new Camera2D(glGraphics, 480, 320);
		batcher = new SpriteBatcher(glGraphics, 1000);
	}

	public void update(float deltaTime)
	{
		tick+=deltaTime;
		while(tick>2.0f){
			game.setScreen(new MainScreen(game));
			tick=0;
		}
	}

	public void present(float deltaTime)
	{
		
		GL10 gl = glGraphics.getGL();        
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();
        gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        
        batcher.beginBatch(Assets.Logo);
        batcher.drawSprite(240, 160, 480, 320, Assets.LogoRegion);
		//
		//
        batcher.endBatch();
        
        gl.glDisable(GL10.GL_BLEND);
	}

	public void pause()
	{
		
	}

	public void resume()
	{
		// TODO: Implement this method
	}

	public void dispose()
	{
		// TODO: Implement this method
	}
	
	
}
