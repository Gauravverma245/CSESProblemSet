

import java.util.Arrays;
import java.util.Scanner;

public class RemovingDigits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(tabSol(n));
    }

    public static int tabSol(int n){
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 1; i<=n; i++){
            String number = String.valueOf(i);
            for(char c: number.toCharArray()){
                int digit = c - '0';
                if(digit != 0) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - digit]);
                }
            }
        }
        return dp[n];
    }
}
