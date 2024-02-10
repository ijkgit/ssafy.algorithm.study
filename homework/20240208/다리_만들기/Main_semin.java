import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_semin {
    static int N;
    static int[][] maps;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        maps = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int landNum = 2; //섬 번호 메기기: 2~
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (maps[i][j] == 1) {
                    makeLandNumber(j, i, landNum++);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (maps[i][j] != 0) {
                    res = Math.min(res, getBridgeLength(j, i, maps[i][j]));
                }
            }
        }
        System.out.println(res);

    }

    private static int getBridgeLength(int x, int y, int startLandNum) {
        Deque<Point> dq = new ArrayDeque<>();
        int[][] visited = new int[N][N];
        dq.offer(new Point(x, y));
        while (!dq.isEmpty()) {
            Point p = dq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (nx >= N || ny >= N || nx < 0 || ny < 0) {
                    continue;
                }
                if (maps[ny][nx] != startLandNum && maps[ny][nx] != 0) { //시작한 섬과 다른 섬 발견, 바다 아님
                    return (visited[p.y][p.x]);
                }
                if (maps[ny][nx] == 0 && visited[ny][nx] == 0) { //바다 o, 건넌적 x
                    visited[ny][nx] = visited[p.y][p.x] + 1;
                    dq.offer(new Point(nx, ny));
                }
            }
        }
        return Integer.MAX_VALUE; //섬과 섬이 만나지 못한 경우
    }

    public static void makeLandNumber(int x, int y, int landNum) {
        Deque<Point> dq = new ArrayDeque<>();
        dq.offer(new Point(x, y));
        maps[y][x] = landNum;
        while (!dq.isEmpty()) {
            Point p = dq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (nx >= N || ny >= N || nx < 0 || ny < 0) {
                    continue;
                }
                if (maps[ny][nx] == 1) {
                    maps[ny][nx] = landNum;
                    dq.offer(new Point(nx, ny));
                }
            }
        }
    }
}