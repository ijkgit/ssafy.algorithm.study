import java.util.*;
import java.io.*;

public class Solution_sangphil {
	static int ans, n;
	
	public static void main(String[] args) throws IOException {
		System.setIn(Main.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			ans = Integer.MIN_VALUE;
			
			n = Integer.parseInt(br.readLine());

			divide(n, 0);
			
			sb.append(String.format("#%d %d\n", t, ans));
		}
		System.out.println(sb);
	}
	
	static void divide (int num, int depth) {
		if (num < 10) {
			ans = Math.max(ans, depth);
			return;
		}
		
		// length 에는 숫자의 자릿수가 담김....
		int length = (int)(Math.log10(num)+1);
		
		// l(eft) 에는 첫 자리부터..
		// r(ight) 에는 l 이후부터..
		for (int i = 1; i < length; i++) {
			int l = num / (int) Math.pow(10, length - i);
			int r = num % (int) Math.pow(10, length - i);
			divide(l*r, depth+1);
		}
	}
}