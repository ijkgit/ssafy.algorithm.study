import java.util.*;
import java.io.*;

public class Solution_sangphil {
    static class Pair {int x; int y; int w; public Pair(int x, int y, int w) {this.x=x;this.y=y;this.w=w;}}
    static List<Pair> cores;
    static int[][] arr;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int n;
    static int count, ans;
    public static void main (String[] args) throws IOException {
        System.setIn(Solution.class.getResourceAsStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            cores = new ArrayList<Pair>();
            n = Integer.parseInt(br.readLine());
            arr = new int[n+2][n+2];
            count = 0;
            ans = Integer.MAX_VALUE;

            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if (arr[i][j] == 1) {
                        if (i == 1 || j == 1 || i == n || j == n) continue;
                        int dis = Math.min(Math.min(j-1, n-j), Math.min(i-1, n-i));
                        cores.add(new Pair(i, j, dis));
                    }
                }
            }
            //Collections.sort(cores, (o1, o2) -> o1.w-o2.w);

            traveling(0, 0, 0, 0);

            sb.append(String.format("#%d %d\n", t, ans));
        }
        System.out.println(sb);
    }

    static void traveling(int depth, int curr, int cnt, int dis) {
        if (depth == cores.size()) {
            if (count < cnt) {
                count = cnt;
                ans = dis;
            } else if (count == cnt) {
                ans = Math.min(ans, dis);
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            Pair reserve = check(cores.get(curr), i);

            if (reserve != null && arr[reserve.x][reserve.y] == 0) {
                arr[reserve.x][reserve.y] = curr;
                addi(cores.get(curr), i);
                traveling(depth+1, curr+1, cnt+1, dis + reserve.w);
                diff(cores.get(curr), i);
                arr[reserve.x][reserve.y] = 0;
            } else {
                traveling(depth+1, curr+1, cnt, dis);
            }

        }
        //traveling(depth+1, curr+1, cnt, dis);
    }

    static Pair check (Pair p, int i) {
        int cnt = 0;
        int nx = p.x + dx[i];
        int ny = p.y + dy[i];
        while (true) {
            if (nx == 0 || nx ==  n+1 || ny == 0 || ny == n+1) {
                break;
            }
            if (arr[nx][ny] != 0) {
                return null;
            }

            nx += dx[i];
            ny += dy[i];
            cnt ++;
        }

        return new Pair(nx, ny, cnt);
    }

    static void addi (Pair p, int i) {
        int nx = p.x + dx[i];
        int ny = p.y + dy[i];
        while (true) {
            if (nx == 0 || nx ==  n+1 || ny == 0 || ny == n+1) {
                break;
            }

            arr[nx][ny] += 3;

            nx += dx[i];
            ny += dy[i];
        }
    }

    static void diff (Pair p, int i) {
        int nx = p.x + dx[i];
        int ny = p.y + dy[i];
        while (true) {
            if (nx == 0 || nx ==  n+1 || ny == 0 || ny == n+1) {
                break;
            }

            arr[nx][ny] -= 3;

            nx += dx[i];
            ny += dy[i];
        }
    }
}