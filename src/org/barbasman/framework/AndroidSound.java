package org.barbasman.framework;

import android.media.SoundPool;
import org.barbasman.framework.AndroidAudio;
public class AndroidSound implements Sound
{	
	SoundPool soundPool;
	int soundId;
	public AndroidSound(SoundPool soundPool, int soundId){
		this.soundId = soundId;
		this.soundPool=soundPool;
	}
	@Override
	public void play(float volume){
		soundPool.play(soundId,volume,volume,0,0,1);
	}
	@Override
	public void dispose(){
		soundPool.unload(soundId);
	}
}
