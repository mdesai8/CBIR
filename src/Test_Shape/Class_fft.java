

package Test_Shape;


import Test_Texture.*;
import flanagan.math.*;
import flanagan.complex.Complex;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class Class_fft {

private double[][] real = null;
private double[][] imaginary = null;
private double[][] magnitude = null;
private Complex[][] complex_array = null;
public int new_width=0;
public int new_height=0;

public Class_fft(){};

public void set_Data(double[][] arr, int wd, int ht){

    ///////////// include zero padding

    double[] temp = new double[ht];
    Complex[] temp2 = null;


    Complex[][] intermediate =  null;



    FourierTransform var = new FourierTransform();

    boolean flag = true;

    for(int i=0; i< wd; i++){

        for(int j=0; j< ht; j++ ){

            temp[j] = arr[i][j] ;
            //System.out.println(j + "\t" + temp[j]);
            }

        var. setData(temp);
        var.transform();

        temp2 = var.getTransformedDataAsComplex();

         if(flag){
            new_height = temp2.length;
            intermediate = new Complex[wd][new_height];      //  instantiate intermediate array only once
            flag = false;
        }

        for(int j= 0; j< new_height;j++){
        intermediate[i][j] = temp2[j];                        //  2*j returns real values only
        //System.out.println(j + "\t" +temp2[2*j]);
        }
    }

    Complex[] ctemp = new Complex[wd];
    flag = true;

    for(int j = 0; j < new_height ; j++){

        for(int i = 0; i < wd; i++){
            ctemp[i] = intermediate[i][j];
            //System.out.println(temp[i]);
        }

        var.setData(ctemp);
        var.transform();

        temp2 = var.getTransformedDataAsComplex();

        if(flag){
            new_width = temp2.length;
            complex_array = new Complex[new_width][new_height];
            flag = false;
        }

        for(int i = 0; i < new_width; i++)
            complex_array[i][j] = temp2[i];
    }

    real = new double[new_width][new_height];
    imaginary = new double[new_width][new_height];
    magnitude = new double[new_width][new_height];

    for(int i = 0; i < new_width; i++)
        for(int j = 0; j < new_height; j++){

            real[i][j] = complex_array[i][j].getReal();
            imaginary[i][j] = complex_array[i][j].getImag();

            magnitude[i][j] = Math.sqrt(Math.pow(real[i][j],2) + Math.pow(imaginary[i][j], 2));



        }


//System.out.println("Class_fft\t" + "width : " + new_width + "\t height : "+ new_height);

}

public double[][] return_magnitude_array(){

    return magnitude;
}

public Complex[][] return_complex_array(){

    return complex_array;
}

public BufferedImage return_magnitude_image(){

    BufferedImage mag_image = new BufferedImage (new_width,new_height,BufferedImage.TYPE_BYTE_GRAY);
    WritableRaster wraster = mag_image.getRaster();

    double min =0, max =0, tmp = 0;
    for(int i =0; i< new_width;i++)
        for(int j=0; j< new_height; j++ ){
            tmp = magnitude[i][j];
            min = (tmp<=min ? tmp : min);
            max = (tmp>=max ? tmp : max);
        }

    tmp = max - min;
    
    for(int i =0; i< new_width;i++)
        for(int j=0; j< new_height; j++ ){

            wraster.setSample(i, j, 0,(magnitude[i][j]-min)*255/tmp );
        }

    return mag_image;


}





}
