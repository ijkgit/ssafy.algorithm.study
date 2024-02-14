import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_semin {
    static int N;
    static int[][] maps;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        maps = new int[N][N];
        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                maps[i][j] = Character.getNumericValue(line[j]);
            }
        }
        devideNconquer(N, 0, 0);
        System.out.println(sb);
    }

    public static void devideNconquer(int n, int x, int y) {
        if (n == 0) {
            return;
        }
        int start = maps[y][x];
        for (int i = y; i < y + n; i++) {
            for (int j = x; j < x + n; j++) {
                if (start != maps[i][j]) {
                    sb.append("(");
                    //dnq
                    int hMid = y + n / 2;
                    int wMid = x + n / 2;
                    devideNconquer(n / 2, x, y);
                    devideNconquer(n / 2, wMid, y);
                    devideNconquer(n / 2, x, hMid);
                    devideNconquer(n / 2, wMid, hMid);
                    sb.append(")");
                    return;
                }
            }
        }
        sb.append(maps[y][x]);
    }
}