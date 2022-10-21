package week2;

import java.util.Scanner;

/**
 * @author 教徒
 */
public class Exam20211201 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int[] arr=new int[n+1];
        arr[0]=0;
        for(int i=1;i<=n;i++){
            arr[i]=sc.nextInt();
        }
        int sum=0;
        for(int i=0;i<m;i++){
            for(int j=1;j<=n;j++){
                if(i>=arr[n]){
                    sum+=n;
                    break;
                }
                if(i<arr[j]) {
                    sum += j - 1;
                    break;
                }
            }
        }
        System.out.println(sum);
    }
}
