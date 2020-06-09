

package Test_Shape;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class PolarRaster {

    BufferedImage image;
    double ratio = .7;


    public PolarRaster(BufferedImage img){

        polarize(img);
    }

    private void polarize (BufferedImage img)
    {

        int width = img.getWidth();
        int height = img.getHeight();

        int pheight = (int)(Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2)))/2;

        int pwidth  =(int) (ratio * 2 * Math.PI  * pheight);


        image = new BufferedImage(pwidth, pheight, BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster wraster = image.getRaster();

        for(int i=0; i<pwidth; i++)
            for (int j=0; j<pheight; j++){
                wraster.setSample(i, j, 0, 0);
            }

        WritableRaster input_raster = img.getRaster();

        double angular_inc = 1/(pheight * ratio);

        int xc = width/2; int yc = height/2;
        int x = 0 ; int y = 0; double angle =0;
        int xt = 0; int yt = 0;
        int temp = 0;

        System.out.println("height: " + height + "\twidth: " + width + "\tp_width: " + pwidth + "p height: " + pheight + "angular_inc" + angular_inc*pwidth );



        for(int i = 0; i < pwidth ; i++){
            angle = i*angular_inc;
            for(int j = 0; j < pheight; j++ )
            {                
                xt = (int)(j*Math.cos(angle));
                yt = (int)(j*Math.sin(angle));              

                 //if(Math.abs(xt)<= xc && Math.abs(yt)<= yc){
                x = xc + xt;
                y = yc + yt;

                if(x>=0 && x<=width && y>=0 && y<=height){
                temp = input_raster.getSample(x, y, 0);
                System.out.println("xt: " + xt + "yt: " + yt + "temp: " + temp);

                wraster.setSample(i, j, 0, temp );
                }                                
            }
        }

        }                            // end of polarize

   public BufferedImage retpolarimage(){

    return image;
    
   }
        


    }


