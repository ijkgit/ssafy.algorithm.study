import java.util.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main_semin {
    private static int N, M, res, maps[][], originWallCount;
    private static List<Point> virus;
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        res = -1;
        originWallCount = 0;
        maps = new int[N][M];
        virus = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
                if (maps[i][j] == 2) {
                    virus.add(new Point(j, i));
                }
                if (maps[i][j] == 1) {
                    originWallCount += 1;
                }
            }
        }
        combinationWall(0);
        System.out.println(res);
    }

    private static void combinationWall(int cnt) {
        if (cnt == 3) {
            bfs();
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maps[i][j] == 0) {
                    maps[i][j] = 1;
                    combinationWall(cnt + 1);
                    maps[i][j] = 0;
                }
            }
        }
    }

    private static void bfs() {
        Deque<Point> dq = new ArrayDeque<>();
        boolean[][] existVirus = new boolean[N][M];
        for (Point v : virus) {
            dq.offer(v);
            existVirus[v.y][v.x] = true;
        }
        while (!dq.isEmpty()) {
            Point p = dq.poll();
            for (int k = 0; k < 4; k++) {
                int nx = p.x + dx[k];
                int ny = p.y + dy[k];
                if (nx < 0 || ny < 0 || nx >= M || ny >= N) {
                    continue;
                }
                if (maps[ny][nx] == 1 || maps[ny][nx] == 2) {
                    continue;
                }
                if (!existVirus[ny][nx]) {
                    existVirus[ny][nx] = true;
                    dq.offer(new Point(nx, ny));
                }
            }
        }

        //안전영역 : 전체 영역 - 벽 개수 - 바이러스 개수
        int safeArea = N * M - originWallCount - 3;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (existVirus[i][j]) {
                    safeArea--;
                }
            }
        }
        res = Math.max(res, safeArea);
    }
}
