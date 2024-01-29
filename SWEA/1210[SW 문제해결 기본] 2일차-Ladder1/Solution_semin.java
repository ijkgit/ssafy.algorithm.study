import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_semin {
	static int[][] maps = new int[100][100];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			int T = Integer.parseInt(br.readLine());
			sb.append("#").append(T).append(" ");
			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					maps[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int x = 0; x < 100; x++) {
				if (maps[0][x] == 1 && check(x)) {
					sb.append(x).append("\n");
					break;
				}
			}
		}
		System.out.println(sb);
	}

	private static boolean check(int startX) {
		int x = startX;
		for (int i = 1; i < 100; i++) {
			if (i == 99) {
				if (maps[99][x] == 2) {
					return true;
				}
			}
			boolean chaged = false;
			while (true) {
				int leftX = x - 1;
				if (leftX >= 0) {
					if (maps[i][leftX] == 1 && maps[i + 1][leftX] != 0) {
						x = leftX;
						chaged = true;
						break;
					}
					if (maps[i][leftX] == 1) {
						x = leftX;
						continue;
					}
				}
				break;
			}
			while (!chaged) {
				int rightX = x + 1;
				if (rightX < 100) {
					if (maps[i][rightX] == 1 && maps[i + 1][rightX] != 0) {
						x = rightX;
						break;
					}
					if (maps[i][rightX] == 1) {
						x = rightX;
						continue;
					}
				}
				break;
			}

		}
		return false;
	}
}
