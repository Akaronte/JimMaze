package org.barbasman.jimmazev2;
import org.barbasman.framework.GLScreen;
import org.barbasman.framework.*;
import org.barbasman.framework.Input.TouchEvent;
import javax.microedition.khronos.opengles.GL10;
import android.util.Log;
import java.util.List;
import android.animation.*;
import android.view.KeyEvent;

public class GameScreen extends GLScreen
{

	public static int GAME_RUN=0;
	public static int GAME_PAUSED=1;
	public static int GAME_MAP=2;
	public static int GAME_GAMEOVER=3;
	public static int GAME_WIN=4;
	int state;
	Font font;
	Camera2D guiCam;
	int xZoom=2;
	SpriteBatcher batcher;
	World world;
	WorldRenderer renderer;
	Vector2 touchPoint=new Vector2();
	/*Rectangle but1=new Rectangle(0,160,64,64),but2=new Rectangle(0,160-64,64,64),
	but3=new Rectangle(480-64,160,64,64),but4=new Rectangle(480-64,160-64,64,64);*/
	Rectangle but1=new Rectangle(32,64,32,32),but2=new Rectangle(64,64,32,32),
	but3=new Rectangle(64,32,32,32),but4=new Rectangle(64,0,32,32),
	but5=new Rectangle(32,0,32,32),but6=new Rectangle(0,0,32,32),
	but7=new Rectangle(0,32,32,32),but8=new Rectangle(0,64,32,32);
	Rectangle butMap=new Rectangle(480-96,320-32,96,32);
	Rectangle butMapZoom;
	Rectangle butPaused=new Rectangle(0,320-64,64,64);
	Rectangle butMenu=new Rectangle(0,320-32,128,32);
	float distXmap,distYmap;
	float tickWin=0;
	float tickOver=0;
	String text;
	
	
	public GameScreen(Game game,int numMap){
		super(game);
		state=GAME_RUN;
		butMapZoom=new Rectangle(480-(96*xZoom),320-(32*xZoom),96*xZoom,32*xZoom);
		batcher = new SpriteBatcher(glGraphics,10000);
		world=new World(numMap);
		guiCam = new Camera2D(glGraphics,480,320);
		guiCam.position.set(world.map.startDistX,world.map.startDistY);
		renderer=new WorldRenderer(glGraphics,batcher,world,guiCam); 	
		
	}

	public void update(float deltaTime)
	{	
		List<TouchEvent> touchEvents=game.getInput().getTouchEvents();
		
		if(state==GAME_RUN){
			updateRUN(deltaTime,touchEvents);
		}
		
		if(state==GAME_PAUSED){
			updatePAUSED(deltaTime,touchEvents);
		}
		if(state==GAME_MAP){
			updateMAP(deltaTime,xZoom,touchEvents);
		}
		if(state==GAME_GAMEOVER){
			updateGAMEOVER(deltaTime,touchEvents);
		}
		if(state==GAME_WIN){
			updateGAMEWIN(deltaTime,touchEvents);
		}
	}
	
	public void updateRUN(float deltaTime,List<TouchEvent> touchEvents){
		
		int len=touchEvents.size();
		for(int i=0;i<len;i++){
			TouchEvent event=touchEvents.get(i);
			touchPoint.set(event.x,event.y);
			guiCam.touchToWorld(touchPoint);
			
			if(event.type==TouchEvent.TOUCH_DOWN||event.type==TouchEvent.TOUCH_DRAGGED){
				if(OverlapTester.pointInRectangle(but1,touchPoint)){
					
					world.moveJim(world.jim,0,deltaTime);
				}
				if(OverlapTester.pointInRectangle(but2,touchPoint)){
				
					world.moveJim(world.jim,1,deltaTime);
				}
				if(OverlapTester.pointInRectangle(but3,touchPoint)){
					
					world.moveJim(world.jim,2,deltaTime);
				}
				if(OverlapTester.pointInRectangle(but4,touchPoint)){
					
					world.moveJim(world.jim,3,deltaTime);
				}
				if(OverlapTester.pointInRectangle(but5,touchPoint)){
		
					world.moveJim(world.jim,4,deltaTime);
				}
				if(OverlapTester.pointInRectangle(but6,touchPoint)){

					world.moveJim(world.jim,5,deltaTime);
				}
				if(OverlapTester.pointInRectangle(but7,touchPoint)){
					
					world.moveJim(world.jim,6,deltaTime);
				}
				if(OverlapTester.pointInRectangle(but8,touchPoint)){
					
					world.moveJim(world.jim,7,deltaTime);
				}
				
			}
			
			
			if(event.type==TouchEvent.TOUCH_UP){
				if(OverlapTester.pointInRectangle(butMap,touchPoint)){
					touchEvents.clear();
					distXmap=world.distX;
					distYmap=world.distY;
					state=GAME_MAP;
					world.jim.velocity.set(0,0);
					
				}
				world.jim.velocity.set(0,0);
				if(OverlapTester.pointInRectangle(butMenu,touchPoint)){
					game.setScreen(new MainScreen(game));
				}
			}
		}
		world.update(deltaTime);
		if(world.finish){
			state=GAME_GAMEOVER;
		}
		if(world.winner){
			state=GAME_WIN;
		}
		
	}
	public void updatePAUSED(float deltaTime,List<TouchEvent> touchEvents){
		int len=touchEvents.size();
		for(int i=0;i<len;i++){
			TouchEvent event=touchEvents.get(i);
			touchPoint.set(event.x,event.y);
			guiCam.touchToWorld(touchPoint);

			if(event.type==TouchEvent.TOUCH_DOWN||event.type==TouchEvent.TOUCH_DRAGGED){

				//text="Touch D lowerleft.X"+butMapZoom.lowerLeft.x+" lowerleft.Y"+butMapZoom.lowerLeft.y+" width"+butMapZoom.width;
				//Log.d("org.barbasman",text);

			}
			if(event.type==TouchEvent.TOUCH_UP){
				state=GAME_RUN;
			}
		}
		
	}
	public void updateMAP(float deltaTime,int xZoom,List<TouchEvent> touchEvents){
		
		int len=touchEvents.size();
		for(int i=0;i<len;i++){
			TouchEvent event=touchEvents.get(i);
			touchPoint.set(event.x,event.y);
			guiCam.touchToWorld(touchPoint);

			if(event.type==TouchEvent.TOUCH_DOWN||event.type==TouchEvent.TOUCH_DRAGGED){
				
				//text="Touch D lowerleft.X"+butMapZoom.lowerLeft.x+" lowerleft.Y"+butMapZoom.lowerLeft.y+" width"+butMapZoom.width;
				//Log.d("org.barbasman",text);
				
			}
			if(event.type==TouchEvent.TOUCH_UP){
				if(OverlapTester.pointInRectangle(butMapZoom,touchPoint)){
					guiCam.zoom=1;
					state=GAME_RUN;
				}
			}
		}
		//world.update(deltaTime);
		
	}
	
