import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_semin {
    static class Edge implements Comparable<Edge> {
        int node;
        int weight;

        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }

    }

    static int N, M, maps[][], adjMatrix[][];
    static boolean visited[][], edgeVisited[];
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maps = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //섬 번호 매기기
        int island = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maps[i][j] == 1) {
                    maps[i][j] = island;
                    dfs(new Point(j, i), island);
                    island++;
                }
            }
        }
        adjMatrix = new int[island][island]; //2~island-1까지 간선 정보 저장
        for (int i = 2; i < island; i++) {
            for (int j = 2; j < island; j++) {
                adjMatrix[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maps[i][j] != 0) {
                    for (int k = 0; k < 4; k++) {
                        int nx = j + dx[k];
                        int ny = i + dy[k];
                        if (ny >= N || nx >= M || ny < 0 || nx < 0 || maps[ny][nx] != 0) {
                            continue;
                        }
                        //k 방향으로 (i,j)에 해당하는 섬번호와 다른 섬을 찾는다. 찾으면 인접 행렬 업데이트
                        findIsland(j, i, k, maps[i][j]);
                    }
                }
            }
        }
        edgeVisited = new boolean[island]; //2~island-1 까지 엣지 존재
        System.out.println(prim());
    }

    /**
     * prim approach
     * 1. 임의의 start 정점을 하나 선택하여 우선순위 큐에 넣는다.
     * 2. 우선순위 큐에서 정점을 하나 꺼낸다.
     * (우선순위에 의해 비용이 가장 작은 정점이 우선순위 큐를 빠져나온다.)
     * 3. 우선순위 큐에서 꺼낸 정점이 이미 방문한 정점이라면, 다시 우선순위 큐에서 새로운 정점을 꺼낸다.
     * 4. 우선순위 큐에서 꺼낸 정점이 아직 방문하지 않은 정점이라면, 방문처리를 하고 정점의 비용을 더한다.
     * 5. 위의 정점과 연결된 다른 정점들 중 방문하지 않은 정점들의 정보를 우선순위 큐에 넣음
     * 6. 우선순위 큐가 비어있을 때까지 2~5번 과정을 반복
     */
    private static long prim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        long sum = 0;
        int search = 0; //연결한 정점
        pq.offer(new Edge(2, 0));
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (edgeVisited[edge.node]) {
                continue;
            }
            edgeVisited[edge.node] = true;
            sum += edge.weight;
            search++;
            for (int i = 2; i < adjMatrix.length; i++) {
                if (adjMatrix[edge.node][i] == Integer.MAX_VALUE) {
                    continue; //edge.node 에서 갈 수 없는 곳이거나, 다리 길이가 1
                }
                if (!edgeVisited[i]) {
                    pq.offer(new Edge(i, adjMatrix[edge.node][i]));
                }
            }
        }
        if (search != edgeVisited.length - 2 || sum == 0) { //전체가 이어질 수 없는 조건
            return -1;
        }
        return sum;
    }


    private static void findIsland(int x, int y, int dir, int from) {
        int cnt = 1;
        while (true) {
            int nx = x + dx[dir] * cnt;
            int ny = y + dy[dir] * cnt;
            if (ny >= N || nx >= M || ny < 0 || nx < 0) {
                break;
            }
            if (maps[ny][nx] == from) { //ㄷ 모양으로 생겨서 아래쪽->위쪽 가다가 자기 자신을 다시 만날 수도 있음, 그럼 위에 있는 애가 다리를 놓아야 함.
                return;
            }
            if (maps[ny][nx] != from && maps[ny][nx] != 0) { //섬 발견
                int to = maps[ny][nx];
                int len = cnt - 1;
                if (len < adjMatrix[from][to]) {
                    if (len <= 1) { //최소 거리가 1 이하면 이 방향으론 다리를 놓을 수 x
                        return;
                    }
                    adjMatrix[from][to] = len;
                    adjMatrix[to][from] = len;
                } //섬을 한 번 만나고 안됐으면 다음 기회는 없음
                return;
            }
            cnt++;
        }
    }

    private static void dfs(Point point, int island) {
        for (int d = 0; d < 4; d++) {
            int nx = dx[d] + point.x;
            int ny = dy[d] + point.y;
            if (nx >= M || ny >= N || nx < 0 || ny < 0 || maps[ny][nx] != 1) {
                continue;
            }
            if (!visited[ny][nx]) {
                visited[ny][nx] = true;
                maps[ny][nx] = island;
                dfs(new Point(nx, ny), island);
            }
        }
    }

}
