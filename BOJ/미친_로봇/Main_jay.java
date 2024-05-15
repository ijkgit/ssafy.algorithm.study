import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static double ans = 0;
    private static double[] probabilities = new double[4];
    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 4; i++) probabilities[i] = Integer.parseInt(st.nextToken()) * 0.01;

        sb.append(sol());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static double sol() {
        visited = new boolean[2*N+1][2*N+1];
        visited[N][N] = true;
        dfs(0, N, N, 1);
        return ans;
    }

    private static void dfs(int depth, int x, int y, double res) {
        if (depth == N) {
            ans += res;
            return;
        }

        for (int d = 0; d < 4; d++){
            int nx = x + direction[d][0];
            int ny = y + direction[d][1];

            if (visited[nx][ny]) continue;

            visited[nx][ny] = true;
            dfs(depth + 1, nx, ny, res * probabilities[d]);
            visited[nx][ny] = false;
        }
    }
}
