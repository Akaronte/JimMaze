package org.barbasman.jimmazev2;
import org.barbasman.framework.*;
import android.animation.*;
import android.util.Log;
import android.util.FloatMath;
import java.util.List;
public class MenuRenderer
{
	

	static final float FRUSTUM_WIDTH = 480;
    static final float FRUSTUM_HEIGHT = 320;    
    GLGraphics glGraphics;
    MenuWorld world;
    Camera2D cam;
	float speedCam;
    SpriteBatcher batcher;
	boolean isFirstD=true;
	Font font;
	Rectangle playbounds;
	
	String text;
	
	public MenuRenderer(GLGraphics glGraphics, SpriteBatcher batcher,MenuWorld world,Camera2D guiCam) {
        this.glGraphics = glGraphics;
        this.world = world;
        this.cam = guiCam;
        this.batcher = batcher;        
		
		font=new Font(Assets.FontText,0,0,16,32,32);
		
		playbounds=new Rectangle(160,128,5*32,32);
		
	}
	
	
	 public void render(float deltaTime) {
		 
		 
		 if(world.distX<FRUSTUM_WIDTH/2)
			 world.distX=FRUSTUM_WIDTH/2;
		 if(world.distX>(((world.map.getWidthOfMap()*32)-32)-(FRUSTUM_WIDTH/2)))
			 world.distX=((world.map.getWidthOfMap()*32)-32)-((FRUSTUM_WIDTH/2));
			 
		if(world.distY<FRUSTUM_HEIGHT/2)
			world.distY=FRUSTUM_HEIGHT/2;
		if(world.distY>((world.map.getHeightOfMap()*32)-32)-FRUSTUM_HEIGHT/2)
			world.distY=((world.map.getHeightOfMap()*32)-32)-FRUSTUM_HEIGHT/2;
			
		
		 speedCam=deltaTime*world.jim.speed;
		 
        if((world.jim.position.x-cam.position.x)>(FRUSTUM_WIDTH/2)-128){
			world.distX+=speedCam;
		}
		 if((world.jim.position.x-cam.position.x)<-(FRUSTUM_WIDTH/2)+128){
			 world.distX-=speedCam;
		 }
		 if((world.jim.position.y-cam.position.y)>(FRUSTUM_HEIGHT/2)-128){
			 world.distY+=speedCam;
		 }
		 if((world.jim.position.y-cam.position.y)<-(FRUSTUM_HEIGHT/2)+128){
			 world.distY-=speedCam;
		 }
		 
		 if(world.distX<FRUSTUM_WIDTH/2)
			 world.distX=FRUSTUM_WIDTH/2;
			 
		 if(world.distX>(((world.map.getWidthOfMap()*32)-32)-(FRUSTUM_WIDTH/2)))
			 world.distX=((world.map.getWidthOfMap()*32)-32)-((FRUSTUM_WIDTH/2));
			 
		 if(world.distY<FRUSTUM_HEIGHT/2)
			 world.distY=FRUSTUM_HEIGHT/2;
		 if(world.distY>((world.map.getHeightOfMap()*32)-32)-FRUSTUM_HEIGHT/2)
			 world.distY=((world.map.getHeightOfMap()*32)-32)-FRUSTUM_HEIGHT/2;
			 
		
		cam.position.set(world.distX,world.distY);
        cam.setViewportAndMatrices();
        renderBackground();
        renderJim();
		renderMomias(deltaTime);
		renderJugar();
		renderCreate();
		}
	

	public void renderBackground() {
		for(int i=0;i<world.map.widthOfMap;i++){
		batcher.beginBatch(Assets.ItemMap);
			for(int j=0;j<world.map.heightOfMap;j++){
				batcher.drawSprite(world.map.cmap[i][j].position.x,world.map.cmap[i][j].position.y,32,32,
				world.map.cmap[i][j].getDraw());
				/*if(world.map.cmap[i][j].getBlock())
				batcher.drawSprite(world.map.cmap[i][j].position.x,world.map.cmap[i][j].position.y,world.map.cmap[i][j].bounds.width,world.map.cmap[i][j].bounds.height,
								   Assets.ball);*/
			}
		batcher.endBatch();
		} 
    }
	
