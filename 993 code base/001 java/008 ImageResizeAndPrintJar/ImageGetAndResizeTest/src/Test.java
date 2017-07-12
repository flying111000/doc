import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

public class Test {
	public static void main(String[] args){
		try {
			
			File file = new File("c:/java/824343622_2880x1943.jpg");
			
			URL hdurl = new URL("http://image1.urlforimages.com/Images/798/$Original/824343622_2880x1943.jpg");
			FileUtils.copyURLToFile(hdurl, file);
			
			printJar(FileUtils.class);
			printJar(javax.imageio.ImageIO.class);
			
			resizeImage(file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void printJar(Class klass){
		URL location = klass.getResource('/' + klass.getName().replace('.', '/') + ".class");
		System.out.println(location);
	}
	
	public static void resizeImage(File file){        
		try {
			BufferedImage image = ImageIO.read(file);  
			if (image.getWidth()>800 || image.getHeight()>600) {
				int scale=Math.max(image.getWidth()/800, image.getHeight()/600)+1;
				int width=image.getWidth()/scale;
				int height=image.getHeight()/scale;
				BufferedImage compress = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
				Graphics g = compress.getGraphics();
				g.drawImage(image.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
				ImageIO.write(compress, "JPG", file);  
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }  
	

}
