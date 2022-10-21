package week3;

import java.util.Scanner;

/**  @author 贺亚崇  */
public class Test20210402 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int length = sc.nextInt();

        int radius = sc.nextInt();
        int t = sc.nextInt();
        int[][] data = new int[length+1][length+1];
        int[][] sum = new int[length+1][length+1];
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= length; j++) {
                data[i][j] = sc.nextInt();
                sum[i][j] = data[i][j] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
            }
        }
        int result = 0;
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= length; j++) {
                int leftY =Math.max(0,i-radius-1);
                int rightY =Math.min(length,i+radius);
                int leftX =Math.max(0,j-radius-1);
                int rightX =Math.min(length,j+radius);
                double average =sum[rightX][rightY] - sum[rightX][leftY] - sum[leftX][rightY] + sum[leftX][leftY];
                double n = (rightX - leftX) * (rightY - leftY);
                average /=n;
                if (average <= t) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}
