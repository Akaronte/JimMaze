package org.barbasman.jimmazev2;

import org.barbasman.framework.Music;
import org.barbasman.framework.Sound;
import org.barbasman.framework.Animation;
import org.barbasman.framework.Font;
import org.barbasman.framework.Texture;
import org.barbasman.framework.TextureRegion;
import org.barbasman.framework.GLGame;
import org.barbasman.framework.Game;

public class Assets {

	public static Texture TextScr1;
	public static Texture Momia;
	public static Texture SaveTexture;
	public static Texture FontText;
	public static Texture CustomControls;
	public static Texture Logo;
	public static Texture ItemMap;
	public static TextureRegion background;
	public static TextureRegion LogoRegion;
	public static TextureRegion playBotton;
	public static TextureRegion rightBotton;
	public static TextureRegion downBotton;
	public static TextureRegion loadBotton;
	public static TextureRegion CustomControlsUp;
	public static TextureRegion CustomControlsDown;
	public static TextureRegion CustomControlsLeft;
	public static TextureRegion CustomControlsRigth;
	public static TextureRegion CustomSave;
	public static TextureRegion CustomCofreRegion;
	public static TextureRegion BackButton;
	public static TextureRegion LupaMinus;
	public static TextureRegion LupaMas;
	public static TextureRegion JimCustom;
	public static TextureRegion CustomBlock;
	public static TextureRegion CustomCasilla;
	public static TextureRegion CustomMomia;
	public static TextureRegion casilla;
	public static TextureRegion block;
	public static TextureRegion ball;
	public static TextureRegion ballblue;
	public static TextureRegion end;
	public static TextureRegion tesoro;
	public static Animation walkUp;
	public static Animation walkDown;
	public static Animation walkLeft;
	public static Animation walkRigth;
	public static Animation momiaRigth;
	public static Animation momiaUp;
	public static Animation momiaLeft;
	public static Animation momiaDown;
	public static Sound clickSound;
	public static Texture jim;
	public static Texture controlsText;
	public static TextureRegion controls;
	
