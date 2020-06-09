

package Texture;


import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import flanagan.complex.*;



public class GaborFilter {

    private double gabor_array[][] = null;
    private double scaled_array[][] = null;               // spatial image for display
    private Complex output_array[][] = null;             // frequency domain band
    private double wavelength;
    private double orientation;
    private double phase_offset = 0;
    private double aspect_ratio = 1;
    private double bandwidth = .5;

    private int width = 0;
    private int height = 0;

    public int n_width = 0;
    public int n_height = 0;

    private BufferedImage gabor_image;
    private BufferedImage output_image;

    //public GaborFilter(){}
    public GaborFilter(double wav, double ori,int wd, int ht){

    //public void set_gabor_filter(double wav, double ori,int wd, int ht){

        wavelength = wav;
        orientation = ori;

        width = wd;
        height = ht;

        double temp1=0;
        double temp2=0;
        double temp3=0;
        double temp4=0;


        gabor_array = new double[width][height];

        int tw = width/2;
        int th = height/2;
        for(int x=-tw; x< tw-1; x++)
            for(int y=-th; y<th-1; y++){
                temp1 = (x)*Math.cos(orientation) + (y)*Math.sin(orientation);
                temp2 = (y)*Math.cos(orientation) - (x)*Math.sin(orientation);

                temp3 = Math.exp(-(temp1*temp1 + Math.pow(temp2*aspect_ratio,2))/(2*Math.pow(wavelength*bandwidth,2)));
                temp4 =  temp3 * Math.cos(2*3.146*temp1/wavelength + phase_offset);
                gabor_array[x+tw][y+th] = temp4*Math.pow(-1, x+tw+y+th);

            }

        scaled_array = scaling(gabor_array, width, height, false);                          //  Image scaling for Display only

        gabor_image = new BufferedImage(width,height,BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster tempraster = gabor_image.getRaster();

        for(int i = 0; i < width ; i++)
            for (int j = 0; j < height ; j++){
                tempraster.setSample(i, j, 0, scaled_array[i][j]);
                }


        //  *************************  Fourier Transform of Gabor Filter  ****************************  //


        Class_fft variable = new Class_fft();
        variable.set_Data(gabor_array, width, height);                  // .set_Data performs fft
        output_array = variable.return_complex_array();


        //********************************  Construct Image  ***********************************//


        double[][] tmp = variable.return_magnitude_array();

        n_width = variable.new_width;
        n_height = variable.new_height;

        double [][] array = scaling(tmp,variable.new_width,variable.new_height,false);

        output_image = new BufferedImage(variable.new_width,variable.new_height,BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster wraster = output_image.getRaster();


        for(int i = 0; i < variable.new_width ; i++)
            for (int j = 0; j < variable.new_height ; j++){
                wraster.setSample(i, j, 0,array[i][j]);
                }

      
         //*******************************************************************//


    }



    double[][] scaling(double[][] input_array, int wd, int ht, boolean flag){         // set scaled version of gabor filter in spatial domain

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
                if (flag == true)
                    System.out.println(i+ "\t"+j+"\t" + temp_array[i][j]);

            }
        return temp_array;

    }
  

    public double[][] ret_gabor_array(){
      

        return gabor_array;
    }

    public Complex[][] ret_output_array_complex(){

        return output_array;
    }

    public BufferedImage ret_gabor_image(){

        return gabor_image;
    }
    
    public BufferedImage ret_output_image(){
    
    return output_image;
        
    }


}
