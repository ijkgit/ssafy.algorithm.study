import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int H, W;
    private static char[][] map;
    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static boolean[] hasKey;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());

        for (int T = 0; T < TC; T++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            /*
            시작점은 바깥 테두리 어디든 가능하므로,
            (0,0) ~ (H+2, W+2) 의 진입 가능한 가상 벽 생성
            (1,1) ~ (H+1, W+1) 에 실제 입력 값을 할당
             */
            map = new char[H + 2][W + 2];
            for (int x = 1; x < H + 1; x++) {
                char[] row = br.readLine().toCharArray();
                for (int y = 1; y < W + 1; y++) {
                    map[x][y] = row[y - 1];
                }
            }

            // boolean 배열로 열쇠를 갖고 있는지 확인 가능
            char[] keys = br.readLine().toCharArray();
            hasKey = new boolean['z' - 'a' + 1];
            for (char key : keys) {
                if (key == '0') break;
                hasKey[key - 'a'] = true;
            }

            sb.append(sol()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int sol() {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(0, 0));

        boolean[][] visited = new boolean[H + 2][W + 2];
        visited[0][0] = true;

        /*
        만약 열쇠를 갖고 있지 않은 채 문을 방문한 경우, 해당 문에 대응하는 좌표를 저장해줌.
        추후에 해당 열쇠를 얻었을 경우, 저장된 좌표를 꺼내 방문 후 탐색 시작.
         */
        ArrayList<Point>[] timeMachine = new ArrayList['z' - 'a' + 1];
        for (int i = 0; i < 'z' - 'a' + 1; i++) timeMachine[i] = new ArrayList<>();

        int ans = 0;
        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int[] d : direction) {
                int nx = p.x + d[0];
                int ny = p.y + d[1];

                if (nx < 0 || ny < 0 || nx >= H + 2 || ny >= W + 2) continue;
                if (visited[nx][ny] || map[nx][ny] == '*') continue;

                char current = map[nx][ny];

                if ('A' <= current && current <= 'Z') {
                    // 문에 방문했지만 열쇠가 없을 경우 해당 좌표를 저장
                    if (!hasKey[current - 'A']) {
                        timeMachine[current - 'A'].add(new Point(nx, ny));
                        continue; // 방문처리는 하면 안됨!
                    }
                } else if ('a' <= current && current <= 'z') {
                    hasKey[current - 'a'] = true;

                    // 열쇠가 없어서 방문 못했던 좌표들을 꺼내서 방문처리
                    for (Point tmp : timeMachine[current - 'a']) {
                        visited[tmp.x][tmp.y] = true;
                        q.offer(tmp);
                    }
                } else if (current == '$') ans++;

                // 열쇠가 없는 경우를 제외하고 모두 방문 가능한 case
                visited[nx][ny] = true;
                q.offer(new Point(nx, ny));
            }
        }

        return ans;
    }

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
