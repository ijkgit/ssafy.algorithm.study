import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, x, y, d;
    private static int[][] map;
    private static final int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        sb.append(sol());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int sol() {
        map[x][y] = -1;
        while (true) {
            boolean flag = false;
            for (int i = 1; i <= 4; i++) {
                int nd = d - i;
                if (nd < 0) nd += 4;

                int nx = x + direction[nd][0];
                int ny = y + direction[nd][1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (map[nx][ny] == 1 || map[nx][ny] == -1) continue;

                map[nx][ny] = -1;
                x = nx; y = ny; d = nd;
                flag = true;
                break;
            }
            if (!flag) {
                if(!back()) break;
            }
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == -1) ans++;
            }
        }

        return ans;
    }

    private static boolean back() {
        int nx = x - direction[d][0];
        int ny = y - direction[d][1];
        if (nx < 0 || ny < 0 || nx >= N || ny >= M) return false;
        if (map[nx][ny] == 1) return false;
        map[nx][ny] = -1;
        x = nx;
        y = ny;
        return true;
    }
}
