package endec;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import javafx.scene.image.Image;

import java.io.File;
//import java.util.Random;
import java.awt.Color;


public class encryptImage{
	public static void Encrypt(String filepath, String key) throws IOException{
    	
    	
		System.out.print(filepath+"\n");
		
		File tempFile = new File(filepath);
		BufferedImage img = ImageIO.read(tempFile);
		
		

    	
        int width = img.getWidth();
        int height = img.getHeight();
        int nrgb, rgb, b, r, g, key_temp, k = 0, i = 0, j = 0;

        System.out.print(width + " " + height);

        int arr[][] = new int[((width*height)/2) + 1][];

        for(int row = 0; row < height; row++){
            for(int column = 0; column < width; column++){
                
                if(i < key.length()){
                    key_temp = Integer.parseInt(key.substring(i, i+3));
                    i+=3;
                }                

                else{
                    i = 0;
                    key_temp = Integer.parseInt(key.substring(i, i+3));
                    i+=3;
                }
                
               

                rgb = img.getRGB(column, row);

                Color color = new Color(rgb, true);

                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                if(row==6 && column==5) {
                	System.out.println("BEFORE ENC: R:"+red+" G:"+green+" B:"+blue);
                
                }

                if((row+column)%2 == 0){
                    nrgb = 65536 * blue + 256 * red + green;
                    img.setRGB(column, row, nrgb);     
                    
                    if((row+column)%4 == 0){
                        arr[j] = new int[]{column, row, nrgb};
                        j++;
                    }
                   
                }

                
                else{

                    r = (red + key_temp) % 256;
                    g = (green + key_temp) % 256;
                    b = (blue + key_temp) % 256;
                    
                    if(row==6 && column==5) {
                    	System.out.println("key temp: "+key_temp);
                    	System.out.println("after ENC: R:"+r+" G:"+g+" B:"+b);
                    
                    }
                    

                    nrgb  = 65536 * r + 256 * g + b ;
                    img.setRGB(column, row, nrgb);
                    
                }
            }
        }

        System.out.println("J: ");
        System.out.println(j);
        
        for(int l = 0; l < j; l++){
            img.setRGB(arr[l][0], arr[l][1], arr[j-l-1][2]);
        }   
        
        WriteImage(img, "ENCAGAIN.jpg");
    }
	
  
      
    public static void WriteImage(BufferedImage img, String path){
        File ImageFile = new File(path);

        try{
            ImageIO.write(img,"jpg", ImageFile);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
