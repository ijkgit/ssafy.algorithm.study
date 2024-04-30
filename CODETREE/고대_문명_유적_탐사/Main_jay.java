import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int K, M;
    private static final int N = 5;
    private static int[][] map, copy, tmp;
    private static boolean[][] visited;
    private static ArrayDeque<int[]> queue;
    private static Queue<Integer> bag;
    private static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        copy = new int[N][N];
        tmp = new int[N][N];
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < N; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        bag = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) bag.offer(Integer.parseInt(st.nextToken()));

        bw.write(sol());
        bw.flush();
        bw.close();
    }

    private static String sol() {
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < K; k++) {
            int res = find();
            if (res == 0) break;
            else sb.append(res).append(" ");
        }

        return sb.toString();
    }

    // 탐사 진행
    private static int find() {
        int max = 0, rx = 0, ry = 0, rd = 0;
        for (int x = 0; x < N - 2; x++) {
            for (int y = 0; y < N - 2; y++) {
                setCopy();
                for (int d = 0; d < 3; d++) {
                    rotate(x, y); // 회전
                    int value = explore(); // 모의 탐사
                    // 우선순위에 따라 회전 목표 설정
                    if (max < value || (max == value && d < rd)) {
                        max = value; rx = x; ry = y; rd = d;
                    } else if (max == value && d == rd && y < ry) {
                        max = value; rx = x; ry = y; rd = d;
                    }
                }
            }
        }

        // 회전 목표 설정 후 탐사 시작
        if (max == 0) return 0;
        return go(rx, ry, rd);
    }

    private static int go(int rx, int ry, int rd) {
        // 회전
        setCopy();
        for (int d = 0; d <= rd; d++) rotate(rx, ry);
        setMap();

        int ans = 0;
        while (true) {
            int res = realExplore(); // 진짜 탐사
            if (res == 0) break;
            else ans += res;
            update(); // 새로운 조각들 채우기
        }

        return ans;
    }

    private static void update() {
        for (int y = 0; y < N; y++) {
            for (int x = N - 1; x >= 0; x--) {
                if (map[x][y] == -1) map[x][y] = bag.poll();
            }
        }
    }

    private static int realExplore() {
        visited = new boolean[N][N];
        queue = new ArrayDeque<>();

        int res = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (!visited[x][y]) res += bfs2(x, y);
            }
        }

        return res;
    }

    private static int bfs2(int x, int y) {
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        Queue<int[]> tmpQueue = new ArrayDeque<>();
        tmpQueue.offer(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            for (int[] d : direction) {
                int nx = p[0] + d[0];
                int ny = p[1] + d[1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny] || map[x][y] != map[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.offer(new int[]{nx, ny});
                tmpQueue.offer(new int[]{nx, ny});
            }
        }

        int res = tmpQueue.size();
        if (res >= 3) { // 길이가 3 이상인 유물만 유물 취급
            while (!tmpQueue.isEmpty()) {
                int[] p = tmpQueue.poll();
                map[p[0]][p[1]] = -1;
            }
        }

        return res >= 3 ? res : 0;
    }

    private static int explore() {
        visited = new boolean[N][N];
        queue = new ArrayDeque<>();

        int res = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (!visited[x][y]) res += bfs(x, y);

            }
        }
        return res;
    }

    private static int bfs(int x, int y) {
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        int cnt = 1;
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            for (int[] d : direction) {
                int nx = p[0] + d[0];
                int ny = p[1] + d[1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny] || copy[x][y] != copy[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.offer(new int[]{nx, ny});
                cnt++;
            }
        }

        return cnt >= 3 ? cnt : 0; // 길이가 3 이상만 유물 취급
    }

    private static void rotate(int x, int y) {
        setTmp();
        copy[x][y] = tmp[x + 2][y];
        copy[x][y + 1] = tmp[x + 1][y];
        copy[x][y + 2] = tmp[x][y];
        copy[x + 1][y] = tmp[x + 2][y + 1];
        copy[x + 1][y + 2] = tmp[x][y + 1];
        copy[x + 2][y] = tmp[x + 2][y + 2];
        copy[x + 2][y + 1] = tmp[x + 1][y + 2];
        copy[x + 2][y + 2] = tmp[x][y + 2];
    }

    private static void setTmp() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                tmp[x][y] = copy[x][y];
            }
        }
    }

    private static void setCopy() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                copy[x][y] = map[x][y];
            }
        }
    }

    private static void setMap() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                map[x][y] = copy[x][y];
            }
        }
    }
}
