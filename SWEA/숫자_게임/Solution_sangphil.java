import java.util.*;
import java.io.*;

/*
 * 		[SWEA 7206] 숫자 게임
 * 			- DP
 */
public class Solution {
	static int[] memo = new int[99999+1];
	static int ans;
	static int n;
	
	public static void main(String[] args) throws IOException {
		// System.setIn(Main.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			ans = 0;
			n = Integer.parseInt(br.readLine());
            
			divide(n, "", "", 0);
			
			sb.append(String.format("#%d %d\n", t, ans));
		}
		System.out.println(sb);
	}
	
	static void divide(int N, String left, String right, int cnt) {
		if (right.length() == 0) {
			if (N < 10) {
				ans = Math.max(ans, cnt);
				if (memo[N] < ans) memo[N] = ans;
			} else {
				if (memo[N] == 0) {
					String str = ""+N;
					String ll = left + str.substring(0, 1);
					String rr = str.substring(1, str.length());
					divide(1, ll, rr, cnt+1);					
				} else {
					divide(0, "", "", memo[N]);
				}
			}
			return;
		}
		
		if (!left.equals("")) {
			int l = Integer.parseInt(left);
			int r = Integer.parseInt(right);
			divide(N*l*r, "", "", cnt);	// 넘기기 전 전부 곱하기
			divide(N*l, "", right, cnt);	// 넘기기 전 일부 곱하기
		}
		if (right.length() > 1) {
			// 넘기기
			divide(N, left + right.substring(0,1), right.substring(1,right.length()), cnt);
		} else {
			// 넘길 수 없다면?
			if (left.equals("")) divide(N*casting(right), "", "", cnt);
		}
	}
	
	static int casting(String str) {
		if (str.length() == 0) return 1;
		return Integer.parseInt(str);
	}
}
