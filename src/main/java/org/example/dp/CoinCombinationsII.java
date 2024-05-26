package org.example.dp;/*
 * @author gauravverma
 */

import java.util.Arrays;
import java.util.Scanner;

public class CoinCombinationsII {
    static final int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();

        int[] arr = new int[n];
        for(int i = 0; i<n; i++){
            arr[i] = sc.nextInt();
        }

        System.out.println(recursiveSol(arr, n, n-1, target));

        int[] dp = new int[target+1];
        Arrays.fill(dp, -1);

        System.out.println(memoSol(arr, n, n-1, target, dp));

        System.out.println(tabSol(arr, n, target));
        System.out.println(tabulationSol(arr, n, target));

    }

    public static int tabSol(int[] arr, int n, int target){
        int[][] dp = new int[n+1][target+1];

        // dp[i][0] = 1;
        for(int i = 0; i<n; i++){
            dp[i][0] = 1;
        }

        // dp[i][k] = number of ways to construct sum k such that
        // all the coins before coin[i] are unusable
        // and all the coins from i to n-1 are usable

        // dp[i][k] = dp[i+1][k](Not Picking) + dp[i][k- coin value] (Picking)
        // To construct result for i, we need result of (i+1)(Not Picking) and i (picking)
        // So, i values goes from N to 0 because we need (i + 1)th result to solve for ith.
        // and k values goes from 1 to k


        for(int i = n-1; i>=0; i--){
            for(int t=1; t<=target; t++){
                // not Picking
                int notPick = dp[i+1][t];
                int pick = 0;
                if(arr[i] <= t){
                    pick = dp[i][t-arr[i]];
                }
                dp[i][t] = (pick + notPick) % MOD;
            }
        }
        return dp[0][target];
    }


    public static int tabulationSol(int[] arr, int n, int target) {
        int[] dp = new int[target + 1];

        // Base case: There's exactly one way to make target sum 0, by picking no coins.
        dp[0] = 1;

        // Iterate over each coin
        for (int i = 0; i < n; i++) {
            // Update the dp array from the back to the front
            for (int t = arr[i]; t <= target; t++) {
                dp[t] = (dp[t] + dp[t - arr[i]]) % MOD;
            }
        }

        return dp[target];
    }

    public static int memoSol(int[] arr, int n, int index, int target, int[] dp){
        if(index < 0){
            return 0;
        }

        if (target == 0) {
            return 1;
        }

        if(dp[target] != -1){
            return dp[target];
        }

        int pick = 0;
        if(arr[index] <= target){
            pick = recursiveSol(arr, n, index, target - arr[index]);
        }
        int notPick = recursiveSol(arr, n, index - 1, target);

        return dp[target] = (pick + notPick) % MOD;
    }

    public static int recursiveSol(int[] arr, int n, int index, int target){
        if(index < 0){
            return 0;
        }

        if (target == 0) {
            return 1;
        }

        int pick = 0;
        if(arr[index] <= target){
            pick = recursiveSol(arr, n, index, target - arr[index]);
        }
        int notPick = recursiveSol(arr, n, index - 1, target);

        return (pick + notPick) % MOD;
    }


}
