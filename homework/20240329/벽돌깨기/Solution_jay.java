import java.io.*;
import java.util.*;

public class Solution {
    private static int N, W, H;
    private static int[][] map, original;
    private static int[] sel;
    private final static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static boolean[][] v;
    private static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());
        for (int T = 1; T <= TC; T++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H][W];
            original = new int[H][W];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    original[i][j] = map[i][j];
                }
            }
            sol();
            sb.append("#").append(T).append(" ").append(ans).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void sol() {
        ans = Integer.MAX_VALUE;
        sel = new int[N];
        permutation(0);
    }


    private static void permutation(int k) {
        if (k == N) {
            for (int s : sel) shoot(0, s);
            ans = Math.min(ans, count());
            setOriginal();
            return;
        }

        for (int i = 0; i < W; i++) {
            sel[k] = i;
            permutation(k + 1);
        }
    }

    private static void setOriginal() {
        for (int i = 0; i < H; i++) {
            map[i] = original[i].clone();
        }
    }

    private static int count() {
        int cnt = 0;
        for (int[] mx : map) {
            for (int my : mx) {
                if (my != 0) cnt++;
            }
        }
        return cnt;
    }

    private static void shoot(int x, int y) {
        if (x == H) return;

        if (map[x][y] == 0) shoot(x + 1, y);
        else {
            bomb(x, y);
            update();
        }
    }

    private static void update() {
        for (int y = 0; y < W; y++) {
            int cur = H - 1;
            for (int x = H - 1; x >= 0; x--) {
                if (v[x][y]) continue;
                map[cur][y] = map[x][y];
                cur--;
            }
            for (int c = cur; c >= 0; c--) {
                map[c][y] = 0;
            }
        }
    }

    private static void bomb(int x, int y) {
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(x, y));
        v = new boolean[H][W];
        v[x][y] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int depth = 1; depth <= map[p.x][p.y] - 1; depth++) {
                for (int[] d : direction) {

                    int nx = p.x + d[0] * depth;
                    int ny = p.y + d[1] * depth;

                    if (nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
                    if (map[nx][ny] == 0 || v[nx][ny]) continue;

                    v[nx][ny] = true;
                    q.offer(new Point(nx, ny));
                }
            }
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
