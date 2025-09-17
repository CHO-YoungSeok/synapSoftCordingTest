import java.math.BigInteger;

public class Test {
    static int[] coins = {1, 5, 10, 50, 100, 500, 1000, 5000, 10000, 50000};

    public static void main(String[] args) {
        int amount = 100000;

        BigInteger[] dp = new BigInteger[amount + 1];

        for (int i = 0; i < dp.length; i++) {
            dp[i] = BigInteger.ZERO;
        }
        dp[1] = BigInteger.ONE;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = dp[i].add(dp[i - coin]);
            }
        }

        System.out.println("10만원을 만들 수 있는 경우의 수 = " + dp[amount]);

    }
}
