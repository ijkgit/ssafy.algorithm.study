package edu.ssafy.im.SWEA.D4.No7699;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    private char[][] graph;
    private int key;
    private int ans;
    private int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static void main(String[] args) throws IOException {
        new Solution().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph = new char[r][c];
            for (int x = 0; x < graph.length; x++) {
                String string = br.readLine();
                for (int y = 0; y < graph[0].length; y++) {
                    graph[x][y] = string.charAt(y);
                }
            }
            key = 1 << graph[0][0] - 'A';
            ans = 0;

            sol(0, 0, 1);

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private void sol(int x, int y, int depth) {
        ans = Math.max(ans, depth);

        for (int d = 0; d < direction.length; d++) {
            int nx = x + direction[d][0];
            int ny = y + direction[d][1];

            if(checkStatus(nx, ny)) {
                if(checkKey(nx, ny)) {
                    key |= 1 << graph[nx][ny] - 'A';
                    sol(nx, ny, depth+1);
                    key -= 1 << graph[nx][ny] - 'A';
                }
            }
        }
    }

    private boolean checkStatus(int x, int y) {
        return 0 <= x && x < graph.length && 0 <= y && y < graph[0].length;
    }

    private boolean checkKey(int x, int y) {
        return (key & (1 << graph[x][y] - 'A')) == 0;
    }
}
