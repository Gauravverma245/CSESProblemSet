

import java.util.Arrays;
import java.util.Scanner;

public class RemovalGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        long[][] dp = new long[n][n];
        for(long[] row: dp){
            Arrays.fill(row, -1);
        }

        System.out.println(recSol(arr, 0, n-1, dp));

    }

    public static long recSol(int[] arr, int i, int j, long[][] dp){
        if(i == j){
            return arr[i];
        }

        if((j - i) == 1){
            return Math.max(arr[i], arr[j]);
        }

        //a[i], a[i+1], a[i+2], a[i+3] ......... a[j-3], a[j-2], a[j-1], a[j];
        // take a[i] ->  second play will try to minimize this (a[i+1], a[i+2], a[i+3] ......... a[j-3], a[j-2], a[j-1], a[j]);
        // if second player takes a[i+1] -> first player remaining with (a[i+2], a[i+3] ......... a[j-3], a[j-2], a[j-1], a[j]);
        // if second player takes a[j] -> first player remaining with (a[i+1], a[i+2], a[i+3] ......... a[j-3], a[j-2], a[j-1])

        // take a[j] -> second player will try to minimize this (a[i], a[i+1], a[i+2], a[i+3] ......... a[j-3], a[j-2], a[j-1]);
        // if second play take a[i] -> first player remaining with (a[i+1], a[i+2], a[i+3] ......... a[j-3], a[j-2], a[j-1])
        // if second player take a[j-1] -> first player remaining with (a[i], a[i+1], a[i+2], a[i+3] ......... a[j-3], a[j-2])

        // dp[i][j] = Math.max(a[i] + min(dp[i+2][j], dp[i+1][j-1]), a[j] + min(dp[i+1][j-1], dp[i][j-2]))
        if(dp[i][j] != -1){
            return dp[i][j];
        }

        dp[i][j] = Math.max(arr[i] + Math.min(recSol(arr, i+2, j, dp), recSol(arr, i+1, j-1, dp)), arr[j] + Math.min(recSol(arr, i+1, j-1, dp), recSol(arr, i, j-2, dp)));
        return dp[i][j];
    }
}
