package labreport;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Report2Test {

    @Test
     void main() {
        String[] args={
                "summon 1 3 6",
                "summon 2 4 2",
                "end",
                "summon 1 4 5",
                "summon 1 2 1",
                "attack 1 2",
                "end",
                "attack 1 1"};
        int resultLines=5;
        ArrayList<ArrayList<Integer>> result=new ArrayList<>(5);
        for(int i = 0;i < resultLines;i++){
            result.add(new ArrayList<>());
        }
        result.get(0).add(0);
        result.get(1).add(30);
        result.get(2).add(1);
        result.get(2).add(2);
        result.get(3).add(30);
        result.get(4).add(1);
        result.get(4).add(2);
        ArrayList<ArrayList<Integer>> result2=Report2.game(args);

        final int resultLine =5;
        for(int j=0;j<resultLine;j++){
            for(int i=0;i<result.get(j).size();i++){
                assertEquals(result.get(j).get(i),result2.get(j).get(i));
            }
        }
    }

    @Test
    void change(){
        String s="1212212";
        int res= Report2.changeToInt(s);
        assertEquals(res,1212212);
    }

    @Test
    void wrong(){


        ArrayList<Integer>[] result=new ArrayList[5];
        result[0]=new ArrayList<>();
        result[1]=new ArrayList<>();
        result[2]=new ArrayList<>();
        result[3]=new ArrayList<>();
        result[4]=new ArrayList<>();



        result[0].add(0);
        result[0].get(0);
    }
}