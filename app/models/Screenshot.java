/**
 * 
 */
package models;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Andrew Jackson <Andrew.Jackson@bl.uk>
 *
 */
public class Screenshot {
	
	private static Logger logger = LoggerFactory.getLogger(Screenshot.class);
	
	private static String URLENDER = "scripts/urlender.js";
	
	public byte[] screenshot;
	public String urls;

	/**
	 * 
	 * @param url
	 * @return
	 * @throws IOException 
	 * @throws TimeoutException 
	 * @throws InterruptedException 
	 */
	public static Screenshot getThumbnailPNG( String url ) throws IOException, InterruptedException, TimeoutException {
		// Create temp files:
		File urls = File.createTempFile("urls", ".txt");
		File png = File.createTempFile("screenshot", ".png");
		// Invoke urlender.js to make results
		String[] cmd = new String[] { 
				"phantomjs", URLENDER, url, urls.getAbsolutePath(), png.getAbsolutePath() 
		};
		logger.debug("PNG: "+png.getAbsolutePath());
		int exitStatus = executeCommandLine(cmd, true, true, 90*1000);
		Screenshot shot = new Screenshot();
		// Load PNG
		byte[] data = new byte[(int) png.length()];
		new FileInputStream(png).read(data);
		shot.screenshot = crop(data);
		// TODO Load URLs
		// Return it:
		return shot;
	}
	
	public static byte[] crop(byte[] screenshot) throws IOException {
		Image orig = ImageIO.read(new ByteArrayInputStream(screenshot));

		int x = 0, y = 0, w = 800, h = 600;

		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		bi.getGraphics().drawImage(orig, 0, 0, w, h, x, y, x + w, y + h, null);
		
		File cropped = File.createTempFile("cropped", ".png");
		ImageIO.write(bi, "png", cropped);		
		byte[] data = new byte[(int) cropped.length()];
		new FileInputStream(cropped).read(data);
		return data;
	}
	
	public static int executeCommandLine(final String[] commandLine,
			final boolean printOutput,
			final boolean printError,
			final long timeout)
					throws IOException, InterruptedException, TimeoutException
					{
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec(commandLine);
		/* Set up process I/O. */
		// consume and display the error and output streams
        StreamGobbler outputGobbler = new StreamGobbler(process.getInputStream(), "OUTPUT", printOutput);
        StreamGobbler errorGobbler = new StreamGobbler(process.getErrorStream(), "ERROR", printError);
        outputGobbler.start();
        errorGobbler.start();
        //
		Worker worker = new Worker(process);
		worker.start();
		try {
			worker.join(timeout);
			if (worker.exit != null)
				return worker.exit;
			else
				throw new TimeoutException();
		} catch(InterruptedException ex) {
			worker.interrupt();
			Thread.currentThread().interrupt();
			throw ex;
		} finally {
			process.destroy();
		}
					}

	private static class Worker extends Thread {
		private final Process process;
		private Integer exit;
		private Worker(Process process) {
			this.process = process;
		}
		public void run() {
			try { 
				exit = process.waitFor();
			} catch (InterruptedException ignore) {
				return;
			}
		}  
	}
}
