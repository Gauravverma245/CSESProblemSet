package org.example.dp;/*
 * @author gauravverma
 */
import java.util.Arrays;
import java.util.Scanner;

public class CoinCombinationsI {
    static final int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();

        int[] arr = new int[n];
        for(int i = 0; i<n; i++){
            arr[i] = sc.nextInt();
        }

        System.out.println(recursionSol(arr, n, target));

        int[] dp = new int[target+1];
        Arrays.fill(dp, -1);

        System.out.println(memoSol(arr, n, target, dp));

        System.out.println(tabSol(arr, n, target));

    }

    public static int tabSol(int[] arr, int n, int target){
        int[] dp = new int[target+1];
        dp[0] = 1;

        for(int smallTarget=1; smallTarget<=target; smallTarget++){
            for(int coinIndex=0; coinIndex<n; coinIndex++){
                int coinValue = arr[coinIndex];
                if(coinValue <= smallTarget){
                    dp[smallTarget] = (dp[smallTarget] +  dp[smallTarget - coinValue]) % MOD;
                }
            }
        }
        return dp[target];
    }

    public static int memoSol(int[] arr, int n, int target, int[] dp){
        if(target == 0){
            return 1;
        }

        if(dp[target] != -1){
            return dp[target];
        }

        int ans = 0;
        for(int i = 0; i<n; i++){
            if(arr[i] <= target){
                ans = (ans +  memoSol(arr, n, target - arr[i], dp)) % MOD;
            }
        }
        return dp[target] = ans;
    }

    public static int recursionSol(int[] arr, int n, int target){
        if(target == 0){
            return 1;
        }
        int ans = 0;
        for(int i = 0; i<n; i++){
            if(arr[i] <= target){
                ans += recursionSol(arr, n, target - arr[i]);
            }
        }
        return ans;
    }
}
