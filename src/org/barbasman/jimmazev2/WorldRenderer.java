package org.barbasman.jimmazev2;
import org.barbasman.framework.*;
import android.animation.*;
import android.util.Log;
import android.util.FloatMath;
import java.util.List;
public class WorldRenderer
{
	

	static final float FRUSTUM_WIDTH = 480;
    static final float FRUSTUM_HEIGHT = 320;    
    GLGraphics glGraphics;
    World world;
	
    Camera2D cam;
	float speedCam;
    SpriteBatcher batcher;
	Font font;
	
	String text;
	
	public WorldRenderer(GLGraphics glGraphics, SpriteBatcher batcher,World world,Camera2D guiCam) {
        this.glGraphics = glGraphics;
        this.world = world;
        this.cam = guiCam;
        this.batcher = batcher;        
		
		font=new Font(Assets.FontText,0,0,16,32,32);
	}
	
	
	
	 public void render(float deltaTime) {
		 
		 speedCam=deltaTime*world.jim.speed;
		 if(world.distX<FRUSTUM_WIDTH/2)
			 world.distX=FRUSTUM_WIDTH/2;
		 if(world.distX>(((world.map.getWidthOfMap()*32)-32)-(FRUSTUM_WIDTH/2)))
			 world.distX=((world.map.getWidthOfMap()*32)-32)-((FRUSTUM_WIDTH/2));
			 
		if(world.distY<FRUSTUM_HEIGHT/2)
			world.distY=FRUSTUM_HEIGHT/2;
		if(world.distY>((world.map.getHeightOfMap()*32)-32)-FRUSTUM_HEIGHT/2)
			world.distY=((world.map.getHeightOfMap()*32)-32)-FRUSTUM_HEIGHT/2;
			
		 
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
		renderControls();
		
		}
	public void renderMap(float deltaTime,int toZoom,float distXmap,float distYmap) {
		cam.zoom=toZoom;

		if(distXmap<(FRUSTUM_WIDTH*toZoom)/2)
			distXmap=(FRUSTUM_WIDTH*toZoom)/2;
		
			
		if(distXmap>((world.map.widthOfMap*32)-32)-(FRUSTUM_WIDTH/2)*toZoom)
			distXmap=((world.map.widthOfMap*32)-32)-(FRUSTUM_WIDTH/2)*toZoom;
			

		if(distYmap<(FRUSTUM_HEIGHT*toZoom)/2)
			distYmap=(FRUSTUM_HEIGHT*toZoom)/2;
		if(distYmap>((world.map.getHeightOfMap()*32)-32)-(FRUSTUM_HEIGHT*toZoom)/2)
			distYmap=((world.map.getHeightOfMap()*32)-32)-FRUSTUM_HEIGHT*toZoom/2;
		cam.position.set(distXmap,distYmap);

        cam.setViewportAndMatrices();
        renderBackground();
        renderJim();
		renderMomias(deltaTime);
	}

