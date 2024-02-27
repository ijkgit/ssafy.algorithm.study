import java.util.*;
import java.io.*;

/*
 * 		[SWEA 7206] 숫자 게임
 * 			- DP
 */
public class Solution_sangphil {
	static int ans;
	static String n;
	
	
	public static void main(String[] args) throws IOException {
		System.setIn(Main.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			ans = 0;
			n = br.readLine();
			
			divide(11, "", n, 0);
			
			sb.append(String.format("#%d %d\n", t, ans));
		}
		System.out.println(sb);
	}
	
	static void divide(int N, String left, String right, int cnt) {
		if (N < 10 && right.length() == 0) {
			ans = Math.max(ans, cnt);
			return;
		}
		
		if (!left.equals("")) {
			int l = Integer.parseInt(left);
			int r = Integer.parseInt(right);
			divide(l*r, "", "", cnt + 1);
			String ll = "";
			String rr = ""+(l*r);
			if (rr.length() > 1) {
				divide(l*r, ll + rr.substring(0,1), rr.substring(1, rr.length()), cnt + 1);				
			}
		}
		if (right.length() > 1) {
//			System.out.println(left + right.substring(0,1));
//			System.out.println(right.substring(1,right.length()));
			divide(N, left + right.substring(0,1), right.substring(1,right.length()), cnt);
		}
	}
}
