package edu.ssafy.im.CodeTree.toastEggmold;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    private static int N, L, R;
    private static int[][] map, union;
    private static int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        sb.append(sol());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int sol() {
        int ans = 0;
        while (makeUnion() != 0) {
            move();
            ans++;
        }
        return ans;
    }

    private static void initUnion() {
        union = new int[N][N];
        int num = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                union[i][j] = num++;
            }
        }
    }

    private static int makeUnion() {
        int cnt = 0;

        initUnion();

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                for (int[] d : direction) {
                    int nx = x + d[0];
                    int ny = y + d[1];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if (!check(map[x][y], map[nx][ny])) continue;
                    setUnion(union[nx][ny], union[x][y]);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void setUnion(int u, int n) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (union[x][y] == u) union[x][y] = n;
            }
        }
    }

    private static void move() {
        visited = new boolean[N][N];
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                bfs(x, y);
            }
        }
    }

    private static void bfs(int x, int y) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        ArrayDeque<int[]> unions = new ArrayDeque<>();
        visited[x][y] = true;
        queue.offer(new int[]{x, y});
        unions.offer(new int[]{x, y});

        int sum = map[x][y];
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] d : direction) {
                int nx = cur[0] + d[0];
                int ny = cur[1] + d[1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny]) continue;
                if (union[x][y] != union[nx][ny]) continue;

                visited[nx][ny] = true;
                sum += map[nx][ny];
                queue.offer(new int[]{nx, ny});
                unions.offer(new int[]{nx, ny});
            }
        }
        for (int[] u : unions) map[u[0]][u[1]] = sum / unions.size();
    }

    private static boolean check(int a, int b) {
        return L <= Math.abs(a - b) && Math.abs(a - b) <= R;
    }
}
