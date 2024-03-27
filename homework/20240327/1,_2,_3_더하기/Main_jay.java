package edu.ssafy.im.BOJ.Silver.S3.No9095;

import java.io.*;

public class Main {
    private static int T;
    private static int[] dp = new int[11];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        sol();

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void sol() {
        dp[1] = 1; dp[2] = 2; dp[3] = 4;
        for(int i = 4; i < dp.length; i++) {
            for (int j = i-3; j < i; j++) {
                dp[i] += dp[j];
            }
        }
    }
}
