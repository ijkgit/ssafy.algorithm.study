import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    private int[][] graph;
    private int ans, day;
    private final static int[][] direction = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    private boolean[][] visited;

    public static void main(String[] args) throws IOException {
        new Solution().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());
        for (int t = 1; t <= testCase; t++) {
            int n = Integer.parseInt(br.readLine());
            graph = new int[n][n];
            day = 0;
            for (int i = 0; i < graph.length; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < graph.length; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                    if (graph[i][j] > day) day = graph[i][j];
                }
            }
            ans = 1;
            sol();
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private void sol() {
        for (int d = 1; d <= day; d++) {
            eat(d);

            int res = 0;
            visited = new boolean[graph.length][graph.length];
            L:
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph.length; j++) {
                    if (graph[i][j] != 0 && !visited[i][j]) {
                        bfs(i, j);
                        res++;
                    }
                }
            }
            ans = Math.max(ans, res);
        }
    }

    private void bfs(int x, int y) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (int d = 0; d < direction.length; d++) {
                int nx = p.x + direction[d][0];
                int ny = p.y + direction[d][1];

                if (checkStatus(nx, ny)) {
                    visited[nx][ny] = true;
                    queue.offer(new Point(nx, ny));
                }
            }
        }
    }

    private boolean checkStatus(int nx, int ny) {
        return 0 <= nx && nx < graph.length && 0 <= ny && ny < graph.length && !visited[nx][ny] && graph[nx][ny] != 0;
    }

    private void eat(int d) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j] == d) {
                    graph[i][j] = 0;
                }
            }
        }
    }

    class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
