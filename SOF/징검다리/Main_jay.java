import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[] A;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        
        A = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        sb.append(sol());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int sol() {
        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        for (int i=0; i<N; i++) { // 밟을 돌 선택
            for (int j=0; j<i; j++) { // 밟을 돌 기준 이전의 돌들을 탐색
                if (A[i] > A[j]) { // 밟을 돌보다 높이가 낮은 경우 갱신
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int ans = 0;
        for (int i=0; i<N; i++) {
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }
}
