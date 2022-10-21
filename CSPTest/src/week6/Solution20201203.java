package week6;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

/**
 * @author 教徒
 */
class FileNode{
    HashMap<String, Integer> branch;
    int isLeaf;
    long ldd,lrd;
    long ld,lr;
    long val;
    int fa;
    FileNode(){
        branch = new HashMap<>() ;
    }
}

public class Solution20201203{
    static FileNode [] tr=new FileNode[10000+1];
    static int vex;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        tr[0]=new FileNode();
        tr[0].fa=-1;
        tr[0].isLeaf=0;
        tr[0].ldd=0;
        tr[0].lrd=0;
        for (int i = 0; i < n; i++) {
            boolean flag=true;
            String op=sc.next();
            String path;
            long ld,lr,size;
            switch(op){
                case "C":
                    path=sc.next();
                    size=sc.nextInt();
                    flag=insert(path,size);
                    break;
                case "R":
                    path=sc.next();
                    remove(path);
                    break;
                case "Q":
                    path=sc.next();
                    ld=sc.nextInt();
                    lr=sc.nextInt();
                    flag=set(path,ld,lr);
                    break;
                default:
                    break;
            }
            if(flag){
                System.out.println("Y");
            } else{
                System.out.println("N");
            }
        }
    }
    static class pair{
        int a;
        String b;

        pair(int x,String y){
            a=x;b=y;
        }
    }
    static Vector<pair> v= new Vector<>();
    public static boolean insert(String path, long size){
            v.clear();
        int num=1,fa=0,init=vex,len= path.length();
        while(num<len){

            String temp="";
            int i;
            for(i=num;i<len&path.charAt(i)!='/';i++){
                temp+=path.charAt(i);
            }
            num=i+1;

            if(tr[fa].branch.containsKey(temp)){
                if(tr[tr[fa].branch.get(temp)].isLeaf==1&num<len){
                    vex=init;
                    del();
                    return false;
                }
            }
            int id,flag=0;
            if(tr[fa].branch.containsKey(temp)){
                id=tr[fa].branch.get(temp);
                flag=1;
            }else{
                id=++vex;
                tr[vex]=new FileNode();
                tr[vex].isLeaf=0;
                tr[vex].fa=fa;
                tr[vex].ldd=tr[vex].lrd=0;
                tr[fa].branch.put(temp,vex);
                v.add(new pair(fa,temp));
            }
            if(num<len){
                fa=id;
            }
            if(num>=len){
                long extra;
                if(flag==1){
                    if(tr[id].isLeaf==0){
                        vex=init;
                        del();
                        return false;
                    }
                    extra=size-tr[id].val;
                }else{
                    extra=size;
                }
                if(tr[fa].ld+extra>tr[fa].ldd & tr[fa].ldd!=0){
                    vex=init;
                    del();
                    return false;
                }
                tr[id].val=size;
                tr[id].lr=size;
                tr[id].isLeaf=1;
                for(int r=fa;r!=-1;r=tr[r].fa){
                    if(tr[r].lr+extra>tr[r].lrd & tr[r].lrd!=0){
                        vex=init;
                        del();
                        return false;
                    }
                }
                for(int r=fa;r!=-1;r=tr[r].fa){
                    tr[r].lr+=extra;
                }
                tr[fa].ld+=extra;
            }
        }
        return true;
    }
    public static void del(){
        for (int i = 0; i < v.size(); i++) {
            int a=v.get(i).a;
            String b=v.get(i).b;
            tr[a].branch.remove(b);
        }
    }

    public static void remove(String path){
        int num=1,father=0,len=path.length();
        while(num<len){
            String temp="";
            int i;
            for(i=num;i<len&path.charAt(i)!='/';i++){
                temp+=path.charAt(i);
            }
            num=i+1;

            if(!tr[father].branch.containsKey(temp)){
                return;
            }
            int id=tr[father].branch.get(temp);
            if(num<len){
                father=id;
            }
            if(num<len & tr[id].isLeaf==1){
                return;
            }
            if(num>=len){
                tr[father].branch.remove(temp);
                for(int r=father;r!=-1;r=tr[r].fa){
                    tr[r].lr-=tr[id].lr;
                }
                if(tr[id].isLeaf==1){
                    tr[father].ld-=tr[id].lr;
                }
            }
        }
    }

    public static boolean set(String path,long ld,long lr){
        if(path.length()==1){
            int id=0;
            if((ld<tr[id].ld & ld!=0) || (lr<tr[id].lr & lr!=0)){
                return false;
            }
            tr[id].lrd=lr;
            tr[id].ldd=ld;
            return true;
        }
        int num=1,father=0,len=path.length();
        while(num<len){
            String temp="";
            int i;
            for(i=num;i<len&path.charAt(i)!='/';i++){
                temp+=path.charAt(i);
            }
            num=i+1;

            if(!tr[father].branch.containsKey(temp)){
                return false;
            }
            int id=tr[father].branch.get(temp);
            father=id;
            if(tr[id].isLeaf==1){
                return false;
            }
            if(num>=len){
                if ((ld < tr[id].ld & ld!=0) || (lr < tr[id].lr && lr!=0)){
                    return false;
                }else{
                    tr[id].lrd=lr;
                    tr[id].ldd=ld;
                    return true;
                }

            }
        }
        return true;
    }

}


