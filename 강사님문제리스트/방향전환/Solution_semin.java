import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_semin {
    private static final int[]  dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    private static int x1, x2, y1, y2;
    private static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            visited = new boolean[2][201][201];
            st = new StringTokenizer(br.readLine()); //x1 y1 x2 y2
            x1 = Integer.parseInt(st.nextToken()) + 100; //0~200 범위로 수정
            y1 = Integer.parseInt(st.nextToken()) + 100;
            x2 = Integer.parseInt(st.nextToken()) + 100;
            y2 = Integer.parseInt(st.nextToken()) + 100;
            int result = bfs(y1, x1, 0);
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static int bfs(int y, int x, int dist) {

        Deque<Point> dq = new ArrayDeque<>();
        dq.add(new Point(x, y, dist, 0));
        dq.add(new Point(x, y, dist, 1));
        visited[0][y][x] = true;
        visited[1][y][x] = true;
        int dir;

        while (!dq.isEmpty()) {
            Point p = dq.poll();
            x = p.x;
            y = p.y;
            dist = p.dist;
            dir = p.dir;
            if (x == x2 && y == y2) {
                return dist;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx > 200 || ny < 0 || ny > 200) {
                    continue;
                }
                if (dir == 0 && dx[i] != 0) { ////현재는 세로 이동이고, dx[i] 가 0이 아니다(다음은 가로 이동이다)
                    if (visited[1][ny][nx]) { // 가로방향 visted = true면
                        continue;
                    }
                    visited[1][ny][nx] = true;
                    dq.add(new Point(nx, ny, dist + 1, 1));
                } else if (dir == 1 && dy[i] != 0) { //현재는 가로 이동이고, dy[i] 가 0이 아니다(다음은 세로 이동이다)
                    if (visited[0][ny][nx]) { // 세로방향 visted = true면
                        continue;
                    }
                    visited[0][ny][nx] = true;
                    dq.add(new Point(nx, ny, dist + 1, 0));
                }
            }
        }
        return -1;
    }

    private static class Point {
        int x;
        int y;
        int dist; //거리
        int dir; //방향 세로0 가로1

        public Point(int x, int y, int dist, int dir) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.dir = dir;
        }
    }
}
