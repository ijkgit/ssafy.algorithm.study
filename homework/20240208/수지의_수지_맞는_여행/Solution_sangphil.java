import java.util.*;
import java.io.*;

/*
 *      [SWEA 7699] 수지의 수지 맞는 여행
 * 		        dfs 탐색 알고리즘을 통해서, 알파벳에 따른 방문 여부 체크
 */
public class Solution_sangphil {
    static final int[] dx = {1,-1,0,0};
    static final int[] dy = {0,0,1,-1};
    static char[][] arr;
    static boolean[] visited;
    static int R, C, ans;
	public static void main(String[] args) throws IOException {
		// System.setIn(Solution.class.getResourceAsStream("input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            arr = new char[R][C];
            visited = new boolean[26];

            ans = 0;
            for (int i = 0 ; i < R; i++) {
                String line = br.readLine();
                for (int j = 0; j < C; j++) {
                    arr[i][j] = line.charAt(j);
                }
            }
            
            dfs(0, 0, 1, 1);

            sb.append(String.format("#%d %d\n", t, ans));
        }
        System.out.println(sb);
    }

    static void dfs(int x, int y, int depth, int cnt) {
        if (depth == R * C) {
            ans = Math.max(ans, cnt);
            return;
        }
        visited[arr[x][y] - 'A'] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (0 <= nx && nx < R && 0 <= ny && ny < C) {
                int tmp = arr[nx][ny] - 'A';
                if (!visited[tmp]) {
                    dfs(nx, ny, depth+1, cnt+1);
                } else {
                    ans = Math.max(ans, cnt);
                }
            }
        }
        visited[arr[x][y] - 'A'] = false;
    }
}