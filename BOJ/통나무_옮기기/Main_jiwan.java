import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N;
    static char map[][];
    static Point[] startPoint, endPoint;
    static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

    static class State {
        int x, y, orientation, distance;

        public State() {
            super();
        }

        State(int x, int y, int orientation, int distance) {
            this.x = x;
            this.y = y;
            this.orientation = orientation;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        startPoint = new Point[3];
        endPoint = new Point[3];
        int startIdx = 0, endIdx = 0;
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'B')
                    startPoint[startIdx++] = new Point(i, j);
                if (map[i][j] == 'E')
                    endPoint[endIdx++] = new Point(i, j);
            }
        }
        System.out.println(bfs());
    }

    private static int bfs() {
        boolean[][][] visited = new boolean[2][N][N];
        Queue<State> q = new LinkedList<>();
        int orientation = 0;
        if (startPoint[0].y + 1 == startPoint[1].y)
            orientation = 0;
        else
            orientation = 1;
        q.add(new State(startPoint[1].x, startPoint[1].y, orientation, 0));
        visited[orientation][startPoint[1].x][startPoint[1].y] = true;
        while (!q.isEmpty()) {
            State now = q.poll();
            if (now.x == endPoint[1].x & now.y == endPoint[1].y) {
                if (now.orientation == 0 && map[now.x][now.y - 1] == 'E' && map[now.x][now.y + 1] == 'E') {
                    return now.distance;
                }
                else if (now.orientation == 1 && map[now.x - 1][now.y] == 'E' && map[now.x + 1][now.y] == 'E') {
                    return now.distance;
                }
            }
            for (int d = 0; d < 4; d++) {
                int xx = now.x + dx[d];
                int yy = now.y + dy[d];
                if (now.orientation == 0) {
                    if (!checkHorizontal(xx, yy, d))
                        continue;
                }
                else {
                    if (!checkVertical(xx, yy, d))
                        continue;
                }
                if (visited[now.orientation][xx][yy])
                    continue;
                visited[now.orientation][xx][yy] = true;
                q.add(new State(xx, yy, now.orientation, now.distance + 1));
            }
            if (canRotate(now.x, now.y)) {
                if (now.orientation == 0 && !visited[1][now.x][now.y]) {
                    visited[1][now.x][now.y] = true;
                    q.add(new State(now.x, now.y, 1, now.distance + 1));
                }
                else if (now.orientation == 1 && !visited[0][now.x][now.y]) {
                    visited[0][now.x][now.y] = true;
                    q.add(new State(now.x, now.y, 0, now.distance + 1));
                }
            }
        }
        return 0;
    }

    private static boolean checkVertical(int xx, int yy, int d) {
        if (d < 2) {
            if (xx - 1 < 0 || xx + 1 >= N)
                return false;
            if (map[xx][yy] == '1' || map[xx - 1][yy] == '1' || map[xx + 1][yy] == '1')
                return false;
        }
        else {
            if (yy < 0 || yy >= N)
                return false;
            if (map[xx][yy] == '1' || map[xx - 1][yy] == '1' || map[xx + 1][yy] == '1')
                return false;
        }
        return true;
    }

    private static boolean checkHorizontal(int xx, int yy, int d) {
        if (d < 2) {
            if (xx < 0 || xx >= N)
                return false;
            if (map[xx][yy] == '1' || map[xx][yy - 1] == '1' || map[xx][yy + 1] == '1')
                return false;
        }
        else {
            if (yy - 1 < 0 || yy + 1 >= N)
                return false;
            if (map[xx][yy] == '1' || map[xx][yy - 1] == '1' || map[xx][yy + 1] == '1')
                return false;
        }
        return true;
    }

    private static boolean canRotate(int x, int y) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i < 0 || j < 0 || i >= N || j >= N)
                    return false;
                if (map[i][j] == '1')
                    return false;
            }
        }
        return true;
    }
}
