// Q3. Full HD 화면상의 직사각형들이 차지하고 있는 총면적

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Q3 {

    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("src/input.txt");
        Scanner sc = new Scanner(file);

        boolean[][] screen = new boolean[1920 + 1][1080 + 1];

        while (sc.hasNextLine()) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();

            for (int x = x1; x < x2; x++) {
                for (int y = y1; y < y2; y++) {
                    screen[x][y] = true;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < screen.length; i++) {
            for (int j = 0; j < screen[i].length; j++) {
                if (screen[i][j]) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}