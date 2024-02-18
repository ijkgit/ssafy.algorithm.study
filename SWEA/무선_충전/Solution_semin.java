import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Solution_semin {
    static int M, A;
    static int[] aMove;
    static int[] bMove;
    static AP[] points;
    static int res = 0;
    static int[] dx = {0, 0, 1, 0, -1};
    static int[] dy = {0, -1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            M = parseInt(st.nextToken());
            A = parseInt(st.nextToken());
            aMove = new int[M];
            bMove = new int[M];
            points = new AP[A];
            res = 0;
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                aMove[j] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                bMove[j] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                points[i] = new AP(parseInt(st.nextToken()), parseInt(st.nextToken()), parseInt(st.nextToken()), parseInt(st.nextToken()), i);
            }
            Point a = new Point(1, 1);
            Point b = new Point(10, 10);
            for (int move = 0; move <= M; move++) {
                List<AP> aCan = new ArrayList<>();
                List<AP> bCan = new ArrayList<>();
                for (int idx = 0; idx < A; idx++) {
                    if (Math.abs(a.x - points[idx].x) + Math.abs(a.y - points[idx].y) <= points[idx].c) {
                        aCan.add(points[idx]);
                    }
                    if (Math.abs(b.x - points[idx].x) + Math.abs(b.y - points[idx].y) <= points[idx].c) {
                        bCan.add(points[idx]);
                    }
                }
                Collections.sort(aCan, (AP o1, AP o2) -> (o2.p - o1.p));
                Collections.sort(bCan, (AP o1, AP o2) -> (o2.p - o1.p));

                if (aCan.size() == bCan.size() && bCan.size() == 1 && aCan.get(0).equals(bCan.get(0))) {
                    res += aCan.get(0).p;
                } else if (aCan.size() == bCan.size() && bCan.size() == 1 && !aCan.get(0).equals(bCan.get(0))) {
                    res += aCan.get(0).p;
                    res += bCan.get(0).p;
                } else if (aCan.isEmpty() && !bCan.isEmpty()) {
                    res += bCan.get(0).p;
                } else if (!aCan.isEmpty() && bCan.isEmpty()) {
                    res += aCan.get(0).p;
                } else {
                    boolean[] visited = new boolean[points.length + 1];
                    int tempRes = 0;
                    for (int i = 0; i < aCan.size(); i++) {
                        int temp = aCan.get(i).p;
                        visited[aCan.get(i).idx] = true;
                        for (int j = 0; j < bCan.size(); j++) {
                            if (visited[bCan.get(j).idx]) {
                                continue;
                            }
                            temp += bCan.get(j).p;
                            break;
                        }
                        tempRes = Math.max(tempRes, temp);
                        visited[aCan.get(i).idx] = false;
                    }
                    res += tempRes;
                }
                if (move < M) {
                    a = new Point(a.x + dx[aMove[move]], a.y + dy[aMove[move]]);
                    b = new Point(b.x + dx[bMove[move]], b.y + dy[bMove[move]]);
                }
            }
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }

    static class AP {
        int x, y, c, p, idx;

        AP(int x, int y, int c, int p, int idx) {
            this.x = x;
            this.y = y;
            this.c = c; //충전 범위
            this.p = p; //처리량
            this.idx = idx;
        }


        @Override
        public String toString() {
            return "AP{" +
                    "x=" + x +
                    ", y=" + y +
                    ", c=" + c +
                    ", p=" + p +
                    ", idx=" + idx +
                    '}';
        }
    }
}