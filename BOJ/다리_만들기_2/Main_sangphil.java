import java.util.*;
import java.io.*;

/*
 *      [BOJ] 다리만들기 2
 *          1. BFS로 같은 섬을 파티션
 *          2. 가로 세로 쭉 훑으면서, 섬끼리 거리를 adj 행렬에 갱신 << 여기에서 조건 맞추는 하드코딩이 까다로움
 *          3. 이후부터는 PQ를 활용한 프림 알고리즘 적용
 */
public class Main_sangphil {
    static class Pair {
        int x, y;
        public Pair(int x, int y) {this.x=x; this.y=y;}
    }
    static final int[] dx = {1,-1,0,0};
    static final int[] dy = {0,0,1,-1};
    static final int INF = Integer.MAX_VALUE;

    static int[][] graph;
    static int n, m;

    static int[][] adjMatrix;

    public static void main (String[] args) throws IOException {
        // System.setIn(Bridge.class.getResourceAsStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int order = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 1) {
                    bfs(i, j, order++);
                }
            }
        }

        adjMatrix = new int[order][order];

        int a, b, dis;
        for (int i = 0; i < n; i++) {
            a = b = dis = 0;
            for (int j = 0; j < m; j++) {
                if (a == 0 && graph[i][j] != a && graph[i][j] != 0) {
                    a = graph[i][j];
                    dis = 0;
                } else if (a != 0 && graph[i][j] != a && graph[i][j] != 0) {
                    b = graph[i][j];
                    if (dis >= 2) {
                        if (2 <= adjMatrix[a][b] && adjMatrix[a][b] > dis) {
                            adjMatrix[a][b] = dis;
                            adjMatrix[b][a] = dis;
                        } else if (adjMatrix[a][b] < 2) {
                            adjMatrix[a][b] = dis;
                            adjMatrix[b][a] = dis;
                        }
                    }
                    a = b;
                    dis = 0;
                } else if (graph[i][j] == 0) {
                    dis++;
                } else if (graph[i][j] == a) {
                    dis = 0;
                }
            }
        }
        for (int j = 0; j < m; j++) {
            a = b = dis = 0;
            for (int i = 0; i < n; i++) {
                if (a == 0 && graph[i][j] != a && graph[i][j] != 0) {
                    a = graph[i][j];
                    dis = 0;
                } else if (a != 0 && graph[i][j] != a && graph[i][j] != 0) {
                    b = graph[i][j];
                    if (dis >= 2) {
                        if (2 <= adjMatrix[a][b] && adjMatrix[a][b] > dis) {
                            adjMatrix[a][b] = dis;
                            adjMatrix[b][a] = dis;
                        } else if (adjMatrix[a][b] < 2) {
                            adjMatrix[a][b] = dis;
                            adjMatrix[b][a] = dis;
                        }
                    }
                    a = b;
                    dis = 0;
                } else if (graph[i][j] == 0) {
                    dis++;
                } else if (graph[i][j] == a) {
                    dis = 0;
                }
            }
        }

        //2 <= x <= order
        boolean[] visited = new boolean[order];
        int ans = INF;
        int V = order-2;
        int[] weight = new int[order];

        for (int i = 0; i < order; i++) weight[i] = INF;

        weight[2] = 0;
        int sum = 0;
        PriorityQueue<Pair> q = new PriorityQueue<Pair>((o1,o2)->(o1.y-o2.y));
        q.add(new Pair(2, 0));

        int cnt = 0;
        while(!q.isEmpty()) {
            Pair p = q.poll();
            if (visited[p.x]) continue;
            visited[p.x] = true;
            sum += p.y;
            if (++cnt == V) break;

            for (int i = 2; i < order; i++) {
                if (adjMatrix[p.x][i] != 0 && !visited[i] && adjMatrix[p.x][i] < weight[i]) {
                    weight[i] = adjMatrix[p.x][i];
                    q.add(new Pair (i, adjMatrix[p.x][i]));
                }
            }
        }

        System.out.println(cnt != V ? -1 : sum);
    }

    static void bfs(int x, int y, int order) {
        graph[x][y] = order;
        Deque<Pair> q = new LinkedList<Pair>();
        q.add(new Pair(x, y));
        while(!q.isEmpty()) {
            Pair p = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (graph[nx][ny] == 1) {
                        graph[nx][ny] = order;
                        q.add(new Pair(nx, ny));
                    }
                }
            }
        }
    }
}