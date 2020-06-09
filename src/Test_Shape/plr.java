

package Test_Shape;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class plr {

    BufferedImage image;
    double ratio = .7;


    public plr(BufferedImage img){

        polarize(img);
    }

    private void polarize (BufferedImage img)
    {

        int width = img.getWidth();
        int height = img.getHeight();

        int radius = (int)(.5 * (width <= height ? width : height) );
        int circumference = (int)(1.0*2*Math.PI*radius);
        
        image = new BufferedImage(circumference, radius, BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster wraster = image.getRaster();

        for(int i=0; i<circumference; i++)
            for (int j=0; j<radius; j++){
                wraster.setSample(i, j, 0, 0);
            }

        WritableRaster input_raster = img.getRaster();

        double angular_inc = 1/(1.0*radius);

        int xc = width/2; int yc = height/2;
        int x = 0 ; int y = 0; double angle =0;
        int xt = 0; int yt = 0;
        int temp = 0;

        System.out.println("height: " + height + "\twidth: " + width + "\tp_width: " + radius + "p height: " + circumference + "angular_inc" + angular_inc*radius );

///

        for(int i = 0; i < circumference ; i++){
            angle = i*angular_inc;
            for(int j = 0; j < radius; j++ )
            {
                xt = (int)(j*Math.cos(angle));
                yt = (int)(j*Math.sin(angle));

                 //if(Math.abs(xt)<= xc && Math.abs(yt)<= yc){
                x = xc + xt;
                y = yc + yt;

                if(x>=0 && x<width && y>=0 && y<height){
                temp = input_raster.getSample(x, y, 0);
               // System.out.println("xt: " + xt + "yt: " + yt + "temp: " + temp);

                wraster.setSample(i, j, 0, temp );
                }
            }
        }

        }                            // end of polarize

   public BufferedImage retpolarimage(){

    return image;

   }



    }


