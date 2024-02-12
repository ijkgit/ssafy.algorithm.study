import java.util.*;
import java.io.*;

/*
 *      [SWEA 7733] 치즈 도둑
 * 		        bfs 탐색 알고리즘을 통해서, 방문 여부와 날짜에 따라서 순회
 */
public class Solution_sangphil {
    static class Pair {int x; int y; public Pair(int x, int y) {this.x=x;this.y=y;}}
    static final int[] dx = {1,-1,0,0};
    static final int[] dy = {0,0,1,-1};
    static int[][] arr;
    static boolean[][] visited;
    static int N, ans;
	public static void main(String[] args) throws IOException {
		// System.setIn(Solution.class.getResourceAsStream("input2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];

            int max = 0;
            for (int i = 0 ; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    max = Math.max(max, arr[i][j]);
                }
            }
            ans = 0;

            for (int d = 0; d <= max; d++) {
                int cnt = 0;
                visited = new boolean[N][N];
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (arr[i][j] > d && !visited[i][j]) {
                            bfs(i, j, d);
                            cnt++;
                        }
                    }
                }
                ans = Math.max(ans, cnt);     
            }
            sb.append(String.format("#%d %d\n", t, ans));
        }
        System.out.println(sb);
    }

    static void bfs (int x, int y, int w) {
        Deque<Pair> q = new LinkedList<Pair>() {{add(new Pair(x, y));}};
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Pair p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                    if (arr[nx][ny] > w && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        q.add(new Pair(nx, ny));
                    }
                }
            }
        }
    }
}