// q1 '얼른 마스크'씨 회사 전기자동차의 행복한 일련번호

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Q1 {

    // 행복 수 여부 검사 후 저장하는 배열
    static int[] isHappyNum;

    static boolean checkNum(int num, Set<Integer> set) {
        int sum = 0;
        while (num > 0) {
            int digit = num % 10;
            sum += Math.pow(digit, 2);
            num /= 10;
        }
        if (isHappyNum[sum] == -1 || set.contains(sum)) {
            return false;
        }
        if (isHappyNum[sum] == 1) {
            return true;
        }

        set.add(sum);
        return checkNum(sum, set);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = 2;

        while (t-- > 0) {
            int n = sc.nextInt();
            isHappyNum = new int[9999 + 1]; // -1이면 안행복한 수, 0이면 아직 모름, 1이면 행복한 수
            Arrays.fill(isHappyNum, 0);
            isHappyNum[1] = 1;

            for (int i = 2; i <= n; i++) {
                if (isHappyNum[i] == 0) {
                    Set<Integer> set = new HashSet<>();
                    set.add(i);

                    if (checkNum(i, set)) {
                        set.stream().forEach(e -> isHappyNum[e] = 1);
                    } else {
                        set.stream().forEach(e -> isHappyNum[e] = -1);
                    }
                    set.clear();
                }
            }

            int sum = 0;
            int count = 0;
            for (int i = 1; i <= n; i++) {
                if (isHappyNum[i] == 1) {
                    sum += i;
                    count++;
                }
            }

            System.out.println("1 ~ " + n + " 범위의 행복 수는 " + count + "개이고 총합은 " + sum + "입니다.");

        }

        sc.close();
    }
}