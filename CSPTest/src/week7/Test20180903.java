package week7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
class Node{
    String id;
    String label;
    int level;
    int father;

    public Node(){
        id="";
        label="";
    }
}

/**
 * @author 教徒
 */
public  class Test20180903 {
    public static String s="#";
    public static  Node[] tree=new Node[110];
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            tree[i]=new Node();
            String temp=sc.nextLine();
            divide(temp,i);
        }
        ArrayList<Integer> answer=new ArrayList<>();
        LinkedList<Integer> answer2=new LinkedList<>();
        //改用hashmap
        for (int i = 0; i < m; i++) {
            answer.clear();
            answer2.clear();
            String temp=sc.nextLine();
            //分割选择的元素
            String [] finder=temp.split(" ");
            //先找到所有符合最后一个条件的
            for (int j = 0; j < n; j++) {
                int k=finder.length-1;
                if(finder[k].contains("#")){
                    if(tree[j].id.equals(finder[k])){
                        answer.add(j);
                        answer2.addLast(j);
                    }
                }else{
                    if(tree[j].label.equalsIgnoreCase(finder[k])){
                        answer.add(j);
                        answer2.addLast(j);
                    }
                }
            }
            int answerSize=answer.size();
            int gap=0;
            for (int j = 0; j < answerSize; j++) {
                int k=finder.length-2;
                int flag=0;
                int fa=tree[answer.get(j)].father;
                while(k>=0){
                    if(fa==-1) {
                        flag = -1;
                        break;
                    }
                    if(finder[k].contains("#")){
                        if(tree[fa].id.equals(finder[k])) {
                            k--;
                        }
                    }else{
                        if(tree[fa].label.equalsIgnoreCase(finder[k])) {
                            k--;
                        }
                    }
                    fa=tree[fa].father;
                }
                if(flag!=0){
                    answer2.remove(j-gap);
                    gap++;
                }
            }
            System.out.print(answer2.size());
            if(answer2.size()!=0){
                System.out.print(" ");
                for (Integer integer : answer2) {
                    System.out.print(integer + 1);
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    public static void divide(String temp, int i){
        int len=temp.length();
        int j;
        //确定level
        for (j = 0; j < len; j++) {
            if (temp.charAt(j) == '.') {
                j++;
            } else {
                tree[i].level = j / 2;
                break;
            }
        }
        //确认标签和id
        if(temp.contains(s)){
            tree[i].label =temp.substring(j,temp.indexOf("#")-1);
            tree[i].id=temp.substring(temp.indexOf("#"));
        }else{
            tree[i].label=temp.substring(j);
        }
        //父亲是谁
        if(i==0){
            tree[i].father=-1;
        }else{
            for (int k = i-1; k >= 0 ; k--) {
                if(tree[k].level<tree[i].level){
                    tree[i].father=k;
                    break;
                }
            }
        }
    }
}

