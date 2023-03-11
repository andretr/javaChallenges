package hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

/**
 * Created by Andre Ticona.
 * Date: 3/10/2023
 */
public class BirthdayCandles {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(3);
        BirthdayCandles test = new BirthdayCandles();
        int result = test.countCandles(list);
        System.out.println(result);
    }

    public int countCandles(List<Integer> candleList) {
        OptionalInt max = candleList.stream().mapToInt(Integer::intValue).max();
        Long counter = candleList.stream().filter((item) -> {
            return item == max.getAsInt();
        }).count();
        return counter.intValue();
    }
}
