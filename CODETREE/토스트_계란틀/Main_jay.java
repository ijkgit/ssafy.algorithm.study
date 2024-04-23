package edu.ssafy.im.CodeTree.toastEggmold;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    private static int N, L, R;
    private static int[][] map, union;
    private static int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        sb.append(sol());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int sol() {
        int ans = 0;
        while (makeUnion() != 0) { // 분리된 계란 틀의 수가 0이라면, 계란의 이동이 더 이상 필요 없는 경우
            move();
            ans++; // 이동 횟수 세주기
        }
        return ans;
    }

    private static int makeUnion() {
        int cnt = 0;

        // 선의 분리를 통해 합쳐진 계란틀의 계란은 하나로 합치고 이후에 다시 분리한다.
        initUnion(); // 계란틀 분리하기

        // 모든 계란 틀에 대해 검사를 실시하여 계란틀의 선을 분리
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                for (int[] d : direction) {
                    int nx = x + d[0];
                    int ny = y + d[1];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if (!check(map[x][y], map[nx][ny])) continue;

                    // 두 계란틀의 계란의 양의 차이가 L 이상 R 이하라면 계란틀의 해당 선을 분리
                    setUnion(union[nx][ny], union[x][y]);
                    cnt++;
                }
            }
        }

        return cnt; // 분리된 계란 틀의 수를 반환
    }

    // 계란틀을 만들어준다.
    private static void initUnion() {
        union = new int[N][N];
        int num = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                union[i][j] = num++;
            }
        }
    }

    // 계란틀의 해당 선을 분리할 수 있는지 확인
    private static boolean check(int a, int b) {
        return L <= Math.abs(a - b) && Math.abs(a - b) <= R;
    }

    private static void setUnion(int u, int n) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (union[x][y] == u) union[x][y] = n;
            }
        }
    }

    // 각 계란이 변동된 내용을 갱신해주기
    private static void move() {
        visited = new boolean[N][N]; // 방문 배열은 공용으로 사용
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                bfs(x, y);
            }
        }
    }

    private static void bfs(int x, int y) {
        ArrayDeque<int[]> queue = new ArrayDeque<>(); // 탐색을 위한 큐
        ArrayDeque<int[]> unions = new ArrayDeque<>(); // 연합끼리 모으기 위한 큐
        visited[x][y] = true;
        queue.offer(new int[]{x, y});
        unions.offer(new int[]{x, y});

        int sum = map[x][y];
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] d : direction) {
                int nx = cur[0] + d[0];
                int ny = cur[1] + d[1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny]) continue;
                if (union[x][y] != union[nx][ny]) continue;

                // 합쳐질 계란들의 총 합을 구해주고, 각 큐에 좌표를 넣어준다.
                visited[nx][ny] = true;
                sum += map[nx][ny];
                queue.offer(new int[]{nx, ny});
                unions.offer(new int[]{nx, ny});
            }
        }

        // 각 계란틀별 계란의 양은 (합쳐진 계란의 총 합)/(합쳐진 계란틀의 총 개수)
        // 연합 계란들의 값을 갱신해준다.
        for (int[] u : unions) map[u[0]][u[1]] = sum / unions.size();
    }
}
