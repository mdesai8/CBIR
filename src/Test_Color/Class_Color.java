

package Test_Color;

import java.awt.image.BufferedImage ;



public class Class_Color 
{
    BufferedImage img ; 

    int[][][] count = new int[10][4][4] ;


    public Class_Color(){}

    public void set_color_class(BufferedImage image)
    {         

        img = image;

        drawColorHistogram() ;
        

    }


    private void drawColorHistogram()
    {
        int w1,h1 ; //,h2,w2 ;

        w1 = img.getWidth() ;
        h1 = img.getHeight() ; 
             

        //perform intensity assignment

        int value ;
        double alpha, r, g, b , min, sum; //[][] = new int[w1][h1] ;
        double h, s, v ;
        double temp ;

        int hh, ss, vv ;
        

        System.out.println( (Math.acos(0.5)* 180) / 3.14 ) ;
        System.out.println( (Math.acos(1)* 180) / 3.14 ) ;

        for( int i = 0 ; i < w1 ; i++ )
                   for( int j = 0 ; j < h1 ; j++ )
                   {
                       // outer loop -> xcoordinate & inner -> y-coordinate
                       value = img.getRGB( i, j ) ; //store value

                       //alpha =get_alpha( value ) ;
                       // alpha controls image transparency
                       r = get_red ( value ) / 255.0 ;
                       g = get_green ( value ) / 255.0 ;
                       b = get_blue ( value ) / 255.0 ;
                       
                       //System.out.println("red: "+ r +" green: "+ g +" blue: "+b ) ;
                       //obtained values are between 0-1
                       
                       
                       //obtain H,S & V from R, G, & B values
                       if(r==g && g==b)
                       {
                           h = 0 ;
                           s = 0 ;
                           v = r ;
                       }
                       
                       else
                       {
                           temp = r*r + g*g + b*b - ( r*g + g*b + r*b ) ;
                           // temp always > 0
                           //if(temp<0)
                               //System.out.println("gud gud 1 ") ;
                           //temp = 2 * Math.sqrt(temp) ;
                           temp = ( 2*r - g - b ) / ( 2 * Math.sqrt(temp) ) ;
                           // temp +ve or -ve
                           //if(temp<0)
                              //System.out.println("gud gud 2 ") ;

                           //System.out.println("temp: "+ temp) ;

                           h = Math.acos(temp) / Math.PI ;
                           //since, H between 0.0-pi
                           //h = (float)( (Math.acos(temp)* 180) / Math.PI) ;

                           //min = Math.min( r, Math.min(g, b) ) ;
                           min = r ;
                           if(min>g)
                               min = g ;
                           if(min>b)
                               min = b ;

                           sum= r+g+b ;
                           s =  1 - (3* min) / sum ;
                           //System.out.println("min= "+ min + "sum = "+ sum );

                           v =  sum /3 ;


                           //System.out.println("H: "+ h +" S: "+ s +" V: "+v ) ;
                           // H, S & V between 0.0-1.0
                       }


                       // HSV -> 10*4*4= 160 quantization bins

                       hh = check_h(h) ;
                       ss = check_s(s) ;
                       vv = check_v(v) ;

                       //System.out.println("H: "+ hh +" S: "+ ss +" V: "+vv ) ;
                       count[hh][ss][vv]++ ; // =   count[hh][ss][vv] + 1 ;

                   }
        sum =0 ;
        for(int i=0;i<10;i++)
            for(int j=0;j<4;j++)
                for(int k=0;k<4;k++)
                {   System.out.println("Count "+ i+" "+j+" "+k+" = " +count[i][j][k] );
                    sum  = sum + count[i][j][k] ;
                }

        System.out.println("sum "+ sum) ;

    }


    private int check_h(double h)
    {
        if(h>=0 && h<0.1)
            return 0 ;
        else if(h>=0.1 && h<0.2)
            return 1 ;
        else if(h>=0.2 && h<0.3)
            return 2 ;
        else if(h>=0.3 && h<0.4)
            return 3 ;
        else if(h>=0.4 && h<0.5)
            return 4 ;
        else if(h>=0.5 && h<0.6)
            return 5 ;
        else if(h>=0.6 && h<0.7)
            return 6 ;
        else if(h>=0.7 && h<0.8)
            return 7 ;
        else if(h>=0.8 && h<0.9)
            return 8 ;
        else //if(h>=0.9 && h<=1.0)
            return 9 ;
        //else
            //return 100 ;
    }


    private int check_s(double s)
    {
        if(s>=0 && s<0.25)
            return 0 ;
        else if(s>=0.25 && s<0.5)
            return 1 ;
        else if(s>=0.5 && s<0.75)
            return 2 ;
        else //if(s>=0.75 && s<=1.0)
            return 3 ;
        //else
            //return 100 ;
    }


    private int check_v(double v)
    {
        if(v>=0 && v<0.25)
            return 0 ;
        else if(v>=0.25 && v<0.5)
            return 1 ;
        else if(v>=0.5 && v<0.75)
            return 2 ;
        else //if(v>=0.75 && v<=1.0)
            return 3 ;
        //else
            //return 100 ;
    }



    //public static int get_alpha(int rgb)
    //{
    //   return (rgb >> 24) & 0xFF ;
    //}


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



    public double[] ret_histogram(){

        double[] array =  new double[10*4*4] ;
        int bincount = 0;

        for(int i=0;i<10;i++)
            for(int j=0;j<4;j++)
                for(int k=0;k<4;k++){

                    array[bincount]= count[i][j][k];
                    bincount++;

                }
        return (array);

    }


}
