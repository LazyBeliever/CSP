package labreport;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author 教徒
 */
public class Report2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String temp=sc.nextLine();
        int n=changeToInt(temp);
        String[] operation=new String[n];
        for (int i = 0; i < n; i++) {
            operation[i]=sc.nextLine();
        }
        int resultLines=5;
        ArrayList<ArrayList<Integer>> result=new ArrayList<>(5);
        for(int i = 0;i < resultLines;i++){
            result.add(new ArrayList<>());
        }
        result=game(operation);
        final int resultLine=5;
        for(int i=0;i<resultLine;i++){
            if(!result.get(i).isEmpty())
            {
                for(int j=0;j<result.get(i).size();j++){
                    System.out.print(result.get(i).get(j)+" ");
                }
            }else{
                System.out.print(0);
            }
            System.out.println();
        }
    }

    /**
     *
     * @param s String形式的全部操作
     * @return 题目要求的五行数据，用ArrayList[] 返回
     */
    public static ArrayList<ArrayList<Integer>> game(String[] s){
        Player[] players=new Player[2];
        players[0]=new Player();
        players[1]=new Player();
        int who=0;
        for (String value : s) {
            String[] temp = value.split(" ");
            switch (temp[0]) {
                case "summon":
                    int position = changeToInt(temp[1]);
                    int atk = changeToInt(temp[2]);
                    int hp = changeToInt(temp[3]);
                    Card card = new Card(hp, atk);
                    players[who].summon(card, position - 1);
                    break;
                case "attack":
                    int offensive = changeToInt(temp[1]) - 1;
                    //此处-1是因为下标和编号有1的差距
                    int defense = changeToInt(temp[2]);
                    if (defense != 0) {
                        defense -= 1;
                        //此处是因为下标和编号有1的差距
                        players[who].attackCard(offensive, players[(who + 1) % 2].cards.get(defense));
                        players[who].check(offensive);
                        players[(who + 1) % 2].check(defense);
                    } else {
                        players[who].attackHero(offensive, players[(who + 1) % 2].hero);
                    }
                    break;
                case "end":
                    who = (who + 1) % 2;
                    break;
                default:
                    break;
            }
        }
        int resultLines=5;
        ArrayList<ArrayList<Integer>> result=new ArrayList<>(5);
        for(int i = 0;i < resultLines;i++){
            result.add(new ArrayList<>());
        }

        if(players[0].hero.health>0&&players[1].hero.health>0){
            result.get(0).add(0);
        }else if(players[0].hero.health<=0){
            result.get(0).add(-1);
        }else{
            result.get(0).add(1);
        }
        //先手玩家英雄生命值
        result.get(1).add(players[0].hero.health);
        //先手玩家场上card
        result.get(2).add(players[0].cards.size());
        if(players[0].cards.size()!=0){
            for (int i = 0; i < players[0].cards.size(); i++) {
                result.get(2).add(players[0].cards.get(i).health);
            }
        }
        //后手玩家英雄生命值
        result.get(3).add(players[1].hero.health);
        //后手玩家场上card
        int exitCards2=players[1].cards.size();
        result.get(4).add(exitCards2);
        if(exitCards2!=0){
            for (int i = 0; i < exitCards2; i++) {
                result.get(4).add(players[1].cards.get(i).health);
            }
        }
        return result;
    }
    public static int changeToInt(String s){
        int res=0;
        for (int i = 0; i < s.length(); i++) {
            res=res*10+(s.charAt(i)-'0');
        }
        return res;
    }
}


class Role {
    int health;
    int attack;
    public Role(int health, int attack){
        this.health=health;
        this.attack=attack;
    }
}
/**
 * 手牌、随从,拥有攻击方法
 */
class Card extends Role {
    public Card(int health, int attack){
        super(health,attack);
    }
    public void attack(Card enemy){  //攻击方调用
        this.health-=enemy.attack;
        enemy.health-=this.attack;
    }
}

/**
 * 英雄，玩家控制的英雄，英雄的生死决定的玩家的输赢
 */
class Hero extends Role {
    public Hero(int health, int attack){
        super(health,attack);
    }
}

/**
 * 玩家，每场游戏的参与者，拥有一个英雄和手牌（最多七张
 */
class Player {
    ArrayList<Card> cards;
    Hero hero;
    private static final int CARD_NUMBER =7;
    public Player() {
        cards = new ArrayList<>(7);
        hero = new Hero(30, 0);
    }
    public boolean summon(Card card,int pos){    //召唤卡牌
        if(cards.size()<CARD_NUMBER){
            cards.add(pos,card);
            return true;
        }
        return false;
    }   //召唤手牌
    public boolean check(int index){   //检查场上卡牌是否被摧毁，在进攻或者防守之后检查
        if(cards.get(index).health<=0){
            cards.remove(cards.get(index));
            return true;
        }
        return false;
    }       //检查手牌血量，若<=0就从cards中删除该卡牌

    /**
     * attack方法分为两个，分别为attackCard，攻击对方卡牌，attackHero攻击对方英雄
     * @param index 攻击方的卡牌的下标
     * @param enemy 防守对象
     */
    public void attackCard(int index,Card enemy){
        this.cards.get(index).attack(enemy);
    }
    public void attackHero(int index,Hero enemy){
        enemy.health-=this.cards.get(index).attack;
    }
}



