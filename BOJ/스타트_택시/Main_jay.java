import java.io.*;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, E;
    private static int[][] map;
    private static Point Taxi;
    private static final int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    private static Point[][] targets;

    static class Point implements Comparable<Point> {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (this.x == o.x) return this.y - o.y;
            return this.x - o.x;
        }
    }

    static class Guest {
        Point start, target;
        int cost;

        public Guest(Point start, Point target, int cost) {
            this.start = start;
            this.target = target;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < N; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        Taxi = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

        targets = new Point[N][N];
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int rx = Integer.parseInt(st.nextToken()) - 1;
            int ry = Integer.parseInt(st.nextToken()) - 1;
            targets[x][y] = new Point(rx, ry);
        }

        sb.append(sol());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int sol() {
        for (int m = 0; m < M; m++) {
            Guest guest = findGuest();
            if (guest == null) return -1; // 모든 손님을 이동시킬 수 없는 경우
            if(!canGo(guest)) return -1; // 이동하는 도중에 연료가 바닥나는 경우
        }
        return E;
    }

    private static boolean canGo(Guest guest) {
        E -= guest.cost;
        Taxi.x = guest.start.x;
        Taxi.y = guest.start.y;
        if (E < 0) return false;

        int before = E;
        if (!go(guest)) return false;

        if (E >= 0) E += (before - E) * 2;
        else return false;

        Taxi.x = guest.target.x;
        Taxi.y = guest.target.y;

        return true;
    }

    private static boolean go(Guest guest) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(Taxi.x, Taxi.y));
        boolean[][] visited = new boolean[N][N];
        visited[Taxi.x][Taxi.y] = true;
        int cost = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int c = 0; c < size; c++) {
                Point p = q.poll();
                if (p.x == guest.target.x && p.y == guest.target.y) return true;
                for (int[] d : direction) {
                    int nx = p.x + d[0];
                    int ny = p.y + d[1];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if (visited[nx][ny] || map[nx][ny] == 1) continue;

                    visited[nx][ny] = true;
                    q.offer(new Point(nx, ny));
                }
            }
            E--;
            if (E < 0) return false;
        }
        return false;
    }

    private static Guest findGuest() {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(Taxi.x, Taxi.y));
        boolean[][] visited = new boolean[N][N];
        visited[Taxi.x][Taxi.y] = true;
        int cost = 0;
        Queue<Point> starts = new PriorityQueue<>();
        while (!q.isEmpty()) {
            int size = q.size();
            for (int c = 0; c < size; c++) {
                Point p = q.poll();
                if (targets[p.x][p.y] != null) starts.offer(new Point(p.x, p.y));
                for (int[] d : direction) {
                    int nx = p.x + d[0];
                    int ny = p.y + d[1];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if (visited[nx][ny] || map[nx][ny] == 1) continue;

                    visited[nx][ny] = true;
                    q.offer(new Point(nx, ny));
                }
            }
            if (!starts.isEmpty()) {
                Point start = starts.poll();
                Point target = targets[start.x][start.y];
                targets[start.x][start.y] = null;
                return new Guest(start, target, cost);
            }
            cost++;
        }
        return null;
    }
}
