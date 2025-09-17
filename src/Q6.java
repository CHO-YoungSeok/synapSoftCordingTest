//Q6. 특정 구간내의 모든 피보나치 수의 합
import java.util.HashMap;
import java.util.Map;

public class Q6 {

    public static void main(String[] args)  {
        long from = 12345678999L;
        long to = 98765432111L;

        Map<Long, Long> map = new HashMap<>();
        map.put(1L, 1L);
        map.put(2L, 2L);

        long a = 0L, b = 0L; //  f(a) ~ f(b)까지가 범위 내의 피보나치 수들의 집합.
        for (a = 2L; map.get(a) < from; a++) {
            map.put(a + 1, map.get(a) + map.get(a - 1));
        }
        System.out.println("a : " + a + ", " + map.get(a));

        long sum = 0L;
        int count = 0;

        for (b = a; map.get(b) <= to; b++) {
            map.put(b + 1, map.get(b) + map.get(b - 1));
            sum += map.get(b);
            count++;
        }

        System.out.println("b : " + b + ", " + map.get(b));

        System.out.println("합 = " + sum);
        System.out.println("개수 = " + count);
    }
}