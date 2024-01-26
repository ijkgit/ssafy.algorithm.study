package 보급로;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

import static java.lang.Character.getNumericValue;

public class Solution_semin {
    private static int N;
    private static int[][] maps, distance;
    private static boolean[][] visited;
    private static int[] nx = {1, -1, 0, 0};
    private static int[] ny = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            N = Integer.parseInt(br.readLine());
            maps = new int[N][N];
            distance = new int[N][N];
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    maps[i][j] = getNumericValue(line.charAt(j));
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }
            calculate(0, 0);
            sb.append(distance[N - 1][N - 1]).append("\n");
        }
        System.out.println(sb);
    }

    private static void calculate(int startX, int startY) {
        Queue<Point> dq = new ArrayDeque<>();
        dq.offer(new Point(startX, startY));
        distance[startY][startX] = 0;
        while (!dq.isEmpty()) {
            Point point = dq.poll();
            int x = point.x;
            int y = point.y;
            for (int i = 0; i < 4; i++) {
                int dx = nx[i] + x;
                int dy = ny[i] + y;
                if (dx >= N || dy >= N || dx < 0 || dy < 0) {
                    continue;
                }
                if (distance[dy][dx] > distance[y][x] + maps[dy][dx]) {
                    distance[dy][dx] = distance[y][x] + maps[dy][dx];
                    dq.offer(new Point(dx, dy));
                }
            }
        }
    }
}
