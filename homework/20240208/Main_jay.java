import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static final int SIZE = 9;
    private static final int BOX_SIZE = 3;
    private int[][] graph;
    private List<Point> arrayList;
    private StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        graph = new int[SIZE][SIZE];
        arrayList = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < SIZE; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 0) arrayList.add(new Point(i, j));
            }
        }
        sol(0);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private void sol(int depth) {
        if (sb.length() != 0) {
            return;
        }

        if (depth == arrayList.size()) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    sb.append(graph[i][j]).append(" ");
                }
                sb.append("\n");
            }
            return;
        }

        int x = arrayList.get(depth).x;
        int y = arrayList.get(depth).y;
        for (int n = 1; n <= SIZE; n++) {
            if (checkCol(n, x) && checkRow(n, y) && checkBox(n, x, y)) {
                graph[x][y] = n;
                sol(depth + 1);
                graph[x][y] = 0;
            }
        }
    }

    private boolean checkCol(int n, int r) {
        for (int c = 0; c < SIZE; c++) {
            if (graph[r][c] == n) return false;
        }
        return true;
    }

    private boolean checkRow(int n, int c) {
        for (int r = 0; r < SIZE; r++) {
            if (graph[r][c] == n) return false;
        }
        return true;
    }

    private boolean checkBox(int n, int r, int c) {
        int rSize = (r / BOX_SIZE) * BOX_SIZE;
        int cSize = (c / BOX_SIZE) * BOX_SIZE;
        for (int x = rSize; x < rSize + BOX_SIZE; x++) {
            for (int y = cSize; y < cSize + BOX_SIZE; y++) {
                if (graph[x][y] == n) return false;
            }
        }
        return true;
    }

    class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
