import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, K, C;
    private static int[][] map, copyMap, visited;
    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static final int[][] diagonal = {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        copyMap = new int[N][N];
        visited = new int[N][N];
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < N; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        sb.append(sol());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void updateVisited() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (visited[x][y] > 0) visited[x][y]--;
                else if (map[x][y] == -2) map[x][y] = 0;
            }
        }
    }

    private static int sol() {
        for (int m = 0; m < M; m++) {
            updateVisited();
            grow();
            breed();
            kill();
        }
        return ans;
    }

    private static void kill() {
        Tree tree = findMax();
        if (tree.x != -1) spread(tree);
    }

    private static void spread(Tree tree) {
        map[tree.x][tree.y] = -2;
        visited[tree.x][tree.y] = C;
        for (int[] d : diagonal) {
            for (int k = 1; k <= K; k++) {
                int nx = tree.x + d[0] * k;
                int ny = tree.y + d[1] * k;

                if (checkRange(nx, ny)) break;
                if (map[nx][ny] == -1) break;
                int tmp = map[nx][ny];
                map[nx][ny] = -2;
                visited[nx][ny] = C;
                if (tmp == 0 || tmp == -2) break;
            }
        }
    }

    private static Tree findMax() {
        int rs = 0, rx = -1, ry = -1;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (map[x][y] <= 0) continue;
                int sum = map[x][y];
                for (int[] d : diagonal) {
                    for (int k = 1; k <= K; k++) {
                        int nx = x + d[0] * k;
                        int ny = y + d[1] * k;

                        if (checkRange(nx, ny) || map[nx][ny] <= 0) break;
                        sum += map[nx][ny];
                    }
                }
                if (sum < rs || (sum == rs && x > rx) || (sum == rs && x == rx && y > ry)) continue;
                rs = sum;
                rx = x;
                ry = y;
            }
        }

        ans += rs;

        return new Tree(rx, ry);
    }

    private static void breed() {
        copy();

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (map[x][y] <= 0) continue;

                int count = 0;
                for (int[] d : direction) {
                    int nx = x + d[0];
                    int ny = y + d[1];

                    if (checkRange(nx, ny)) continue;
                    if (map[nx][ny] == 0) count++;
                }

                if (count == 0) continue;
                int newTree = map[x][y] / count;

                for (int[] d : direction) {
                    int nx = x + d[0];
                    int ny = y + d[1];

                    if (checkRange(nx, ny)) continue;
                    if (map[nx][ny] != 0) continue;

                    copyMap[nx][ny] += newTree;
                }
            }
        }

        paste();
    }

    private static void paste() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                map[x][y] = copyMap[x][y];
            }
        }
    }

    private static void copy() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                copyMap[x][y] = map[x][y];
            }
        }
    }

    private static void grow() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (map[x][y] <= 0) continue;
                int count = 0;
                for (int[] d : direction) {
                    int nx = x + d[0];
                    int ny = y + d[1];

                    if (checkRange(nx, ny)) continue;
                    if (map[nx][ny] > 0) count++;
                }
                map[x][y] += count;
            }
        }
    }

    private static boolean checkRange(int x, int y) {
        return 0 > x || x >= N || 0 > y || y >= N;
    }

    static class Tree {
        int x, y;

        public Tree(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
