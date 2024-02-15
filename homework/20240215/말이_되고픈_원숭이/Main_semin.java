package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class boj1600 {
    static int K, W, H, maps[][];
    static boolean[][][] visited; // 정상이 0, 말 이동 한 게 1
    static Deque<Point> dq = new ArrayDeque<>();
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static int[] hx = {-1, 1, 2, 2, 1, -1, -2, -2};
    static int[] hy = {2, 2, 1, -1, -2, -2, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        maps = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[H][W][K + 1];
        bfs();
    }

    private static void bfs() {
        dq.clear();
        visited[0][0][K] = true;
        dq.add(new Point(0, 0, 0, K));
        while (!dq.isEmpty()) {
            Point p = dq.poll();
            if (p.x == W - 1 && p.y == H - 1) {
                System.out.println(p.dist);
                return;
            }
            // 말 이동 하는 경우
            if (p.k > 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = p.x + hx[i];
                    int ny = p.y + hy[i];
                    if (nx < 0 || ny < 0 || nx >= W || ny >= H) {
                        continue;
                    }
                    if (maps[ny][nx] == 1 || visited[ny][nx][p.k - 1]) {
                        continue;
                    }
                    Point np = new Point(nx, ny, p.dist + 1, p.k - 1);
                    dq.add(np);
                    visited[ny][nx][p.k - 1] = true;
                }
            }

            // 정상 이동 하는 경우 (장애물 있으면 못감)
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= W || ny >= H) {
                    continue;
                }
                if (maps[ny][nx] == 1 || visited[ny][nx][p.k]) {
                    continue;
                }
                Point np = new Point(nx, ny, p.dist + 1, p.k);
                dq.add(np);
                visited[ny][nx][p.k] = true;
            }
        }
        System.out.println(-1);
    }

    static class Point {
        int x;
        int y;
        int dist;
        int k;

        Point(int x, int y, int dist, int k) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.k = k;
        }

        @Override
        public String toString() {
            return "Point [x=" + x + ", y=" + y + ", dist=" + dist + ", k=" + k + "]";
        }

    }
}