import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
    static int N, W, H, maps[][], ans;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Deque<Point> dq = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            maps = new int[H][W];
            ans = Integer.MAX_VALUE;
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    maps[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int[] selected = new int[N];
            dupCombination(selected, 0, 0);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void dupCombination(int[] selected, int w, int k) {
        if (k == selected.length) {
            ans = Math.min(solve(selected), ans);
            return;
        }
        for (int i = 0; i < W; i++) {
            selected[k] = i;
            dupCombination(selected, i, k + 1);
        }
    }

    private static int solve(int[] selected) {
        int[][] copy = makeCopy(maps);
        for (int y : selected) {
            for (int i = 0; i < H; i++) {
                if (copy[i][y] != 0) {
                    shoot(i, y, copy);
                    break;
                }
            }
        }
        return countLeft(copy);
    }

    private static int[][] makeCopy(int[][] maps) {
        int[][] copy = new int[H][W];
        for (int i = 0; i < H; i++) {
            copy[i] = maps[i].clone();
        }
        return copy;
    }

    private static int countLeft(int[][] maps) {
        int sum = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (maps[i][j] != 0) {
                    sum++;
                }
            }
        }
        return sum;
    }

    private static void shoot(int x, int y, int[][] maps) {
        dq.clear();
        dq.add(new Point(x, y));
        while (!dq.isEmpty()) {
            Point p = dq.poll();
            x = p.x;
            y = p.y;
            int num = maps[x][y];
            maps[x][y] = 0;
            for (int d = 0; d < 4; d++) {
                for (int cnt = 1; cnt < num; cnt++) {
                    int nx = x + dx[d] * cnt;
                    int ny = y + dy[d] * cnt;
                    if (nx < 0 || ny < 0 || nx >= H || ny >= W) {
                        continue;
                    }
                    if (maps[nx][ny] == 0) {
                        continue;
                    }
                    dq.add(new Point(nx, ny));
                }
            }
        }
        moveDown(maps);
    }

    private static void moveDown(int[][] maps) {
        for (int j = 0; j < W; j++) {
            for (int i = H - 1; i > 0; i--) {
                if (maps[i][j] == 0) {
                    for (int k = i - 1; k >= 0; k--) {
                        if (maps[k][j] != 0) {
                            maps[i][j] = maps[k][j];
                            maps[k][j] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }
}
