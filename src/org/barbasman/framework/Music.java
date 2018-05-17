package org.barbasman.framework;

public interface Music
{	
	public void play();
	public void pause();
	public void stop();
	public void setLooping(boolean looping);
	public void setVolume(float volume);
	public boolean isPlaying();
	public boolean isStopped();
	public boolean isLooping();	
	public void dispose();
	}
