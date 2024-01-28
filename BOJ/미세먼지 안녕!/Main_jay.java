import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int r;
    static int c;
    static int startX1, startY1, startX2, startY2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[][] graph = new int[r][c];
        int[][] after = new int[r][c];
        boolean status = false;

        for (int x = 0; x < r; x++) {
            input = br.readLine();
            st = new StringTokenizer(input);
            for (int y = 0; y < c; y++) {
                graph[x][y] = Integer.parseInt(st.nextToken());
                after[x][y] = graph[x][y];
                if (!status && graph[x][y] == -1) {
                    startX1 = x;
                    startY1 = y;
                    startX2 = x + 1;
                    startY2 = y;
                    status = true;
                }
            }
        }

        int[][] direction = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        for (int i = 0; i < t; i++) {
            // 확산
            for (int x = 0; x < r; x++) {
                for (int y = 0; y < c; y++) {
                    int a = graph[x][y] / 5;
                    int count = 0;
                    for (int j = 0; j < 4; j++) {
                        int newX = x + direction[j][0];
                        int newY = y + direction[j][1];

                        if (checkStatus(newX, newY)) {
                            after[newX][newY] += a;
                            count++;
                        }
                    }
                    after[x][y] -= a * count;
                }
            }

            // deep copy
            for (int x = 0; x < r; x++) {
                for (int y = 0; y < c; y++) {
                    graph[x][y] = after[x][y];
                }
            }

            // 위쪽 정화
            after[startX1][1] = 0;
            for (int y = startY1 + 1; y < c - 1; y++) {
                after[startX1][y + 1] = graph[startX1][y];
            }
            for (int x = startX1; x > 0; x--) {
                after[x - 1][c - 1] = graph[x][c - 1];
            }
            for (int y = c - 1; y > 0; y--) {
                after[0][y - 1] = graph[0][y];
            }
            for (int x = 0; x < startX1 - 1; x++) {
                after[x + 1][0] = graph[x][0];
            }

            // 아래쪽 정화
            after[startX2][1] = 0;
            for (int y = startY2 + 1; y < c - 1; y++) {
                after[startX2][y + 1] = graph[startX2][y];
            }
            for (int x = startX2; x < r - 1; x++) {
                after[x + 1][c - 1] = graph[x][c - 1];
            }
            for (int y = c - 1; y > 0; y--) {
                after[r - 1][y - 1] = graph[r - 1][y];
            }
            for (int x = r - 1; x > startX2 + 1; x--) {
                after[x - 1][0] = graph[x][0];
            }

            // deep copy
            for (int x = 0; x < r; x++) {
                for (int y = 0; y < c; y++) {
                    graph[x][y] = after[x][y];
                }
            }
        }
        int ans = 0;
        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                ans += after[x][y];
            }
        }
        sb.append(ans + 2);
        System.out.println(sb);
    }

    public static boolean checkStatus(int x, int y) {
        return 0 <= x && x < r && 0 <= y && y < c && !((x == startX1 || x == startX2) && (y == startY1));
    }
}
