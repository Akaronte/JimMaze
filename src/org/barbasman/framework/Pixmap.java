package org.barbasman.framework;

import org.barbasman.framework.Graphics.PixmapFormat;

public interface Pixmap
{
	public int getWidth();
	public int getHeight();
	public PixmapFormat getFormat();
	public void dispose();
}
