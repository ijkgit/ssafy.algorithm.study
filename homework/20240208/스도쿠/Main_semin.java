import java.awt.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main_semin {
    static final int SIZE = 9;
    static final int MINI_SIZE = 3;
    static int[][] maps = new int[SIZE][SIZE];
    static List<Point> fillPoints = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < SIZE; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < SIZE; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
                if (maps[i][j] == 0) {
                    fillPoints.add(new Point(j, i));
                }
            }
        }
        dfs(0);
        System.out.println(sb);
    }

    private static void dfs(int depth) {
        if (sb.length() != 0) {
            return;
        }
        if (depth == fillPoints.size()) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    sb.append(maps[i][j]).append(" ");
                }
                sb.append("\n");
            }
            return;
        }
        int x = fillPoints.get(depth).x;
        int y = fillPoints.get(depth).y;
        for (int num = 1; num <= 9; num++) {
            if (validHeight(x, num) && validWidth(y, num) && validMiniMap(x, y, num)) {
                maps[y][x] = num;
                dfs(depth + 1);
                maps[y][x] = 0;
            }
        }
    }

    private static boolean validMiniMap(int x, int y, int num) {
        for (int i = (y / 3) * MINI_SIZE; i < (y / 3) * MINI_SIZE + MINI_SIZE; i++) {
            for (int j = (x / 3) * MINI_SIZE; j < (x / 3) * MINI_SIZE + MINI_SIZE; j++) {
                if (maps[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }


    private static boolean validWidth(int y, int num) {
        for (int x = 0; x < SIZE; x++) {
            if (maps[y][x] == num) {
                return false;
            }
        }
        return true;
    }

    private static boolean validHeight(int x, int num) {
        for (int y = 0; y < SIZE; y++) {
            if (maps[y][x] == num) {
                return false;
            }
        }
        return true;
    }

}
