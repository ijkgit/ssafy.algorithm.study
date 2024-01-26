package edu.ssafy.im.BOJ.No16234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] graph;
    static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int n, l, r;
    static boolean[][] visited;
    static int[][] union;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        graph = new int[n][n];

        for (int x = 0; x < n; x++) {
            input = br.readLine();
            st = new StringTokenizer(input);
            for (int y = 0; y < n; y++) {
                graph[x][y] = Integer.parseInt(st.nextToken());
            }
        }


        union = new int[n][n];
        int day = 0;
        while (true) {
            visited = new boolean[n][n];
            int cnt = 1;
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    if (!visited[x][y]) {
                        dfs(x, y, cnt);
                        cnt++;
                    }
                }
            }

            if (cnt == n * n + 1)
                break;

            int sum = 0;
            int denominator = 0;
            for (int i = 1; i <= cnt; i++) {
                for (int x = 0; x < n; x++) {
                    for (int y = 0; y < n; y++) {
                        if (union[x][y] == i) {
                            sum += graph[x][y];
                            denominator++;
                        }
                    }
                }
                int unionResult = sum / denominator;
                for (int x = 0; x < n; x++) {
                    for (int y = 0; y < n; y++) {
                        if (union[x][y] == i) {
                            graph[x][y] = unionResult;
                        }
                    }
                }
            }

            System.out.println("---union---");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(union[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("---graph---");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(graph[i][j] + " ");
                }
                System.out.println();
            }
            day++;
        }
        sb.append(day);
        System.out.print(sb);
    }

    public static void dfs(int x, int y, int cnt) {
        visited[x][y] = true;
        union[x][y] = cnt;

        for (int[] d : direction) {
            int newX = x + d[0];
            int newY = y + d[1];

            if (checkStatus(newX, newY)) {
                if (checkRange(x, y, newX, newY)) {
                    if (!visited[newX][newY]) {
                        dfs(newX, newY, cnt);
                    }
                }
            }
        }
    }

    public static boolean checkStatus(int x, int y) {
        return (0 <= x && x < n && 0 <= y && y < n);
    }

    public static boolean checkRange(int x, int y, int newX, int newY) {
        int diff = Math.abs(graph[x][y] - graph[newX][newY]);
        return (l <= diff && diff <= r);
    }

}
