package edu.ssafy.im.BOJ.Silver.S2.No11722;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, ans = 0;
    private static int[] A, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N];
        for (int a : A) {
            for (int i = 0; i < N; i++) {
                if (dp[i] <= a) { // 큰 값 순서대로 해당 위치에 갱신
                    dp[i] = a;
                    break;
                }
            }
        }

        for (int d : dp) { // 값이 비어있는 경우 길이 끝
            if (d != 0) ans++;
            else break;
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
