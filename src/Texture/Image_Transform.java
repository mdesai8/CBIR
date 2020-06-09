

package Texture;





import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

import java.awt.image.WritableRaster;

import flanagan.complex.*;





public class Image_Transform {

    private String imgname = null;
    int width;
    int height;
    private Complex output_array[][] = null;
    private double input_array[][] = null;             // input image array
    private double padded_array[][] = null;

    public int n_width = 0;
    public int n_height = 0;


    public BufferedImage img;
    BufferedImage binimage;
    BufferedImage output_image;                // for testing

    public Image_Transform(String name){

        imgname =  name;

        try{
        img = (BufferedImage)ImageIO.read(new File(imgname));
        }
        catch(IOException ie){

            System.out.println(" IO Error : 1");
            ie.printStackTrace();
        }

        width = img.getWidth();
        height = img.getHeight();

        input_array = new double[width][height];

        System.out.println("//   *******   Constructor Called    *******   //");

        texture_extraction();


    }

    public Image_Transform(BufferedImage bim){

        img = bim;
        width = img.getWidth();
        height = img.getHeight();
        input_array = new double[width][height];

        texture_extraction();


    }

    private void texture_extraction(){


        doGrayScaleTransform();                                    // fetch value at input_array

        System.out.println("//   *******   Image Binarized    *******   //");
        
        //**************************      Fourier transform of input image      ******************//
//        Class_fft myvar = new Class_fft();
//
//        myvar.set_Data(input_array, width, height); //*             find new height and width for zero padding (temporary fix )
//                                                    //*
//        n_width = myvar.new_width;                  //*
//        n_height = myvar.new_height;                //*

        n_width = pad_len(width);
        n_height = pad_len(height);
        
        zeropadding();                                                                   // zero padding

        Class_fft myvar2 = new Class_fft();
        myvar2.set_Data(padded_array, n_width, n_height);

         output_array=  myvar2.return_complex_array();

        System.out.println("//   *******   Image Transformed    *******   //");

        double[][] tmp = myvar2.return_magnitude_array();

        n_width = myvar2.new_width;
        n_height = myvar2.new_height;



        output_image = new BufferedImage(myvar2.new_width,myvar2.new_height,BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster wraster = output_image.getRaster();


        double[][] temp = scaling(tmp,n_width,n_height);
        //**************************** Set Output Image    ****************************//

        for(int i = 0; i < n_width ; i++)
            for (int j = 0; j < n_height ; j++){
                wraster.setSample(i, j, 0, temp[i][j]);
                }

     
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

    public BufferedImage ret_output_image(){

        return output_image;
    }


   public void doGrayScaleTransform(){

        binimage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster tempraster = binimage.getRaster();

        int alpha, r, g, b ;
        int value ;
        
        //System.out.println("//   *******   Inside doGrayScaleTransform    *******   //");

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
                       tempraster.setSample(i, j, 0, value);

                   }



    }

   private int pad_len (double x){

       int n = 0;
       while(Math.pow(2, n)<= x)
           n++;

       return (int)(Math.pow(2, n));
   }


    double[][] scaling(double[][] input_array, int wd, int ht){         // set scaled version of gabor filter in spatial domain

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
