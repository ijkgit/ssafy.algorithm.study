import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    private int N;
    private char S;
    private final static int[][] direction = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private int[][] map;
    private boolean[][] isMerged;
    private StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        new Solution().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            S = st.nextToken().charAt(0);

            map = new int[N][N];
            isMerged = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append("#").append(t).append("\n");
            sol();
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private void sol() {
        switch (S) {
            case 'l':
                goLeft();
                break;
            case 'r':
                goRight();
                break;
            case 'u':
                goUp();
                break;
            case 'd':
                goDown();
                break;
        }
        print();
    }

    private void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
    }

    /*
    바뀌는 방향 기준으로 바깥 인덱스에서 안쪽 인덱스 순서대로 탐색
    1.
    같은 숫자일 경우 합치면서, 방문 체크 후 바깥 인덱스 값 0 처리
    탐색 중 사이에 0이 껴있다면 계속 탐색, 만약 0이 아니면 서로 다른 숫자이므로 탐색 종료
    2.
    숫자가 다 합쳐졌으므로 해당 방향으로 쭉 밀기
     */
    private void goLeft() {
        for (int x = 0; x < N; x++) {
            for (int y = 1; y < N; y++) {
                for (int ny = y - 1; ny >= 0; ny--) {
                    if (map[x][ny] == map[x][y] && !isMerged[x][ny]) {
                        map[x][ny] *= 2;
                        map[x][y] = 0;
                        isMerged[x][ny] = true;
                    } else if (map[x][ny] != 0) break;
                }
            }
            for (int y = 1; y < N; y++) {
                for (int ny = y-1; ny >= 0; ny--) {
                    if (map[x][ny] == 0) {
                        map[x][ny] = map[x][ny+1];
                        map[x][ny+1] = 0;
                    } else break;
                }
            }
        }
    }

    private void goRight() {
        for (int x = 0; x < N; x++) {
            for (int y = N-2; y >= 0; y--) {
                for (int ny = y + 1; ny < N; ny++) {
                    if (map[x][ny] == map[x][y] && !isMerged[x][ny]) {
                        map[x][ny] *= 2;
                        map[x][y] = 0;
                        isMerged[x][ny] = true;
                    } else if (map[x][ny] != 0) break;
                }
            }
            for (int y = N-2; y >= 0; y--) {
                for (int ny = y+1; ny < N; ny++) {
                    if (map[x][ny] == 0) {
                        map[x][ny] = map[x][ny-1];
                        map[x][ny-1] = 0;
                    } else break;
                }
            }
        }
    }

    private void goUp() {
        for (int y = 0; y < N; y++) {
            for (int x = 1; x < N; x++) {
                for (int nx = x - 1; nx >= 0; nx--) {
                    if (map[nx][y] == map[x][y] && !isMerged[nx][y]) {
                        map[nx][y] *= 2;
                        map[x][y] = 0;
                        isMerged[nx][y] = true;
                    }
                    else if (map[nx][y] != 0) break;
                }
            }
            for (int x = 1; x < N; x++) {
                for (int nx = x-1; nx >= 0; nx--) {
                    if (map[nx][y] == 0) {
                        map[nx][y] = map[nx+1][y];
                        map[nx+1][y] = 0;
                    } else break;
                }
            }
        }
    }

    private void goDown() {
        for (int y = 0; y < N; y++) {
            for (int x = N-2; x >= 0; x--) {
                for (int nx = x + 1; nx < N; nx++) {
                    if (map[nx][y] == map[x][y] && !isMerged[nx][y]) {
                        map[nx][y] *= 2;
                        map[x][y] = 0;
                        isMerged[nx][y] = true;
                    } else if (map[nx][y] != 0) break;
                }
            }
            for (int x = N-2; x >= 0; x--) {
                for (int nx = x+1; nx < N; nx++) {
                    if (map[nx][y] == 0) {
                        map[nx][y] = map[nx-1][y];
                        map[nx-1][y] = 0;
                    } else break;
                }
            }
        }
    }
}