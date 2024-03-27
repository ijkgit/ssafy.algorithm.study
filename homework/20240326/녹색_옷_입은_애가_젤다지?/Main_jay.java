package edu.ssafy.im.BOJ.Gold.G4.No4485;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] map;
    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = 1;
        while ((N = Integer.parseInt(br.readLine())) != 0) {
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            sb.append("Problem ").append(T).append(":").append(" ").append(Dijkstra()).append("\n");
            T++;
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int Dijkstra() {
        int[][] weight = new int[N][N];
        for(int[] w : weight) Arrays.fill(w, Integer.MAX_VALUE);
        weight[0][0] = map[0][0];

        Queue<Point> pq = new PriorityQueue<>();
        pq.offer(new Point(0, 0, weight[0][0]));

        while(!pq.isEmpty()) {
            Point p = pq.poll();

            // 목적지 도달 시 금액 반환
            if (p.x == N-1 && p.y == N-1) return p.w;

            for (int[] d: direction) {
                int nx = p.x + d[0];
                int ny = p.y + d[1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                if (p.w + map[nx][ny] < weight[nx][ny]) { // 최소 금액 갱신
                    weight[nx][ny] = p.w + map[nx][ny];
                    pq.add(new Point(nx, ny, weight[nx][ny]));
                }
            }
        }
        return -1;
    }

    static class Point implements Comparable<Point> {
        int x, y, w;

        public Point(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(Point p) {
            return this.w - p.w;
        }
    }
}
