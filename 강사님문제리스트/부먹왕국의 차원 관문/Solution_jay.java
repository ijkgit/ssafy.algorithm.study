import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int t = 1; t <= testCase; t++) {
			String input = br.readLine();
			StringTokenizer st = new StringTokenizer(input);

			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			input = br.readLine();
			st = new StringTokenizer(input);
			int[] map = new int[n];
			for (int i = 0; i < n; i++) {
				map[i] = Integer.parseInt(st.nextToken());
			}

			int cnt = 0;
			int ans = 0;
			for (int i = 0; i < n; i++) {
				if (map[i] == 0)
					cnt++;
				else
					cnt = 0;
				if (cnt == d) {
					cnt = 0;
					ans++;
				}
			}
			
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.print(sb);
	}
}
