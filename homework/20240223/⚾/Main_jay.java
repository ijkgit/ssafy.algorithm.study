import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    private int N;
    private static final int SIZE = 9;
    private int[][] graph;
    private int[] sel;
    private int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws NumberFormatException, IOException {
        new Main().io();
    }

    private void io() throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        graph = new int[N][SIZE];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < SIZE; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sol();
        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private void sol() {
        sel = new int[SIZE];
        sel[3] = 0; // 1번 타자 타순 4번으로 고정 (zerobase)
        permutation(0, 1 << 0);
    }

    // 타순 조합
    private void permutation(int k, int v) {
        if (k == 3) k++; // 4번 타순 패스

        if (k == sel.length) {
            startGame();
            return;
        }

        for (int i = 1; i < SIZE; i++) {
            if ((v & (1 << i)) == 0) {
                sel[k] = i;
                permutation(k + 1, v | 1 << i);
            }
        }
    }

    /*
    base 변수 v : 비트마스킹으로 표현
    0000001 : 1루에 주자 존재
    1111000 : 1,2,3 루에 주자 존재 -> 다음 타자가 홈런시
     */
    private void startGame() {
        int res, score = 0, s = 0, chk = 0b0000111;
        for (int now = 0; now < N; now++) {
            int out = 0, v = 0;
            while(out < 3) {
                    res = graph[now][sel[s++%SIZE]];
                    if (res == 0) {
                        out++;
                    } else {
                        v <<= res; // 주자가 친 결과만큼 베이스 밀기
                        v |= (1 << res-1); // 주자 진루
                        for (int i = 3; i < 7; i++) { // 3,4,5,6 에 존재 시 홈에 들어온 것
                            if ((v & (1 << i)) != 0) score++;
                        }
                        v &= chk; // 1,2,3 루만 남기고 삭제
                    }
            }
        }
        ans = Math.max(ans, score);
    }
}
