import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static class Piece {
        int num, dir;

        Piece(int num, int dir) {
            this.num = num;
            this.dir = dir;
        }
    }

    static int[][] board;
    static LinkedList<Piece>[][] chess;
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N + 1][N + 1];
        chess = new LinkedList[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                chess[i][j] = new LinkedList<>();
            }
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            chess[x][y].add(new Piece(i, dir));
        }

        for (int turn = 1; turn <= 1000; turn++) {
            if (simulation()) {
                System.out.println(turn);
                return;
            }
        }
        System.out.println(-1);
    }

    private static boolean simulation() {

        L:
        for (int cur = 1; cur <= K; cur++) { // 이번에 이동하는 원판

            for (int x = 1; x <= N; x++) {
                for (int y = 1; y <= N; y++) {

                    if (!chess[x][y].isEmpty()) { //보드에 원판이 있는지 체크
                        for (int i = 0; i < chess[x][y].size(); i++) {
                            Piece p = chess[x][y].get(i);
                            if (p.num == cur) { //찾는 원판이 있나

                                int nx = x + dx[p.dir];
                                int ny = y + dy[p.dir];
                                int nextColor = getColor(x, y, p.dir); // 이동하려는 칸의 색 가져오기 (0은 흰색, 1은 빨간색, 2는 파란색)

                                switch (nextColor) {
                                    case (0):
                                        WHITE(x, y, i, nx, ny);
                                        if (check(x, y, nx, ny))
                                            return true;
                                        break;
                                    case (1):
                                        RED(x, y, i, nx, ny);
                                        if (check(x, y, nx, ny))
                                            return true;
                                        break;
                                    case (2):
                                        int nd = getNd(p);
                                        nx = x + dx[nd];
                                        ny = y + dy[nd];
                                        chess[x][y].get(i).dir = nd;
                                        if (getColor(x, y, nd) == 0) {
                                            WHITE(x, y, i, nx, ny);
                                            if (check(x, y, nx, ny))
                                                return true;
                                        }
                                        //파란색에서 방향을 바꾸고 나서 1칸을 이동할때 빨간색이 나오면 빨간색 룰을 적용시켜야 함
                                        if (getColor(x, y, nd) == 1) {
                                            RED(x, y, i, nx, ny);
                                            if (check(x, y, nx, ny))
                                                return true;
                                        }
                                        break;
                                }
                                continue L; // 다음 원판 찾으러 가자~~
                            }
                        }

                    }

                }

            }
        }
        return false;
    }

    private static void WHITE(int x, int y, int i, int nx, int ny) {
        chess[nx][ny].addAll(new LinkedList<>(chess[x][y].subList(i, chess[x][y].size())));
        chess[x][y] = new LinkedList<>(chess[x][y].subList(0, i));
    }

    private static void RED(int x, int y, int i, int nx, int ny) {
        LinkedList<Piece> temp = new LinkedList<>(chess[x][y].subList(i, chess[x][y].size()));
        Collections.reverse(temp);
        chess[nx][ny].addAll(temp);
        chess[x][y] = new LinkedList<>(chess[x][y].subList(0, i));
    }

    private static int getColor(int x, int y, int dir) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        if (nx > N || ny > N || nx < 1 || ny < 1) {
            return 2;
        }
        return board[nx][ny];
    }

    private static int getNd(Piece p) {
        int nd = 0;
        switch (p.dir) {
            case 1:
                nd = 2;
                break;
            case 2:
                nd = 1;
                break;
            case 3:
                nd = 4;
                break;
            case 4:
                nd = 3;
                break;
        }
        return nd;
    }

    private static boolean check(int x, int y, int nx, int ny) {
        if (chess[nx][ny].size() >= 4 || chess[x][y].size() >= 4) {
            return true;
        }
        return false;
    }

}
