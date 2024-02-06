import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_semin {
    static int N, M, res;
    static int[][] maps;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        res = -1;
        maps = new int[N][M];
        visited = new boolean[N][M][65]; //모든 키에 대한 조합(없으면 0) (A~F 총 6개, 2^6)
        int x = 0;
        int y = 0;
        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                maps[i][j] = line[j];
                if (maps[i][j] == '0') {
                    x = j;
                    y = i;
                }
            }
        }
        bfs(x, y);
        System.out.println(res);
    }

    private static void bfs(int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Deque<Point> dq = new ArrayDeque<>();
        dq.offer(new Point(x, y, 0, 0));
        visited[y][x][0] = true; //key 아무것도 없이 첫 방문
        while (!dq.isEmpty()) {
            //System.out.println(dq);
            Point p = dq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (nx >= M || ny >= N || nx < 0 || ny < 0) {
                    continue;
                }
                if (maps[ny][nx] == '1') {
                    res = p.dist + 1;
                    return;
                }
                if (maps[ny][nx] == '#') {
                    continue;
                }
                if (maps[ny][nx] >= 'A' && maps[ny][nx] <= 'F') { //문이라면
                    int cur = maps[ny][nx] - 'A'; //지금 필요한 열쇠에 해당하는 인덱스 추출
                    if ((p.key & (1 << cur)) > 0) { //P의 key 상태가 현재 문이 원하는 키를 가지고 있는가
                        if (!visited[ny][nx][p.key]) { //현재 열쇠 상태로 방문한 적이 있는지 체크
                            visited[ny][nx][p.key] = true;
                            dq.offer(new Point(nx, ny, p.dist + 1, p.key | 1 << cur));
                        }
                    }  //열쇠가 없으면 못감
                }
                if (maps[ny][nx] >= 'a' && maps[ny][nx] <= 'f') { //열쇠라면
                    int cur = maps[ny][nx] - 'a';
                    if ((p.key & (1 << cur)) > 0) { //이미 이 열쇠가 있는가 (a키 라면 00000001인지 보는 것)
                        if (visited[ny][nx][p.key]) {
                            continue;
                        }
                        visited[ny][nx][p.key] = true; //cur에는 해당 key+ 다른 key가 포함된 것. 최신 key상태로 업데이트
                        dq.offer(new Point(nx, ny, p.dist + 1, p.key));
                    } else {
                        visited[ny][nx][p.key | 1 << cur] = true; //열쇠 없으면 아예 방문한 적 x -> 방문 체크
                        dq.offer(new Point(nx, ny, p.dist + 1, p.key | 1 << cur)); //열쇠 상태 변경
                    }
                }
                if (maps[ny][nx] == '.' || maps[ny][nx] == '0') {
                    if (visited[ny][nx][p.key]) { //현재 들고 있는 key 조합으로 visited 했는지 확인
                        continue;
                    }
                    visited[ny][nx][p.key] = true;
                    dq.offer(new Point(nx, ny, p.dist + 1, p.key));
                }
            }
        }
    }

    static class Point {
        int x, y, dist, key;

        //key: x(0) a(1) b(2) c(3) d(4) e(5) f(6)
        Point(int x, int y, int dist, int key) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.key = key;
        }

    }
}
