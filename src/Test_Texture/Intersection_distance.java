
package Test_Texture;

public class Intersection_distance {
    
    private double[] array_a = null;
    private int arrlen = 0;

    public Intersection_distance(double[] arr){

        array_a = arr;
        arrlen = array_a.length;
    }

       public double get_distance(double array[]){

       if(array.length == arrlen){

           double sum1 = 0; double sum2 = 0; double sum3=0;

           for(int i=0 ; i<arrlen ; i++){

               sum1 += ((array_a[i]<=array[i]) ? array_a[i]: array[i] );
               sum2 += array_a[i];
               sum3 += array[i];

           }

           return((sum2 <= sum3) ? (sum1/sum2) : (sum1/sum3));


       }

       else return -1;




       }

}
