package labreport;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player temp;
    @Test
    public void test1(){
        temp=new Player();
            Card card1=new Card(2,2);
            Card card12=new Card(-1,2);
            temp.cards.add(0,card1);
            temp.cards.add(1,card12);
            temp.cards.add(1,card12);
            temp.cards.add(1,card12);
            temp.cards.add(1,card12);
            temp.cards.add(1,card12);
            assertTrue(temp.summon(card12,1));
            assertTrue(temp.check(1));
    }

    @Test
    public void test2(){
        temp=new Player();
        Card card1=new Card(2,2);
        Card card12=new Card(1,2);
        temp.cards.add(0,card1);
        temp.cards.add(1,card12);
        temp.cards.add(1,card12);
        temp.cards.add(1,card12);
        temp.cards.add(1,card12);
        temp.cards.add(1,card12);
        temp.cards.add(1,card12);
        assertFalse(temp.summon(card12,1));
        assertFalse(temp.check(1));
    }

}