	public void renderJim(){
		int lookat=world.jim.getLookAt(world.jim.velocity);
		
		/*switch(lookat){
			case 0:
				batcher.beginBatch(Assets.TextScr1);
				TextureRegion keyframeRight=Assets.walkRigth.getKeyFrame(world.jim.walkTime,Assets.walkRigth.ANIMATION_LOOPING);
				batcher.drawSprite(world.jim.position.x-8,world.jim.position.y-8,32,32,1,keyframeRight);
				batcher.endBatch();
				break;
			case 1:
				batcher.beginBatch(Assets.TextScr1);
				TextureRegion keyframeUp=Assets.walkUp.getKeyFrame(world.jim.walkTime,Assets.walkUp.ANIMATION_LOOPING);
				batcher.drawSprite(world.jim.position.x-8,world.jim.position.y-8,32,32,1,keyframeUp);
				batcher.endBatch();
				break;
			case 2:
				batcher.beginBatch(Assets.TextScr1);
				TextureRegion keyframeLeft=Assets.walkLeft.getKeyFrame(world.jim.walkTime,Assets.walkLeft.ANIMATION_LOOPING);
				batcher.drawSprite(world.jim.position.x-8,world.jim.position.y-8,32,32,1,keyframeLeft);
				batcher.endBatch();
				break;
			case 3:
				batcher.beginBatch(Assets.TextScr1);
				TextureRegion keyframeDown=Assets.walkDown.getKeyFrame(world.jim.walkTime,Assets.walkDown.ANIMATION_LOOPING);
				batcher.drawSprite(world.jim.position.x-8,world.jim.position.y-8,32,32,1,keyframeDown);
				batcher.endBatch();
				break;
		}*/
		
		
		
		
		switch(lookat){
			case 0:
				batcher.beginBatch(Assets.jim);
				TextureRegion keyframeRight=Assets.walkRigth.getKeyFrame(world.jim.walkTime,Assets.walkRigth.ANIMATION_LOOPING);
				batcher.drawSprite(world.jim.position.x,world.jim.position.y,32,32,1,keyframeRight);
				batcher.endBatch();
			break;
			case 1:
				batcher.beginBatch(Assets.jim);
				TextureRegion keyframeUp=Assets.walkUp.getKeyFrame(world.jim.walkTime,Assets.walkUp.ANIMATION_LOOPING);
				batcher.drawSprite(world.jim.position.x,world.jim.position.y,32,32,1,keyframeUp);
				batcher.endBatch();
			break;
			case 2:
				batcher.beginBatch(Assets.jim);
				TextureRegion keyframeLeft=Assets.walkLeft.getKeyFrame(world.jim.walkTime,Assets.walkLeft.ANIMATION_LOOPING);
				batcher.drawSprite(world.jim.position.x,world.jim.position.y,-32,32,1,keyframeLeft);
				batcher.endBatch();
			break;
			case 3:
				batcher.beginBatch(Assets.jim);
				TextureRegion keyframeDown=Assets.walkDown.getKeyFrame(world.jim.walkTime,Assets.walkDown.ANIMATION_LOOPING);
				batcher.drawSprite(world.jim.position.x,world.jim.position.y,32,32,1,keyframeDown);
				batcher.endBatch();
			break;
		}
		
		/*batcher.beginBatch(Assets.TextScr1);
		for(int wt=0;wt<world.tail;wt++){
			batcher.drawSprite(world.tailJim[wt].position.x,world.tailJim[wt].position.y,world.tailJim[wt].bounds.width,world.tailJim[wt].bounds.height,Assets.ballblue);
		}
		batcher.endBatch();
		
		batcher.beginBatch(Assets.FontText);
		for(int wt2=0;wt2<world.tail;wt2++){
			font.drawText(batcher,""+wt2,world.tailJim[wt2].position.x,world.tailJim[wt2].position.y);
		}
		batcher.endBatch();*/
		
	}
	
