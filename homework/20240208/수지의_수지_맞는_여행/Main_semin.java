import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 같은 알파벳을 두 번 이상 보지 않아야 함. 그 중에 볼 수 있는 최대 갯수 구하기.
public class Main_semin {
    static int R, C, res;
    static char[][] maps;
    static int[] dc = {-1, 1, 0, 0};
    static int[] dr = {0, 0, -1, 1};
    static boolean[] visited; //A~Z: 0~25

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            maps = new char[R][C];
            visited = new boolean[26];
            res = 0;
            for (int r = 0; r < R; r++) {
                char[] line = br.readLine().toCharArray();
                for (int c = 0; c < C; c++) {
                    maps[r][c] = line[c];
                }
            }
            visited[maps[0][0] - 'A'] = true;
            dfs(0, 0, 1, 1);
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int r, int c, int cnt, int depth) {
        if (depth == R * C) {
            res = Math.max(cnt, res);
            return;
        }
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                continue;
            }
            int alpha = maps[nr][nc] - 'A';
            if (visited[alpha]) {
                res = Math.max(cnt, res);
                continue;
            }
            visited[alpha] = true;
            dfs(nr, nc, cnt + 1, depth + 1);
            visited[alpha] = false;
        }
    }
}
