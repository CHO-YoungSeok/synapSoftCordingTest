// Q7. 암호산술 1. SYNAP + SOFT = WANTS + YOU

import java.util.*;

public class Q7 {

    static final char[] LETTERS = {'S', 'Y', 'N', 'A', 'P', 'O', 'F', 'T', 'W', 'U'};
    static Map<Character, Integer> map = new HashMap<>();
    static boolean[] usedDigits = new boolean[10];
    static List<Map<Character, Integer>> answers = new ArrayList<>();
    static int answerCount = 0;

    static void choose(int letterIndex) {
        if (letterIndex == LETTERS.length) {
            if (isSolutionValid()) {
                answerCount++;
//                System.out.println(answerCount + ": " + map);
                answers.add(new HashMap<>(map));
            }
            return;
        }

        char currentLetter = LETTERS[letterIndex];

        // 0부터 9까지의 숫자를 하나씩 시도
        for (int digit = 0; digit <= 9; digit++) {
            if (usedDigits[digit]) {
                continue;
            }

            if ((currentLetter == 'S' || currentLetter == 'W' || currentLetter == 'Y') && digit == 0) {
                continue;
            }

            map.put(currentLetter, digit);
            usedDigits[digit] = true;
            choose(letterIndex + 1);
            usedDigits[digit] = false;
            map.remove(currentLetter);
        }
    }

    static boolean isSolutionValid() {
        return (10999 * map.get('S') + 900 * map.get('Y')
                - 990 * map.get('A') + map.get('P')
                + 90 * map.get('O') + 10 * map.get('F')
                - 9 * map.get('T') - 10000 * map.get('W') - map.get('U')) == 0;
    }

    public static void main(String[] args) {
        // 'SYNAP + SOFT = WANTS + YOU'
        // 'SYNAP + SOFT - WANTS - YOU' = 0
        // (11000 - 1)S + (1000 - 100)Y + (100 - 100)N
        //      + (10 - 1000)A + P + (100 - 10)O + (10)F + (1 - 10)T - (10000)W - U = 0
        // (S, W, Y) != 0

        // 10999*S + 900*Y - 990*A + P + 90*O  + 10*F - 9*T - 10000*W - U = 0


        choose(0);
        int sum = 0;
        for (int i = 0; i < answers.size(); i++) {
            Map<Character, Integer> answer = answers.get(i);
            sum += answer.get('S') * 10000 + answer.get('Y') * 1000 + answer.get('N') * 100 + answer.get('A') * 10 + answer.get('P');
            sum += answer.get('S') * 1000 + answer.get('O') * 100 + answer.get('F') * 10 + answer.get('T');
        }

        System.out.println("가능한 모든 좌변의 합 = " + sum);
    }
}
