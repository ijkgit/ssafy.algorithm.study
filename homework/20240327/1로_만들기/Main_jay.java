package edu.ssafy.im.BOJ.Silver.S3.No1463.second;

import java.io.*;

public class Main {
    private static int N;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];

        dp[1] = 0;
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i-1] + 1;
            if (i % 3 == 0) dp[i] = Math.min(dp[i], dp[i/3] + 1);
            if (i % 2 == 0) dp[i] = Math.min(dp[i], dp[i/2] + 1);
        }

        sb.append(dp[N]);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
