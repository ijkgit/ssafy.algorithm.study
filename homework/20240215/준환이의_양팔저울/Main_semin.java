package algorithm.day0215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** recursive 함수 하나로 하면 시간 초과남.. 로직이 다른게 없는 것 같아서 매우 혼란스러웠다 :()
 * 학우들과 고민을 해봤는데 2^n에서 가지치기를 하느냐 n!을 구하고 가지치기를 하느냐의 차이인듯
 * n!을 구하고 가지치기를 하면 한 번에 가지쳐지는 경우의 수가 훨씬 많음
 * */
public class swea3234 {
	static int N, res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			N = Integer.parseInt(br.readLine());
			res = 0; // 아무것도 놓지 않는 경우

			int[] arr = new int[N];
			boolean[] visited = new boolean[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);

			/* (0, arr[0], 1, arr, visited); */
			permutation(0, arr, visited, new int[N]);
			sb.append(res).append("\n");
		}
		System.out.println(sb);
	}

	/* 순열 구하고 부분집합 구하기 (permutation+powerset) */
	public static void permutation(int k, int[] arr, boolean[] visited, int[] selected) {
		if (k == N) {
			powerSet(0, 0, 0, selected);
			return;
		}
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				selected[k] = arr[i];
				permutation(k + 1, arr, visited, selected);
				visited[i] = false;
			}
		}
	}

	private static void powerSet(int k, int left, int right, int[] arr) {
		if (left < right) {
			return;
		}
		if (k == N) {
			res++;
			return;
		}
		powerSet(k + 1, left + arr[k], right, arr);
		powerSet(k + 1, left, right + arr[k], arr);
	}

	/* 시간 초과 나는 코드 (recursive) */
	public static void recursive(int right, int left, int idx, int[] arr, boolean[] visited) {
		// System.out.printf("right:%d left:%d\n", right, left);
		if (left < right) {
			return;
		}
		if (idx == N) {
			res++;
			return;
		}
		for (int i = 1; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				recursive(right, left + arr[i], idx + 1, arr, visited);
				recursive(right + arr[i], left, idx + 1, arr, visited);
				visited[i] = false;
			}
		}
	}

}