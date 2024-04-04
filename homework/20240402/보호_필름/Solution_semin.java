import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int D, W, K, maps[][], ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			maps = new int[D][W];
			ans = Integer.MAX_VALUE;

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					maps[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			if (K == 1) {
				sb.append(0).append("\n");
				continue;
			}

			powerset(0, 0, maps);
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}

	private static void powerset(int row, int cnt, int[][] maps) {
		if (cnt >= ans) {
			return;
		}
		if (row == D) {
			if (checkAnswer(maps)) {
				ans = cnt;
			}
			return;
		}

		int[] temp = maps[row].clone();
		Arrays.fill(maps[row], 0);
		powerset(row + 1, cnt + 1, maps);

		Arrays.fill(maps[row], 1);
		powerset(row + 1, cnt + 1, maps);

		maps[row] = temp;
		powerset(row + 1, cnt, maps);
	}

	private static boolean checkAnswer(int[][] maps) {
		L: for (int j = 0; j < W; j++) {
			int prev = maps[0][j];
			int cnt = 1;
			for (int i = 1; i < D; i++) {
				if (prev == maps[i][j]) {
					cnt++;
					if (cnt == K) {
						continue L; // 한 라인에서 성공
					}
					continue;
				}
				prev = maps[i][j];
				cnt = 1;
			}
			return false; // 이 라인에서 성공을 못하면 전체도 x
		}
		return true;
	}
}
