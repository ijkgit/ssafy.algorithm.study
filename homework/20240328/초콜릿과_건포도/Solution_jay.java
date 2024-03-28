package edu.ssafy.im.SWEA.D4.No9282;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    private static int TC, N, M;
    private static int[][] map;
    private static int[][][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        TC = Integer.parseInt(br.readLine());
        for (int T = 1; T <= TC; T++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp = new int[N+1][M+1][N+1][M+1];
            for (int[][][] d1 : dp) {
                for (int[][] d2 : d1) {
                    for (int[] d3 : d2) {
                        Arrays.fill(d3, Integer.MAX_VALUE);
                    }
                }
            }

            sb.append("#").append(T).append(" ").append(dfs(0, 0, N, M)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int dfs(int x, int y, int h, int w) {
        if (h == 1 && w == 1) return 0;

        int sum = 0;
        for (int i = x; i < x + h; i++) {
            for (int j = y; j < y + w; j++) {
                sum += map[i][j];
            }
        }

        for (int i = 1; i < h; i++) {
            if (dp[x][y][i][w] == Integer.MAX_VALUE) dp[x][y][i][w] = dfs(x, y, i, w);
            if (dp[x + i][y][h - i][w] == Integer.MAX_VALUE) dp[x + i][y][h - i][w] = dfs(x + i, y, h - i, w);
            dp[x][y][h][w] = Math.min(dp[x][y][h][w], sum + dp[x][y][i][w] + dp[x + i][y][h - i][w]);
        }

        for (int i = 1; i < w; i++) {
            if (dp[x][y][h][i] == Integer.MAX_VALUE) dp[x][y][h][i] = dfs(x, y, h, i);
            if (dp[x][y + i][h][w - i] == Integer.MAX_VALUE) dp[x][y + i][h][w - i] = dfs(x, y + i, h, w - i);
            dp[x][y][h][w] = Math.min(dp[x][y][h][w], sum + dp[x][y][h][i] + dp[x][y + i][h][w - i]);
        }

        return dp[x][y][h][w];
    }
}
