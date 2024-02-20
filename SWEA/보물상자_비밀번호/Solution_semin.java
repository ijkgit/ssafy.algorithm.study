import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution_semin {
	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			Set<String> set = new HashSet<>();

			String origin = br.readLine();
			for (int j = N / 4; j <= N; j = j + N / 4) {
				set.add(origin.substring(j - N / 4, j));
			}
			for (int i = 0; i < N / 4; i++) {
				String current = makeNewString(origin);
				for (int j = N / 4; j <= N; j = j + N / 4) {
					set.add(origin.substring(j - N / 4, j));
				}
				origin = current;
			}

			String[] arr = new String[set.size()];
			int idx = 0;
			for (String str : set) { //정렬 하기 위해 array 에 담음
				arr[idx++] = str;
			}
			Arrays.sort(arr, (a, b) -> b.compareTo(a));
			sb.append(getDecimal(arr[K - 1])).append("\n");
		}
		System.out.println(sb);
	}

	private static int getDecimal(String str) {
		int num = 0;
		int idx = 0;
		for (int i = str.length() - 1; i >= 0; i--) {
			char ch = str.charAt(i);
			if (Character.isDigit(ch)) {
				num += Character.getNumericValue(ch) * Math.pow(16, idx++);
				continue;
			}
			num += (int) (ch - 65 + 10) * Math.pow(16, idx++);
		}
		return num;
	}

	private static String makeNewString(String origin) {
		return origin.substring(origin.length() - 1, origin.length()) + origin.substring(0, origin.length() - 1);
	}
}
