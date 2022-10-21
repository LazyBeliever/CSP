package week2;

import java.util.Scanner;

/**
 * @author 教徒
 */
public class Exam20210901 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] b=new int[n];
        int sumMax=0,sumMin=0;
        for(int i=0;i<n;i++){
            // max
            b[i]=sc.nextInt();
            sumMax+=b[i];
        }
        sumMin+=b[0];
        for(int i=1;i<n;i++){
            // min
            sumMin+=b[i]==b[i-1]?0:b[i];
        }
        System.out.println(sumMax);
        System.out.println(sumMin);
    }
}
