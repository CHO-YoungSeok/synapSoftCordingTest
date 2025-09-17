//Q5.숫자 목록을 이용해 만든 두 자연수 합의 최솟값


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Q5 {

    static void printValid(String[] nums, int zeroCount) {
        StringBuilder n1 = new StringBuilder();
        StringBuilder n2 = new StringBuilder();

        n1.append(nums[zeroCount]);
        n2.append(nums[zeroCount + 1]);
        for (int i = 0; i < zeroCount; i++) {
            if (i % 2 == 0) {
                n1.append(nums[i]);
            } else {
                n2.append(nums[i]);
            }
        }
        for (int i = zeroCount + 2; i < nums.length; i++) {
            if (i % 2 == 0) {
                n1.append(nums[i]);
            } else {
                n2.append(nums[i]);
            }
        }

        System.out.println(Integer.parseInt(n1.toString()) + Integer.parseInt(n2.toString()));
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/input.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] nums = line.split(", ");
            Arrays.sort(nums);
//            Arrays.stream(nums).forEach(s -> System.out.print(s + " "));
//            System.out.println();

            int zeroCount = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i].equals("0")) {
                    zeroCount++;
                } else {
                    break;
                }
            }
            if (zeroCount + 2 > nums.length) {
                System.out.println(-1);
            } else {
                printValid(nums, zeroCount);
            }
        }
    }
}