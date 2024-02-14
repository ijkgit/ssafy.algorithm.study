package problem_solving;
import java.util.*;
import java.io.*;

/*
 * 		[BOJ 1074] Z
 * 			재귀를 활용해서,
 * 			전체 사각형에서 1/4 칸 씩 분할 탐색
 */
public class Main_sangphil {
	static int r, c, ans = 0;
	public static void main(String[] args) throws IOException {
		// System.setIn(Main_Z.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = (int)Math.pow(2, Integer.parseInt(st.nextToken()));
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		shoot(0, 0, n);
	}
	
	static void shoot(int x, int y, int w) {
		if (x == r && y == c) {
			System.out.println(ans);
			return;
		}
		if (w == 1) {
			ans++;
			return;
		}
		if (!( x <= r && r < x + w && y <= c && c < y + w)) {
			ans += w*w;
			return;
		}
		int half = w/2;
		
		shoot(x, y, half);
		shoot(x, y+half, half);
		shoot(x+half, y, half);
		shoot(x+half, y+half, half);
	}
}