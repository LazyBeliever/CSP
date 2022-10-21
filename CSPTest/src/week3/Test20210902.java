package week3;
import java.util.Scanner;

/**  @author 贺亚崇  */
public class Test20210902 {
    public static int[] dif = new int[1000001];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] data = new int[n+1];
        for(int i = 1 ;i <= n ; i++) {
            //提前设好a[0] = 0
            data[i] = sc.nextInt();
        }
        for(int i = 1 ;i <= n ; i++) {
            if(data[i-1] < data[i]) {

                add(data[i-1]+1 , data[i]);
            }
        }
        int sum = 0 ,maxS = 0 ,temp=10001;
        for(int i = 1 ; i < temp ; i++) {
            sum += dif[i];
            maxS = Math.max(maxS, sum);
        }
        System.out.println(maxS);
    }

    public static void add(int l , int r) {
        dif[l]++;
        //海面会露出的山峰加一
        dif[r+1]--;
        //海平面没过山峰，要减一
    }
}