
package Test_Texture;

import flanagan.complex.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Class_Texture {

    private Complex[][] gabor_array = null;
    private Complex[][] image_array = null;
    private Complex[][] product_array = null;

    private double[] texture_vector = null;

    final int[] wavelength = {3,5,8,10,15,25};
    final double[] orientation = {0, Math.PI/4, Math.PI/2, 3/4*Math.PI};

    private int initial_width = 0;
    private int initial_height  = 0;
    private int final_width = 0;
    private int final_height  = 0;

    public Class_Texture(){}

    public boolean set_texture_class(BufferedImage source_image){


        Input_Image var_image = new Input_Image(source_image);          // image spatial to fourier transformation
        image_array = var_image.ret_output_array_complex();
        
        initial_width = source_image.getWidth();
        initial_height  = source_image.getHeight();
        final_width = var_image.n_width;
        final_height  = var_image.n_height;

        product_array = new Complex[final_width][final_height];

        GaborFilter var_filter = new GaborFilter(final_width, final_height);    // instantiate gabor filter height and width
        
        texture_vector = new double[2*wavelength.length*orientation.length];

        Class_ifft var_ifft = new Class_ifft();
        Reorient var_reorient = new Reorient();     
     
        int count = 0;
        for(int x = 0; x < wavelength.length; x++)
            for(int y= 0; y < orientation.length; y++){

                var_filter.set_filter(wavelength[x], orientation[y]);
                gabor_array = var_filter.ret_output_array_complex();

                for(int i = 0; i < final_width; i++)
                    for (int j = 0; j < final_height; j++){                    
                        product_array[i][j] = Complex.times(image_array[i][j],gabor_array[i][j]);
                    }

                var_ifft.set_Data(product_array, final_width, final_height);
                var_reorient.reorient_function(var_ifft.return_magnitude_array(), final_width, final_height,initial_width-10, initial_height-10 );
                
                texture_vector[count] = var_reorient.ret_mean();
                count++;
                texture_vector[count] = var_reorient.ret_deviation();
                count++;            
                
            }

        return (true);

        
    }


    public double[] ret_feature_vector(){

        return (texture_vector);
    }



}
