package edu.ssafy.im.SWEA.D0.No5644;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {
    private final static int SIZE = 10;
    private final static int[][] direction = {{0, 0}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int M;
    private int[] a, b;
    private AP[] ap;
    
    public static void main(String[] args) throws IOException {
        new Solution().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());

            a = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            b = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                b[i] = Integer.parseInt(st.nextToken());
            }

            ap = new AP[A];
            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken()) - 1;
                int x = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                ap[i] = new AP(x, y, c, p);
            }

            sb.append("#").append(t).append(" ").append(sol()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private int sol() {
        Point pA = new Point(0, 0);
        Point pB = new Point(SIZE - 1, SIZE - 1);

        // 사용자의 초기 위치(0초)부터 충전을 할 수 있다.
        ArrayList<Integer> resA = checkMap(pA.x, pA.y);
        ArrayList<Integer> resB = checkMap(pB.x, pB.y);

        int ans = checkDuplicate(resA, resB);

        // 명령문을 통해 좌표 이동 및 충전 검사
        for (int i = 0; i < M; i++) {
            pA.x += direction[a[i]][0];
            pA.y += direction[a[i]][1];
            pB.x += direction[b[i]][0];
            pB.y += direction[b[i]][1];

            resA = checkMap(pA.x, pA.y);
            resB = checkMap(pB.x, pB.y);

            ans += checkDuplicate(resA, resB);
        }

        return ans;
    }

    // AP 객체마다 해당 좌표가 충전범위인지 확인
    private ArrayList<Integer> checkMap(int x, int y) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int k = 0; k < ap.length; k++) {
            if (ap[k].map[x][y]) {
                res.add(k);
            }
        }
        return res;
    }

    // 충전 성능에 따라 내림차순 정렬 후 문제의 조건에 맞춰 최대 충전 성능 값 반환
    private int checkDuplicate(ArrayList<Integer> resA, ArrayList<Integer> resB) {
        resA.sort((a, b) -> ap[b].p - ap[a].p);
        resB.sort((a, b) -> ap[b].p - ap[a].p);

        if(resA.isEmpty() && resB.isEmpty()) {
            return 0;
        }
        if(resA.isEmpty()) {
            return ap[resB.get(0)].p;
        }
        if(resB.isEmpty()) {
            return ap[resA.get(0)].p;
        }
        if(resA.get(0) != resB.get(0)) {
            return ap[resA.get(0)].p + ap[resB.get(0)].p;
        }
        if(resA.get(0) == resB.get(0)) {
            if(resA.size() == 1 && resB.size() == 1) {
                return ap[resA.get(0)].p;
            }
            if(resB.size() == 1) {
                return ap[resA.get(1)].p + ap[resB.get(0)].p;
            }
            if(resA.size() == 1) {
                return ap[resA.get(0)].p + ap[resB.get(1)].p;
            }
            if(ap[resA.get(1)].p > ap[resB.get(1)].p) {
                return ap[resA.get(1)].p + ap[resB.get(0)].p;
            } else {
                return ap[resA.get(0)].p + ap[resB.get(1)].p;
            }
        }
        return 0;
    }

    class AP {
        int x, y, c, p;
        boolean[][] map = new boolean[SIZE][SIZE];

        public AP(int x, int y, int c, int p) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.p = p;

            // AP 인스턴스 생성시 자동으로 해당 인스턴스의 충전범위 할당
            int startX = this.x - this.c;
            int startY = this.y;
            for (int i = 0; i <= c; i++) {
                for (int j = -i; j <= i; j++) {
                    int nx = startX + i;
                    int ny = startY + j;
                    if (nx < 0 || ny < 0 || nx >= SIZE || ny >= SIZE) continue;
                    map[nx][ny] = true;
                }
            }
            startX = this.x + this.c;
            startY = this.y;
            for (int i = 0; i < c; i++) {
                for (int j = -i; j <= i; j++) {
                    int nx = startX - i;
                    int ny = startY + j;
                    if (nx < 0 || ny < 0 || nx >= SIZE || ny >= SIZE) continue;
                    map[nx][ny] = true;
                }
            }
        }
    }

    class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
