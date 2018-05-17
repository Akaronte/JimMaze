package org.barbasman.framework;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.SoundPool;

import org.barbasman.framework.Audio;
import org.barbasman.framework.Sound;
import org.barbasman.framework.Music;
public class AndroidAudio implements Audio
{
	AssetManager assets;
	SoundPool soundPool;
	
	public AndroidAudio(Activity activity){
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.assets = activity.getAssets();
		this.soundPool = new SoundPool(20,AudioManager.STREAM_MUSIC,0);
	}
	public Sound newSound(String fileName){
		try{
			AssetFileDescriptor assetDescriptor = assets.openFd(fileName);
			int soundId = soundPool.load(assetDescriptor,0);
			return new AndroidSound(soundPool,soundId);
		}catch (IOException e){
			throw new RuntimeException("no se pudo cargar el sonido"+ fileName);
		}
	}
	public Music newMusic(String fileName){
		try{
			AssetFileDescriptor assetDescriptor = assets.openFd(fileName);
			return new AndroidMusic(assetDescriptor);
		}	
	catch (IOException e) {
		throw new RuntimeException("no se pudo cargar la musica");
		}
	}
}
