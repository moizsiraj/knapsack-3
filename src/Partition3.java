import java.util.*;
import java.io.*;

public class Partition3 {
    private static int partition3(int[] values) {
        int total = 0;
        int i, j;

        for (i = 0; i < values.length; i++)
            total += values[i];

        if (total % 3 != 0)
            return 0;
        else {
            boolean[][] truthTable = new boolean[total / 3 + 1][values.length + 1];

            for (i = 0; i <= values.length; i++)
                truthTable[0][i] = true;
            for (i = 1; i <= total / 3; i++)
                truthTable[i][0] = false;

            for (i = 1; i <= total / 3; i++) {
                for (j = 1; j <= values.length; j++) {
                    truthTable[i][j] = truthTable[i][j - 1];
                    if (i >= values[j - 1])
                        truthTable[i][j] = truthTable[i][j] || truthTable[i - values[j - 1]][j - 1];
                }
            }
            if (truthTable[total / 3][values.length]) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }
        System.out.println(partition3(A));
    }
}

