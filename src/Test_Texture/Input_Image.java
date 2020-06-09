

package Test_Texture;





import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

import java.awt.image.WritableRaster;

import flanagan.complex.*;





public class Input_Image {

    private int width;
    private int height;
    private Complex output_array[][] = null;
    private double input_array[][] = null;             // input image array
    private double padded_array[][] = null;

    public int n_width = 0;
    public int n_height = 0;

    private BufferedImage img;

    public Input_Image(BufferedImage bim){

        img = bim;
        width = img.getWidth();
        height = img.getHeight();
        input_array = new double[width][height];

        texture_extraction();


    }

    private void texture_extraction(){


        doGrayScaleTransform();                                    // fetch value at "input_array"

        System.out.println("//   *******   Image Binarized    *******   //");
        
        //**************************      Fourier transform of input image      ******************//

        n_width = pad_len(width);                                                       // new width to pad the input
        n_height = pad_len(height);                                                     // new height to pad the input
        
        zeropadding();                                                                   // zero padding done on "padded_array"

        Class_fft myvar2 = new Class_fft();
        myvar2.set_Data(padded_array, n_width, n_height);

        output_array=  myvar2.return_complex_array();                                    // FT of image at "output_array"

        System.out.println("//   *******   Image Transformed    *******   //");
     
    }

    private void zeropadding(){

        padded_array = new double[n_width][n_height];

        for(int i =0;i<n_width;i++)
            for(int j =0;j<n_height;j++){

               padded_array[i][j] = 255;
            }

        int x_diff = (n_width - width)/2;
        int y_diff = (n_height - height)/2;

        for(int i = 0; i<width;i++)
            for(int j=0; j<height; j++){

                padded_array[x_diff + i][y_diff + j] = input_array[i][j];
            }
    }

    public Complex[][] ret_output_array_complex(){

        return output_array;
    }

    private void doGrayScaleTransform(){

        int alpha, r, g, b ;
        int value ;
        
               for( int i = 0 ; i < width ; i++ )
                   for( int j = 0 ; j < height ; j++ )
                       // outer loop -> xcoordinate & inner -> y-coordinate
                   {
                       value = img.getRGB( i, j ) ; //store value

                       alpha =get_alpha( value ) ;

                       r = get_red ( value ) ;
                       g = get_green ( value ) ;
                       b = get_blue ( value ) ;



                       
                       value = (r + g + b)/3 ;
                       input_array[i][j] = value*Math.pow(-1, i+j);

                   }



    }

   private int pad_len (double x){

       int n = 0;
       while(Math.pow(2, n)<= x)
           n++;

       return (int)(Math.pow(2, n));
   }


   private double[][] scaling(double[][] input_array, int wd, int ht){         // set scaled version of gabor filter in spatial domain

        double max = 0;
        double min = 0;

        double [][] temp_array = new double[wd][ht];

        for(int i=0; i< wd; i++)
            for(int j=0; j< ht; j++){

                if(max < input_array[i][j]) max = input_array[i][j];
                if(min > input_array[i][j]) min = input_array[i][j];


            }

        double diff = max - min;
        for(int i=0; i< wd; i++)
            for(int j=0; j< ht; j++){

                temp_array[i][j] = (input_array[i][j]-min)/(diff)*255;

            }
        return temp_array;

    }


   private static int create_rgb(int alpha, int r, int g, int b)
   {
       int rgb = (alpha << 24) + (r << 16) + (g << 8) + b ;
       return rgb ;
   }



   private static int get_alpha(int rgb)
   {
       return (rgb >> 24) & 0xFF ;
   }


   private static int get_red(int rgb)
   {
       return (rgb >> 16) & 0xFF;
   }


   private static int get_green(int rgb)
   {
       return (rgb >> 8) & 0xFF;
   }


   private static int get_blue(int rgb)
   {
       return rgb & 0xFF;
   }

}
