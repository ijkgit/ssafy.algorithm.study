import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		new Solution().io();
	}

	private int N;
	private int[] arr;
	private int ans;

	private void io() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			ans = 0;
			sol();
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	/*
	 * 오름차순 정렬 후 거꾸로 탐색하면서 세번째 인덱스가 가장 비싸게 할인 받을 수 있는 옷
	 */
	private void sol() {
		Arrays.sort(arr);
		int cnt = 0;
		for (int i = N-1; i >= 0; i--) {
			cnt++;
			if (cnt % 3 == 0) continue;
			ans += arr[i];
		}
	}
}
