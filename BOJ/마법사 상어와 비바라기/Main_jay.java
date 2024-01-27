import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] water = new int[n][n];
        for (int x = 0; x < n; x++) {
            input = br.readLine();
            st = new StringTokenizer(input);
            for (int y = 0; y < n; y++) {
                water[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] direction = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
        int[][] start = {{n - 1, 0}, {n - 1, 1}, {n - 2, 0}, {n - 2, 1}};
        boolean[][] cloud = new boolean[n][n];

        for (int[] s : start) {
            cloud[s[0]][s[1]] = true;
        }
        for (int i = 0; i < m; i++) {
            input = br.readLine();
            st = new StringTokenizer(input);

            int d = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            int dx = direction[d - 1][0] * (r % n);
            int dy = direction[d - 1][1] * (r % n);
            int newX, newY;

            boolean[][] afterCloud = new boolean[n][n];
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    // 1번 작업 수행
                    if (cloud[x][y]) {
                        newX = x + dx;
                        newY = y + dy;
                        if (newX < 0) {
                            newX = n + x + dx;
                        } else if (newX > n - 1) {
                            newX = x + dx - n;
                        }
                        if (newY < 0) {
                            newY = n + y + dy;
                        } else if (newY > n - 1) {
                            newY = y + dy - n;
                        }

                        // 2번 작업 수행
                        afterCloud[newX][newY] = true;
                        water[newX][newY]++;
                    }
                }
            }

            // deep copy
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    cloud[j][k] = afterCloud[j][k];
                }
            }

            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    if (cloud[x][y]) {
                        // 3번 작업 수행
                        afterCloud[x][y] = false;

                        // 4번 작업 수행
                        int count = 0;
                        for (int j = 1; j < 8; j += 2) {
                            newX = x + direction[j][0];
                            newY = y + direction[j][1];

                            if (0 <= newX && newX < n && 0 <= newY && newY < n) {
                                if (water[newX][newY] != 0) {
                                    count++;
                                }
                            }
                        }
                        water[x][y] += count;
                    }
                }
            }
            // 5번 작업 수행
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    if (water[x][y] >= 2) {
                        if (!cloud[x][y]) {
                            afterCloud[x][y] = true;
                            water[x][y] -= 2;
                        }
                    }
                }
            }

            // deep copy
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    cloud[x][y] = afterCloud[x][y];
                }
            }
        }
        int ans = 0;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                ans += water[x][y];
            }
        }
        sb.append(ans);
        System.out.print(sb);
    }
}
