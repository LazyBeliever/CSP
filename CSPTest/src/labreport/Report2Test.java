package labreport;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Report2Test {

    @Test
     void main() {
        Report2 test=new Report2();
        String[] args={
                "summon 1 3 6",
                "summon 2 4 2",
                "end",
                "summon 1 4 5",
                "summon 1 2 1",
                "end",};
        ArrayList<Integer>[] result=new ArrayList[5];
        result[0]=new ArrayList<>();
        result[1]=new ArrayList<>();
        result[2]= new ArrayList<>();
        result[3]=new ArrayList<>();
        result[4]=new ArrayList<>();
        result[0].add(0);
        result[1].add(30);
        result[2].add(2);
        result[2].add(6);
        result[2].add(2);
        result[3].add(30);
        result[4].add(2);
        result[4].add(1);
        result[4].add(5);

        ArrayList<Integer>[] result2=test.game(args);
        for(int j=0;j<5;j++){
            for(int i=0;i<result[j].size();i++){
                assertEquals(result[j].get(i),result2[j].get(i));
            }
        }
    }
}