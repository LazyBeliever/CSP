package labreport;

import java.util.ArrayList;
import java.util.Scanner;

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
    public void attack( Card enemy){  //攻击方调用
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
        cards = new ArrayList<>(8);
        hero = new Hero(30, 0);
    }
    public void summon(Card card,int pos){    //召唤卡牌
        if(cards.size()<CARD_NUMBER){
            cards.add(pos,card);
        }
    }
    public void check(int index){   //检查场上卡牌是否被摧毁，在进攻或者防守之后检查
        if(cards.get(index).health<0){
            cards.remove(cards.get(index));
        }
    }
    public void attackCard(int index,Card enemy){
        this.cards.get(index).attack(enemy);
    }
    public void attackHero(int index,Hero enemy){
        enemy.health-=this.cards.get(index).attack;
    }
}
/**
 * @author 教徒
 */
public class Report2 {
    public void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Player[] player=new Player[2];
        player[0]=new Player();
        player[1]=new Player();
        int who=1;
        int operationTimes=sc.nextInt();

        String op;
        int position;
        int hp;
        int atk;
        int offensive;
        int defense;
        for (int i = 0; i < operationTimes; i++) {
            op=sc.next();
            switch(op){
                case "summon":
                    position=sc.nextInt();
                    hp=sc.nextInt();
                    atk=sc.nextInt();
                    Card card=new Card(hp,atk);
                    player[who].summon(card,position);
                    break;
                case"attack":
                    offensive=sc.nextInt()-1;
                    //此处-1是因为下标和编号有1的差距
                    defense=sc.nextInt();
                    if(defense!=0){
                        defense-=1;
                        //此处是因为下标和编号有1的差距
                        player[who].attackCard(offensive,player[(who+1)%2].cards.get(defense));
                        player[who].check(offensive);
                        player[(who+1)%2].check(defense);
                    }else{
                        player[who].attackHero(offensive,player[(who+1)%2].hero);
                    }
                    break;
                case "end":
                    who=(who+1)%2;
                    break;
                default:
                    break;
            }
        }
        //胜负
        if(player[0].hero.health>0&&player[1].hero.health>0){
            System.out.println(0);
        }else if(player[0].hero.health<=0){
            System.out.println(-1);
        }else{
            System.out.println(1);
        }
        //先手玩家英雄生命值
        System.out.println(player[0].hero.health);
        //先手玩家场上card
        int exitCards=player[0].cards.size();
        System.out.print(exitCards);
        if(exitCards!=0){
            System.out.print(" ");
            for (int i = 0; i < exitCards; i++) {
                System.out.print(player[0].cards.get(i).health+" ");
            }
        }
        System.out.println();

        System.out.println(player[1].hero.health);
        //先手玩家场上card
        int exitCards2=player[1].cards.size();
        System.out.print(exitCards2);
        if(exitCards2!=0){
            System.out.print(" ");
            for (int i = 0; i < exitCards2; i++) {
                System.out.print(player[1].cards.get(i).health+" ");
            }
        }
    }
}

