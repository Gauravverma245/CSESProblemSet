
import java.util.Arrays;
import java.util.Scanner;

public class MinimizingCoins {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();

        int[] arr = new int[n];
        for(int i = 0; i<n; i++){
            arr[i] = sc.nextInt();
        }
        System.out.println(minimumCoinsTabulation(arr, target, n));
    }

    public static int minimumCoinsTabulation(int[] coins, int target, int n) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, Integer.MAX_VALUE-1);
        dp[0] = 0;

        for(int t = 1; t<=target; t++){
            for(int coin=0; coin<n; coin++){
                if (coins[coin] <= t) {
                    dp[t] = Math.min(dp[t], dp[t - coins[coin]] + 1);
                }
            }
        }

        return dp[target] != Integer.MAX_VALUE-1 ? dp[target] : -1;
    }
}
