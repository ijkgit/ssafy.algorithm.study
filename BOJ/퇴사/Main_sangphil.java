import java.io.*;
import java.util.*;

public class Main_sangphil {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n+1][2];

		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[n+2];

		int ans = 0;
		for (int i = 1; i <= n; i++) {
			if (i + arr[i][0] <= n + 1) { // 범위를 안넘는선에서
                // i 번째 것을 넣었을 경우의 최적해를 dp에 갱신
				dp[i] = Math.max(dp[i-1], dp[i]);
				dp[i + arr[i][0]] = Math.max(dp[i + arr[i][0]], dp[i] + arr[i][1]);
				ans = Math.max(ans, dp[i + arr[i][0]]);
			}
		}
		System.out.println(ans);
	}
}