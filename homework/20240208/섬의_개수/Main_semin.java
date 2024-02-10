import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_semin {
    static int w, h, maps[][], res;
    static int[] dc = {-1, 1, 0, 0, -1, 1, -1, 1};
    static int[] dr = {0, 0, -1, 1, -1, 1, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if (w == 0 && h == 0) {
                break;
            }
            maps = new int[h][w];
            for (int r = 0; r < h; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < w; c++) {
                    maps[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            res = 0;
            for (int r = 0; r < h; r++) {
                for (int c = 0; c < w; c++) {
                    if (maps[r][c] == 1) {
                        dfs(maps, r, c);
                        res++;
                    }
                }
            }
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int[][] maps, int r, int c) {
        for (int d = 0; d < 8; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr >= h || nc >= w || nr < 0 || nc < 0 || maps[nr][nc] == 0) {
                continue;
            }
            maps[nr][nc] = 0; //방문 처리
            dfs(maps, nr, nc);
        }
    }

    private static void bfs(int[][] maps, int r, int c) {
        Deque<Point> dq = new ArrayDeque<>();
        dq.offer(new Point(r, c));
        maps[r][c] = 0;
        while (!dq.isEmpty()) {
            Point p = dq.poll();
            for (int d = 0; d < 8; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if (nr >= h || nc >= w || nr < 0 || nc < 0 || maps[nr][nc] == 0) {
                    continue;
                }
                maps[nr][nc] = 0; //방문 처리
                dq.offer(new Point(nr, nc));
            }
        }
    }

    private static class Point {
        int r;
        int c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
