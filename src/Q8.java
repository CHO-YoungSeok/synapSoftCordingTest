// Q8. 암호산술 2. COFFEE + COFFEE + COFFEE = ???????

import java.io.File;
import java.util.*;

public class Q8 {

    static List<String> answers = new ArrayList<>();
    static Map<Character, Integer> map = new HashMap<>();
    static List<Character> letters = new ArrayList<>();
    static boolean[] usedDigits = new boolean[10];
    static final String COFFEE = "COFFEE";
    static String targetWord;

    static boolean choose(int letterIndex) {
        if (letterIndex == letters.size()) {
            if (isValid()) {
                return true;
            }
            return false;
        }

        char currentLetter = letters.get(letterIndex);
        for (int digit = 0; digit < 10; digit++) {
            if (usedDigits[digit]) {
                continue;
            }

            if (digit == 0 && (currentLetter == COFFEE.charAt(0) || currentLetter == targetWord.charAt(0))) {
                continue;
            }

            map.put(currentLetter, digit);
            usedDigits[digit] = true;
            // target단어가 성공하면 다음 단어로 넘어감.
            if (choose(letterIndex + 1)) {
                return true;
            }
            usedDigits[digit] = false;
            map.remove(currentLetter);
        }
        return false;
    }

    static boolean isValid() {
        long coffeeValue = convertToNumber(COFFEE);
        long wordValue = convertToNumber(targetWord);

        return 3 * coffeeValue == wordValue;
    }

    static long convertToNumber(String str) {
        long value = 0;
        for (int i = 0; i < str.length(); i++) {
            value  = value * 10 + map.get(str.charAt(i));
        }

        return value;
    }

    public static void main(String[] args) throws Exception {

        File file = new File("src/input.txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNext() && answers.size() < 78) {

            // 초기화
            targetWord = sc.nextLine();
            map.clear();
            letters.clear();
            Arrays.fill(usedDigits, false);

            // 알파벳 추추 및 리스트 저장
            for (int i = 0; i < COFFEE.length(); i++) {
                if (!letters.contains(COFFEE.charAt(i))) {
                    letters.add(COFFEE.charAt(i));
                }
            }
            for (int i = 0; i < targetWord.length(); i++) {
                if (!letters.contains(targetWord.charAt(i))) {
                    letters.add(targetWord.charAt(i));
                }
            }
            if (letters.size() > 10) {
                continue;
            }
            System.out.println(answers.size());
            if (choose(0)) {
                answers.add(targetWord);
            }

        }
        // 78번째 단어 출력
        System.out.println(answers.get(77));
        sc.close();
    }
}
