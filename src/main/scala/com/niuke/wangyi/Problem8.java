package com.niuke.wangyi;



import java.util.Scanner;

public class Problem8 {
    private final static int MOD = 1000000007;

    public static void main(String [] args){
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            int n = in.nextInt();
            int k = in.nextInt();

            int [][] state = new int[n + 1][k + 1];
            state[0][1] = 1;
            for (int i = 1; i <= k; i++){
                state[1][i] = i;
            }

            for (int i = 1; i <= n; i++){
                int sum = 0;
                for (int j = 1; j <= k; j++){
                    sum = (sum + state[i - 1][j]) % MOD;
                }


                for (int j = 1; j <= k; j++){
                    int invalid = 0;
                    int p = 2;
                    while (p * j <= k){
                        invalid = (invalid + state[i - 1][p * j]) % MOD;
                        p++;
                    }
                    state[i][j] = (sum - invalid + MOD) % MOD;
                }

            }
            int sum = 0;
            for (int i = 1; i <= k; i++){
                sum = (sum + state[n][i]) % MOD;
            }
            System.out.println(sum);

        }

    }

}
