package org.example.dp;/*
 * @author gauravverma
 */

import java.util.Arrays;
import java.util.Scanner;

public class EditDistance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();

        int m = s1.length();
        int n = s2.length();

        System.out.println(recursiveSol(s1, s2, m-1, n-1));

        // memo
        int[][] dp = new int[m][n];
        for(int[] row: dp){
            Arrays.fill(row, -1);
        }
        System.out.println(memoSol(s1, s2, m-1, n-1, dp));

        System.out.println(tabSol(s1, s2, m, n));
    }

    public static int tabSol(String s1, String s2, int m, int n){
        int[][] dp = new int[m+1][n+1];
        // when s1 string length is zero, then we have add s2.length() character into s1.
        for(int i=0; i<=m; i++){
            dp[i][0] = i;
        }

        for(int j=0; j<=n; j++){
            dp[0][j] = j;
        }

        for(int i = 1; i<=m; i++){
            for(int j = 1; j<=n; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
                }
            }
        }

        return dp[m][n];

    }

    public static int memoSol(String s1, String s2, int s1Index, int s2Index, int[][] dp) {
        if (s1Index < 0) {
            return s2Index + 1;
        }

        if (s2Index < 0) {
            return s1Index + 1;
        }

        if (dp[s1Index][s2Index] != -1) {
            return dp[s1Index][s2Index];
        }

        if (s1.charAt(s1Index) == s2.charAt(s2Index)) {
            dp[s1Index][s2Index] = memoSol(s1, s2, s1Index - 1, s2Index - 1, dp);
        } else {
            int remove = 1 + memoSol(s1, s2, s1Index - 1, s2Index, dp);
            int insert = 1 + memoSol(s1, s2, s1Index, s2Index - 1, dp);
            int replace = 1 + memoSol(s1, s2, s1Index - 1, s2Index - 1, dp);
            dp[s1Index][s2Index] = Math.min(remove, Math.min(insert, replace));
        }
        return dp[s1Index][s2Index];
    }


    public static int recursiveSol(String s1, String s2, int s1Index, int s2Index){
        if(s1Index < 0){
            return s2Index + 1;
        }

        if(s2Index < 0){
            return s1Index + 1;
        }

        if(s1.charAt(s1Index) == s2.charAt(s2Index)){
            return recursiveSol(s1, s2, s1Index -1, s2Index - 1);
        }else{
            int remove = 1 + recursiveSol(s1, s2, s1Index - 1, s2Index);
            int insert = 1 + recursiveSol(s1, s2, s1Index, s2Index - 1);
            int replace = 1 + recursiveSol(s1, s2, s1Index - 1, s2Index - 1);
            return Math.min(remove, Math.min(insert, replace));
        }
    }
}
