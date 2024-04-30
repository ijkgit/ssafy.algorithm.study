import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    private static final int[][] direction = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    private static DragonCurve[] dragonCurves;
    private static boolean[][] map = new boolean[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        dragonCurves = new DragonCurve[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            dragonCurves[i] = new DragonCurve(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        sb.append(sol());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int sol() {
        makePath();
        move();
        return search();
    }

    private static void makePath() {
        for (DragonCurve dc : dragonCurves) {
            for (int g = 0; g < dc.g; g++) {
                Stack<Integer> stack = new Stack<>();
                stack.addAll(dc.history);
                while (!stack.isEmpty()) {
                    dc.history.push((stack.pop() + 1) % 4);
                }
            }
        }
    }

    private static void move() {
        for (DragonCurve dc : dragonCurves) {
            map[dc.x][dc.y] = true;
            for (int d : dc.history) {
                dc.x += direction[d][0];
                dc.y += direction[d][1];

                map[dc.x][dc.y] = true;
            }
        }
    }

    private static int search() {
        int ans = 0;
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                if (map[x][y] && map[x][y + 1] && map[x + 1][y] && map[x + 1][y + 1]) ans++;
            }
        }
        return ans;
    }

    static class DragonCurve {
        int x, y, d, g;
        Stack<Integer> history;

        public DragonCurve(int x, int y, int d, int g) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.g = g;
            this.history = new Stack<>();
            history.push(d);
        }
    }
}
