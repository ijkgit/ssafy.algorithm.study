import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NavigableMap;
import java.util.StringTokenizer;

public class Main {
    static int N, M, maps[][];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maps = new int[N][M];
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        go(x, y, d);
        System.out.println(getAns(maps));
    }

    private static int getAns(int[][] maps) {
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maps[i][j] != 2) {
                    continue;
                }
                ans ++;
            }
        }
        return ans;
    }

    private static void go(int x, int y, int d) {
        maps[x][y] = 2; //방문 처리
        for (int k = 1; k <= 4; k++) {
            int left = (d - k + 4) % 4;
            int nx = x + dx[left];
            int ny = y + dy[left];
            if (maps[nx][ny] == 0) {
                go(nx, ny, left);
                return;
            }
        }
        int back = (d + 2) % 4;
        int nx = x + dx[back];
        int ny = y + dy[back];
        if (maps[nx][ny] != 1) {
            go(nx, ny, d);
        }
    }
}
