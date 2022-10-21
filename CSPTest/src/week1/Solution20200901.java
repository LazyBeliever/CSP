package week1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 教徒
 */
public class Solution20200901 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //n表示检测点总数
        int n = input.nextInt();
        //X, Y 表示市民所在位置
        int x = input.nextInt();
        int y = input.nextInt();

        //保存检测点位置
        int[][] posArr = new int[n][3];
        for (int i = 0; i < n; i++) {
            posArr[i][0] = input.nextInt();
            posArr[i][1] = input.nextInt();
            //保存编号
            posArr[i][2] = i + 1;
        }
        sort(posArr, x, y);

        int three=3;
        for (int i = 0; i < three; i++) {
            System.out.println(posArr[i][2]);
        }

        input.close();
    }

    //根据题目要求，进行排序

    public static void sort(int[][] arr, int x, int y) {
        Arrays.sort(arr, ((o1, o2) -> {
            int dis1 = countDis(x, y, o1[0], o1[1]);
            int dis2 = countDis(x, y, o2[0], o2[1]);

            return dis1 == dis2 ? o1[2] - o2[2] : dis1 - dis2;
        }));
    }

    //为了方便，按照平方计算的

    private static int countDis(int x, int y, int posX, int posY) {
        return (posX - x) * (posX - x) + (posY - y) * (posY - y);
    }
}