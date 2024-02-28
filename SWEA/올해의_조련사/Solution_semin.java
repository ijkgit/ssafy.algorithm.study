import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_semin {
	static String res;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			int N = Integer.parseInt(br.readLine());
			char[] arr = new char[N];
			for (int i = 0; i < N; i++) {
				arr[i] = br.readLine().charAt(0);
			}
			makeCandidate(arr, 0, N - 1);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void makeCandidate(char[] arr, int head, int tail) {
		if (head == tail) {
			sb.append(arr[head]);
			return;
		}
		if (arr[head] < arr[tail]) {
			sb.append(arr[head]);
			makeCandidate(arr, head + 1, tail);
		}
		if (arr[head] == arr[tail]) {
			if (checkLeftIsSmall(arr, head + 1, tail - 1)) {
				sb.append(arr[head]);
				makeCandidate(arr, head + 1, tail);
			} else {
				sb.append(arr[tail]);
				makeCandidate(arr, head, tail - 1);
			}
		}
		if (arr[head] > arr[tail]) {
			sb.append(arr[tail]);
			makeCandidate(arr, head, tail - 1);
		}
	}

	private static boolean checkLeftIsSmall(char[] arr, int head, int tail) {
		if (head >= tail) {
			return true;
		}
		if (arr[head] < arr[tail]) {
			return true;
		}
		if (arr[head] > arr[tail]) {
			return false;
		}
		return checkLeftIsSmall(arr, head + 1, tail - 1);
	}
}
