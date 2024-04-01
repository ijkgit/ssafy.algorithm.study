import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    private static int N;
    private static int[][] map;
    private static Point start, end;
    private static boolean[][] v;
    private final static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int TC = 1; TC <= T; TC++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            st = new StringTokenizer(br.readLine());
            start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            st = new StringTokenizer(br.readLine());
            end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            sb.append("#").append(TC).append(" ").append(sol()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int sol() {
        Queue<Point> pq = new PriorityQueue<>();
        pq.offer(start);

        v = new boolean[N][N];
        v[start.x][start.y] = true;

        while (!pq.isEmpty()) {
            Point p = pq.poll();
            for (int[] d : direction) {
                int nx = p.x + d[0];
                int ny = p.y + d[1];

                if (nx == end.x && ny == end.y) return p.c + 1;
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (v[nx][ny] || map[nx][ny] == 1) continue;

                if (map[nx][ny] == 2 && p.c % 3 != 2) pq.offer(new Point(nx, ny, p.c + ((p.c % 3) == 0 ? 3 : 2)));
                else pq.offer(new Point(nx, ny, p.c + 1));
                v[nx][ny] = true;
            }
        }

        return -1;
    }

    static class Point implements Comparable<Point> {
        int x, y, c;

        public Point(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return this.c - o.c;
        }
    }
}
