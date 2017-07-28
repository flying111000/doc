package com.statictool.gui;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


/**
 * 
 * Solution for this Exception : Numbers of source Raster bands and source color space components do not match when i read image
 *
 */
public class StrangeImageTest {

	public static void main(String[] args) throws IOException {
		final BufferedImage image = readImage(new File("BigPoster.jpg.jpg"));

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame f = new JFrame();
				f.getContentPane().add(new JLabel(new ImageIcon(image)));
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.pack();
				f.setLocationRelativeTo(null);
				f.setVisible(true);
			}
		});
	}

	static BufferedImage readImage(File file) throws IOException {
		return readImage(new FileInputStream(file));
	}

	static BufferedImage readImage(InputStream stream) throws IOException {
		Iterator<ImageReader> imageReaders = ImageIO
				.getImageReadersBySuffix("jpg");
		ImageReader imageReader = imageReaders.next();
		ImageInputStream iis = ImageIO.createImageInputStream(stream);
		imageReader.setInput(iis, true, true);
		Raster raster = imageReader.readRaster(0, null);
		int w = raster.getWidth();
		int h = raster.getHeight();

		BufferedImage result = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
		int rgb[] = new int[3];
		int pixel[] = new int[3];
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				raster.getPixel(x, y, pixel);
				int Y = pixel[0];
				int CR = pixel[1];
				int CB = pixel[2];
				toRGB(Y, CB, CR, rgb);
				int r = rgb[0];
				int g = rgb[1];
				int b = rgb[2];
				int bgr = ((b & 0xFF) << 16) | ((g & 0xFF) << 8) | (r & 0xFF);
				result.setRGB(x, y, bgr);
			}
		}
		return result;
	}

	// Based on http://www.equasys.de/colorconversion.html
	private static void toRGB(int y, int cb, int cr, int rgb[]) {
		float Y = y / 255.0f;
		float Cb = (cb - 128) / 255.0f;
		float Cr = (cr - 128) / 255.0f;

		float R = Y + 1.4f * Cr;
		float G = Y - 0.343f * Cb - 0.711f * Cr;
		float B = Y + 1.765f * Cb;

		R = Math.min(1.0f, Math.max(0.0f, R));
		G = Math.min(1.0f, Math.max(0.0f, G));
		B = Math.min(1.0f, Math.max(0.0f, B));

		int r = (int) (R * 255);
		int g = (int) (G * 255);
		int b = (int) (B * 255);

		rgb[0] = r;
		rgb[1] = g;
		rgb[2] = b;
	}
}
