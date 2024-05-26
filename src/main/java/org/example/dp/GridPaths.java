package org.example.dp;/*
 * @author gauravverma
 */

import java.util.Arrays;
import java.util.Scanner;

public class GridPaths {
    static final int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        char[][] matrix = new char[n][n];
        for(int i = 0; i<n; i++){
            String line = sc.nextLine();
            for(int j=0; j<n; j++){
                matrix[i][j] = line.charAt(j);
            }
        }

        int[][] dp = new int[n][n];
        for(int[] arr: dp){
            Arrays.fill(arr, -1);
        }

        System.out.println(recursionSol(matrix, n, n-1, n-1));
        System.out.println(memoSol(matrix, n, n-1, n-1, dp));
        System.out.println(tabSol(matrix, n));
    }

    public static int tabSol(char[][] matrix, int n){
        int[][] dp = new int[n][n];

        // Initialize the start point
        if (matrix[0][0] != '*') {
            dp[0][0] = 1;
        }

        // Initialize the first row
        for (int j = 1; j < n; j++) {
            if (matrix[0][j] != '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }

        // Initialize the first column
        for (int i = 1; i < n; i++) {
            if (matrix[i][0] != '*') {
                dp[i][0] = dp[i - 1][0];
            }
        }

        // Fill the dp table
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] != '*') {
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MOD;
                }
            }
        }

        return dp[n - 1][n - 1];
    }

    public static int memoSol(char[][] matrix, int n, int i, int j, int[][] dp){
        if(i == 0 && j == 0 && matrix[i][j] == '*'){
            return 0;
        }

        // right or down move only
        if(i == 0 && j == 0)
            return 1;

        if(!isValid(matrix, i, j, n)){
            return 0;
        }

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        // go right -> j - 1
        int right = 0;
        if(j > 0){
            right = memoSol(matrix, n, i, j-1, dp);
        }


        // go down -> i - 1
        int down = 0;
        if(i > 0){
            down = memoSol(matrix, n, i-1, j, dp);
        }

        return dp[i][j] = (down + right) % MOD;
    }

    public static boolean isValid(char[][] matrix, int i, int j, int n){
        return (i >= 0 && i < n) && (j >= 0 && j < n) && matrix[i][j] != '*';
    }

    public static int recursionSol(char[][] matrix, int n, int i, int j){
        if(i == 0 && j == 0 && matrix[i][j] == '*'){
            return 0;
        }

        if(i == 0 && j == 0)
            return 1;

        if(!isValid(matrix, i, j, n)){
            return 0;
        }

        // go right -> j - 1
        int right = 0;
        if(j > 0){
            right = recursionSol(matrix, n, i, j-1);
        }


        // go down -> i - 1
        int down = 0;
        if(i > 0){
            down = recursionSol(matrix, n, i-1, j);
        }

        return (down + right) % MOD;
    }
}
