package edu.ssafy.im.SWEA.D0.No5653;

import java.io.*;
import java.util.*;

public class Solution {
    private int n, m, k;
    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private boolean[][] visited;
    private ArrayList<Point> cellList;

    class Point implements Comparable<Point> {
        int x, y, t, v;

        public Point(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
            this.v = t;
        }

        @Override
        public int compareTo(Point o) {
            if (o.t == this.t) {
                return o.v - this.v;
            }
            return o.t - this.t;
        }

        @Override
        public String toString() {
            return "{" +
                    t +
                    " " + v +
                    '}';
        }
    }

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
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            int SIZE = Math.max(n, m) + 2 * k;
            cellList = new ArrayList<>();
            visited = new boolean[SIZE][SIZE];
            for (int i = (SIZE - n) / 2; i < (SIZE + n) /2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = (SIZE - m) / 2; j < (SIZE+m) / 2; j++) {
                    int v = Integer.parseInt(st.nextToken());
//                    visited[i][j] = true;
                    if(v != 0) {
                        cellList.add(new Point(i, j, v));
                    }
                }
            }

            sb.append("#").append(t).append(" ").append(sol()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private int sol() {
        for (int now = 0; now <= k; now++) {
            Collections.sort(cellList);
            ArrayList<Point> delList = new ArrayList<>();
            System.out.println(cellList);
            for (int i = 0; i < cellList.size(); i++) {
                if (cellList.get(i).t == -2) break;
                if (visited[cellList.get(i).x][cellList.get(i).y]) {
                    cellList.get(i).t = -2;
                    continue;
                }
                cellList.get(i).t--;
                if (cellList.get(i).t == -1) {
                    cellList.get(i).t = -2;
                    visited[cellList.get(i).x][cellList.get(i).y] = true;
                    delList.add(new Point(cellList.get(i).x, cellList.get(i).y, cellList.get(i).v));
//                    ans++;
                }
            }
            for (Point p : delList) {
                for (int d = 0; d < direction.length; d++) {
                    int nx = p.x + direction[d][0];
                    int ny = p.y + direction[d][1];

                    if(visited[nx][ny]) continue;

                    cellList.add(new Point(nx, ny, p.v));
                }
            }
        }

        int ans = 0;
        Collections.sort(cellList);
        for (Point p: cellList) {
            if(p.t < 0) break;
            ans++;
        }
        return ans;
    }
}
