

package Test_Texture;


import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import flanagan.complex.*;



public class GaborFilter {


    private double phase_offset = 0;
    private double aspect_ratio = 1;
    private double bandwidth = .5;

    private int width = 0;
    private int height = 0;

    private double gabor_array[][] = null;
    private Complex output_array[][] = null;             // frequency domain band

    public GaborFilter(int wd, int ht){

        width = wd;
        height = ht;
    }

    public void set_filter(double wavelength, double orientation){

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

        //  *************************  Fourier Transform of Gabor Filter  ****************************  //

        Class_fft variable = new Class_fft();
        variable.set_Data(gabor_array, width, height);                  // .set_Data performs fft
        output_array = variable.return_complex_array();
      
    }

 

    public double[][] ret_gabor_array(){
      

        return gabor_array;
    }

    public Complex[][] ret_output_array_complex(){

        return output_array;
    }


}
