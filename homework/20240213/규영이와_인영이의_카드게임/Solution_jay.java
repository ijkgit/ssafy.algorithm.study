import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	private final static int SIZE = 9;
	private int[] arr;
	private int[] card;
	private int[] sel;
	private int sw;
	private int aw;

	public static void main(String[] args) throws NumberFormatException, IOException {
		new Solution().io();
	} 

	private void io() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());

		for (int t = 1; t <= testCase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr = new int[SIZE];
			for (int i = 0; i < SIZE; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			card = new int[SIZE];
			int idx = 0;
			for (int i = 1; i <= SIZE * 2; i++) {
				boolean flag = true;
				for (int j = 0; j < SIZE; j++) {
					if (arr[j] == i) {
						flag = false;
						break;
					}
				}
				if(flag) {
					card[idx] = i;
					idx++;
				}
			}
			sw = 0; aw = 0;
			sol();
			sb.append("#").append(t).append(" ").append(aw).append(" ").append(sw).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private void sol() {
		sel = new int[SIZE];
		permutation(0, 0);
	}

	private void permutation(int k, int v) {
		if (k == SIZE) {
			int ss = 0, as = 0;
			for (int i = 0; i < SIZE; i++) {
				if(sel[i] > arr[i]) {
					ss += sel[i] + arr[i];
				} else {
					as += sel[i] + arr[i];
				}
			}
			if (ss > as) {
				sw += 1;
			} else if (as > ss) {
				aw += 1;
			}
			return;
		}

		for (int i = 0; i < SIZE; i++) {
			if ((v & (1 << i)) == 0) {
				sel[k] = card[i];
				permutation(k + 1, v | 1 << i);
			}
		}
	}
}
