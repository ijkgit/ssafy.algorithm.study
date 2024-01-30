import java.util.*;
import java.io.*;

public class Main_sangphil {
    static class Pair {
        int x;
        int y;
        public Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};

    static int R, C, T;
    static int[][] graph;
    static int[][] weight;
    static int air, ans;
    static ArrayDeque<Pair> q = new ArrayDeque<Pair>();
    static ArrayDeque<Pair> qq = new ArrayDeque<Pair>();

    public static void main (String[] args) throws IOException {
        // System.setIn(Main_sangphil.class.getResourceAsStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        graph = new int[R][C];
        

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == -1) {
                    air = i;
                } else if (graph[i][j] != 0) {
                    q.add(new Pair(i, j));
                }
            }
        }
        
        for (int i = 0; i < T; i++) {
            ans = 0;
            weight = new int[R][C];
            while(!q.isEmpty()) {
                Pair p = q.poll();

                int w = (int) graph[p.x][p.y] / 5;
                for (int j = 0; j < 4; j++) {
                    int nx = p.x + dx[j];
                    int ny = p.y + dy[j];
                    if (0 <= nx && nx < R && 0 <= ny && ny < C) {
                        // if ((ny != 0 && nx != air) || (ny != 0 && nx != air - 1)) {
                        if (graph[nx][ny] != -1) {
                            weight[nx][ny] += w;
                            graph[p.x][p.y] -= w;
                        }
                    }
                }
                weight[p.x][p.y] += graph[p.x][p.y];
            }

            vacuum();
            weight[air][0] = -1;
            weight[air-1][0] = -1;

            for (int j = 0; j < R; j++) {
                for (int k = 0; k < C; k++) {
                    if (weight[j][k] > 0) {
                        q.add(new Pair(j, k));
                        ans += weight[j][k];
                    }
                    graph[j][k] = weight[j][k];
                }
            }
        }
        System.out.println(ans);
    }

    static void vacuum () {
        for (int i = air-2; i > 0; i--) {
            weight[i][0] = weight[i-1][0];
        }
        for (int i = 0; i < C-1; i++) {
            weight[0][i] = weight[0][i+1];
        }
        for (int i = 0; i < air-1; i++) {
            weight[i][C-1] = weight[i+1][C-1];
        }
        for (int i = C-1; i > 0; i--) {
            weight[air-1][i] = weight[air-1][i-1];
        }

        for (int i = air+1; i < R-1; i++) {
            weight[i][0] = weight[i+1][0];
        }
        for (int i = 0; i < C-1; i++) {
            weight[R-1][i] = weight[R-1][i+1];
        }
        for (int i = R-1; i > air; i--) {
            weight[i][C-1] = weight[i-1][C-1];
        }
        for (int i = C-1; i > 0; i--) {
            weight[air][i] = weight[air][i-1];
        }
    }
}