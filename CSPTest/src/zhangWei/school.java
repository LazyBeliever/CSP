package zhangWei;

class Szu{
    Collage[] collages;
    int index;      //记录学院数目
    Szu(){
        collages=new Collage[100];       //其实，为了以防万一，下面添加Collage对象时应有判满操作，
    }
    public void addCollages(String name) {//通过学院名  添加学院成员
        collages[index]=new Collage();  //Collage的构造方法1
        collages[index].setName(name);
        index++;
        /*
        或者，这样写：
        collages[index]=new Collage(name);  Collage的构造方法2
        index++;
         */
    }
    public void addCollages(Collage collage) {//通过学院实例对象  添加学院成员
        collages[index]=new Collage(collage);   //Collage的构造方法3
        index++;
    }
    public void getCollageNames(){
        for (int i = 0; i < index; i++) {
            System.out.print(collages[i].getName()+" ");
        }
    }
    public void getCollageNumber(){
        System.out.println(index);  //输出学院的数目
    }
}
class Collage{
    private String name;
    public Collage(){       //构造方法1
        this.name="";    //学院的无参构造函数，为name赋一个初值
                        //系统本身为每个类自带一个无参构造函数。  但是一旦自己写了有参构造函数，系统自带的无参构造器就消失了，想用就得自己写
    }
    public Collage(String  name){    //构造方法2
        this.name=name;
    }
    public Collage(Collage collage){    //构造方法3
        this.name=collage.name;         //  拷贝构造
    }
    public String getName() {
        return name;  //获取学院名
    }
    public void  setName(String name) {
        this.name=name; //修改学院的名字
    }
}
public class school {
    public static void main(String[] args) {
        Szu szu=new Szu();

        //通过学院名直接向szu中添加学院对象
        szu.addCollages("计软");
        szu.addCollages("人文");

        //通过学院对象 向szu中添加
        Collage collage1=new Collage("传院");
        Collage collage2=new Collage("艺院");
        szu.addCollages(collage1);
        szu.addCollages(collage2);

        szu.getCollageNames();
        szu.getCollageNumber();
    }
}
