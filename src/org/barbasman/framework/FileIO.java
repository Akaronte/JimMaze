package org.barbasman.framework;
import java.io.*;

public interface FileIO
{
	public InputStream readAsset(String fileName) throws IOException;
	public InputStream readFile(String fileName) throws IOException;
	public OutputStream writeFile(String fileName) throws IOException;
}
