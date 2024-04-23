import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_semin {
    static int N, origin[][], maps[][];
    static int tempSum;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        origin = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                origin[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = 0;
        for (int i = 0; i < 4; i++) {
            int num = resetNumberedMap();
            tempSum = 0;
            int[] selected = new int[2];
            combination(0, 1, selected, num);
            ans += tempSum;
            rotation();
        }
        System.out.println(ans);
    }

    private static void combination(int k, int idx, int[] selected, int max) {
        if (k == selected.length) {
            int val = getArtValue(selected);
            if (val > 0) {
                tempSum += val;
            }
            return;
        }
        if (idx == max) {
            return;
        }
        selected[k] = idx;
        combination(k + 1, idx + 1, selected, max);
        combination(k, idx + 1, selected, max);
    }

    private static int getArtValue(int[] selected) {
        int a = selected[0], b = selected[1]; //넘버링 된 수
        int realA = 0, realB = 0; //오리지널 수
        int side = 0; //맞닿은 개수
        int val = 0; //그룹 a에 속한 칸의 수 + 그룹 b에 속한 칸의 수
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (maps[i][j] == a) {
                    val++;
                    realA = origin[i][j];
                    for (int k = 0; k < 4; k++) { //b와 맞닿아 있는지 확인
                        int nx = dx[k] + i;
                        int ny = dy[k] + j;
                        if (nx >= N || ny >= N || nx < 0 || ny < 0) {
                            continue;
                        }
                        if (maps[nx][ny] == b) {
                            side++;
                        }
                    }
                }
                if (maps[i][j] == b) {
                    val++;
                    realB = origin[i][j];
                }
            }
        }
        val *= (realA * realB * side);
        return val;
    }

    private static void numbering(int num, boolean[][] visited, int start, int x, int y) {
        Deque<Point> dq = new ArrayDeque<>();
        visited[x][y] = true;
        maps[x][y] = num;
        dq.add(new Point(x, y));
        while (!dq.isEmpty()) {
            Point p = dq.poll();
            for (int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                if (nx >= N || ny >= N || nx < 0 || ny < 0) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if (origin[nx][ny] != start) {
                    continue;
                }
                maps[nx][ny] = num;
                visited[nx][ny] = true;
                dq.add(new Point(nx, ny));
            }
        }
    }

    private static int resetNumberedMap() {
        boolean[][] visited = new boolean[N][N];
        maps = new int[N][N];
        int num = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    numbering(num++, visited, origin[i][j], i, j);
                }
            }
        }
        return num;
    }

    private static void rotation() {
        int[][] temp = new int[N][N];

        //시계
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N / 2; j++) {
                temp[i][j] = origin[N / 2 - j - 1][i];
            }
        }
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N / 2; j++) {
                temp[N / 2 + i + 1][j] = origin[N - j - 1][i];
            }
        }
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N / 2; j++) {
                temp[i][N - j - 1] = origin[j][N / 2 + i + 1];
            }
        }
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N / 2; j++) {
                temp[N / 2 + i + 1][N / 2 + j + 1] = origin[N - j - 1][N / 2 + i + 1];
            }
        }

        //반시계
        for (int i = 0; i < N / 2; i++) {
            temp[N / 2][i] = origin[i][N / 2];
        }
        for (int i = 0; i < N / 2; i++) {
            temp[i][N / 2] = origin[N / 2][N - i - 1];
        }
        for (int i = 0; i < N / 2; i++) {
            temp[N / 2][N - i - 1] = origin[N - i - 1][N / 2];
        }
        for (int i = 0; i < N / 2; i++) {
            temp[N - i - 1][N / 2] = origin[N / 2][i];
        }
        temp[N / 2][N / 2] = origin[N / 2][N / 2];
        origin = temp;
    }
}