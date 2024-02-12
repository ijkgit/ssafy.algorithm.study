import java.io.*;
import java.util.*;

public class Main {
    private int[][] graph;
    private List<Point> virusList;
    private int ans = Integer.MIN_VALUE;
    private Point[] sel = new Point[3];
    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    boolean[][] visited;

    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        virusList = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < graph[0].length; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 2) virusList.add(new Point(i, j));
            }
        }
        sol();
        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private void sol() {
        combination(0);
    }

    private void combination(int k) {
        if (k == 3) {
            bfs();
            ans = Math.max(ans, countSafe());
            return;
        }

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if (graph[i][j] == 0) {
                    graph[i][j] = 1;
                    combination(k + 1);
                    graph[i][j] = 0;
                }
            }
        }
    }

    private void bfs() {
        Queue<Point> queue = new ArrayDeque<>();
        visited = new boolean[graph.length][graph[0].length];
        for (Point v : virusList) {
            queue.offer(new Point(v.x, v.y));
            visited[v.x][v.y] = true;
        }
        while(!queue.isEmpty()) {
            Point p = queue.poll();
            for (int d = 0; d < direction.length; d++) {
                int nx = p.x + direction[d][0];
                int ny = p.y + direction[d][1];

                if(checkStatus(nx, ny)) {
                    visited[nx][ny] = true;
                    queue.offer(new Point(nx, ny));
                }
            }
        }
    }

    private boolean checkStatus(int nx, int ny) {
        return 0 <= nx && nx < graph.length && 0 <= ny && ny < graph[0].length && graph[nx][ny] == 0 && !visited[nx][ny];
    }

    private int countSafe() {
        int count = 0;
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if(graph[i][j] == 0 && !visited[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
