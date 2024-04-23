package edu.ssafy.im.CodeTree.autonomousDriving;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, x, y, d;
    private static int[][] map;
    private static final int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        sb.append(sol());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int sol() {
        map[x][y] = -1; // 방문한 곳은 -1 처리
        while (true) {
            boolean flag = false;

            for (int i = 1; i <= 4; i++) {
                // 현재 방향을 기준으로 왼쪽으로 회전하면서
                int nd = d - i;
                if (nd < 0) nd += 4;

                int nx = x + direction[nd][0];
                int ny = y + direction[nd][1];

                // 한 번도 간 적이 없다면 해당 방향으로 한 칸 전진한다.
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (map[nx][ny] == 1 || map[nx][ny] == -1) continue;

                map[nx][ny] = -1;
                x = nx; y = ny; d = nd;
                flag = true; // 이동했음을 표시
                break;
            }
            if (!flag) { // 4방향 모두 확인했으나 전진하지 못한 경우에는
                if(!back()) break; // 후진 불가능 시 멈춤, 후진이 가능하면 다시 1번 과정 반복
            }
        }

        // 거쳐갔던 도로의 총 면적 구해주기
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == -1) ans++;
            }
        }

        return ans;
    }

    private static boolean back() {
        // 바라보는 방향을 유지한 채로 한 칸 후진
        int nx = x - direction[d][0];
        int ny = y - direction[d][1];

        // 뒷 공간이 인도여서 후진조차 하지 못한다면 작동을 멈춤
        if (nx < 0 || ny < 0 || nx >= N || ny >= M) return false;
        if (map[nx][ny] == 1) return false;

        // 후진을 하고 다시 1번 과정을 시도
        map[nx][ny] = -1;
        x = nx;
        y = ny;
        return true;
    }
}
