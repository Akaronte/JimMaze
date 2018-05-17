package org.barbasman.jimmazev2;

import org.barbasman.framework.*;
import org.barbasman.framework.Input.TouchEvent;
import org.barbasman.framework.Input.KeyEvent;
import javax.microedition.khronos.opengles.GL10;
import android.util.Log;
import java.util.List;
import android.animation.*;
import java.security.*;
//import android.view.KeyEvent;

public class CreateMapScreen extends GLScreen
{

	Camera2D guiCam;
	Camera2D zoomCam;
	LoadAssetsMap lam;
	SpriteBatcher batcher;
	SaveCreation guardar;
	WorldRenderer renderer;
	
	World world;
	Vector2 touchPoint=new Vector2();
	Rectangle bUp=new Rectangle((480/2)-32,320-64,64,64);
	Rectangle bDown=new Rectangle((480/2)-32,0,64,64);
	Rectangle bRigth=new Rectangle(480-64,(320/2)-32,64,64);
	Rectangle bLeft=new Rectangle(0,(320/2)-32,64,64);
	Rectangle bSave=new Rectangle(480-32,320-32,32,32);
	Rectangle bLupaMinus=new Rectangle(480-64,320-32,32,32);
	Rectangle bLupaMas=new Rectangle(480-96,320-32,32,32);
	Rectangle bCjim=new Rectangle(480-128,320-32,32,32);
	Rectangle bCcas=new Rectangle(480-160,320-32,32,32);
	Rectangle bCblock=new Rectangle(480-192,320-32,32,32);
	Rectangle bCmomia=new Rectangle(0,320-32,32,32);
	Rectangle bCcofre=new Rectangle(5*32,320-32,32,32);
	Rectangle bBack=new Rectangle(32,320-32,32,32);
	int x,y;
	
	int width,height;
	float zoom=1.0f;
	String text;
	static String nameMap;
	int stateControl=0;
	float tickentry=0;
	float ticksaved=6;
	boolean touchSaved=false;
	int a,b,c;
	
	public CreateMapScreen(Game game,String nameMap,int a,int b,int c,int width,int height){
		super(game);
		lam.cload(game.getFileIO(),nameMap+width+'x'+height+".map.txt");
		batcher=new SpriteBatcher(glGraphics,10000);
		world=new World(3);
		this.width=width;
		this.height=height;
		this.nameMap=nameMap;
		this.a=a;
		this.b=b;
		this.c=c;
		this.width=width;
		this.height=height;
		guiCam = new Camera2D(glGraphics,480,320);
		//guiCam.position.set(world.map.startDistX,world.map.startDistY);
		//guiCam.position.set((world.map.getWidthOfMap()*32)/2,(world.map.getHeightOfMap()*32)/2);
		world.distX=world.map.getWidthOfMap()*32/2;
		world.distY=world.map.getHeightOfMap()*32/2;
		//world.jim.position.set(1*32,1*32);
		renderer=new WorldRenderer(glGraphics,batcher,world,guiCam);
		
		guardar=new SaveCreation();
	}

	public void update(float deltaTime){
		/*List<KeyEvent> keysEvents=game.getInput().getKeyEvents();
		int lenK= keysEvents.size();
		for(int ki=0;ki<lenK;ki++){
			if(keysEvents.get(ki).keyCode==4){
				game.setScreen(new CreateScreen(game));
				return;
			}
		}*/
		if(tickentry<4.0f){
			tickentry+=deltaTime;
		}
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		int len = touchEvents.size();
		for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);    
			
			if(event.type == TouchEvent.TOUCH_DOWN){
				if(OverlapTester.pointInRectangle(bLupaMinus,touchPoint)){
					if(zoom<5)
						zoom+=1.0f;
				}
				if(OverlapTester.pointInRectangle(bLupaMas,touchPoint)){
					if(zoom>1)
						zoom-=1.0f;
				}
			}
			
            if(event.type == TouchEvent.TOUCH_DOWN||event.type == TouchEvent.TOUCH_DRAGGED) {
                touchPoint.set(event.x, event.y);
				
                guiCam.touchToWorld(touchPoint);


                if(OverlapTester.pointInRectangle(bUp, touchPoint)) {
					world.moveCam(0,8);
				 return;
				 }
				 if(OverlapTester.pointInRectangle(bDown, touchPoint)) {
					 world.moveCam(0,-8);
				 return;
				 }
				if(OverlapTester.pointInRectangle(bRigth, touchPoint)) {
					world.moveCam(8,0);
					
					return;
				}
				if(OverlapTester.pointInRectangle(bLeft, touchPoint)) {
					world.moveCam(-8,0);
					return;
				}
                
            }		
			
