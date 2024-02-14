import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_semin {
    static int R, C, Ans;
    static boolean[][] maps;
    static int[] dc = {1, 1, 1};
    static int[] dr = {-1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        Ans = 0;
        maps = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < line.length; j++) {
                if (line[j] == 'x') {
                    maps[i][j] = true; // 지나갈 수 없음
                }
            }
        }
        for (int r = 0; r < R; r++) {
            if (dfs(0, r)) {
                Ans++;
            }
        }
        System.out.println(Ans);
    }

    private static boolean dfs(int c, int r) {
        if (c == C - 1) {
            return true;
        }
        for (int k = 0; k < 3; k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            if (nr < 0 || nc < 0 || nc >= C || nr >= R) {
                continue;
            }
            if (maps[nr][nc]) {
                continue;
            }
            maps[nr][nc] = true;
            if (dfs(nc, nr)) {
                return true;
            }
            //maps[nr][nc] = false;
        }
        return false;
    }
}