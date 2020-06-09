

package Distances;

import Test_Color.*;
import java.math.*;

public class Crosscorrelation_neg {

   private double arr[];        //  query image histogram
   private double brr[];        //  comparing image histogram
   private double drr[];        //  difference
   private double err[];        //  matrix multipication
   private double matrixA[][];

   private int arrlen ;

   public Crosscorrelation_neg(double ar1[]){

       arr = new double[arrlen = ar1.length];
       arr = ar1;
       
       matrixA = new double[arrlen][arrlen];
       brr = new double[arrlen];
       drr = new double[arrlen];

       for(int i=0;i<arrlen;i++){
           for(int j=0;j<arrlen;j++){

               matrixA[i][j] = 1 - Math.abs((double)(i-j))/(arrlen-1);   //**********************
               System.out.printf("\t" + matrixA[i][j]);
               }
           System.out.println("\n");
           }

      int sum1 = 0 ;                          //       normalize arr

      
      for(int i=0;i<arrlen;i++)
           sum1 += arr[i];
      for(int i=0;i<arrlen;i++)
           arr[i] /= sum1;

       System.out.println("*************\n\n***********\n");



   };
   
   public double crdistance (double array[]){

       
       double distance = 0;

       if(array.length==arrlen){                          // begin if 1
       brr = array;

       //normalize();

       int sum = 0;
       for(int i=0;i<arrlen;i++)
           sum += brr[i];

       for(int i=0;i<arrlen;i++)
           brr[i] /= sum;

        for(int i=0;i<arrlen;i++){
           drr[i]= (arr[i]-brr[i]);            // DIFFERENCE     *********************************
           System.out.println(drr[i]+ "\n");
           }

        distance = matrix_multiply();
        }                                                    // end if 1

       else{

           System.out.println("  Error :  Unequal  BINCOUNT \n ");
       }

       
       return distance;

   }

   public double intrdistance(double array[]){

       if(array.length == arrlen){

           double sum1 = 0; double sum2 = 0; double sum3=0;

           for(int i=0 ; i<arrlen ; i++){

               sum1 += ((arr[i]<=array[i]) ? arr[i]: array[i] );
               sum2 += arr[i];
               sum3 += array[i];
               
           }

           return((sum2 <= sum3) ? (sum1/sum2) : (sum1/sum3));

                 
       }

       else return -1;




       }

   private void normalize(){

       int sum = 0;
       for(int i=0;i<arrlen;i++)
           sum += brr[i];

       for(int i=0;i<arrlen;i++)
           brr[i] /= sum;
       
   }

   private double matrix_multiply(){

       err = new double[arrlen];

       double tempsum;

       for(int i=0;i<arrlen;i++){
           tempsum = 0;
           for(int j=0;j<arrlen;j++){

               tempsum += matrixA[i][j]*drr[j];

           }
           err[i] = tempsum;
       }

       tempsum = 0;

       for(int i=0;i<arrlen;i++){

               tempsum += drr[i]*err[i];
       }

       return tempsum;
   }


}
