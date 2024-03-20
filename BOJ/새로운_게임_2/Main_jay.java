import java.io.*;
import java.util.*;

public class Main {
    private static int N, K;
    private static int[][] map;
    private static List<Integer>[][] chessMap;
    private static ArrayList<Chess> chessList;
    private static final int[][] direction = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
        map : 위치에 따른 색을 저장
        chessMap : 해당 위치에 존재하는 체스 말들을 저장 -> ArrayList를 갖는 2차원 배열
         */
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        chessMap = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                chessMap[i][j] = new ArrayList<>();
            }
        }

        /* chessList : 각 체스의 위치와 방향 데이터를 저장
            zerobase로 구현
         */
        chessList = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            chessList.add(new Chess(x, y, d));
            chessMap[x][y].add(i);
        }

        sb.append(sol());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int sol() {
        // 턴 한 번은 1번 말부터 K번 말까지 순서대로 이동시키는 것이다.
        // 게임이 종료되는 턴의 번호를 출력한다.
        // 그 값이 1,000보다 크거나 절대로 게임이 종료되지 않는 경우에는 -1을 출력한다.
        for (int turn = 1; turn <= 1000; turn++) {
            for (int i = 0; i < chessList.size(); i++) {
                Chess c = chessList.get(i);

                int nx = c.x + direction[c.d][0];
                int ny = c.y + direction[c.d][1];

                if (go(i, c.x, c.y, nx, ny, false, c.d)) return turn;
            }
        }
        return -1;
    }

    private static boolean go(int chess, int x, int y, int nx, int ny, boolean status, int d) {
        // 파란색인 경우에는 A번 말의 이동 방향을 반대로 하고 한 칸 이동한다.
        // 체스판을 벗어나는 경우에는 파란색과 같은 경우이다.
        if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 2) {
            // 방향을 반대로 바꾼 후에 이동하려는 칸이 파란색인 경우에는 이동하지 않고 가만히 있는다.
            if (status) return false;

            // A번 말의 이동 방향을 반대로 하고
            if (d <= 1) d = d == 1 ? 0 : 1;
            else d = d == 2 ? 3 : 2;
            chessList.get(chess).d = d;

            nx = x + direction[d][0];
            ny = y + direction[d][1];

            // 한 칸 이동한다.
            return go(chess, x, y, nx, ny, true, d);
        } else {
            // 한 말이 이동할 때 위에 올려져 있는 말도 함께 이동한다.
            List<Integer> leavedChessList = new ArrayList<>(chessMap[x][y].subList(0, chessMap[x][y].indexOf(chess)));
            List<Integer> movedChessList = new ArrayList<>(chessMap[x][y].subList(chessMap[x][y].indexOf(chess), chessMap[x][y].size()));

            // 빨간색인 경우에는 이동한 후에 A번 말과 그 위에 있는 모든 말의 쌓여있는 순서를 반대로 바꾼다.
            if (map[nx][ny] == 1) Collections.reverse(movedChessList);

            // A번 말의 위에 다른 말이 있는 경우에는 A번 말과 위에 있는 모든 말이 이동한다.
            chessMap[nx][ny].addAll(movedChessList);

            // 턴이 진행되던 중에 말이 4개 이상 쌓이는 순간 게임이 종료된다.
            if (chessMap[nx][ny].size() >= 4) return true;

            // A번 말의 위에 다른 말이 있는 경우에는 A번 말과 위에 있는 모든 말이 이동한다.
            for (int idx : movedChessList) {
                chessList.get(idx).x = nx;
                chessList.get(idx).y = ny;
            }

            // 기존 위치 남아있는 말들 업데이트
            chessMap[x][y] = leavedChessList;
        }

        return false;
    }

    static class Chess {
        int x, y, d;

        public Chess(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
