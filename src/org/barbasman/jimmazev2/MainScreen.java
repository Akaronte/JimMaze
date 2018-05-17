package org.barbasman.jimmazev2;
import org.barbasman.framework.Input.TouchEvent;
import org.barbasman.framework.GLScreen;
import org.barbasman.framework.*;
import javax.microedition.khronos.opengles.GL10;
import java.util.List;

public class MainScreen extends GLScreen
{

	Camera2D guiCam;
	SpriteBatcher batcher;
	Vector2 touchPoint;
	MenuWorld nw;
	MenuRenderer renderer;
	Rectangle playBounds;
	Rectangle editBounds;
	
	
	public MainScreen(Game game){
	super(game);
	guiCam = new Camera2D(glGraphics, 480, 320);
	batcher = new SpriteBatcher(glGraphics, 10000);
	touchPoint = new Vector2();
	nw=new MenuWorld(1);
	playBounds = new Rectangle(480/2-80,320/2-16, 160, 32);
	editBounds= new Rectangle(480/2-(6*32/2),(320/2)-32,6*32,32);
	renderer=new MenuRenderer(glGraphics,batcher,nw,guiCam);
	
	
	
	}
	public void update(float deltaTime)
	{
		nw.update(deltaTime);


List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);                        
            if(event.type == TouchEvent.TOUCH_UP) {
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);
                
                if(OverlapTester.pointInRectangle(playBounds, touchPoint)) {
                    //Assets.playSound(Assets.clickSound);
                    //game.setScreen(new GameScreen2(game));
					game.setScreen(new GameScreen(game,2));
					//create.save(game.getFileIO());
                    return;
                }
                if(OverlapTester.pointInRectangle(editBounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    game.setScreen(new CreateScreen(game,74,73,77,32,32));
                    return;
                }
               
            }
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
        renderer.render(deltaTime);
		
        /*batcher.beginBatch(Assets.TextScr1);
        //batcher.drawSprite(240, 160, 480, 320, Assets.background);
		batcher.drawSprite(240,160,64,64,Assets.playBotton);
		batcher.drawSprite(guiCam.position.x,guiCam.position.y,64,64,Assets.loadBotton);
        batcher.endBatch();
		//renderer.render(deltaTime);
		*/
        
        gl.glDisable(GL10.GL_BLEND);
		
	}

	public void pause()
	{
		// TODO: Implement this method
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
