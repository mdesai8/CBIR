


package Texture;

import flanagan.complex.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.Graphics;






public class multiply_main {
    

    public static void main( String args[]){


    Complex[][] gabor_array = null;                                          // always odd dimension
    Complex[][] image_array = null;                                          // always even dimension
    Complex[][] result_array = null; // new Complex[256][256];

    int ini_width = 0;
    int ini_height = 0;
    int width = 0;
    int height = 0;

    //   *******************       Source Image      *******************   //

    Image_Transform txtr_image = new Image_Transform("comb.jpg");
    image_array = txtr_image.ret_output_array_complex();              //   unscaled FT band of image

    ini_width = txtr_image.width;
    ini_height = txtr_image.height;
    
    width = txtr_image.n_width;
    height = txtr_image.n_height;

    result_array = new Complex[width][height];

    //   *******************       Gabor Filter      ********************   //
    
    GaborFilter filter = new GaborFilter(5,Math.PI/4,width,height); //Math.PI/2);
    gabor_array = filter.ret_output_array_complex();                   //  unscaled FT band of gabor filter

    System.out.println("Gabor: \t" + filter.n_width + "\t" + filter.n_height);
    System.out.println("Image: \t" + txtr_image.n_width + "\t" + txtr_image.n_height);


    //   ********************       Multiply      ********************   //

    for(int i = 0; i < width; i++)
        for(int j = 0; j < height; j++){
            result_array[i][j] = Complex.times(gabor_array[i][j],image_array[i][j]);
        }

    //   ############################################################################   //


    Class_ifft var = new Class_ifft();
    var.set_Data(result_array,width,height);                                    // do inverse fourier transform

    Reorient rnt = new Reorient();

    //   ************************   Display   ************************   //

    JFrame mainFrame = new JFrame(" MULTIPLY ");
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setSize(535,305);

       MyPanel panel = new MyPanel();
       //panel.loadimage(filtered_image);                                // show final filterd image
       //panel.loadimage(result_ftimage);                              // show spectrum of result image
       //panel.loadimage(filter.ret_output_image());                   // show fft of gabor filter
       //panel.loadimage(filter.ret_gabor_image());                    // show gabor filter
       //panel.loadimage(txtr_image.ret_output_image());               // show fft of image
       //panel.loadimage(var.ret_output_image());
       panel.loadimage(rnt.reorient_function(var.ret_output_image(), ini_width-10, ini_height-10));   //  -5 just to cut out boundry,, temporary fix
       panel.repaint();

       mainFrame.add(panel);
       mainFrame.setVisible(true);

    }

}

 class MyPanel extends JPanel{

    BufferedImage im;

    public void loadimage(BufferedImage i){ this.im = i; }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        g.drawImage(im,0, 0,im.getWidth(),im.getHeight(), this);
        this.setVisible(true);
    }

 }
