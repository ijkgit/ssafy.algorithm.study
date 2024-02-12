import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_semin {
    private static int N, shark, maps[][], ate, time, visited[][];
    private static Point start;
    private static int[] dx = {0, -1, 1, 0}; //탐색 순서: 위,왼,오,아래
    private static int[] dy = {-1, 0, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        maps = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
                if (maps[i][j] == 9) {
                    start = new Point(j, i);
                    maps[i][j] = 0;
                }
            }
        }
        ate = 0;
        time = 0;
        shark = 2;
        while (bfs()) {
        }
        System.out.println(time);
    }

    private static boolean bfs() {
        Deque<Point> dq = new ArrayDeque<>();
        PriorityQueue<Point> pq = new PriorityQueue<>((p1, p2) -> {
            if (visited[p1.y][p1.x] == visited[p2.y][p2.x]) {
                if (p1.y == p2.y) { //x좌표 작은 게 먼저(왼->오: 오름차순)
                    return p1.x - p2.x;
                }
                return p1.y - p2.y; //y좌표 작은 게 먼저(아래->위: 오름차순)
            }
            return visited[p1.y][p1.x] - visited[p2.y][p2.x]; //거리 짧은 게 먼저(오름차순)
        });

        dq.offer(start);
        visited = new int[N][N];
        visited[start.y][start.x] = 0;

        while (!dq.isEmpty()) {
            Point p = dq.poll();
            for (int k = 0; k < 4; k++) {
                int nx = p.x + dx[k];
                int ny = p.y + dy[k];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }
                if (maps[ny][nx] > shark || visited[ny][nx] != 0) {
                    continue;
                }
                if (maps[ny][nx] == shark || maps[ny][nx] == 0) {
                    visited[ny][nx] = visited[p.y][p.x] + 1;
                    dq.offer(new Point(nx, ny));
                    continue;
                }
                if (maps[ny][nx] < shark) {
                    visited[ny][nx] = visited[p.y][p.x] + 1;
                    pq.offer(new Point(nx, ny));
                }
            }
        }
        if (pq.isEmpty()) {
            return false; //물고기를 먹지 못함
        }
        start = pq.poll(); // 우선순위가 가장 높은 물고기 하나 먹기
        maps[start.y][start.x] = 0;
        time += visited[start.y][start.x];
        if (++ate == shark) {
            shark++;
            ate = 0;
        }
        return true;
    }
}