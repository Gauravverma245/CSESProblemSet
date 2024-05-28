package org.example.dp;/*
 * @author gauravverma
 */

import java.util.Arrays;
import java.util.Scanner;

public class TwoSetsII {
    static final int MOD = 1000000007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = n * (n + 1) / 2;

        int[][] dp = new int[sum +1][n+1];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        if(sum % 2 != 0){
            System.out.println(0);
        }else{
            //System.out.println(recursiveSol(sum / 2, n) / 2);
            //System.out.println(memoSol(sum/2, n, dp)/2);
            System.out.println(tabSol(sum/2, n));
        }

    }

    public static int tabSol(int sum, int n){
        int[][] dp = new int[sum+1][n+1];
        for(int i = 0; i<=n; i++){
            dp[0][i] = 1;
        }


        for(int currSum = 1; currSum <= sum; currSum++){
            for(int i = 1; i<=n; i++){
                int pick = 0;
                if(i <= currSum){
                    pick = dp[currSum-i][i-1];
                }
                int notPick = dp[currSum][i-1];

                dp[currSum][i] = (pick + notPick) % MOD;
            }
        }
        // we can't directly divide the values that are coming using MOD.
        // so we have to do modulo inverse.
        // we took the modulo inverse of 2 with 1000000007 and got 500000004
        long ans = (dp[sum][n] * 500000004L) % MOD;
        return (int) ans;
    }

    public static int memoSol(int sum, int n, int[][] dp){
        if(sum == 0){
            return 1;
        }

        if(n == 0 && sum > 0){
            return 0;
        }

        if(dp[sum][n] != -1){
            return dp[sum][n];
        }


        int pick = 0;
        if(n <= sum){
            pick = memoSol(sum - n, n-1, dp);
        }
        int notPick = memoSol(sum, n -1, dp);

        return dp[sum][n] = (pick + notPick) % MOD;
    }

    public static int recursiveSol(int sum, int n){
        if(sum == 0){
            return 1;
        }

        if(n == 0 && sum > 0){
            return 0;
        }


        int pick = 0;
        if(n <= sum){
            pick = recursiveSol(sum - n, n-1);
        }
        int notPick = recursiveSol(sum, n -1);

        return (pick + notPick) % MOD;

    }
}
