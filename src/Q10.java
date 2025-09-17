// Q10. 우리나라 돈으로 10만원을 지불하는 방법

import java.math.BigInteger;

public class Q10 {

    public static void main(String[] args) {

        // f_50000(m)=(m^9 + a1m^8 + a2m^7 + a3m^6 + ...+ a8m + a9)/a9
        // 9차 방정식 이므로 미지수를 해결하기 위해서는 방정식 9개 필요.
        int[] coins = {1, 5, 10, 50, 100, 500, 1000, 5000, 10000, 50000};
        int amount = 50000;

        // 방정식 amount * (1 ... 9) 총 9개 구함.
        BigInteger[] dp = new BigInteger[amount * 9 + 1];

        for (int i = 0; i < dp.length; i++) {
            dp[i] = BigInteger.ZERO;
        }

        dp[1] = BigInteger.ONE;
        for (int coin : coins) {
            for (int i = coin; i <= amount * 9; i++) {
                dp[i] = dp[i].add(dp[i - coin]);
            }
        }

        for (int i = 1; i <= 9; i++) {
            System.out.println("f_50000(" + 50000 * i + ") = " + dp[50000 * i]);
        }

        // 9개의 방정식을 가지고 9차 방정식을 해결한다.
        // a1 ~ a9를 구해 정답을 구한다.
        // ...



    }
}
