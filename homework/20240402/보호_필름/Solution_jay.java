import java.io.*;
import java.util.StringTokenizer;
 
public class Solution {
    private static int D, W, K;
    private static int[][] map, original;
    private static int[] sel, rSel;
    private static int ans;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
 
        int TC = Integer.parseInt(br.readLine());
        for (int T = 1; T <= TC; T++) {
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[D][W];
            original = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    original[i][j] = map[i][j];
                }
            }
            sol();
            sb.append("#").append(T).append(" ").append(ans).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
 
    private static void sol() {
        ans = 0;
        if (check()) return;
 
        for (int i = 1; i < K; i++) {
            sel = new int[i];
            rSel = new int[i];
            combination(0, 0);
            if (ans != 0) return;
        }
 
        ans = K;
    }
 
    private static boolean check() {
        for (int y = 0; y < W; y++) {
            boolean flag = false;
            for (int x = 0; x < D - K + 1; x++) {
                int sum = 0;
                for (int nx = x; nx < x + K; nx++) {
                    sum += map[nx][y];
                }
                if (sum == 0 || sum == K) {
                    flag = true;
                    break;
                }
            }
            if (!flag) return false;
        }
        return true;
    }
 
    private static void combination(int k, int v) {
        if (ans != 0) return;
        if (k == sel.length) {
            recursive(0);
            return;
        }
 
        for (int i = 0; i < D; i++) {
            if ((v & (1 << i)) != 0) continue;
            sel[k] = i;
            combination(k + 1, v |= 1 << i);
        }
    }
 
    private static void recursive(int k) {
        if (ans != 0) return;
        if (k == rSel.length) {
            injection();
            return;
        }
 
        rSel[k] = 0;
        recursive(k + 1);
        rSel[k] = 1;
        recursive(k + 1);
    }
 
    private static void injection() {
        for (int i = 0; i < sel.length; i++) {
            for (int y = 0; y < W; y++) {
                map[sel[i]][y] = rSel[i];
            }
        }
 
        if (check()) {
            ans = sel.length;
            return;
        }
 
        for (int i = 0; i < sel.length; i++) {
            map[sel[i]] = original[sel[i]].clone();
        }
    }
}
