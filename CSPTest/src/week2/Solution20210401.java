package week2;

import java.util.Scanner;

/**
 * @author 教徒
 */
public class Solution20210401 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int l = sc.nextInt();
        int [][]a =new int[n][m];
        int []h = new int[l];

        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                a[i][j] = sc.nextInt();
                h[a[i][j]]++;
            }
        }
        for(int i = 0;i<l;i++){
            System.out.print(h[i]+" ");
        }
    }

}