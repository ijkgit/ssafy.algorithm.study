import java.util.*;
import java.io.*;

public class Solution_sangphil {
    // 탱크의 좌표랑 방향을 담는 클래스
    static class Pair {
        int x;
        int y;
        int d;
        public Pair(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    static int T, H, W, N;
    static char[][] board;
    static Pair tank;
    static final int[] dx = {-1,1,0,0}; // 시뮬용
    static final int[] dy = {0,0,-1,1}; // 시뮬용
    static final HashMap<Character, Integer> moveMap = new HashMap<Character, Integer>() {{
        put('U', 0);
        put('D', 1);
        put('L', 2);
        put('R', 3);
    }};
    static final HashMap<Character, Integer> map = new HashMap<Character, Integer>() {{
        put('^', 0);
        put('v', 1);
        put('<', 2);
        put('>', 3);
    }};

    public static void main(String[] args) throws IOException {
        // System.setIn(Solution_sangphil.class.getResourceAsStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        // 마지막 탱크 방향용 키,벨류의 역 딕셔너리
        HashMap<Integer, Character> reversedMap = new HashMap<Integer, Character> ();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            reversedMap.put(entry.getValue(), entry.getKey());
        }

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            board = new char[H][W];

            // 보드판 초기화 및 탱크 좌표 설정
            for (int i = 0; i < H; i++) {
                String line = br.readLine();
                for (int j = 0; j < W; j++) {
                    board[i][j] = line.charAt(j);
                    if (map.containsKey(board[i][j])) {
                        tank = new Pair(i, j, map.get(board[i][j]));
                    }
                }
            }

            N = Integer.parseInt(br.readLine());

            String line = br.readLine();

            // S 면 shoot() 메소드
            // 그 외에는 move() 메소드
            for (int i = 0; i < N; i++) {
                //print();
                char cmd = line.charAt(i);
                if (cmd == 'S') shoot();
                else move(cmd);
            }

            board[tank.x][tank.y] = reversedMap.get(tank.d); // 마지막 탱크 방향

            sb.append("#").append(t+1).append(" ");
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    sb.append(board[i][j]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    static void move(char cmd) {
        tank.d = moveMap.get(cmd);
        int nx = tank.x + dx[tank.d];
        int ny = tank.y + dy[tank.d];

        if (0 <= nx && nx < H && 0 <= ny && ny < W) {
            if (board[nx][ny] == '.') {
                board[tank.x][tank.y] = '.';
                tank.x = nx;
                tank.y = ny;
            }
        }
    }

    static void shoot() {
        int nx = tank.x + dx[tank.d];
        int ny = tank.y + dy[tank.d];

        while (!(0 > nx || nx >= H || 0 > ny || ny >= W)) {
            if (board[nx][ny] == '#') {
                return;
            }
            if (board[nx][ny] == '*') {
                board[nx][ny] = '.';
                return;
            }
            nx += dx[tank.d];
            ny += dy[tank.d];
        }
    }
}