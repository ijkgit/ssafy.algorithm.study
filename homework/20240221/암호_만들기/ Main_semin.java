import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_semin {
	static int L, C;
	static List<Character> vowel = Arrays.asList('a', 'e', 'i', 'o', 'u');
	static char[] candidate;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		candidate = new char[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			candidate[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(candidate);
		char[] selected = new char[L];
		powerset(0, 0, selected);
	}

	private static void powerset(int idx, int k, char[] selected) {
		if (k == selected.length) {
			if (isValid(selected)) {
				System.out.println(selected);
			}
			return;
		}
		if (idx == candidate.length) {
			return;
		}
		selected[k] = candidate[idx];
		powerset(idx + 1, k + 1, selected);
		powerset(idx + 1, k, selected);
	}

	public static boolean isValid(char[] arr) {
		int cCnt = 0; // 자음
		int vCnt = 0; // 모음
		for (char ch : arr) {
			if (vowel.contains(ch)) {
				vCnt++;
				continue;
			}
			cCnt++;
		}
		// System.out.println(arr.toString() + " cCnt: " + cCnt + " vCnt: " + vCnt);
		if (vCnt >= 1 && cCnt >= 2) {
			return true;
		}
		return false;
	}

}
