

package Test_Texture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;




public class texture_main_single {

    public static void main (String args[]){

        String filename1 = "bld1 (6).jpg";

        BufferedImage imagefile1 = null;

        try{

            imagefile1 = ImageIO.read(new File(filename1));
        }

        catch(IOException e){

            System.out.println(" Input file I/O read ERROR");
            e.printStackTrace();
        }

        Class_Texture var_texture = new Class_Texture();

        var_texture.set_texture_class(imagefile1);
        double[] vector_a = var_texture.ret_feature_vector();


        for (int i = 0; i < vector_a.length; i ++){
            System.out.println(i + "\t" + vector_a[i]);
        }

    }

}
