// Q2. 암아존 배조스씨를 위한 계좌이체 한글 음성 안내

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q2 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/input.txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            input = input.replaceAll(",", "").replace("원", "");
//            System.out.println(input);

            List<String> result = new ArrayList<>();
            String[] klarges = {"", "만", "억", "조"};
            String[] kSmalls = {"", "십", "백", "천"};
            String[] kNums = {"", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구"};
            int largeIndex = 0;

            while (input.length() > 0) {
                String part = new String();
                if (input.length() > 4) {
                    part = input.substring(input.length() - 4);
                    input = input.substring(0, input.length() - 4);

                } else {
                    part = input;
                    input = "";
                }

                // part를 한국어 발음으로 치환.
                StringBuilder korPart = new StringBuilder();
                for (int i = 0; i < part.length(); i++) {
                    int numIndex = part.charAt(i) - '0';
                    if (numIndex == 0) {
                        continue;
                    }
                    //  '십', '백', '천' 으로 발음.  '일천', '일백', '일십' 발음 X.
                    // part중 가장 낮은 자리수는 '일'로 발음.
                    if (numIndex == 1 && (part.length() - i - 1) > 0) {
                        korPart.append(kSmalls[part.length() - i - 1]);
                    } else {
                        korPart.append(kNums[numIndex]);
                        korPart.append(kSmalls[part.length() - i - 1]);
                    }
                }
//                System.out.println(korPart +" ");

                // 100,000,000와 같이 part가 비어있는 경우를 제외하고
                if (!korPart.isEmpty()) {
                    // 숫자 '10,000' 은 '만'으로 발음, '일만'으로 발음 X.
                    if (largeIndex == 1 && korPart.toString().equals("일")) {
                        result.add("만");
                    } else {
                        result.add(korPart.toString() + klarges[largeIndex]);
                    }
                }

                largeIndex++;
            }

            result = result.reversed();
            for (int i = 0; i < result.size(); i++) {
                System.out.print(result.get(i));
                if (i != result.size() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println("원");
        }
    }
}