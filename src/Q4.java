// Q4 스프레드시트 컬럼의 영문명칭 계산

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Q4 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/input.txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            int num = sc.nextInt();
            StringBuilder sbResult = new StringBuilder();
            for (int n = num;  n > 0; n = (n-1) / 26) {
                sbResult.append((char)('A' + (n - 1) % 26));
            }
            sbResult.reverse();
            System.out.println(sbResult);
        }
    }
}