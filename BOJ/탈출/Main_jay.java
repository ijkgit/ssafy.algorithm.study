package edu.ssafy.im.BOJ.Gold.G4.No3055;

import java.io.*;
import java.util.*;

public class Main {
    private static int R;
    private static int C;
    private static char[][] map;
    private static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static Point start;
    private static ArrayDeque<Point> q;
    private static int ans = -1;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        q = new ArrayDeque<>();
        for (int i = 0; i < R; i++) {
            String row = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = row.charAt(j);
                if (map[i][j] == '*') q.offer(new Point(i, j, 0));
                if (map[i][j] == 'S') start = new Point(i, j, 0);
            }
        }

        sol();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void sol() {
        ans = go();
        if (ans == -1) sb.append("KAKTUS");
        else sb.append(ans);
    }

    private static int go() {
        q.add(start);
        while(!q.isEmpty()) {
            Point p = q.poll();
            for (int d = 0; d < direction.length; d++) {
                int nx = p.x + direction[d][0];
                int ny = p.y + direction[d][1];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if (map[nx][ny] == 'X') continue;
                if (map[nx][ny] == '.') {
                    q.offer(new Point(nx, ny, p.t + 1));
                    if (map[p.x][p.y] == '*') map[nx][ny] = '*';
                    if (map[p.x][p.y] == 'S') map[nx][ny] = 'S';
                } else if (map[nx][ny] == 'D') {
                    if (map[p.x][p.y] == 'S') return p.t+1;
                }
            }
        }
        return -1;
    }

    static class Point {
        int x, y, t;

        public Point(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }
}
