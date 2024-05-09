import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int K, M, N = 5, candiIdx = 0;
    static int[][] maps;
    static int[] candi;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Piece {
        int x, y, cnt;

        public Piece(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken()); //탐사 반복 횟수
        M = Integer.parseInt(st.nextToken()); //벽면 유물 조각 개수
        maps = new int[N][N];
        candi = new int[M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            candi[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < K; i++) { //i번째 탐사 시작
            Piece startP = findStartPoint();
            for (int cnt = 0; cnt < startP.cnt; cnt++) {
                rotateClock90(maps, startP.x, startP.y);
            }
            int turnRes = 0;
            while (true) { //유물 연쇄 획득
                int get = 0; //이번 턴에 획득하는 유물
                boolean[][] visited = new boolean[N][N];
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < N; c++) {
                        if (visited[r][c]) {
                            continue;
                        }
                        ArrayDeque<Point> dq = new ArrayDeque<>();
                        dfs2(r, c, maps, maps[r][c], visited, dq);
                        if (dq.size() >= 3) { //3개 이상 모였을 때만 터뜨린다.
                            while (!dq.isEmpty()) {
                                Point p = dq.poll();
                                maps[p.x][p.y] = 0;
                            }
                        }
                    }
                }
                for (int c = 0; c < N; c++) {
                    for (int r = N - 1; r >= 0; r--) {
                        if (maps[r][c] == 0) {
                            get++;
                            maps[r][c] = candi[candiIdx++]; //벽면 숫자로 다시 채운다.
                        }
                    }
                }
                if (get == 0) { //채운 숫자가 없음 -> k번째 탐사의 유물 획득이 종료
                    break;
                }
                turnRes += get;
            }
            if (turnRes == 0) { //k번째 탐사에 유물을 아예 얻지 못함 -> 전체 종료
                break;
            }
            System.out.print(turnRes + " ");
        }
    }

    /**
     * 최대 이익을 낼 수 있는 시작점을 찾는다.
     * */
    private static Piece findStartPoint() {
        int maxScore = 0;
        Piece startP = new Piece(0, 0, 50000);
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                int[][] copy = copyMap(maps);
                for (int cnt = 1; cnt <= 3; cnt++) { //90,180,270 회전
                    rotateClock90(copy, i, j);
                    int score = getScore(copy);
                    if (score > maxScore || (score == maxScore && cnt < startP.cnt)) {
                        startP = new Piece(i, j, cnt); //조건에 따라, 더 높은 가치를 낼 수 있는 좌표로 시작점 초기화
                        maxScore = score;
                    }
                }
            }
        }
        return startP;
    }

    /**
     * 로테이션 되어 들어온 지도를 받아, 유물 가치를 몇 점 얻을 수 있는지 센다.
     */
    private static int getScore(int[][] copy) {
        boolean[][] visited = new boolean[N][N];
        int score = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (visited[r][c]) {
                    continue;
                }
                visited[r][c] = true;
                int temp = dfs(r, c, copy, visited, copy[r][c]);
                if (temp >= 3) {
                    score += temp;
                }
            }
        }
        return score;
    }

    /**
     * start로 들어왔을 때 몇개의 조각을 터뜨릴 수 있는지 센다.
     * */
    private static int dfs(int r, int c, int[][] maps, boolean[][] visited, int start) {
        int cnt = 1;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr >= N || nc >= N || nr < 0 || nc < 0) {
                continue;
            }
            if (visited[nr][nc]) {
                continue;
            }
            if (maps[nr][nc] == start) {
                visited[nr][nc] = true;
                cnt += dfs(nr, nc, maps, visited, start);
            }
        }
        return cnt;
    }

    /**
     *  4방 탐색을 해서 start와 같은 조각 = 즉, 터뜨릴 조각을 찾는다.
     */
    private static void dfs2(int r, int c, int[][] maps, int start, boolean[][] visited, ArrayDeque<Point> dq) {
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr >= N || nc >= N || nr < 0 || nc < 0) {
                continue;
            }
            if (visited[nr][nc]) {
                continue;
            }
            if (maps[nr][nc] == start) {
                visited[nr][nc] = true;
                dq.add(new Point(nr, nc));
                dfs2(nr, nc, maps, start, visited, dq);
            }
        }
    }

    /**
     * 시계 방향 회전
     * */
    private static void rotateClock90(int[][] copy, int sx, int sy) {
        int tmp = copy[sy + 0][sx + 2];
        copy[sy + 0][sx + 2] = copy[sy + 0][sx + 0];
        copy[sy + 0][sx + 0] = copy[sy + 2][sx + 0];
        copy[sy + 2][sx + 0] = copy[sy + 2][sx + 2];
        copy[sy + 2][sx + 2] = tmp;
        tmp = copy[sy + 1][sx + 2];
        copy[sy + 1][sx + 2] = copy[sy + 0][sx + 1];
        copy[sy + 0][sx + 1] = copy[sy + 1][sx + 0];
        copy[sy + 1][sx + 0] = copy[sy + 2][sx + 1];
        copy[sy + 2][sx + 1] = tmp;
    }

    private static int[][] copyMap(int[][] maps) {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            copy[i] = maps[i].clone();
        }
        return copy;
    }

}