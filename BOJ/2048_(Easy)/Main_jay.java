import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int N, ans = 0;
    private final static int LIMIT = 5;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sol(0, map);

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void sol(int k, int[][] map) {
        if (k == LIMIT) {
            ans = Math.max(ans, check(map));
            return;
        }

        for (int d = 0; d < 4; d++) {
            int[][] copy = new int[N][N];
            for (int i = 0; i < N; i++) copy[i] = map[i].clone();
            sol(k + 1, run(d, copy));
        }
    }

    private static int check(int[][] map) {
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                res = Math.max(res, map[i][j]);
            }
        }
        return res;
    }

    private static int[][] run(int d, int[][] map) {
        if (d == 0) {
            for (int x = 0; x < N; x++) {
                int cursor = N - 1, count = 0;
                for (int y = N - 1; y >= 0; y--) {
                    if (map[x][y] == 0) count++;
                    else {
                        map[x][cursor] = map[x][y];
                        cursor--;
                    }
                }
                for (int y = 0; y < count; y++) {
                    map[x][y] = 0;
                }
            }
            for (int x = 0; x < N; x++) {
                for (int y = N - 1; y >= 1; y--) {
                    if (map[x][y] != map[x][y - 1]) continue;
                    map[x][y] *= 2;
                    map[x][y - 1] = 0;
                }
            }
            for (int x = 0; x < N; x++) {
                int cursor = N - 1, count = 0;
                for (int y = N - 1; y >= 0; y--) {
                    if (map[x][y] == 0) count++;
                    else {
                        map[x][cursor] = map[x][y];
                        cursor--;
                    }
                }
                for (int y = 0; y < count; y++) {
                    map[x][y] = 0;
                }
            }
        }

        if (d == 1) {
            for (int x = 0; x < N; x++) {
                int cursor = 0, count = 0;
                for (int y = 0; y < N; y++) {
                    if (map[x][y] == 0) count++;
                    else {
                        map[x][cursor] = map[x][y];
                        cursor++;
                    }
                }
                for (int y = N - 1; y > N - 1 - count; y--) {
                    map[x][y] = 0;
                }
            }
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N - 1; y++) {
                    if (map[x][y] != map[x][y + 1]) continue;
                    map[x][y] *= 2;
                    map[x][y + 1] = 0;
                }
            }
            for (int x = 0; x < N; x++) {
                int cursor = 0, count = 0;
                for (int y = 0; y < N; y++) {
                    if (map[x][y] == 0) count++;
                    else {
                        map[x][cursor] = map[x][y];
                        cursor++;
                    }
                }
                for (int y = N - 1; y > N - 1 - count; y--) {
                    map[x][y] = 0;
                }
            }
        }

        if (d == 2) {
            for (int y = 0; y < N; y++) {
                int cursor = N - 1, count = 0;
                for (int x = N - 1; x >= 0; x--) {
                    if (map[x][y] == 0) count++;
                    else {
                        map[cursor][y] = map[x][y];
                        cursor--;
                    }
                }
                for (int x = 0; x < count; x++) {
                    map[x][y] = 0;
                }
            }
            for (int y = 0; y < N; y++) {
                for (int x = N - 1; x >= 1; x--) {
                    if (map[x][y] != map[x - 1][y]) continue;
                    map[x][y] *= 2;
                    map[x - 1][y] = 0;
                }
            }
            for (int y = 0; y < N; y++) {
                int cursor = N - 1, count = 0;
                for (int x = N - 1; x >= 0; x--) {
                    if (map[x][y] == 0) count++;
                    else {
                        map[cursor][y] = map[x][y];
                        cursor--;
                    }
                }
                for (int x = 0; x < count; x++) {
                    map[x][y] = 0;
                }
            }
        }

        if (d == 3) {
            for (int y = 0; y < N; y++) {
                int cursor = 0, count = 0;
                for (int x = 0; x < N; x++) {
                    if (map[x][y] == 0) count++;
                    else {
                        map[cursor][y] = map[x][y];
                        cursor++;
                    }
                }
                for (int x = N - 1; x > N - 1 - count; x--) {
                    map[x][y] = 0;
                }
            }
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N - 1; x++) {
                    if (map[x][y] != map[x + 1][y]) continue;
                    map[x][y] *= 2;
                    map[x + 1][y] = 0;
                }
            }
            for (int y = 0; y < N; y++) {
                int cursor = 0, count = 0;
                for (int x = 0; x < N; x++) {
                    if (map[x][y] == 0) count++;
                    else {
                        map[cursor][y] = map[x][y];
                        cursor++;
                    }
                }
                for (int x = N - 1; x > N - 1 - count; x--) {
                    map[x][y] = 0;
                }
            }
        }

        return map;
    }
}
