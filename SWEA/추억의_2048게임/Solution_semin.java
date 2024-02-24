import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_semin {
    static int N;
    static int[][] maps, result;
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append("\n");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            maps = new int[N][N];
            result = new int[N][N];
            String calc = st.nextToken();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    maps[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            switch (calc) {
                case "left":
                    moveLeft();
                    break;
                case "right":
                    moveRight();
                    break;
                case "up":
                    moveUp();
                    break;
                case "down":
                    moveDown();
                    break;
            }
            drawResult(result);
        }
        System.out.print(sb);
    }

    private static void drawResult(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
    }

    private static void moveDown() {
        for (int x = 0; x < N; x++) {
            for (int y = N - 1; y > 0; y--) {
                if (maps[y][x] == 0) {
                    continue;
                }
                int ny = y - 1;
                while (ny > 0 && maps[ny][x] == 0) {
                    ny--;
                }
                if (maps[y][x] == maps[ny][x]) {
                    maps[y][x] *= 2;
                    maps[ny][x] = 0;
                }
            }
            int idx = N - 1;
            for (int y = N - 1; y >= 0; y--) {
                if (maps[y][x] != 0) {
                    result[idx--][x] = maps[y][x];
                }
            }
        }
    }

    private static void moveUp() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N - 1; y++) {
                if (maps[y][x] == 0) {
                    continue;
                }
                int ny = y + 1;
                while (ny < N - 1 && maps[ny][x] == 0) {
                    ny++;
                }
                if (maps[y][x] == maps[ny][x]) {
                    maps[y][x] *= 2;
                    maps[ny][x] = 0;
                }
            }
            int idx = 0;
            for (int y = 0; y < N; y++) {
                if (maps[y][x] != 0) {
                    result[idx++][x] = maps[y][x];
                }
            }
        }
    }


    private static void moveRight() {
        for (int y = 0; y < N; y++) {
            for (int x = N - 1; x > 0; x--) {
                if (maps[y][x] == 0) {
                    continue;
                }
                int nx = x - 1;
                while (nx > 0 && maps[y][nx] == 0) {
                    nx--;
                }
                if (maps[y][nx] == maps[y][x]) {
                    maps[y][x] *= 2;
                    maps[y][nx] = 0;
                }
            }
            int idx = N - 1;
            for (int x = N - 1; x >= 0; x--) {
                if (maps[y][x] != 0) {
                    result[y][idx--] = maps[y][x];
                }
            }
        }
    }

    private static void moveLeft() {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N - 1; x++) {
                if (maps[y][x] == 0) {
                    continue;
                }
                int nx = x + 1;
                while (nx < N-1 && maps[y][nx] == 0) {
                    nx++;
                }
                if (maps[y][x] == maps[y][nx]) {
                    maps[y][x] *= 2;
                    maps[y][nx] = 0;
                }
            }
            int idx = 0;
            for (int x = 0; x < N; x++) {
                if (maps[y][x] != 0) {
                    result[y][idx++] = maps[y][x];
                }
            }
        }
    }

}
