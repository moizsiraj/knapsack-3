import java.util.Scanner;

public class PlacingParentheses {
    static long[][] Max;
    static long[][] Min;

    private static long getMaximValue(String exp) {
        StringBuilder numbers = new StringBuilder();
        StringBuilder ops = new StringBuilder();
        String[] numArray;

        for (int i = 0; i < exp.length(); i++) {
            if (i % 2 == 0) {
                numbers.append(exp.charAt(i)).append(' ');
            } else {
                ops.append(exp.charAt(i)).append(' ');
            }
        }
        numArray = numbers.toString().split(" ");

        Max = new long[numArray.length][numArray.length];
        Min = new long[numArray.length][numArray.length];

        for (int i = 0; i < numArray.length; i++) {
            Max[i][i] = Long.parseLong(numArray[i]);
            Min[i][i] = Long.parseLong(numArray[i]);
        }

        for (int i = 0; i < numArray.length - 1; i++) {
            for (int j = 0; j < numArray.length - i; j++) {
                int temp = i + j;
                long[] minmax = eval(i, temp, ops.toString());
                Min[i][j] = minmax[0];
                Max[i][j] = minmax[1];
            }
        }
        return Max[0][Max[0].length - 1];
    }

    private static long[] eval(int a, int b, String ops) {
        long min = (long) Double.POSITIVE_INFINITY;
        long max = (long) Double.NEGATIVE_INFINITY;

        long[] ans = new long[2];
        long MM;
        long Mm;
        long mM;
        long mm;

        for (int i = a; i <= b; i++) {
            char op = ops.charAt(i);
            if (op == '+') {
                MM = Max[a][i] + Max[i + 1][b];
                Mm = Max[a][i] + Min[i + 1][b];
                mM = Min[a][i] + Max[i + 1][b];
                mm = Min[a][i] + Min[i + 1][b];
                long minX = Math.min(MM, Mm);
                long minY = Math.min(mM, mm);
                long minZ = Math.min(min, minY);
                min = Math.min(minX, minZ);
                long maxX = Math.max(MM, Mm);
                long maxY = Math.max(mM, mm);
                long maxZ = Math.max(max, maxY);
                max = Math.min(maxX, maxZ);
            } else if (op == '-') {
                MM = Max[a][i] - Max[i + 1][b];
                Mm = Max[a][i] - Min[i + 1][b];
                mM = Min[a][i] - Max[i + 1][b];
                mm = Min[a][i] - Min[i + 1][b];
                long minX = Math.min(MM, Mm);
                long minY = Math.min(mM, mm);
                long minZ = Math.min(min, minY);
                min = Math.min(minX, minZ);
                long maxX = Math.max(MM, Mm);
                long maxY = Math.max(mM, mm);
                long maxZ = Math.max(max, maxY);
                max = Math.min(maxX, maxZ);
            } else if (op == '*') {
                MM = Max[a][i] * Max[i + 1][b];
                Mm = Max[a][i] * Min[i + 1][b];
                mM = Min[a][i] * Max[i + 1][b];
                mm = Min[a][i] * Min[i + 1][b];
                long minX = Math.min(MM, Mm);
                long minY = Math.min(mM, mm);
                long minZ = Math.min(min, minY);
                min = Math.min(minX, minZ);
                long maxX = Math.max(MM, Mm);
                long maxY = Math.max(mM, mm);
                long maxZ = Math.max(max, maxY);
                max = Math.min(maxX, maxZ);
            } else {
                assert false;
            }
        }
        ans[0] = min;
        ans[1] = max;
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }
}

