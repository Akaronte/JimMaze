package org.barbasman.jimmazev2;
import org.barbasman.framework.GLScreen;
import org.barbasman.framework.*;
import javax.microedition.khronos.opengles.GL10;
import org.barbasman.framework.Input.TouchEvent;
import java.util.List;
import android.util.Log;


public class CreateScreen extends GLScreen
{

	SpriteBatcher batcher;
	Camera2D guiCam;
	Vector2 touchPoint;
	
	Rectangle createBounds;
	CreateEmptyMap create;
	String text;
	String nameMap;
	RenderText renderText;
	//int a=74,b=73,c=77;
	//int x1=0,x2=3,x3=2,y1=0,y2=3,y3=2;
	//int x=4,y=4;
	int a,b,c,x,y,x1,x2,x3,y1,y2,y3;
	int mx=50;
	
	//rectangulos de botones
	Rectangle aUp=new Rectangle(64-16,220-16,32,32),bUp=new Rectangle(64-16+32,220-16,32,32),cUp=new Rectangle(64-16+64,220-16,32,32),
	aDown=new Rectangle(64-16,160-16,32,32),bDown=new Rectangle(64-16+32,160-16,32,32),cDown=new Rectangle(64-16+64,160-16,32,32),
	
	x1Up=new Rectangle(184-16,220-16,32,32),x2Up=new Rectangle(184-16+32,220-16,32,32),x3Up=new Rectangle(184-16+64,220-16,32,32),
	x1Down=new Rectangle(184-16,160-16,32,32),x2Down=new Rectangle(184-16+32,160-16,32,32),x3Down=new Rectangle(184-16+64,160-16,32,32),
	
	y1Up=new Rectangle(318-16,220-16,32,32),y2Up=new Rectangle(318-16+32,220-16,32,32),y3Up=new Rectangle(318-16+64,220-16,32,32),
	y1Down=new Rectangle(318-16,160-16,32,32),y2Down=new Rectangle(318-16+32,160-16,32,32),y3Down=new Rectangle(318-16+64,160-16,32,32);
	
	Rectangle editBounds=new Rectangle(84,74,128,32);
	Rectangle playBounds=new Rectangle(266,74,128,32);
	
	public CreateScreen(Game game,int a,int b,int c,int x,int y){
	super(game);
		guiCam = new Camera2D(glGraphics,480,320);
		batcher = new SpriteBatcher(glGraphics,1000);
		touchPoint= new Vector2();
		renderText=new RenderText(glGraphics,batcher,guiCam);
		this.a=a;
		this.b=b;
		this.c=c;
		this.x=x;
		this.y=y;
		x1=(x-(x%100))/100;
		x2=((x%100)-(x%10))/10;
		x3=(x%10);
		y1=(y-(y%100))/100;
		y2=((y%100)-(y%10))/10;
		y3=(y%10);
		
		create=new CreateEmptyMap();
	}
	public void update(float deltaTime)
	{
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		int len = touchEvents.size();
		for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);                        
            if(event.type==TouchEvent.TOUCH_DOWN) {
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);
				
				//text="createUp";
				//Log.d("org.barbasman.org",text);
				
				//Assets.customLoad(game);
				//game.setScreen(new CreateMapScreen(game));
				
				if(OverlapTester.pointInRectangle(editBounds,touchPoint)){
					//nameMap=""+((char)a)+((char)b)+((char)c);
					if(!create.existMap(game.getFileIO(),nameMap+x+'x'+y)){
						create.saveMap(game.getFileIO(),nameMap+x+'x'+y,x,y);
					}
					
					//create.saveMap(game.getFileIO(),nameMap+x+'x'+y,x,y);
					//create.existMap(game.getFileIO(),nameMap+x+'x'+y);
					//Assets.customLoad(game,nameMap+x+'x'+y+".map.txt");
					//game.setScreen(new CreateMapScreen(game));
					
					if(create.existMap(game.getFileIO(),nameMap+x+'x'+y)){
						Assets.customLoad(game,nameMap+x+'x'+y+".map.txt");
						game.setScreen(new CreateMapScreen(game,nameMap,a,b,c,x,y));
					}
				}
				
				if(OverlapTester.pointInRectangle(playBounds,touchPoint)){
					//nameMap=""+((char)a)+((char)b)+((char)c);
					if(create.existMap(game.getFileIO(),nameMap+x+'x'+y)){
						Assets.customLoad(game,nameMap+x+'x'+y+".map.txt");
						
					}
					game.setScreen(new GameScreen(game,3));

					//create.saveMap(game.getFileIO(),nameMap+x+'x'+y,x,y);
					//create.existMap(game.getFileIO(),nameMap+x+'x'+y);
					//Assets.customLoad(game,"primerMapa");
					//game.setScreen(new CreateMapScreen(game));
				}
				
				//botonesca b c arriba y abajo
				if(OverlapTester.pointInRectangle(aUp,touchPoint)){
					a+=1;
					if(a>90){
						a=65;
					}
				}
				
				if(OverlapTester.pointInRectangle(bUp,touchPoint)){
					b+=1;
					if(b>90){
						b=65;
					}
				}
				
				if(OverlapTester.pointInRectangle(cUp,touchPoint)){
					c+=1;
					if(c>90){
						c=65;
					}
				}
				
				if(OverlapTester.pointInRectangle(aDown,touchPoint)){
					a-=1;
					if(a<65){
						a=90;
					}
				}
				
