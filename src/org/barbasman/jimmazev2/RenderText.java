package org.barbasman.jimmazev2;
import org.barbasman.framework.*;
import android.animation.*;
import android.util.Log;
import android.util.FloatMath;
import java.util.List;

public class RenderText
{

		static final float FRUSTRUM_WIDTH = 480;
		static final float FRUSTRUM_HEIGHT = 480;
		GLGraphics glGraphics;
		Camera2D cam;
		SpriteBatcher batcher;
		Font font;
		
	public RenderText(GLGraphics glGraphics, SpriteBatcher batcher,Camera2D guiCam){
		this.glGraphics = glGraphics;
		this.cam = guiCam;
		this.batcher = batcher;
		font=new Font(Assets.FontText,0,0,16,32,32);
	}
	
	public void Text(String texto,float x, float y){
		
		int largo=texto.length();
		cam.zoom=1.0f;
		//cam.position.set(world.distX,world.distY);
		cam.setViewportAndMatrices();

		batcher.beginBatch(Assets.FontText);
		font.drawText(batcher,texto,cam.position.x-((32*largo)/2)+(x),cam.position.y+(y));
		batcher.endBatch();
	}
}
