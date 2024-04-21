import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_semin {
    static int n, l, r, maps[][];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        maps = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int totalCount = 0;
        while (true) {
            boolean did = false;
            boolean[][] visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        if (!bfs(i, j, visited)) {
                            continue;
                        }
                        did = true;
                    }
                }
            }
            if (!did) {
                break;
            }
            totalCount++;
        }
        System.out.println(totalCount);
    }

    private static boolean bfs(int x, int y, boolean[][] visited) {
        Deque<Point> dq = new ArrayDeque<>();
        Deque<Point> union = new ArrayDeque<>();
        visited[x][y] = true;
        dq.add(new Point(x, y));
        union.add(new Point(x, y));
        int sum = maps[x][y];
        while (!dq.isEmpty()) {
            Point p = dq.poll();
            x = p.x;
            y = p.y;
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                int diff = Math.abs(maps[x][y] - maps[nx][ny]);
                if (diff >= l && diff <= r) {
                    visited[nx][ny] = true;
                    sum += maps[nx][ny];
                    dq.push(new Point(nx, ny));
                    union.push(new Point(nx, ny));
                }
            }
        }
        if (union.size()==1) {
            return false;
        }
        int avg = sum / union.size();
        while (!union.isEmpty()) {
            Point p = union.pop();
            maps[p.x][p.y] = avg; //한 스코프 단위로 방문처리 하기 때문에 copy 안해도 됨
        }
        return true;
    }
}
