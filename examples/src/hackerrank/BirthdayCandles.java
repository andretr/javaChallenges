package hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

/**
 * Created by Andre Ticona.
 * https://www.hackerrank.com/challenges/birthday-cake-candles/problem
 * Date: 3/10/2023
 */
public class BirthdayCandles {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList();
        list.add(4);
        list.add(4);
        list.add(1);
        list.add(3);
        BirthdayCandles test = new BirthdayCandles();
        int result1 = test.countCandlesTraditional(list);
        int result2 = test.countCandlesFunctional(list);
        System.out.println("Result traditional: " + result1);
        System.out.println("Result functional programming: " + result2);
    }

    public int countCandlesTraditional(List<Integer> candleList) {
       int max = 0;
       int countMax = 0;
       for(int candle: candleList){
           if(candle > max){
               max = candle;
               countMax = 1;
           } else if (candle == max){
               countMax ++;
           }
       }
        return countMax;
    }

    public int countCandlesFunctional(List<Integer> candleList) {
        OptionalInt max = candleList.stream().mapToInt(Integer::intValue).max();
        Long counter = candleList.stream().filter((item) -> {
            return item == max.getAsInt();
        }).count();
        return counter.intValue();
    }
}
