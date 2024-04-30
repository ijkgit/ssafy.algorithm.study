import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int R, C, K;
    private static int[][] map = new int[100][100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken()) - 1;
        C = Integer.parseInt(st.nextToken()) - 1;
        K = Integer.parseInt(st.nextToken());

        for (int x = 0; x < 3; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < 3; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        sb.append(sol());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int sol() {
        int X = 3, Y = 3, TIME = 0;

        while (TIME < 100) {
            if (map[R][C] == K) return TIME;

            if (X >= Y) { // 행의 개수가 열의 개수보다 크거나 같은 경우
                Y = row(X, Y);
            } else { // 행의 개수가 열의 개수보다 작은 경우
                X = col(X, Y);
            }

            TIME++;
        }

        return -1;
    }

    private static int col(int X, int Y) {
        Queue<Point> pq = new PriorityQueue<>();
        int[][] after = new int[100][100];

        int size = 0;
        for (int y = 0; y < Y; y++) {
            int[] count = new int[101];
            for (int x = 0; x < X; x++) {
                count[map[x][y]]++; // 출현 빈도수 측정
            }
            for (int c = 1; c < 101; c++) { // 정렬
                if (count[c] != 0) pq.offer(new Point(c, count[c]));
            }
            size = Math.max(size, pq.size() * 2); // 사이즈 측정
            int nx = 0;
            while (!pq.isEmpty()) {
                Point p = pq.poll();
                after[nx++][y] = p.value; // 숫자와 해당 숫자의 출현 빈도 수를 함께 할당
                after[nx++][y] = p.count;
                if (nx == 100) pq = new PriorityQueue<>(); // 100개의 격자를 제외하고는 모두 버림
            }
        }
        map = after;
        return Math.min(size, 100); // 격자가 100개 초과 시 100개로 고정
    }

    private static int row(int X, int Y) {
        Queue<Point> pq = new PriorityQueue<>();
        int[][] after = new int[100][100];

        int size = 0;
        for (int x = 0; x < X; x++) {
            int[] count = new int[101];
            for (int y = 0; y < Y; y++) {
                count[map[x][y]]++;
            }
            for (int c = 1; c < 101; c++) {
                if (count[c] != 0) pq.offer(new Point(c, count[c]));
            }
            size = Math.max(size, pq.size() * 2);
            int ny = 0;
            while (!pq.isEmpty()) {
                Point p = pq.poll();
                after[x][ny++] = p.value;
                after[x][ny++] = p.count;
                if (ny == 100) pq = new PriorityQueue<>();
            }
        }

        map = after;
        return Math.min(size, 100);
    }

    static class Point implements Comparable<Point> {
        int value, count;

        public Point(int value, int count) {
            this.value = value;
            this.count = count;
        }

        @Override
        public int compareTo(Point p) {
            if (this.count == p.count) return this.value - p.value; // 출현 횟수가 같을 경우 숫자가 작은 순서대로 정렬
            return this.count - p.count; // 출현 빈도 수가 적은 순서대로 정렬
        }
    }
}
