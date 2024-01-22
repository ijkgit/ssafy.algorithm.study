import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_semin {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());

			int[] pXy = getXy(p);
			int[] qXy = getXy(q);

			int pqXsum = pXy[0] + qXy[0];
			int pqYsum = pXy[1] + qXy[1];

			sb.append(getTarget(pqXsum, pqYsum)).append("\n");
		}
		System.out.println(sb);
	}

	private static int getTarget(int targetX, int targetY) {
		int n = 1;
		int x, y;
		int cnt = 1;
		while (true) {
			x = 1;
			y = n;
			for (int i = 0; i <= n; i++) {
				if (x == targetX && y == targetY) {
					return cnt;
				}
				if (x > n || y < 1) {
					break;
				}
				x++;
				y--;
				cnt++;
			}
			n++;
		}
	}

	private static int[] getXy(int target) {
		int n = 1;
		int px = -1, py = -1;
		L: for (int s = 1; s <= getIdx(target); s++) {
			int x = 1, y = s;
			if (n++ == target) {
				px = x;
				py = y;
				break;
			}
			for (int i = 0; i <= s; i++) {
				x++;
				y--;
				if (n++ == target) {
					px = x;
					py = y;
					break L;
				}
			}
		}
		return new int[] { px, py };
	}

	private static int getIdx(int num) {
		int i = 1;
		while (i < num) {
			i++;
		}
		return i;
	}
}
