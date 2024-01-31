import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main_semin {
    static int N, M;
    static Magic[] magics; //주문, 몇 칸
    static int[][] maps;
    static boolean[][] clouds;

    //                         ←,        ↖,      ↑,       ↗,       →,       ↘,      ↓,       ↙
    static int[][] delta = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}}; //0:x, 1:y

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        maps = new int[N + 1][N + 1];
        clouds = new boolean[N + 1][N + 1];
        magics = new Magic[M];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            magics[i] = new Magic(parseInt(st.nextToken()), parseInt(st.nextToken()));
        }
        //(N, 1), (N, 2), (N-1, 1), (N-1, 2)
        clouds[N][1] = clouds[N][2] = clouds[N - 1][1] = clouds[N - 1][2] = true; //처음 구름 생성되는 위치
        for (int i = 0; i < M; i++) { //M으로 변경하기
            moveCloudsAndRain(magics[i]);
            waterCopyBugMagic();
            createNewClouds(); // 새 구름을 만든 뒤 기존 구름이 없어짐
        }
        System.out.println(getTotalCnt());
    }

    private static int getTotalCnt() {
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                cnt += maps[i][j];
            }
        }
        return cnt;
    }

    private static void createNewClouds() { //3,5
        boolean[][] newClouds = new boolean[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (!clouds[i][j] && maps[i][j] >= 2) {
                    newClouds[i][j] = true; //구름이 생기는 대신
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                clouds[i][j] = newClouds[i][j];
                if (clouds[i][j]) {
                    maps[i][j] -= 2; //물의 양이 줄어든다
                }
            }
        }
    }

    private static void waterCopyBugMagic() { //4
        int[] dx = {-1, 1, 1, -1};
        int[] dy = {-1, 1, -1, 1};
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (!clouds[i][j]) {
                    continue;
                }
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = j + dx[k];
                    int ny = i + dy[k];
                    if (nx < 1 || ny < 1 || nx > N || ny > N) {
                        continue;
                    }
                    if (maps[ny][nx] > 0) {
                        cnt++;
                    }
                }
                maps[i][j] += cnt;
            }
        }
    }

    private static void moveCloudsAndRain(Magic magic) { //1,2
        boolean[][] newClouds = new boolean[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (clouds[i][j]) {
                    int x = j + delta[magic.s - 1][0] * magic.d;
                    x = makeRightIdx(x); //인덱스 조정
                    int y = i + delta[magic.s - 1][1] * magic.d;
                    y = makeRightIdx(y);
                    newClouds[y][x] = true;
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                clouds[i][j] = newClouds[i][j]; //구름이 이동한다.
                if (clouds[i][j]) {
                    maps[i][j] += 1; //이동한 구름에서 비가 내린다.
                }
            }
        }
    }

    private static int makeRightIdx(int n) {
        if (n > N) {
            n = ((n - 1) % N) + 1;
        }
        while (n < 1) {
            n += N;
        }
        return n;
    }

    static class Magic {
        int s;
        int d;

        Magic(int s, int d) {
            this.d = d;
            this.s = s;
        }
    }
}
