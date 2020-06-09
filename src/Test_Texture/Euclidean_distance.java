
package Test_Texture;



public class Euclidean_distance {

    double[] array_a = null;

    public Euclidean_distance( double[] arr){

        array_a = arr;
    }

    public double get_distance (double[] brr){

        double sum =0;

        if(array_a.length == brr.length){
        for(int i=0; i < array_a.length; i++){

            sum += Math.pow(Math.abs(array_a[i] - brr[i]),2);
        }

        return Math.sqrt(sum);

        }

        else return -1;

    }

}
