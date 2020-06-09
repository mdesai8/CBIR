//
//package Test_Color;
//
//
//public class Test_cl_main {
//
//    public static void main( String args[]){
//
//        Class_Color var = new Class_Color("img.jpg");
//        Class_Color var2 = new Class_Color("paat.jpg");
//
//        int lengt = (var.ret_histogram()).length;
//        double arr[] = new double[lengt];
//        double brr[] = new double[lengt];
//
//
//        arr = var.ret_histogram();
//        brr = var2.ret_histogram();
//
//        for(int i=0;i<lengt;i++)
//            System.out.printf("*************** "+ arr[i] + "\t"+ brr[i] + "\n");
//
//        Crosscorrelation_neg ccr = new Crosscorrelation_neg(arr);
//        double intr_distance = ccr.intrdistance(brr);
//        double cross_distance = ccr.crdistance(brr);
//
//        System.out.println("Intr Distance: " + intr_distance + "\t Cross Distance: " + cross_distance);
//
//
//    }
//
//}
