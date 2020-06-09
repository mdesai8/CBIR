

package Test_Texture;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import flanagan.complex.*;


public class Reorient {

    private double mean = 0;
    private double deviation = 0;

    public BufferedImage image = null;


    public Reorient(){}


    public void reorient_function (  double[][] imgarray, int arrwidth, int arrheight, int wd, int ht){

        
        int width = arrwidth;
        int height = arrheight;

        int xa = wd/2;
        int ya = ht/2;

        int xb = width - xa;
        int yb = height - ya;


        double[][] result_array = new double[wd][ht];

        for(int i = 0; i < xa; i++)
            for( int j = 0; j < ya; j++){

                result_array[i][j]         = imgarray[xb + i][yb + j];
                result_array[xa+i][ya + j] = imgarray[i][j];
                result_array[xa + i][j]    = imgarray[i][yb + j];
                result_array[i][ya + j]    = imgarray[xb + i][j];
            }

        int[][] scaled_array = scaling(result_array, wd, ht, false);

        image = new BufferedImage(wd,ht,BufferedImage.TYPE_BYTE_GRAY);      //
        WritableRaster wraster = image.getRaster();                         //
        

        double sum1 = 0;
        
        mean = 0;
        deviation = 0;
        
        //**************************** MEAN AND STANDARD DEVIATION  **********************************//
        for(int i = 0; i < wd; i++)                                    
            for( int j = 0; j < ht; j++){
                //sum1 += result_array[i][j];
               sum1 += scaled_array[i][j];
               wraster.setSample(i, j, 0, scaled_array[i][j]);
            }
        mean = sum1/(wd*ht);                                     // mean

        double sum2 = 0;
        for(int i = 0; i < wd; i++)                                     
            for( int j = 0; j < ht; j++){

                sum2 += Math.sqrt(Math.abs(mean-result_array[i][j]));

            }
        deviation = sum2/(wd*ht);                                  // standard deviation  
        
          
    }

    public double ret_mean(){

        return mean;
    }

    public double ret_deviation(){

        return deviation;
    }


    public int[][] scaling(double[][] input_array, int wd, int ht, boolean flag){         // set scaled version of gabor filter in spatial domain

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