				if(OverlapTester.pointInRectangle(bDown,touchPoint)){
					b-=1;
					if(b<65){
						b=90;
					}
				}
				
				if(OverlapTester.pointInRectangle(cDown,touchPoint)){
					c-=1;
					if(c<65){
						c=90;
					}
				}
				
				//Botones X arriba y abajo
				if(OverlapTester.pointInRectangle(x1Up,touchPoint)){
					x1+=1;
					if(x1>2){
						x1=0;
					}
				}
				
				if(OverlapTester.pointInRectangle(x2Up,touchPoint)){
					x2+=1;
					if(x2>9){
						x2=0;
					}
				}
				
				if(OverlapTester.pointInRectangle(x3Up,touchPoint)){
					x3+=1;
					if(x3>9){
						x3=0;
					}
				}
				
				
				
				if(OverlapTester.pointInRectangle(x1Down,touchPoint)){
					x1-=1;
					if(x1<0){
						x1=2;
					}
				}
				
				if(OverlapTester.pointInRectangle(x2Down,touchPoint)){
					x2-=1;
					if(x2<0){
						x2=9;
					}
				}
				
				if(OverlapTester.pointInRectangle(x3Down,touchPoint)){
					x3-=1;
					if(x3<0){
						x3=9;
					}
				}
				
				//Botones y arriba y abajo
				
				if(OverlapTester.pointInRectangle(y1Up,touchPoint)){
					y1+=1;
					if(y1>2){
						y1=0;
					}
				}
				
				if(OverlapTester.pointInRectangle(y2Up,touchPoint)){
					y2+=1;
					if(y2>9){
						y2=0;
					}
				}
				
				if(OverlapTester.pointInRectangle(y3Up,touchPoint)){
					y3+=1;
					if(y3>9){
						y3=0;
					}
				}
				
				if(OverlapTester.pointInRectangle(y1Down,touchPoint)){
					y1-=1;
					if(y1<0){
						y1=2;
					}
				}
				
				if(OverlapTester.pointInRectangle(y2Down,touchPoint)){
					y2-=1;
					if(y2<0){
						y2=9;
					}
				}
				if(OverlapTester.pointInRectangle(y3Down,touchPoint)){
					y3-=1;
					if(y3<0){
						y3=9;
					}
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
		gl.glBlendFunc(GL10.GL_SRC_ALPHA,GL10.GL_ONE_MINUS_SRC_ALPHA);
		batcher.beginBatch(Assets.TextScr1);
		batcher.drawSprite(480/2,320/2,480,320,Assets.ballblue);
		//batcher.drawSprite(64,220,32,32,Assets.ball);
		batcher.endBatch();
		
		if(create.existMap(game.getFileIO(),nameMap+x+'x'+y)){
			renderText.Text("exist",0,-120);
		}
		
		
		renderText.Text("map:",0,120);
		renderText.Text(""+((char)a),-210+mx,32);
		renderText.Text(""+((char)b),-210+32+mx,32);
		renderText.Text(""+((char)c),-210+64+mx,32);
		x=x1*100+x2*10+x3;
		y=y1*100+y2*10+y3;
		nameMap=""+(char)a+(char)b+(char)c;
		renderText.Text(""+x1,-90+mx,32);
		renderText.Text(""+x2,-90+32+mx,32);
		renderText.Text(""+x3,-90+64+mx,32);
		
		renderText.Text("x",8+mx,32);
		
		renderText.Text(""+y1,44+mx,32);
		renderText.Text(""+y2,44+32+mx,32);
		renderText.Text(""+y3,44+64+mx,32);
		
		renderText.Text("Edit",-90,-70);
		renderText.Text("play",90,-70);
		
		//buttons de a,b,c
		batcher.beginBatch(Assets.CustomControls);
		batcher.drawSprite(64,220,32,32,Assets.CustomControlsUp);
		batcher.drawSprite(64+32,220,32,32,Assets.CustomControlsUp);
		batcher.drawSprite(64+64,220,32,32,Assets.CustomControlsUp);
		
		
		batcher.drawSprite(64,160,32,32,Assets.CustomControlsDown);
		batcher.drawSprite(64+32,160,32,32,Assets.CustomControlsDown);
		batcher.drawSprite(64+64,160,32,32,Assets.CustomControlsDown);
		
		//buttons ancho X
		batcher.drawSprite(184,220,32,32,Assets.CustomControlsUp);
		batcher.drawSprite(184+32,220,32,32,Assets.CustomControlsUp);
		batcher.drawSprite(184+64,220,32,32,Assets.CustomControlsUp);
		
		batcher.drawSprite(184,160,32,32,Assets.CustomControlsDown);
		batcher.drawSprite(184+32,160,32,32,Assets.CustomControlsDown);
		batcher.drawSprite(184+64,160,32,32,Assets.CustomControlsDown);
		
		//buttons alto Y
		batcher.drawSprite(318,220,32,32,Assets.CustomControlsUp);
		batcher.drawSprite(318+32,220,32,32,Assets.CustomControlsUp);
		batcher.drawSprite(318+64,220,32,32,Assets.CustomControlsUp);
		
		batcher.drawSprite(318,160,32,32,Assets.CustomControlsDown);
		batcher.drawSprite(318+32,160,32,32,Assets.CustomControlsDown);
		batcher.drawSprite(318+64,160,32,32,Assets.CustomControlsDown);
		
		
		batcher.endBatch();
		
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
