

package Test_Texture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class texture_main_comparison {

    public static void main (String args[]){

        String filename1 = "bld1 (4).jpg";
        String filename2 = "bld1 (3).jpg";
        String filename3 = "bld1 (5).jpg";

        BufferedImage imagefile1 = null;
        BufferedImage imagefile2 = null;
        BufferedImage imagefile3 = null;

        try{

            imagefile1 = ImageIO.read(new File(filename1));
            imagefile2 = ImageIO.read(new File(filename2));
            imagefile3 = ImageIO.read(new File(filename3));
        }

        catch(IOException e){

            System.out.println(" Input file I/O read ERROR");
            e.printStackTrace();
        }

        Class_Texture var_texture = new Class_Texture();

        var_texture.set_texture_class(imagefile1);
        double[] vector_a = var_texture.ret_feature_vector();
        
        var_texture.set_texture_class(imagefile2);
        double[] vector_b = var_texture.ret_feature_vector();

        var_texture.set_texture_class(imagefile3);
        double[] vector_c = var_texture.ret_feature_vector();

        for (int i = 0; i < vector_a.length; i ++){

            System.out.println(i + "\t" + vector_a[i] + "\t" + vector_b[i]);
        }
        Intersection_distance isdist = new Intersection_distance(vector_a);
        Euclidean_distance eudist = new Euclidean_distance(vector_a);

        System.out.println("Euclidean :\t" + eudist.get_distance(vector_b) + "\t" + eudist.get_distance(vector_c));
        System.out.println("Intersect :\t" + isdist.get_distance(vector_b) + "\t" + isdist.get_distance(vector_c));
             
    }

}
