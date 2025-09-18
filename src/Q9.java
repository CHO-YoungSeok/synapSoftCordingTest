//Q9. 암호산술 3. 범용 풀이 프로그램

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Q9 {

    static Map<Character, Integer> map = new HashMap<>();
    static List<Integer> answers = new ArrayList<>();
    static List<String> leftWords = new ArrayList<>();
    static List<String> rightWords = new ArrayList<>();
    static int leftValue = 0;
    static int rightValue = 0;
    static List<Character> leftOperations = new ArrayList<>();
    static List<Character> rightOperations = new ArrayList<>();
    static List<Character> letters = new ArrayList<>();
    static boolean[] usedDigits = new boolean[10];


    static void choose(int letterIndex) {

        if (letterIndex == letters.size()) {
            if (isValid()) {
                System.out.println(map);
//                System.out.println(leftValue);

                answers.add(leftValue);
            }
            return;
        }

        char currentLetter = letters.get(letterIndex);

        for (int digit = 0; digit < 10; digit++) {
            if (usedDigits[digit]) {
                continue;
            }

            if (digit == 0 && (isInitialLetter(currentLetter))) {
                continue;
            }

            map.put(currentLetter, digit);
            usedDigits[digit] = true;
            choose(letterIndex + 1);
            usedDigits[digit] = false;
            map.remove(currentLetter);
        }

    }

    static boolean isInitialLetter(char c) {
        for (int i = 0; i < leftWords.size(); i++) {
            if (leftWords.get(i).charAt(0) == c) {
                return true;
            }
        }
        for (int i = 0; i < rightWords.size(); i++) {
            if (rightWords.get(i).charAt(0) == c) {
                return true;
            }
        }

        return false;
    }

    static boolean isValid() {
        leftValue = getValue(leftWords, leftOperations);
        rightValue = getValue(rightWords, rightOperations);

        return leftValue == rightValue;
    }

    private static int getValue(List<String> words, List<Character> operations) {
        int value = convertToNumber(words.get(0));

        for (int i = 0; i < operations.size(); i++) {
            char op = operations.get(i);
            if (op == '+') {
                value += convertToNumber(words.get(i + 1));
            } else if (op == '*') {
                value *= convertToNumber(words.get(i + 1));
            } else if (op == '-') {
                value -= convertToNumber(words.get(i + 1));
            } else if (op == '/') {
                value /= convertToNumber(words.get(i + 1));
            } else { throw new IllegalArgumentException("Invalid operation"); }
        }

        return value;
    }

    static int convertToNumber(String str) {
        int value = 0;
        for (int i = 0; i < str.length(); i++) {
            value = value * 10 + map.get(str.charAt(i));
        }

        return value;
    }

    static List<String> extractSrings(String str) {
        StringBuilder sb = new StringBuilder();
        List<String> result = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c >= 'A' && c <= 'Z') {
                sb.append(c);
            } else if (c == ' ') {
                continue;
            } else {
                if (sb.length() > 0) {
                    result.add(sb.toString());
                    sb.setLength(0); // sb 초기화
                }
            }
        }

        if (sb.length() > 0) {
            result.add(sb.toString());
        }

        return result;
    }

    static List<Character> extractOperations(String str) {
        List<Character> operations = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '+' || c == '*' || c == '-' || c == '/') {
                operations.add(c);
            }
        }
        return operations;
    }

    static void initializedLetters() {
        for (int i = 0; i < leftWords.size(); i++) {
            String word = leftWords.get(i);
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                if (!letters.contains(c)) {
                    letters.add(c);
                }
            }
        }
        for (int i = 0; i < rightWords.size(); i++) {
            String word = rightWords.get(i);
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                if (!letters.contains(c)) {
                    letters.add(c);
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src/input.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            map.clear();
            leftWords.clear();
            rightWords.clear();
            leftOperations.clear();
            rightOperations.clear();
            letters.clear();
            leftValue = 0;
            rightValue = 0;


            String line = sc.nextLine();
            String[] words = line.split("=");
            leftWords = extractSrings(words[0]);
            rightWords = extractSrings(words[1]);
            leftOperations = extractOperations(words[0]);
            rightOperations = extractOperations(words[1]);
//            System.out.println(words[0] + " " + words[1]);
//            System.out.println(leftWords + " " + rightWords);
//            System.out.println(leftOperations + " " + rightOperations);

            initializedLetters();
            choose(0);
        }

        int result = answers.stream().mapToInt(Integer::intValue).sum();
        System.out.println(result);
    }
}