package week4;

import java.util.LinkedList;
import java.util.Scanner;

/** @author 贺亚崇 */
public class Test20220603 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int q=sc.nextInt();
        LinkedList<Role> list=new LinkedList<>();
        for (int i = 0; i < n; i++) {
            Role temp=new Role();
            temp.name=sc.next();
            temp.op(sc.nextInt(),sc);
            temp.rsT(sc.nextInt(),sc);
            temp.rs(sc.nextInt(),sc);
            list.addFirst(temp);
        }
        for (int i = 0; i < m; i++) {
            String role=sc.next();
            for (int i1 = 0; i1 < n; i1++) {
                if(role.equals(list.get(i1).name)){
                    list.get(i1).related(sc);
                }
            }
        }
        for (int i = 0; i < q; i++) {
            int flag=0;
            String name=sc.next();
            LinkedList<String> inGroup=new LinkedList<>();
            int ng=sc.nextInt();
            for (int i1 = 0; i1 < ng; i1++) {
                inGroup.addFirst(sc.next());
            }
            String operator=sc.next();
            String resourceType=sc.next();
            String resource=sc.next();
            OUT:
            for (Role role : list) {
                for (String s : inGroup) {
                    if (role.groups.contains(s) || role.users.contains(name)) {
                        if (role.exitOperator(operator) || role.exitOperator("*")) {
                            if (role.exitResourceType(resourceType) || role.exitResourceType("*")) {
                                if (role.exitResource(resource) || role.resource.isEmpty()) {
                                    flag = 1;
                                    break OUT;
                                }
                            }
                        }
                    }
                }
            }
            System.out.println(flag);
        }
    }
}

class Role{
    String name;
    LinkedList<String> operator=new LinkedList<>();
    LinkedList<String> resourceType=new LinkedList<>();
    LinkedList<String> resource=new LinkedList<>();
    LinkedList<String> users=new LinkedList<>();
    LinkedList<String> groups=new LinkedList<>();

    public void  op(int nv,Scanner sc) {
        if(nv==0){
            return;
        }

        for (int i = 0; i < nv; i++) {
            String temp=sc.next();
            operator.addFirst(temp);
        }
    }
    public boolean exitOperator(String op){
        return operator.contains(op);
    }
    public void rsT(int no,Scanner sc){
        if(no==0){
            return;
        }
        for (int i = 0; i < no; i++) {
            resourceType.addFirst(sc.next());
        }
    }
    public boolean exitResourceType(String rst){
        return resourceType.contains(rst);
    }
    public void rs(int nn,Scanner sc){
        if(nn==0){
            return;
        }
        for (int i = 0; i < nn; i++) {
            resource.add(sc.next());
        }
    }
    public boolean exitResource(String rs){
        return resource.contains(rs);
    }
    public void related(Scanner sc){
        int ns=sc.nextInt();
        for (int i = 0; i < ns; i++) {
            if("u".equals(sc.next())){
                users.addFirst(sc.next());
            }else {
                groups.addFirst(sc.next());
            }
        }
    }

}
