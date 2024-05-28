package org.example.dp;/*
 * @author gauravverma
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MoneySums {
    static final int maxSum = 100000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n+1];
        for(int i = 1; i<=n; i++){
            arr[i] = sc.nextInt();
        }

        boolean[][] dp = new boolean[n+1][maxSum + 1];
        dp[0][0] = true;
        for(int i = 1; i<=n; i++){
            for(int currSum = 0; currSum <= maxSum; currSum++){
                dp[i][currSum] = dp[i-1][currSum];
                int prevSum = currSum - arr[i];
                if(prevSum >=0 && dp[i-1][prevSum]){
                    dp[i][currSum] = true;
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        for(int i = 1; i<=maxSum; i++){
            if(dp[n][i]){
                ans.add(i);
            }
        }
        System.out.println(ans.size());
        for(int num: ans){
            System.out.print(num + " ");
        }

    }
}
