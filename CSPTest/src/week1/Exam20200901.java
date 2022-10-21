package week1;

import java.util.Scanner;

/**
 * @author 教徒
 */
public class Exam20200901 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int x=sc.nextInt();
        int y=sc.nextInt();
        int[][] position=new int[n+1][2];
        int[] distance=new int[n+1];
        for(int i=1;i<=n;i++){
            position[i][0]=sc.nextInt();
            position[i][1]=sc.nextInt();
            distance[i]=(position[i][0]-x)*(position[i][0]-x)+(position[i][1]-y)*(position[i][1]-y);
        }
        int min,index=0;
        int three=3;
        for(int j=0;j<three;j++){
            min=-1;
            for(int i=1;i<=n;i++){
                if(min==-1 && distance[i]>=0){
                    min=distance[i];
                    index=i;
                    distance[i]=-1;
                }

                if(distance[i]<min && distance[i]>=0){
                    distance[index] = min;
                    min = distance[i];
                    index = i;
                    distance[i] = -1;
                }
            }
            System.out.println(index);
        }


    }
}
