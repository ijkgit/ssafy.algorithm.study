import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_semin {
    static int R, C, T, maps[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()); // R행
        C = Integer.parseInt(st.nextToken()); // C열
        T = Integer.parseInt(st.nextToken()); // T초

        maps = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int tc = 0; tc < T; tc++) {
            dustSpread();
//            printMaps();
            cleanAir();
//            printMaps();
        }

        System.out.println(getDustSum());
    }

    private static void cleanAir() {
        int[] cleaner = new int[2];
        int idx = 0;
        for (int i = 0; i < R; i++) {
            if (maps[i][0] == -1) {
                cleaner[idx++] = i; // 청정기 y 좌표 저장, 0이 위에거(반시계), 1이 아래거(시계)
            }
        }

        // 반시계 돌기
        for (int i = cleaner[0] - 2; i >= 0; i--) {
            maps[i + 1][0] = maps[i][0];
        }
        for (int i = 1; i <= C - 1; i++) {
            maps[0][i - 1] = maps[0][i];
        }
        for (int i = 1; i <= cleaner[0]; i++) {
            maps[i - 1][C - 1] = maps[i][C - 1];
        }
        for (int i = C - 2; i >= 1; i--) {
            maps[cleaner[0]][i + 1] = maps[cleaner[0]][i];
        }
        maps[cleaner[0]][1] = 0;

        // x:0~C-1, y:cleaner[1]~R-1 시계
        for (int i = cleaner[1] + 1; i < R - 1; i++) {
            maps[i][0] = maps[i + 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            maps[R - 1][i] = maps[R - 1][i + 1];
        }
        for (int i = R - 1; i >= cleaner[1]; i--) {
            maps[i][C - 1] = maps[i - 1][C - 1];
        }
        for (int i = C - 2; i >= 1; i--) {
            maps[cleaner[1]][i + 1] = maps[cleaner[1]][i];
        }
        maps[cleaner[1]][1] = 0;
    }

    private static int getDustSum() {
        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (maps[i][j] == 0 || maps[i][j] == -1) {
                    continue;
                }
                sum += maps[i][j];
            }
        }
        return sum;
    }

    private static void printMaps() {
        for (int i = 0; i < R; i++) {
            System.out.println(Arrays.toString(maps[i]));
        }
        System.out.println();
    }

    private static void dustSpread() {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int[][] dustMap = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (maps[i][j] != 0 && maps[i][j] != -1) {
                    int dust = maps[i][j] / 5;
                    int cnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = dx[k] + j;
                        int ny = dy[k] + i;
                        if (nx >= C || ny >= R || nx < 0 || ny < 0) {
                            continue;
                        }
                        if (maps[ny][nx] == -1) {
                            continue;
                        }
                        dustMap[ny][nx] += dust; // 4방은 미세먼지 추가
                        cnt++;
                    }
                    dustMap[i][j] -= dust * cnt; // 코어는 확산된 만큼 빠짐
                }
            }
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                maps[r][c] += dustMap[r][c];
            }
        }
    }
}
