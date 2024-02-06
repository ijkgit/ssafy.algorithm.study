import java.io.*;
import java.util.*;

public class Main_sangphil {
	static int N;
	static int[][] arr;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		// System.setIn(Main_sangphil.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		search(0);

		System.out.println(ans);
	}

	static void search(int n) {
		if (n == N) {
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				if (arr[i][0] <= 0) {
					cnt++;
				}
			}
			ans = Math.max(ans, cnt);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (n != i) {
				if ( n != i && arr[n][0] > 0 && arr[i][0] > 0) {
					arr[i][0] -= arr[n][1];
					arr[n][0] -= arr[i][1];
					search(n+1);
					arr[i][0] += arr[n][1];
					arr[n][0] += arr[i][1];
				} else {
					search(n+1);
				}
			}
		}
	}
}