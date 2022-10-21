package week6;
import java.util.*;
class FileSystem{
    ArrayList<String> son;
    int type;
    //文件类型

    long fileSize;
    //若是普通文件，其内存大小

    long fileQuota,sonQuota;
    //   目录配额，  后代配额

    long exitFile,exitSon;
    //已经有的目录配额，已经占用的后代配额

    public FileSystem(){
        //目录文件
        type=0;
        this.fileQuota=0;
        this.sonQuota=0;
        son= new ArrayList<>();
    }
    public FileSystem(long fileSize){
        //普通文件
        type=-1;
        this.fileSize=fileSize;
    }
}

/**
 * @author 教徒
 */
public class Test20201203 {
    public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    int n=sc.nextInt();
    HashMap<String, FileSystem> sum= new HashMap<>(1);
    sum.put("/",new FileSystem());
        for (int i = 0; i < n; i++) {
            String op=sc.next();
            switch (op) {
                case "C" :{
                    String path1 = sc.next();
                    long size = sc.nextInt();
                    System.out.println(creat(path1, size, sum));
                    break;
                }
                case "R" : {
                    String path2 = sc.next();
                    System.out.println(remove(path2, sum));
                    break;
                }
                case "Q" : {
                    String path3 = sc.next();
                    long fileQuota = sc.nextInt();
                    long sonQuota = sc.nextInt();
                    System.out.println(quota(path3, fileQuota, sonQuota, sum));
                    break;
                }
                default : {
                    break;
                }
            }
        }
    }

    /**
     *创建普通文件
     * @return Y或者N
     */
    public static String creat(String path,long size,HashMap<String, FileSystem> sum){
        List<String> way= new ArrayList<>();
        way.add("/");
        for(int i=1;i<path.length();i++){
            if(path.charAt(i)=='/'){
                way.add(path.substring(0,i));
            }
        }//拿到所有路径文件(不包括创建的普通文件

        for (String temp : way) {
            if (sum.containsKey(temp)) {
                FileSystem temp2 = sum.get(temp);
                if (temp2.sonQuota != 0 && temp2.exitSon + size > temp2.sonQuota) {
                    return "N";
                }
                if(temp2.type == -1){
                    return "N";
                }
            }else{
                break;
            }
        }//所有路径文件的后代配额满足需求且没有普通文件
        FileSystem temp=sum.get(way.get(way.size()-1));
        if(temp!=null){
            if(temp.fileQuota!=0&&temp.exitFile+size>temp.fileQuota){
                return "N";
            }
        }//父目录的孩子配额满足需求
        if(sum.containsKey(path)){
            if(sum.get(path).type==0) {
                return "N";
            }else {
                size = size - sum.get(path).fileSize;
            }
        }//将要创建的文件不存在或者其是普通文件
        for (String s : way) {
            if (!sum.containsKey(s)) {
                sum.put(s,new FileSystem());
            }
        }//创建路径中不存在的目录
        sum.put(path,new FileSystem(size));
        //创建普通文件
        for(String s :way){
            sum.get(s).exitSon+=size;
        }//所有上级目录的exitSon加上创建的新普通文件的大小
        String father=way.get(way.size()-1);
        sum.get(father).exitFile+=size;
        //父目录的exitFile加上创建的普通文件的大小
        sum.get(father).son.add(path);
        return "Y";
    }

    /**
     *移除文件
     * @return Y
     */
    public static String remove(String path,HashMap<String, FileSystem> sum){
        if(!sum.containsKey(path)){
            return "Y";
        }
        List<String> way= new ArrayList<>();
        way.add("/");
        for(int i=1;i<path.length();i++){
            if(path.charAt(i)=='/'){
                way.add(path.substring(0,i));
            }
        }//拿到所有路径文件(不包括删除的文件
        if(sum.get(path).type==-1){
            long size=sum.get(path).fileSize;
            for (String s : way) {
                sum.get(s).exitSon -= size;
            }//将该文件所在的所有上级目录的exitSon减去该普通文件的大小
            String father=way.get(way.size()-1);
            sum.get(father).exitFile-=size;
            sum.get(father).son.remove(path);
            sum.remove(path);
        }else{
            long size=sum.get(path).exitSon;
            for (String s : way) {
                sum.get(s).exitSon -= size;
            }//将该文件所在的所有上级目录的exitSon减去该目录文件的exitSon大小
            String father=way.get(way.size()-1);
            sum.get(father).son.remove(path);
            delete(path,sum);
            sum.remove(path);
        }
        return "Y";
    }
    public static void delete(String path, HashMap<String, FileSystem> sum) {
        FileSystem temp=sum.get(path);
        for (String s : temp.son) {
            sum.remove(s);
        }
    }

    /**
     *设置配额
     * @return Y或者N
     */
    public static String quota(String path,long fileQuota,long sonQuota,HashMap<String, FileSystem> sum){
        if (sum.containsKey(path) && sum.get(path).type != -1) {
            if (sum.get(path).exitFile <= fileQuota || fileQuota == 0) {
                if(sum.get(path).exitSon <= sonQuota || sonQuota == 0){
                    sum.get(path).fileQuota = fileQuota;
                    sum.get(path).sonQuota = sonQuota;
                    return "Y";
                }
            }
        }
        return "N";
    }
}