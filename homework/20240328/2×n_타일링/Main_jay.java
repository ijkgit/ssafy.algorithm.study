package edu.ssafy.im.BOJ.Silver.S3.No11726;


import java.io.*;

public class Main {
    private static int N;
    private static int[] dp;

    // f(1) =  1, f(2) = 2, f(3) = 3, f(4) = 5, f(5) = 8
    // f(n+2) = f(n+1) + f(n)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            if (i < 3) dp[i] = i;
            else dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
        }

        sb.append(dp[N]);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
