import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_semin {
    static int N, M, maps[][], res;
    static ArrayList<Point> chickens;
    static Point[] selected;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maps = new int[N][N];
        chickens = new ArrayList<>();
        res = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
                if (maps[i][j] == 2) {
                    chickens.add(new Point(j, i));
                }
            }
        }
        selected = new Point[M];
        combination(0, 0);
        System.out.println(res);
    }

    private static void combination(int idx, int k) {
        if (k == selected.length) {
            res = Math.min(res, bfs());
            return;
        }
        if (idx == chickens.size()) {
            return;
        }
        selected[k] = chickens.get(idx);
        combination(idx + 1, k + 1);
        combination(idx + 1, k);
    }

    public static int bfs() {
        int distance = 0;
        Deque<Point> dq = new ArrayDeque<>();
        int[][] visited = new int[N][N];
        for (int i = 0; i < M; i++) {
            dq.add(selected[i]);
            visited[selected[i].y][selected[i].x] = 1;
        }
        while (!dq.isEmpty()) {
            Point p = dq.poll();
            for (int k = 0; k < 4; k++) {
                int nx = p.x + dx[k];
                int ny = p.y + dy[k];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }
                if (visited[ny][nx] != 0) {
                    continue;
                }
                visited[ny][nx] = visited[p.y][p.x] + 1;
                if (maps[ny][nx] == 1) {
                    distance += visited[p.y][p.x];
                }
                dq.offer(new Point(nx, ny));
            }
        }
        return distance;
    }
}
