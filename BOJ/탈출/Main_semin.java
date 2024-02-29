import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main_semin {
    static int R, C;
    static Point D, S;
    static char[][] maps;
    static boolean filled[] = new boolean[2501];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        maps = new char[R][C];
        for (int i = 0; i < R; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                maps[i][j] = chars[j];
                if (maps[i][j] == 'D') {
                    D = new Point(i, j); //도착
                    continue;
                }
                if (maps[i][j] == 'S') {
                    S = new Point(i, j); //시작
                }
            }
        }
        bfs();
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static void bfs() {
        ArrayDeque<Point> dq = new ArrayDeque<>();
        int[][] visited = new int[R][C];
        visited[S.x][S.y] = 1;
        dq.add(S);
        while (!dq.isEmpty()) {
            Point p = dq.poll();
            if (p.x == D.x && p.y == D.y) {
                System.out.println(visited[p.x][p.y] - 1);
                return;
            }
            //같은 level일 때 물은 한 번만 이동한다.
            if (!filled[visited[p.x][p.y]]) {
                //물 이동
                List<Point> list = new ArrayList<>();
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        if (maps[i][j] != '*') {
                            continue;
                        }
                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];
                            if (nx >= R || ny >= C || nx < 0 || ny < 0) {
                                continue;
                            }
                            if (maps[nx][ny] != '.') {
                                continue;
                            }
                            list.add(new Point(nx, ny));
                        }
                    }
                }

                for (Point point : list) {
                    maps[point.x][point.y] = '*';
                }
                filled[visited[p.x][p.y]] = true;
            }

            //고슴 도치 이동
            for (int k = 0; k < 4; k++) {
                int nx = p.x + dx[k];
                int ny = p.y + dy[k];
                if (nx >= R || ny >= C || nx < 0 || ny < 0) {
                    continue;
                }
                if (visited[nx][ny] != 0 || maps[nx][ny] == 'X' || maps[nx][ny] == '*') {
                    continue;
                }
                visited[nx][ny] = visited[p.x][p.y] + 1;
                dq.add(new Point(nx, ny));
            }
        }
        System.out.println("KAKTUS");
    }
}