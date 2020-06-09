

package Texture;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import flanagan.complex.*;


public class Reorient {


    public BufferedImage reorient_function (BufferedImage img, int wd, int ht){

        
        int width = img.getWidth();
        int height = img.getHeight();

        int xa = wd/2;
        int ya = ht/2;

        int xb = width - xa;
        int yb = height - ya;

        BufferedImage result_image = new BufferedImage(wd, ht, BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster result_raster = result_image.getRaster();

        WritableRaster wraster = img.getRaster();

        int[][] temparr = new int[wd][ht];

        for(int i = 0; i < xa; i++)
            for( int j = 0; j < ya; j++){

                result_raster.setSample( i, j, 0, wraster.getSample(xb + i, yb + j, 0));

                result_raster.setSample( xa + i, ya + j, 0, wraster.getSample(i, j, 0));

                result_raster.setSample( xa + i, j, 0 , wraster.getSample(i, yb + j, 0));

                result_raster.setSample( i, ya + j, 0 , wraster.getSample(xb + i, j, 0));


            }        

        double sum = 0;
        
        //**************************** MEAN AND STANDARD DEVIATION  **********************************//
//        for(int i = 0; i < wd; i++)                                     // testing values
//            for( int j = 0; j < ht; j++){
//               sum += result_raster.getSample(i, j, 0);
//            }

        return result_image;
           
    }


    int[][] scaling(int[][] input_array, int wd, int ht, boolean flag){         // set scaled version of gabor filter in spatial domain

        double max = 0;
        double min = 0;

        int [][] temp_array = new int[wd][ht];

        for(int i=0; i< wd; i++)
            for(int j=0; j< ht; j++){

                if(max < input_array[i][j]) max = input_array[i][j];
                if(min > input_array[i][j]) min = input_array[i][j];


            }

        double diff = max - min;
        for(int i=0; i< wd; i++)
            for(int j=0; j< ht; j++){

                temp_array[i][j] = (int)((input_array[i][j]-min)/(diff)*255);
                if (flag == true)                                                       // to check values//
                    System.out.println(i+ "\t"+j+"\t" + temp_array[i][j]);

            }
        return temp_array;

    }
}
