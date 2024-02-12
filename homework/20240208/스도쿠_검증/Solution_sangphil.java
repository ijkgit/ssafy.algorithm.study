import java.util.*;
import java.io.*;

/*
 * [SWEA] 1974. 스도쿠 검증
 * 		가로, 세로, 3x3셀 의 값을 set에 넣어서 중복 값 검사
 */
public class Solution_sangphil {
	public static void main(String[] args) throws IOException {
		// System.setIn(Solution.class.getResourceAsStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int[][] board = new int[9][9];

            for (int i = 0 ; i < 9; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 9; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int ans = 1;

            Set s = null;
            
            for (int i = 0; i < 9; i++) {
                s = new HashSet();
                for (int j = 0; j < 9; j++) {
                    s.add(board[i][j]);
                }
                if (s.size() != 9) {
                    ans = 0;
                }
            }

            for (int i = 0; i < 9; i++) {
                if (ans == 0) break;
                s = new HashSet();
                for (int j = 0; j < 9; j++) {
                    s.add(board[j][i]);
                }
                if (s.size() != 9) {
                    ans = 0;
                }
            }
            for (int i = 0; i < 9; i += 3) {
                if (ans == 0) break;
                s = new HashSet();
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        s.add(board[i+j][i+k]);
                    }
                }
                if (s.size() != 9) {
                    ans = 0;
                }
            }

            System.out.printf("#%d %d\n", t, ans);
        }
    }
}