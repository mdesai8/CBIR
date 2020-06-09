

package Test_Shape;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Graphics;


public class polarrastermain {

    public static void main(String args[]){

        String filename = "matt.jpg";

        BufferedImage input_image;

        try{

        input_image = ImageIO.read(new File(filename));
        
        plr pr = new plr(input_image);
       // PolarRaster pr = new PolarRaster(input_image);
        //Class_fft fft = new Class_fft();
       // fft.set_Data(pr.retpolararray(),pr.circumference,pr.radius);


    JFrame mainFrame = new JFrame(" MULTIPLY ");
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setSize(1000,400);

       MyPanel panel = new MyPanel();
       panel.loadimage(pr.retpolarimage());
       //panel.loadimage(fft.return_magnitude_image());

       //ClassDilation dilation = new ClassDilation(varreorient.image);
       //panel.loadimage(dilation.retimage());

       panel.repaint();

       mainFrame.add(panel);
       mainFrame.setVisible(true);


        
        }

         catch(IOException ie){

            ie.printStackTrace();
        }

        

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
