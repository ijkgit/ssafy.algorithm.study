package edu.ssafy.im.BOJ.No16234;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.signum;

public class Main {
    static int N, L, R;
    static int[][] maps;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        maps = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        while (true) {
            boolean isMoved = false;
            visited = new boolean[N][N];
            List<Map<Integer, List<Point>>> pairs = new ArrayList<>();
            List<Point> unions;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        unions = new ArrayList<>();
                        Map<Integer, List<Point>> pair = bfs(j, i, unions);
                        if (pair != null) {
                            pairs.add(pair);
                            isMoved = true;
                        }
                    }
                }
            }
            if (!isMoved) {
                break;
            }
            for (Map<Integer, List<Point>> pair : pairs) {
                for (Integer avg : pair.keySet()) {
                    for (Point p : pair.get(avg)) {
                        maps[p.y][p.x] = avg;
                    }
                }
            }
            day++;
        }
        System.out.println(day);
    }

    public static Map<Integer, List<Point>> bfs(int sx, int sy, List<Point> unions) {
        Deque<Point> dq = new ArrayDeque<>();
        dq.add(new Point(sx, sy));
        unions.add(new Point(sx, sy));
        visited[sy][sx] = true;
        int setSum = maps[sy][sx];
        int setCnt = 1;
        while (!dq.isEmpty()) {
            Point p = dq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + p.x;
                int ny = dy[i] + p.y;
                if (nx >= N || ny >= N || nx < 0 || ny < 0) {
                    continue;
                }
                if (visited[ny][nx]) {
                    continue;
                }
                if (abs(maps[p.y][p.x] - maps[ny][nx]) >= L && abs(maps[p.y][p.x] - maps[ny][nx]) <= R) {
                    dq.add(new Point(nx, ny));
                    unions.add(new Point(nx, ny));
                    visited[ny][nx] = true;
                    setSum += maps[ny][nx];
                    setCnt++;
                }
            }
        }
        if (setCnt == 1) {
            return null;
        }
        Map<Integer, List<Point>> pair = new HashMap<>();
        pair.put(setSum / setCnt, unions);
        return pair;
    }
}