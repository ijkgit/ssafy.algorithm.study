package edu.ssafy.im.BOJ.Gold.G3.No17135;

import java.io.*;
import java.util.*;

public class Main {
    private int[][] map;
    private static final int SIZE = 3;
    private int[] sel;
    private int n, m, d;
    private static final int[][] direction = {{0, -1}, {-1, 0}, {0, 1}};
    private ArrayList<Point> delList;
    private int[][] original;
    private boolean[][] visited;
    private int res;
    private int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        original = new int[n][m];
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                original[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sol();
        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private void sol() {
        sel = new int[SIZE];
        permutation(0, 0);
    }


    // 궁수의 위치를 결정한 후 게임을 시작한다.
    private void permutation(int k, int v) {
        if (k == SIZE) {
            startGame();
            ans = Math.max(ans, res);
            return;
        }

        for (int i = 0; i < m; i++) {
            if ((v & (1 << i)) == 0) {
                sel[k] = i;
                permutation(k + 1, v |= 1 << i);
            }
        }
    }

    /*
    게임의 로직
    1. 전역 변수 초기화
    2. 각 궁수의 위치를 기점으로 BFS 탐색 후 적의 위치 확인
    3. 적 제거
    4. 적 위치 한 칸 전진
    5. 2~4 번을 n번 반복 ( 행의 갯수만큼 반복 시 모든 적이 전진하므로 종료 )
     */
    private void startGame() {
        init();
        for (int i = 0; i < n; i++) {
            for (int s : sel) {
                bfs(n - 1, s);
            }
            updateEnemy();
            moveForward();
        }
    }

    private void init() {
        delList = new ArrayList<>();
        res = 0;
        copy();
    }

    /*
    문제의 조건 중, 동일 거리일 경우 왼쪽 적을 우선 제거하는 조건이 있다.

    BFS 탐색의 첫 좌표를 궁수의 바로 윗좌표(궁수 R-1, 궁수 C) 로 지정할 경우,
    해당 위치에 적이 존재하면 탐색 종료

    해당 위치에 적이 존재하지 않는다면, 동 -> 북 -> 서 순서로 탐색
    전체 탐색 : 북 -> 동 -> 북 -> 서 -> 동 -> 북 -> 서 ...
    순서대로 탐색 시 거리는 1 -> 2 -> 2 -> 2 -> 3 -> 3 -> 3 ...
    따라서, 첫번째 탐색 위치를 북쪽으로 따로 지정 후 동 북 서 순으로 탐색할 경우
    거리가 동일할시 동쪽부터 적이 선택되면서 탐색 종료됨
     */
    private void bfs(int x, int y) {
        if (map[x][y] == 1) {
            delList.add(new Point(x, y));
            return;
        }

        Queue<Point> queue = new ArrayDeque<>();
        visited = new boolean[n][m];
        queue.offer(new Point(x, y, 1));

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            for (int dr = 0; dr < direction.length; dr++) {
                int nx = p.x + direction[dr][0];
                int ny = p.y + direction[dr][1];
                int nd = p.d + 1;

                if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny]) continue;

                if (nd > d) return;

                if (map[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    queue.offer(new Point(nx, ny, nd));
                }

                if (map[nx][ny] == 1) {
                    delList.add(new Point(nx, ny));
                    return;
                }
            }
        }
    }

    private void copy() {
        for (int i = 0; i < n; i++) {
            map[i] = original[i].clone();
        }
    }

    private void updateEnemy() {
        for (Point p : delList) {
            if (map[p.x][p.y] == 1) {
                map[p.x][p.y] = 0;
                res++;
            }
        }
        delList.clear();
    }

    private void moveForward() {
        for (int r = n - 1; r > 0; r--) {
            map[r] = map[r - 1].clone();
        }
        for (int c = 0; c < m; c++) {
            map[0][c] = 0;
        }
    }

    class Point {
        int x, y, d;

        public Point(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
