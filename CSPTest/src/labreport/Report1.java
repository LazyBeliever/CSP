package labreport;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author 教徒
 * 题目为csp201703-2
 * 思路：因为要多次删除插入，所以采用LinkedList效率更快
 * 通过输入的id获取学生当前的位置pos，然后从列表中删除学生(用linkedlist.remove(object o))
 * 再将学生(id)插入(用linkedlist.add(index, object))到指定的位置(index = pos+change(学生位置的变化))
 *
 */
public class Report1 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        LinkedList<Integer> list=new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.addLast(i+1);
        }
        for (int i = 0; i < m; i++) {
            int id=sc.nextInt();
            //id 为学号，通过学号移动学生
            int change=sc.nextInt();
            //change为位置变化
            int pos=list.indexOf(id);
            //id出队前的的位置为pos
            list.remove((Integer)id);
            //id离开队伍
            list.add(pos+change, id);
            //id进入队伍，进入位置是计算好的(离开前+变动)
            //不考虑位置变化不合法的情况
        }
        for (Integer integer : list) {
            System.out.print(integer + " ");
        }
    }
}