	public static void load(GLGame game){
		TextScr1= new Texture(game,"TextScr1.png");
		CustomControls= new Texture(game,"CustomControls.png");
		Logo= new Texture(game,"Logo.png");
		Momia= new Texture(game,"Momia.png");
		ItemMap= new Texture(game,"ItemMap.png");
		FontText= new Texture(game,"FontText.png");
		SaveTexture=new Texture(game,"SaveTexture.png");
		CustomControlsUp=new TextureRegion(CustomControls,256,256,256,256);
		CustomControlsDown=new TextureRegion(CustomControls,0,256,256,256);
		CustomControlsLeft=new TextureRegion(CustomControls,256,0,256,256);
		CustomControlsRigth=new TextureRegion(CustomControls,0,0,256,256);
		CustomSave=new TextureRegion(SaveTexture,128,0,128,128);
		CustomCofreRegion=new TextureRegion(SaveTexture,0,128,128,128);
		BackButton=new TextureRegion(SaveTexture,128,128,128,128);
		LupaMinus=new TextureRegion(SaveTexture,256,64,64,64);
		LupaMas=new TextureRegion(SaveTexture,256,0,64,64);
		JimCustom=new TextureRegion(SaveTexture,0,0,128,128);
		CustomBlock=new TextureRegion(SaveTexture,320,0,64,64);
		CustomCasilla=new TextureRegion(SaveTexture,320,64,64,64);
		CustomMomia=new TextureRegion(SaveTexture,384,0,128,128);
		controlsText=new Texture(game,"Controls.png");
		controls=new TextureRegion(controlsText,0,0,96,96);
		background= new TextureRegion(TextScr1,0,0,480,320);
		LogoRegion= new TextureRegion(Logo,0,0,480,320);
		playBotton=new TextureRegion(TextScr1,0,320,96,64);
		rightBotton=new TextureRegion(TextScr1,96+32,320,64,64);
		downBotton=new TextureRegion(TextScr1,96+32+64,320,64,64);
		loadBotton=new TextureRegion(TextScr1,256,320,128,64);
		casilla=new TextureRegion(ItemMap,64,0,32,32);
		block=new TextureRegion(ItemMap,64,32,32,32);
		jim=new Texture(game,"Jim.png");
		tesoro=new TextureRegion(TextScr1,0,0,10,10);
		ball=new TextureRegion(TextScr1,0,0,10,10);
		end=new TextureRegion(ItemMap,0,0,64,64);
		ballblue=new TextureRegion(TextScr1,96+32,320,10,10);
		
		
		walkLeft=new Animation(0.2f,
								new TextureRegion(jim,0,256,256,256),
							   new TextureRegion(jim,256,256,256,256),
							   new TextureRegion(jim,0,256,256,256),
							   new TextureRegion(jim,512,256,256,256));
		walkRigth=new Animation(0.2f,
							   new TextureRegion(jim,0,256,256,256),
							   new TextureRegion(jim,512,256,256,256),
							   new TextureRegion(jim,0,256,256,256),
							   new TextureRegion(jim,256,256,256,256));
							   
		walkDown=new  Animation(0.2f,
								new TextureRegion(jim,0,0,256,256),
								new TextureRegion(jim,256,0,256,256),
								new TextureRegion(jim,0,0,256,256),
								new TextureRegion(jim,512,0,256,256));
		walkUp=new Animation(0.2f,
							 new TextureRegion(jim,0,512,256,256),
							 new TextureRegion(jim,256,512,256,256),
							 new TextureRegion(jim,0,512,256,256),
							 new TextureRegion(jim,512,512,256,256));
		
		/*walkLeft=new Animation(0.2f,
							new TextureRegion(TextScr1,0,384+32,32,32),
							new TextureRegion(TextScr1,32,384+32,32,32),
							new TextureRegion(TextScr1,64,384+32,32,32),
							new TextureRegion(TextScr1,96,384+32,32,32));
		walkRigth=new Animation(0.2f,
							 new TextureRegion(TextScr1,0,384+32*2,32,32),
							 new TextureRegion(TextScr1,32,384+32*2,32,32),
							 new TextureRegion(TextScr1,64,384+32*2,32,32),
							 new TextureRegion(TextScr1,96,384+32*2,32,32));
		walkDown=new Animation(0.2f,
							 new TextureRegion(TextScr1,0,384,32,32),
							 new TextureRegion(TextScr1,32,384,32,32),
							 new TextureRegion(TextScr1,64,384,32,32),
							 new TextureRegion(TextScr1,96,384,32,32));
		walkUp=new Animation(0.2f,
							 new TextureRegion(TextScr1,0,384+32*3,32,32),
							 new TextureRegion(TextScr1,32,384+32*3,32,32),
							 new TextureRegion(TextScr1,64,384+32*3,32,32),
							 new TextureRegion(TextScr1,96,384+32*3,32,32));*/
		momiaRigth=new Animation(0.2f,
							new TextureRegion(TextScr1,128,384+64,32,32),
							new TextureRegion(TextScr1,128+32,384+64,32,32),
							new TextureRegion(TextScr1,128+64,384+64,32,32)
							,new TextureRegion(TextScr1,128+32,384+64,32,32)
							);
		momiaUp=new Animation(0.2f,
							  new TextureRegion(TextScr1,128,384,32,32),
							  new TextureRegion(TextScr1,128+32,384,32,32),
							  new TextureRegion(TextScr1,128+64,384,32,32),
							  new TextureRegion(TextScr1,128,384,32,32));
		momiaLeft=new Animation(0.2f,
							new TextureRegion(TextScr1,128,384+32,32,32),
							new TextureRegion(TextScr1,128+32,384+32,32,32),
							new TextureRegion(TextScr1,128+64,384+32,32,32),
							new TextureRegion(TextScr1,128+32,384+32,32,32));
		momiaDown=new Animation(0.2f,
								new TextureRegion(TextScr1,128,384,32,32),
								new TextureRegion(TextScr1,128+32,384,32,32),
								new TextureRegion(TextScr1,128+64,384,32,32),
								new TextureRegion(TextScr1,128,384,32,32));
		
		//LoadAssetsMap.load(game.getFileIO(),"jimmaze.map1.txt");
		LoadingMapa.load(0,game.getFileIO(),"jimmaze8.map.txt");
		LoadingMapa.load(1,game.getFileIO(),"jimmaze16.map.txt");
		LoadingMapa.load(2,game.getFileIO(),"jimmaze32.map.txt");
		//clickSound=game.getAudio().newSound("click.ogg");
		
	}
	public static void customLoad(Game game,String nameMap){
		LoadingMapa.cload(3,game.getFileIO(),nameMap);
	}
	
	public static void playSound(Sound sound) {
            //sound.play(1);
    }
	
