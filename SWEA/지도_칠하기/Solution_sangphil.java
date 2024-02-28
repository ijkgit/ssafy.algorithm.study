import java.util.*;
import java.io.*;

public class Solution_sangphil {
    static int[][] arr;
    static int[] colors;
    static int[] sel;
    static int n, ans;
    public static void main(String[] args) throws IOException {
        // System.setIn(Solution_sangphil.class.getResourceAsStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine());
            colors = new int[n];
            sel = new int[n];
            arr = new int[n][n];
            ans = Integer.MAX_VALUE;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) colors[i] = Integer.parseInt(st.nextToken());
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            bruteForce(0);
            
            sb.append(String.format("#%d %d\n", t, ans));
        }
        System.out.println(sb);
    }

    static void bruteForce(int k) {
        if (k == n) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] == 1) {
                        if (sel[i] == sel[j]) return;
                    }
                }
            }
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (sel[i] != colors[i]) cnt++;
            }
            ans = Math.min(ans, cnt);
            return;
        }
        for (int i = 1; i <= 4; i++) {
            sel[k] = i;
            bruteForce(k+1);
        }
    }
}