	public void updateGAMEOVER(float deltaTime,List<TouchEvent> touchEvents){		
		/*int len=touchEvents.size();
		for(int i=0;i<len;i++){
			TouchEvent event=touchEvents.get(i);
			touchPoint.set(event.x,event.y);
			guiCam.touchToWorld(touchPoint);

			if(event.type==TouchEvent.TOUCH_DOWN||event.type==TouchEvent.TOUCH_DRAGGED){

			}
			if(event.type==TouchEvent.TOUCH_UP){
				game.setScreen(new MainScreen(game));
			}
		}
		*/
		tickOver+=deltaTime;
		while(tickOver>3.0f){
			
			game.setScreen(new MainScreen(game));
			tickOver=0;
		}

	}
	public void updateGAMEWIN(float deltaTime,List<TouchEvent> touchEvents){		
		/*
		int len=touchEvents.size();
		for(int i=0;i<len;i++){
			TouchEvent event=touchEvents.get(i);
			touchPoint.set(event.x,event.y);
			guiCam.touchToWorld(touchPoint);

			if(event.type==TouchEvent.TOUCH_DOWN||event.type==TouchEvent.TOUCH_DRAGGED){
				game.setScreen(new MainScreen(game));
			}
			if(event.type==TouchEvent.TOUCH_UP){
				//game.setScreen(new MainScreen(game));
			}
		}*/
		tickWin+=deltaTime;
		while(tickWin>3.0f){
			game.setScreen(new MainScreen(game));
			tickWin=0;
		}


	}

	public void present(float deltaTime){
		if(state==GAME_RUN){
			presentRUN(deltaTime);
		}
		if(state==GAME_PAUSED){
			presentPAUSED(deltaTime);
		}
		if(state==GAME_MAP){
			presentMAP(deltaTime,xZoom);
		}
		if(state==GAME_GAMEOVER){
			presentGAMEOVER(deltaTime);
		}
		if(state==GAME_WIN){
			presentGAMEWIN(deltaTime);
		}
	}

	
	public void presentRUN(float deltaTime){
		GL10 gl=glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		guiCam.setViewportAndMatrices();

		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA,GL10.GL_ONE_MINUS_SRC_ALPHA);


		renderer.render(deltaTime);
		
		
		
		renderer.renderTextMap();
		renderer.manuText();
	
	}
	
	public void presentPAUSED(float deltaTime)
		
	{
		GL10 gl=glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		guiCam.setViewportAndMatrices();

		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA,GL10.GL_ONE_MINUS_SRC_ALPHA);


		renderer.render(deltaTime);

		batcher.beginBatch(Assets.TextScr1);


		renderer.renderTextMap();
		renderer.renderTextPaused();
		renderer.manuText();

	}
	public void presentMAP(float deltaTime,int xZoom)
	{
		
		GL10 gl=glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		guiCam.setViewportAndMatrices();

		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA,GL10.GL_ONE_MINUS_SRC_ALPHA);

		renderer.renderMap(deltaTime,xZoom,distXmap,distYmap);

		renderer.renderTextMap();
	}
	
	public void presentGAMEOVER(float deltaTime){
		GL10 gl=glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		guiCam.setViewportAndMatrices();

		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA,GL10.GL_ONE_MINUS_SRC_ALPHA);


		renderer.render(deltaTime);

		renderer.renderTextMap();
		renderer.renderTextGameOver();
		renderer.manuText();

	}
	
	public void presentGAMEWIN(float deltaTime){
		GL10 gl=glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		guiCam.setViewportAndMatrices();

		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA,GL10.GL_ONE_MINUS_SRC_ALPHA);


		renderer.render(deltaTime);

		batcher.beginBatch(Assets.TextScr1);


		renderer.renderTextMap();
		renderer.renderTextWin();

	}

	public void pause()
	{
		
		state=GAME_PAUSED;
	}

	public void resume()
	{
		//state=GAME_PAUSED;
	}

	public void dispose()
	{
		// TODO: Implement this method
	}
	
}
