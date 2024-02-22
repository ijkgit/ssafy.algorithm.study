import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.*;

public class Main_semin {
    static int N, M, maps[][], checkArea, emptyArea;
    static HashMap<Integer, List<int[]>> strategy;
    static List<Point> points;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        List<int[]> one = Arrays.asList(new int[]{0}, new int[]{1}, new int[]{2}, new int[]{3});
        List<int[]> two = Arrays.asList(new int[]{0, 2}, new int[]{1, 3});
        List<int[]> three = Arrays.asList(new int[]{0, 1}, new int[]{1, 2}, new int[]{2, 3}, new int[]{3, 0});
        List<int[]> four = Arrays.asList(new int[]{0, 1, 2}, new int[]{0, 2, 3}, new int[]{1, 2, 3}, new int[]{0, 1, 3});
        List<int[]> five = Arrays.asList(new int[]{0, 1, 2, 3});
        strategy = new HashMap<>();
        strategy.put(1, one);
        strategy.put(2, two);
        strategy.put(3, three);
        strategy.put(4, four);
        strategy.put(5, five);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        points = new ArrayList<>();
        maps = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
                if (maps[i][j] != 0 && maps[i][j] != 6) {
                    points.add(new Point(j, i));
                } else if (maps[i][j] == 0) {
                    emptyArea++; //아무 것도 없는 빈 칸
                }
            }
        }
        solve(0);
        System.out.println(emptyArea - checkArea); //checkArea 가 감시영역, 가장 큰 감시영역을 뽑자
    }

    private static void solve(int idx) {
        if (idx == points.size()) {
            checkArea = Math.max(getCheckedCount(), checkArea);
            return;
        }
        Point p = points.get(idx);
        int key = maps[p.y][p.x];
        for (int[] dir : strategy.get(key)) {
            fillMap(dir, -1, p); //dir 방향들에 대해서 -1씩 빼기. 채운만큼 리턴한다.
            solve(idx + 1);
            fillMap(dir, 1, p); //다시 1씩 더하기
        }
    }

    private static int getCheckedCount() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maps[i][j] < 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void fillMap(int[] dir, int plusNum, Point p) {
        for (int i = 0; i < dir.length; i++) {
            int d = dir[i];
            int cnt = 1;
            while (true) {
                int nx = p.x + dx[d] * cnt;
                int ny = p.y + dy[d] * cnt;
                if (nx >= M || ny >= N || nx < 0 || ny < 0 || maps[ny][nx] == 6) {
                    break;
                }
                if (maps[ny][nx] <= 0) {
                    maps[ny][nx] += plusNum;
                }
                cnt++;
            }
        }
    }

}