	public void renderBackground() {
		for(int i=0;i<world.map.widthOfMap;i++){
		batcher.beginBatch(Assets.ItemMap);
			for(int j=0;j<world.map.heightOfMap;j++){
				if(world.map.cmap[i][j].getDraw()==Assets.end){
					batcher.drawSprite(world.map.cmap[i][j].position.x,world.map.cmap[i][j].position.y,32,32,Assets.casilla);}
				
				batcher.drawSprite(world.map.cmap[i][j].position.x,world.map.cmap[i][j].position.y,32,32,
				world.map.cmap[i][j].getDraw());
				
			}
		batcher.endBatch();
		} 
		
    }
	public void renderControls(){
		batcher.beginBatch(Assets.controlsText);
		batcher.drawSprite((cam.position.x-cam.frustumWidth/2)+96/2,(cam.position.y-cam.frustumHeight/2)+96/2,96,96,Assets.controls);
		batcher.endBatch();
	}
	public void renderJim(){
		int lookat=world.jim.getLookAt(world.jim.velocity);
		
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
	
	public void renderTextMap(){
		cam.zoom=1.0f;
		cam.position.set(world.distX,world.distY);
		cam.setViewportAndMatrices();
		
		batcher.beginBatch(Assets.FontText);
		font.drawText(batcher,"Map",cam.position.x+((cam.frustumWidth/2)-80),cam.position.y+(cam.frustumHeight/2-16));
		batcher.endBatch();
	}
	
	public void renderTextGameOver(){
		cam.zoom=1.0f;
		cam.position.set(world.distX,world.distY);
		cam.setViewportAndMatrices();

		batcher.beginBatch(Assets.FontText);
		font.drawText(batcher,"game over",cam.position.x-((32*9)/2),cam.position.y);
		batcher.endBatch();
	}
	
	public void renderTextWin(){
		cam.zoom=1.0f;
		cam.position.set(world.distX,world.distY);
		cam.setViewportAndMatrices();

		batcher.beginBatch(Assets.FontText);
		font.drawText(batcher,"Winner",cam.position.x-((32*6)/2),cam.position.y);
		batcher.endBatch();
	}
	
	public void renderTextPaused(){
		cam.zoom=1.0f;
		cam.position.set(world.distX,world.distY);
		cam.setViewportAndMatrices();

		batcher.beginBatch(Assets.FontText);
		font.drawText(batcher,"Pause",cam.position.x-((32*5)/2),cam.position.y);
		batcher.endBatch();
	}
	
	public void nameMapText(){
		cam.zoom=1.0f;
		cam.position.set(world.distX,world.distY);
		cam.setViewportAndMatrices();

		batcher.beginBatch(Assets.FontText);
		font.drawText(batcher,"NameMap",cam.position.x-((32*5)/2),cam.position.y);
		batcher.endBatch();
	}
	
	public void manuText(){
		cam.zoom=1.0f;
		cam.position.set(world.distX,world.distY);
		cam.setViewportAndMatrices();

		batcher.beginBatch(Assets.FontText);
		font.drawText(batcher,"menu",cam.position.x-240+16,cam.position.y+160-16);
		batcher.endBatch();
	}
	
	
	
	
	public void customRender(float deltaTime,float zoom,int stateControl) {
		
		speedCam=deltaTime*world.jim.speed;
		/*if(world.distX<FRUSTUM_WIDTH/2)
			world.distX=FRUSTUM_WIDTH/2;
		if(world.distX>(((world.map.getWidthOfMap()*32)-32)-(FRUSTUM_WIDTH/2)))
			world.distX=((world.map.getWidthOfMap()*32)-32)-((FRUSTUM_WIDTH/2));

		if(world.distY<FRUSTUM_HEIGHT/2)
			world.distY=FRUSTUM_HEIGHT/2;
		if(world.distY>((world.map.getHeightOfMap()*32)-32)-FRUSTUM_HEIGHT/2)
			world.distY=((world.map.getHeightOfMap()*32)-32)-FRUSTUM_HEIGHT/2;*/


        /*if((world.jim.position.x-cam.position.x)>(FRUSTUM_WIDTH/2)-128){
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
		}*/
		if(world.distX<0)
			world.distX=0;
		if(world.distX>((world.map.getWidthOfMap()*32))/zoom)
			world.distX=((world.map.getWidthOfMap()*32)/zoom);

		if(world.distY<0)
			world.distY=0;
		if(world.distY>((world.map.getHeightOfMap()*32))/zoom)
			world.distY=((world.map.getHeightOfMap()*32)/zoom);


		cam.position.set(world.distX,world.distY);
        cam.setViewportAndMatrices();
        renderCustomBackground(zoom);
		renderCustomControls(stateControl);
        //renderJim();
		
		//renderControls();

	}
	
	public void renderCustomControls(int stateControl){
		Vector2 jimCustomPos=new Vector2(((cam.position.x)+cam.frustumWidth/2)-112,((cam.position.y)+cam.frustumHeight/2)-16);
		Vector2 customCasillaPos=new Vector2(((cam.position.x)+cam.frustumWidth/2)-176,((cam.position.y)+cam.frustumHeight/2)-16);
		Vector2 customBlockPos=new Vector2(((cam.position.x)+cam.frustumWidth/2)-144,((cam.position.y)+cam.frustumHeight/2)-16);
		Vector2 customMomiaPos=new Vector2(((cam.position.x)-cam.frustumWidth/2)+16,((cam.position.y)+cam.frustumHeight/2)-16);
		Vector2 customCofrePos=new Vector2(((cam.position.x)-cam.frustumWidth/2)+16+32*5,((cam.position.y)+cam.frustumHeight/2)-16);
		
		batcher.beginBatch(Assets.CustomControls);
		batcher.drawSprite((cam.position.x),(cam.position.y+cam.frustumHeight/2)-32,64,64,Assets.CustomControlsUp);
		batcher.drawSprite((cam.position.x),(cam.position.y-cam.frustumHeight/2)+32,64,64,Assets.CustomControlsDown);
		batcher.drawSprite((cam.position.x+cam.frustumWidth/2)-32,(cam.position.y),64,64,Assets.CustomControlsRigth);
		batcher.drawSprite((cam.position.x-cam.frustumWidth/2)+32,(cam.position.y),64,64,Assets.CustomControlsLeft);
		batcher.endBatch();
		
		batcher.beginBatch(Assets.SaveTexture);
		batcher.drawSprite(((cam.position.x)+cam.frustumWidth/2)-16,((cam.position.y)+cam.frustumHeight/2)-16,32,32,Assets.CustomSave);
		batcher.drawSprite(((cam.position.x)+cam.frustumWidth/2)-48,((cam.position.y)+cam.frustumHeight/2)-16,32,32,Assets.LupaMinus);
		batcher.drawSprite(((cam.position.x)+cam.frustumWidth/2)-80,((cam.position.y)+cam.frustumHeight/2)-16,32,32,Assets.LupaMas);
		batcher.drawSprite(((cam.position.x)-cam.frustumWidth/2)+32+16,((cam.position.y)+cam.frustumHeight/2)-16,32,32,Assets.BackButton);
		
		/*batcher.drawSprite(((cam.position.x)+cam.frustumWidth/2)-112,((cam.position.y)+cam.frustumHeight/2)-16,32,32,Assets.JimCustom);
		batcher.drawSprite(((cam.position.x)+cam.frustumWidth/2)-144,((cam.position.y)+cam.frustumHeight/2)-16,32,32,Assets.CustomBlock);
		batcher.drawSprite(((cam.position.x)+cam.frustumWidth/2)-176,((cam.position.y)+cam.frustumHeight/2)-16,32,32,Assets.CustomCasilla);*/
		
		batcher.endBatch();
		
		switch(stateControl){
			case 0:
				batcher.beginBatch(Assets.SaveTexture);
				
				batcher.drawSprite(customCasillaPos.x,customCasillaPos.y,32,32,Assets.CustomCasilla);
				batcher.drawSprite(customBlockPos.x,customBlockPos.y,32,32,Assets.CustomBlock);
				batcher.drawSprite(customMomiaPos.x,customMomiaPos.y,32,32,Assets.CustomMomia);
				batcher.drawSprite(customCofrePos.x,customCofrePos.y,32,32,Assets.CustomCofreRegion);
				
				batcher.drawSprite(jimCustomPos.x,jimCustomPos.y,36,36,Assets.JimCustom);
				batcher.endBatch();
				break;
			case 1:
				batcher.beginBatch(Assets.SaveTexture);
				
				
				batcher.drawSprite(jimCustomPos.x,jimCustomPos.y,32,32,Assets.JimCustom);
				batcher.drawSprite(customCasillaPos.x,customCasillaPos.y,32,32,Assets.CustomCasilla);
				batcher.drawSprite(customMomiaPos.x,customMomiaPos.y,32,32,Assets.CustomMomia);
				batcher.drawSprite(customCofrePos.x,customCofrePos.y,32,32,Assets.CustomCofreRegion);
				
				batcher.drawSprite(customBlockPos.x,customBlockPos.y,36,36,Assets.CustomBlock);
				batcher.endBatch();
				break;
			case 2:
				batcher.beginBatch(Assets.SaveTexture);
				
				
				batcher.drawSprite(customBlockPos.x,customBlockPos.y,32,32,Assets.CustomBlock);
				batcher.drawSprite(jimCustomPos.x,jimCustomPos.y,32,32,Assets.JimCustom);
				batcher.drawSprite(customMomiaPos.x,customMomiaPos.y,32,32,Assets.CustomMomia);
				batcher.drawSprite(customCofrePos.x,customCofrePos.y,32,32,Assets.CustomCofreRegion);
				
				batcher.drawSprite(customCasillaPos.x,customCasillaPos.y,36,36,Assets.CustomCasilla);
				batcher.endBatch();
				break;
			case 3:
				batcher.beginBatch(Assets.SaveTexture);
				batcher.drawSprite(customBlockPos.x,customBlockPos.y,32,32,Assets.CustomBlock);
				batcher.drawSprite(jimCustomPos.x,jimCustomPos.y,32,32,Assets.JimCustom);
				batcher.drawSprite(customCasillaPos.x,customCasillaPos.y,32,32,Assets.CustomCasilla);
				batcher.drawSprite(customCofrePos.x,customCofrePos.y,32,32,Assets.CustomCofreRegion);
				
				batcher.drawSprite(customMomiaPos.x,customMomiaPos.y,36,36,Assets.CustomMomia);
				
				batcher.endBatch();
			
			break;
			
			case 4:
				batcher.beginBatch(Assets.SaveTexture);
				batcher.drawSprite(customBlockPos.x,customBlockPos.y,32,32,Assets.CustomBlock);
				batcher.drawSprite(jimCustomPos.x,jimCustomPos.y,32,32,Assets.JimCustom);
				batcher.drawSprite(customCasillaPos.x,customCasillaPos.y,32,32,Assets.CustomCasilla);
				batcher.drawSprite(customMomiaPos.x,customMomiaPos.y,32,32,Assets.CustomMomia);

				batcher.drawSprite(customCofrePos.x,customCofrePos.y,36,36,Assets.CustomCofreRegion);

				batcher.endBatch();

				break;
		}
		
		/*batcher.beginBatch(Assets.FontText);
		font.drawText(batcher,"menu",cam.position.x-240+32+16,cam.position.y+160-8);
		batcher.endBatch();*/
	}
	public void renderCustomBackground(float zoom) {
		
		for(int i=0;i<world.map.widthOfMap;i++){
			batcher.beginBatch(Assets.ItemMap);
			for(int j=0;j<world.map.heightOfMap;j++){
				if(world.map.cmap[i][j].getDraw()==Assets.end){
					batcher.drawSprite(world.map.cmap[i][j].position.x/zoom,world.map.cmap[i][j].position.y/zoom,32/zoom,32/zoom,Assets.casilla);
					}

				batcher.drawSprite(world.map.cmap[i][j].position.x/zoom,world.map.cmap[i][j].position.y/zoom,32/zoom,32/zoom,
								   world.map.cmap[i][j].getDraw());
			}
			batcher.endBatch();
		}
		batcher.beginBatch(Assets.jim);
		TextureRegion keyframeDown=Assets.walkDown.getKeyFrame(world.jim.walkTime,Assets.walkDown.ANIMATION_LOOPING);
		batcher.drawSprite(world.jim.position.x/zoom,world.jim.position.y/zoom,32/zoom,32/zoom,1,keyframeDown);
		batcher.endBatch();
		renderCustomMomias(zoom);
    }
	
	public void renderCustomMomias(float zoom){
		batcher.beginBatch(Assets.TextScr1);
		int numMomias=world.cMomias.size();
		for(int i=0;i<numMomias;i++){
					batcher.beginBatch(Assets.SaveTexture);
					batcher.drawSprite(world.cMomias.get(i).position.x/zoom,world.cMomias.get(i).position.y/zoom,32/zoom,32/zoom,Assets.CustomMomia);
					batcher.endBatch();
		}

	}
	
	
	
}