	public static void reload(){
		TextScr1.reload();
		FontText.reload();
		jim.reload();
		//LoadingMapa.load(0,game.getFileIO(),"jimmaze.map2.txt");
		//LoadingMapa.load(1,game.getFileIO(),"jimmaze.map2.txt");
	}
	
	
	
}

/*public class Assets {
    public static Texture background;
    public static TextureRegion backgroundRegion;
    
    public static Texture items;        
    public static TextureRegion mainMenu;
    public static TextureRegion pauseMenu;
    public static TextureRegion ready;
    public static TextureRegion gameOver;
    public static TextureRegion highScoresRegion;
    public static TextureRegion logo;
    public static TextureRegion soundOn;
    public static TextureRegion soundOff;
    public static TextureRegion arrow;
    public static TextureRegion pause;    
    public static TextureRegion spring;
    public static TextureRegion castle;
    public static Animation coinAnim;
    public static Animation bobJump;
    public static Animation bobFall;
    public static TextureRegion bobHit;
    public static Animation squirrelFly;
    public static TextureRegion platform;
    public static Animation brakingPlatform;    
    public static Font font;
    
    public static Music music;
    public static Sound jumpSound;
    public static Sound highJumpSound;
    public static Sound hitSound;
    public static Sound coinSound;
    public static Sound clickSound;
    
    public static void load(GLGame game) {
        background = new Texture(game, "background.png");
        backgroundRegion = new TextureRegion(background, 0, 0, 320, 480);
        
        items = new Texture(game, "items.png");        
        mainMenu = new TextureRegion(items, 0, 224, 300, 110);
        pauseMenu = new TextureRegion(items, 224, 128, 192, 96);
        ready = new TextureRegion(items, 320, 224, 192, 32);
        gameOver = new TextureRegion(items, 352, 256, 160, 96);
        highScoresRegion = new TextureRegion(Assets.items, 0, 257, 300, 110 / 3);
        logo = new TextureRegion(items, 0, 352, 274, 142);
        soundOff = new TextureRegion(items, 0, 0, 64, 64);
        soundOn = new TextureRegion(items, 64, 0, 64, 64);
        arrow = new TextureRegion(items, 0, 64, 64, 64);
        pause = new TextureRegion(items, 64, 64, 64, 64);
        
        spring = new TextureRegion(items, 128, 0, 32, 32);
        castle = new TextureRegion(items, 128, 64, 64, 64);
        coinAnim = new Animation(0.2f,                                 
                                 new TextureRegion(items, 128, 32, 32, 32),
                                 new TextureRegion(items, 160, 32, 32, 32),
                                 new TextureRegion(items, 192, 32, 32, 32),
                                 new TextureRegion(items, 160, 32, 32, 32));
        bobJump = new Animation(0.2f,
                                new TextureRegion(items, 0, 128, 32, 32),
                                new TextureRegion(items, 32, 128, 32, 32));
        bobFall = new Animation(0.2f,
                                new TextureRegion(items, 64, 128, 32, 32),
                                new TextureRegion(items, 96, 128, 32, 32));
        bobHit = new TextureRegion(items, 128, 128, 32, 32);
        squirrelFly = new Animation(0.2f, 
                                    new TextureRegion(items, 0, 160, 32, 32),
                                    new TextureRegion(items, 32, 160, 32, 32));
        platform = new TextureRegion(items, 64, 160, 64, 16);
        brakingPlatform = new Animation(0.2f,
                                     new TextureRegion(items, 64, 160, 64, 16),
                                     new TextureRegion(items, 64, 176, 64, 16),
                                     new TextureRegion(items, 64, 192, 64, 16),
                                     new TextureRegion(items, 64, 208, 64, 16));
        
        font = new Font(items, 224, 0, 16, 16, 20);
        
        music = game.getAudio().newMusic("music.mp3");
        music.setLooping(true);
        music.setVolume(0.5f);
        if(Settings.soundEnabled)
            music.play();
        jumpSound = game.getAudio().newSound("jump.ogg");
        highJumpSound = game.getAudio().newSound("highjump.ogg");
        hitSound = game.getAudio().newSound("hit.ogg");
        coinSound = game.getAudio().newSound("coin.ogg");
        clickSound = game.getAudio().newSound("click.ogg");       
    }       
    
    public static void reload() {
        background.reload();
        items.reload();
        if(Settings.soundEnabled)
            music.play();
    }
    
    public static void playSound(Sound sound) {
        if(Settings.soundEnabled)
            sound.play(1);
    }
}*/
