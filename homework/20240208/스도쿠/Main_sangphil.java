import java.util.*;
import java.io.*;

/*
 * 		[BOJ 2580] 스도쿠
 * 			1. 하나의 행을 다 훑으면, 다음 행으로 재귀
 * 			2. 하나의 좌표를 기준으로, 수직-수평-주변 에 스도쿠가 적절한지 판정
 */
public class Main_sangphil {
    static int[][] board = new int[9][9];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        // System.setIn(Main.class.getResourceAsStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = null;

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);

        System.out.println(sb);

    }

    static void dfs(int x, int y) {
        if (y == 9) {
            dfs(x+1, 0);
            return;
        }
        if (x == 9) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(board[i][j] + " ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        }

        if (board[x][y] == 0) {
            for (int i = 1; i < 10; i++) {
                if (valid(x, y, i)) {
                    board[x][y] = i;
                    dfs(x, y+1);
                }
            }
            board[x][y] = 0;
            return;
        }
        dfs(x, y+1);
    }

    static boolean valid(int x, int y, int val) {
        for (int i = 0; i < 9; i++) {
            if (board[x][i] == val) return false;
        }
        for (int i = 0; i < 9; i++) {
            if (board[i][y] == val) return false;
        }

        int r = (x/3)*3;
        int c = (y/3)*3;
        for (int i = r; i < r+3; i++) {
            for (int j = c; j < c+3; j++) {
                if (board[i][j] == val) return false;
            }
        }
        return true;
    }
}