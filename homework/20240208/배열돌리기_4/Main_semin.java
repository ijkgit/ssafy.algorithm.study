import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main_semin {
    static int N, M, K;
    static int[][] maps;
    static Calc[] calcs;
    static List<int[]> orderData;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        K = parseInt(st.nextToken());
        calcs = new Calc[K];
        maps = new int[N][M];
        orderData = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                maps[i][j] = parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            calcs[i] = new Calc(parseInt(st.nextToken()), parseInt(st.nextToken()), parseInt(st.nextToken()));
        }
        permutation(new boolean[K], 0, new int[K]);
        int result = Integer.MAX_VALUE;
        for (int[] order : orderData) { //순서 조합
            int[][] copyMap = new int[maps.length][];
            for (int i = 0; i < maps.length; i++) {
                copyMap[i] = maps[i].clone();
            }
            for (int i = 0; i < order.length; i++) {
                rotation(copyMap, calcs[order[i]]);
            }
            result = Math.min(result, getArrayValue(copyMap));
        }

        System.out.println(result);
    }

    private static int getArrayValue(int[][] maps) {
        int value = Integer.MAX_VALUE;
        for (int i = 0; i < maps.length; i++) {
            value = Math.min(value, Arrays.stream(maps[i]).sum());
        }
        return value;
    }

    static void rotation(int[][] maps, Calc calc) {
        int s = calc.s;
        int r = calc.r;
        int c = calc.c;
        for (int cnt = 0; cnt < s; cnt++) {
            int start = maps[calc.r - s + cnt - 1][calc.c + s - cnt - 1]; //오른쪽 위
            for (int i = 1; i <= (s - cnt) * 2; i++) { // →
                maps[r - s + cnt - 1][c + s - cnt - i] = maps[r - s + cnt - 1][c + s - cnt - i - 1];
            }
            for (int i = 0; i < (s - cnt) * 2; i++) { // ↑
                maps[r - s + cnt + i - 1][c - s + cnt - 1] = maps[r - s + cnt + i][c - s + cnt - 1];
            }
            for (int i = 0; i < (s - cnt) * 2; i++) { // ←
                maps[r + s - cnt - 1][c - s + cnt + i - 1] = maps[r + s - cnt - 1][c - s + cnt + i];
            }
            for (int i = 1; i <= (s - cnt) * 2; i++) { // ↓
                maps[r + s - cnt - i][c + s - cnt - 1] = maps[r + s - cnt - i - 1][c + s - cnt - 1];
            }
            maps[r - s + cnt][c + s - cnt - 1] = start;
        }
    }

    static void permutation(boolean[] visited, int k, int[] selected) {
        if (k == K) {
            orderData.add(selected.clone());
            return;
        }
        for (int i = 0; i < K; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selected[k] = i;
                permutation(visited, k + 1, selected);
                visited[i] = false;
            }
        }
    }

    static class Calc {
        int r, c, s;

        Calc(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }

        @Override
        public String toString() {
            return "Calc{" +
                    "r=" + r +
                    ", c=" + c +
                    ", s=" + s +
                    '}';
        }
    }
}
