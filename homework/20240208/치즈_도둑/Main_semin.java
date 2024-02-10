import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_semin {
    static int N, maps[][], res;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            N = Integer.parseInt(br.readLine());
            maps = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    maps[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            res = 1;
            for (int day = 0; day <= 100; day++) {
                int todayCnt = 0;
                visited = new boolean[N][N];
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (maps[i][j] > day && !visited[i][j]) {
                            visited[i][j] = true;
                            //bfs(j, i, day, visited);
                            dfs(j, i, day, visited);
                            todayCnt++;
                        }
                    }
                }
                res = Math.max(todayCnt, res);
            }
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }

    public static void dfs(int x, int y, int day, boolean[][] visited) {
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx >= N || ny >= N || nx < 0 || ny < 0) {
                continue;
            }
            if (visited[ny][nx] || maps[ny][nx] <= day) {
                continue;
            }
            visited[ny][nx] = true;
            dfs(nx, ny, day, visited);
        }
    }

    public static void bfs(int x, int y, int day, boolean[][] visited) {
        Deque<Point> dq = new ArrayDeque<>();
        dq.offer(new Point(x, y));
        while (!dq.isEmpty()) {
            Point p = dq.poll();
            for (int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                if (nx >= N || ny >= N || nx < 0 || ny < 0) {
                    continue;
                }
                if (visited[ny][nx] || maps[ny][nx] <= day) {
                    continue;
                }
                dq.add(new Point(nx, ny));
                visited[ny][nx] = true;
            }
        }
    }
}
