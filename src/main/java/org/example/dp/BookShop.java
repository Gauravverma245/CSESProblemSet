package org.example.dp;/*
 * @author gauravverma
 */

import java.util.Arrays;
import java.util.Scanner;

public class BookShop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();

        int[] books = new int[n];
        int[] pages = new int[n];
        for(int i = 0; i<n; i++){
            books[i] = sc.nextInt();
        }

        for(int i = 0; i<n; i++){
            pages[i] = sc.nextInt();
        }

        System.out.println(recursiveSol(books, pages, n-1, target));


        // memo
        int[][] dp = new int[n][target+1];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        System.out.println(memoSol(books, pages, n-1, target, dp));

        System.out.println(tabulationSol(books, pages, target));
        System.out.println(tabulationOptimizedSol(books, pages, target));

    }

    public static int tabulationOptimizedSol(int[] books, int[] pages, int target) {
        int n = books.length;
        int[] prev = new int[target + 1];
        int[] curr = new int[target + 1];

        // NotPick => dp[i-1][j] = prev[t];
        // Pick =>  dp[i][j] = curr[t] = pages[i] + prev[t-books[i]]
        for (int i = 0; i < n; i++) {
            for (int t = 0; t <= target; t++) {
                int notPick = prev[t]; // Not picking the current book
                int pick = (books[i] <= t) ? pages[i] + prev[t - books[i]] : Integer.MIN_VALUE; // Picking the current book if possible
                curr[t] = Math.max(pick, notPick);
            }
            // Move current row to previous row for next iteration
            System.arraycopy(curr, 0, prev, 0, target + 1);
        }

        return prev[target];
    }


    public static int tabulationSol(int[] books, int[] pages, int target) {
        int n = books.length;
        int[][] dp = new int[n][target + 1];

        // Initialize the dp array for the base cases
        for (int i = 0; i < n; i++) {
            for (int t = 0; t <= target; t++) {
                if (i == 0) {
                    // If there's only one book, we can take it only if its size is <= target
                    if (books[i] <= t) {
                        dp[i][t] = pages[i];
                    } else {
                        dp[i][t] = 0;
                    }
                } else {
                    int notPick = dp[i - 1][t]; // Not picking the current book
                    int pick = Integer.MIN_VALUE;
                    if (books[i] <= t) {
                        pick = pages[i] + dp[i - 1][t - books[i]]; // Picking the current book
                    }
                    dp[i][t] = Math.max(pick, notPick); // Take the maximum of picking or not picking
                }
            }
        }

        return dp[n - 1][target];
    }


    public static int memoSol(int[] books, int[] pages, int index, int target, int[][] dp){
        if(index < 0){
            return 0;
        }

        if(dp[index][target] != -1){
            return dp[index][target];
        }

        //pick
        int pick = Integer.MIN_VALUE;
        if(books[index] <= target){
            pick = pages[index] + memoSol(books,pages,index-1,target-books[index],dp);
        }
        // not pick
        int notPick = memoSol(books, pages, index-1, target, dp);

        return dp[index][target] = Math.max(pick, notPick);
    }

    public static int recursiveSol(int[] books, int[] pages, int index, int target){
        if(index < 0){
            return 0;
        }

        //pick
        int pick = Integer.MIN_VALUE;
        if(books[index] <= target){
            pick = pages[index] + recursiveSol(books,pages,index-1,target-books[index]);
        }
        // not pick
        int notPick = recursiveSol(books, pages, index-1, target);

        return Math.max(pick, notPick);
    }
}