	public void renderMomias(float deltaTime){
		batcher.beginBatch(Assets.TextScr1);
		int numMomias=world.numMomias;
		for(int i=0;i<numMomias;i++){
			world.momias[i].tick+=deltaTime;
			
			while(world.momias[i].tick>0.5f){
			world.momias[i].lookat=world.momias[i].getLookAt(world.momias[i].velocity);
			world.momias[i].tick=0;
			}
			int slowLookAt=world.momias[i].lookat;
			switch(slowLookAt){
				case 0:
					batcher.beginBatch(Assets.TextScr1);
					TextureRegion keyframeRight=Assets.momiaRigth.getKeyFrame(world.momias[i].walkTime,Assets.momiaRigth.ANIMATION_LOOPING);
					batcher.drawSprite(world.momias[i].position.x,world.momias[i].position.y,32,32,1,keyframeRight);
					batcher.endBatch();
					break;
				case 1:
					batcher.beginBatch(Assets.TextScr1);
					TextureRegion keyframeUp=Assets.momiaUp.getKeyFrame(world.momias[i].walkTime,Assets.momiaUp.ANIMATION_LOOPING);
					batcher.drawSprite(world.momias[i].position.x,world.momias[i].position.y,32,32,1,keyframeUp);
					batcher.endBatch();
					break;
				case 2:
					batcher.beginBatch(Assets.TextScr1);
					TextureRegion keyframeLeft=Assets.momiaLeft.getKeyFrame(world.momias[i].walkTime,Assets.momiaLeft.ANIMATION_LOOPING);
					batcher.drawSprite(world.momias[i].position.x,world.momias[i].position.y,32,32,1,keyframeLeft);
					batcher.endBatch();
					break;
				case 3:
					batcher.beginBatch(Assets.TextScr1);
					TextureRegion keyframeDown=Assets.momiaDown.getKeyFrame(world.momias[i].walkTime,Assets.momiaDown.ANIMATION_LOOPING);
					batcher.drawSprite(world.momias[i].position.x,world.momias[i].position.y,32,32,1,keyframeDown);
					batcher.endBatch();
					break;
			}			
		/*	if(world.momias[i].vio){
			batcher.beginBatch(Assets.FontText);
			font.drawText(batcher,""+world.momias[i].d,world.momias[i].position.x,world.momias[i].position.y);
			batcher.endBatch();
			}
			if(!world.momias[i].testigos.isEmpty()){
			batcher.beginBatch(Assets.TextScr1);
			batcher.drawSprite(world.momias[i].testigos.get(0).position.x,world.momias[i].testigos.get(0).position.y,32,32,Assets.ballblue);
			batcher.endBatch();
			}
			if(!world.momias[i].testigosBall.isEmpty()){
			batcher.beginBatch(Assets.TextScr1);
			for(int y=0;y<world.momias[i].testigosBall.size();y++){
				batcher.drawSprite(world.momias[i].testigosBall.get(y).position.x,world.momias[i].testigosBall.get(y).position.y,24,24,Assets.ball);
			}
			batcher.endBatch();
			}else{
				
			}*/
		}
		
	}
	public void renderJugar(){
		batcher.beginBatch(Assets.FontText);
		font.drawText(batcher,"jugar",cam.position.x-80,cam.position.y+12);
		batcher.endBatch();
	}
	public void renderCreate(){
		batcher.beginBatch(Assets.FontText);
		font.drawText(batcher,"create",cam.position.x-96,cam.position.y-12);
		batcher.endBatch();
	}
	
}
