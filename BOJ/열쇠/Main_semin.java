import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main_semin {
    static int h, w, ans;
    static char[][] maps;
    static List<Character> keys;
    static List<Point> doors;
    static boolean[][] visited;
    static Deque<Point> dq;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            ans = 0;
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            maps = new char[h + 2][w + 2]; //이렇게 채워줘야 83% 테케를 통과..뭐지..
            keys = new ArrayList<>();
            for (int i = 1; i <= h; i++) {
                char[] line = br.readLine().toCharArray();
                for (int j = 1; j <= w; j++) {
                    maps[i][j] = line[j - 1];
                }
            }
            for (char ch : br.readLine().toCharArray()) {
                keys.add(ch);
            }
            bfs(new Point(0, 0));
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs(Point p) {
        dq = new ArrayDeque<>();
        dq.add(p);
        visited = new boolean[h + 2][w + 2];
        visited[p.x][p.y] = true;
        doors = new ArrayList<>();
        while (!dq.isEmpty()) {
            p = dq.poll();
            if (maps[p.x][p.y] == '$') {
                ans++;
            }
            for (int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                if (nx >= h + 2 || ny >= w + 2 || nx < 0 || ny < 0) {
                    continue;
                }
                if (maps[nx][ny] == '*' || visited[nx][ny]) {
                    continue;
                }
                if (maps[nx][ny] >= 'A' && maps[nx][ny] <= 'Z') { //문
                    char need = Character.toLowerCase(maps[nx][ny]); //필요한 열쇠
                    if (!keys.contains(need)) {
                        doors.add(new Point(nx, ny)); //문 후보에 들어감, 방문은 아직 x (왜 여기서 방문 처리 안해야 하는지는 모르겠다..)
                        continue;
                    }
                }
                if (maps[nx][ny] >= 'a' && maps[nx][ny] <= 'z') { //열쇠
                    keys.add(maps[nx][ny]);
                    for (Point door : doors) {
                        char need = Character.toLowerCase(maps[door.x][door.y]);
                        if (maps[nx][ny] == need) {
                            dq.add(door); //이제 이 문에서도 탐색이 가능하다.
                            visited[door.x][door.y] = true; //여기서 방문처리
                        }
                    }
                }
                // . , 열쇠, $ 은 모두 다시 재탐색 가능한 위치  -> q에 넣는다.
                dq.add(new Point(nx, ny));
                visited[nx][ny] = true;
            }
        }
    }
}