			if(event.type == TouchEvent.TOUCH_DOWN||event.type == TouchEvent.TOUCH_DRAGGED) {
                touchPoint.set(event.x, event.y);
				
                guiCam.touchToWorld(touchPoint);
				//text="x"+((((touchPoint.x)-guiCam.frustumWidth/2)+world.distX)/32);
				//Log.d("org.barbasman",text);
                if(OverlapTester.pointInRectangle(bUp,touchPoint)||OverlapTester.pointInRectangle(bDown,touchPoint)||(OverlapTester.pointInRectangle(bLeft,touchPoint))||(OverlapTester.pointInRectangle(bRigth,touchPoint))||(OverlapTester.pointInRectangle(bSave,touchPoint))
					||OverlapTester.pointInRectangle(bLupaMinus,touchPoint)||OverlapTester.pointInRectangle(bLupaMas,touchPoint)||OverlapTester.pointInRectangle(bCjim,touchPoint)||OverlapTester.pointInRectangle(bCcas,touchPoint)||OverlapTester.pointInRectangle(bCblock,touchPoint)
				   ||OverlapTester.pointInRectangle(bCmomia,touchPoint)||OverlapTester.pointInRectangle(bCcofre,touchPoint)||OverlapTester.pointInRectangle(bBack,touchPoint)){
						if(OverlapTester.pointInRectangle(bSave,touchPoint)){
							guardar.saveMap(game.getFileIO(),nameMap,width,height,world);
							//text="saved";
							//Log.d("org.barbasman",text);
							ticksaved=0;
							
						}
						
						if(OverlapTester.pointInRectangle(bBack,touchPoint)){
							game.setScreen(new CreateScreen(game,a,b,c,width,height));
							return;
						}
						/* if(OverlapTester.pointInRectangle(bLupaMinus,touchPoint)){
							 if(zoom<5)
								 zoom+=1.0f;
						 }
						 if(OverlapTester.pointInRectangle(bLupaMas,touchPoint)){
							 if(zoom>1)
								 zoom-=1.0f;
						 }*/
						
						 
						 if(OverlapTester.pointInRectangle(bCjim,touchPoint)){
						 	stateControl=0;
						 }
						 if(OverlapTester.pointInRectangle(bCcas,touchPoint)){
							 stateControl=1;
						 }
						 if(OverlapTester.pointInRectangle(bCblock,touchPoint)){
							 stateControl=2;
						 }
						 if(OverlapTester.pointInRectangle(bCmomia,touchPoint)){
							 stateControl=3;
						 }
						if(OverlapTester.pointInRectangle(bCcofre,touchPoint)){
							stateControl=4;
						}
						 
					return;
				}else if(tickentry>3.0f){
					
					x=(int)((((((touchPoint.x)-guiCam.frustumWidth/2)+world.distX)+(16/zoom))/(32/zoom)));
					y=(int)((((((touchPoint.y)-guiCam.frustumHeight/2)+world.distY)+(16/zoom))/(32/zoom)));
					if(x>0&&x<world.map.getWidthOfMap()-1&&y>0&&y<world.map.getHeightOfMap()-1){
						/*if(world.map.cmap[x][y].getDraw()==Assets.casilla){
						world.map.cmap[x][y].setDraw(Assets.block);
						world.map.cmap[x][y].setBlock(true);
						}else{
							world.map.cmap[x][y].setDraw(Assets.casilla);
							world.map.cmap[x][y].setBlock(false);
						}*/
						switch(stateControl){
							case 0:
								world.jim.position.set(x*32,y*32);
								world.map.cmap[x][y].setDraw(Assets.casilla);
								world.map.cmap[x][y].setBlock(false);
								
							break;
							case 1:
								world.map.cmap[x][y].setDraw(Assets.casilla);
								world.map.cmap[x][y].setBlock(false);
								for(int mi=0;mi<world.cMomias.size();mi++){
									if(world.cMomias.get(mi).position.x==x*32&&world.cMomias.get(mi).position.y==y*32){
										world.cMomias.remove(mi);
									}
								}
								
							break;
							
							case 2:
								if(x==(int)world.win.position.x/32&&y==(int)world.win.position.y/32){
									
								}else if(x==(int)world.jim.position.x/32&&y==(int)world.jim.position.y/32){
								
								}else{
									world.map.cmap[x][y].setDraw(Assets.block);
									world.map.cmap[x][y].setBlock(true);
								}
								
							break;
							
							case 3:
								
								world.map.cmap[x][y].setDraw(Assets.casilla);
								world.map.cmap[x][y].setBlock(false);
								world.addMomias(x*32,y*32);
								
							
							break;
							
							case 4:
								world.map.cmap[(int)world.win.position.x/32][(int)world.win.position.y/32].setDraw(Assets.casilla);
								world.win.position.set(x*32,y*32);
								world.map.cmap[x][y].setDraw(Assets.end);
								world.map.cmap[x][y].setBlock(false);

								break;
						}
						
					}
					return;
				}

            }
        }
	}

	public void present(float deltaTime){
		GL10 gl = glGraphics.getGL();        
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();
        gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
		renderer.customRender(deltaTime,zoom,stateControl);
		
		while(ticksaved<6.0f){
		batcher.beginBatch(Assets.FontText);
		renderer.font.drawText(batcher,"saved",renderer.cam.position.x-80,renderer.cam.position.y);
		batcher.endBatch();
		ticksaved+=deltaTime;
		}
		
		
		
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
