import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, X, Y, K;
    private static int[][] map;
    private static int[] command;
    private static int[] dice = {0, 0, 0, 0, 0, 0}; // 가장 처음에 주사위에는 모든 면에 0이 적혀져 있다.
    private final static int[][] direction = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; // 동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4로 주어진다.
    private static StringBuilder sb = new StringBuilder();
    private static int[] tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < M; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        command = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            command[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        sol();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void sol() {
        // 놓여져 있는 곳의 좌표는 (x, y) 이다.
        int x = X, y = Y;
        for (int c : command) {
            x += direction[c][0];
            y += direction[c][1];

            // 만약 바깥으로 이동시키려고 하는 경우에는 해당 명령을 무시해야 하며, 출력도 하면 안 된다.
            if (x < 0 || y < 0 || x >= N || y >= M) {
                x -= direction[c][0];
                y -= direction[c][1];
                continue;
            }

            tmp = dice.clone();
            // 주사위는 지도 위에 윗 면이 1이고, 동쪽을 바라보는 방향이 3인 상태로 놓여져 있으며,
            if (c == 0) { dice[5] = tmp[2]; dice[2] = tmp[0]; dice[0] = tmp[3]; dice[3] = tmp[5]; }
            else if (c == 1) { dice[5] = tmp[3]; dice[3] = tmp[0]; dice[0] = tmp[2]; dice[2] = tmp[5]; }
            else if (c == 2) { dice[5] = tmp[1]; dice[1] = tmp[0]; dice[0] = tmp[4]; dice[4] = tmp[5]; }
            else { dice[5] = tmp[4]; dice[4] = tmp[0]; dice[0] = tmp[1]; dice[1] = tmp[5]; }

            // 주사위를 굴렸을 때, 이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다.
            if (map[x][y] == 0) map[x][y] = dice[5];
            // 0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.
            else {
                dice[5] = map[x][y];
                map[x][y] = 0;
            }

            // 이동할 때마다 주사위의 윗 면에 쓰여 있는 수를 출력한다.
            sb.append(dice[0]).append("\n");
        }
    }
}
