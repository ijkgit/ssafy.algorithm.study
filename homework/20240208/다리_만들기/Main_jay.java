import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private int[][] graph;
    private int ans = Integer.MAX_VALUE;
    private int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    boolean[][] visited;
    Queue<Point> queue;

    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        for (int i = 0; i < graph.length; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < graph.length; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        sol();
        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private void sol() {
        setBoundary();
        findWay();
    }

    // 그래프를 탐색하면서 섬일 경우 경계 구분 시작
    private void setBoundary() {
        int idx = 2;
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j] == 1) {
                    findBoundary(i, j, idx++);
                }
            }
        }
    }

    // BFS
    // 섬 내부 영역에 해당하는 경우 표시
    private void findBoundary(int x, int y, int idx) {
        queue = new ArrayDeque<>();
        queue.offer(new Point(x, y));
        graph[x][y] = idx;
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (int d = 0; d < direction.length; d++) {
                int nx = p.x + direction[d][0];
                int ny = p.y + direction[d][1];

                if (checkStatus(nx, ny) && graph[nx][ny] == 1) {
                    graph[nx][ny] = idx;
                    queue.offer(new Point(nx, ny));
                }
            }
        }
    }

    // 그래프 범위 검증 함수
    private boolean checkStatus(int nx, int ny) {
        return 0 <= nx && nx < graph.length && 0 <= ny && ny < graph.length;
    }

    // 섬일 경우 탐색 시작
    private void findWay() {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j] > 0) {
                    ans = Math.min(ans, getWay(i, j, graph[i][j]));
                }
            }
        }
    }

    // 섬에서 탐색을 시작하여 다른 섬의 영역에 도달 시 함수 종료
    // 최소값이 갱신되면서 최소값을 넘어간 경우 함수 종료
    private int getWay(int x, int y, int num) {
        visited = new boolean[graph.length][graph.length];
        queue = new ArrayDeque<>();
        queue.offer(new Point(x, y, 0));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            if (p.cnt >= ans) return Integer.MAX_VALUE;

            for (int d = 0; d < direction.length; d++) {
                int nx = p.x + direction[d][0];
                int ny = p.y + direction[d][1];

                if (checkStatus(nx, ny) && !visited[nx][ny]) {
                    if(graph[nx][ny] != num && graph[nx][ny] != 0) {
                        return p.cnt;
                    }
                    else if(graph[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        queue.offer(new Point(nx, ny, p.cnt + 1));
                    }
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    class Point {
        int x, y, cnt;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
