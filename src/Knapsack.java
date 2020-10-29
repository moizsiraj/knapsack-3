import java.util.*;

public class Knapsack {
    static int optimalWeight(int W, int[] w) {
        //write you code here
        int[][] array = new int[w.length + 1][W + 1];
        for (int i = 1; i < array.length; i++) {
            for (int j = 1; j < array[0].length; j++) {
                array[i][j] = array[i-1][j];
                if(w[i-1] <= j){
                    int value = array[i-1][j-w[i-1]] + w[i-1];
                    if(array[i][j] < value){
                        array[i][j] = value;
                    }
                }
            }
        }
        return array[array.length -1][array[0].length -1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

//    int result = 0;
//        for (int i = 0; i < w.length; i++) {
//        if (result + w[i] <= W) {
//        result += w[i];
//        }
//        }
//        return result;
