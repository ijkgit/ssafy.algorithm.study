import java.util.*;
import java.io.*;

public class Solution {
	static int ans, n;
	
	public static void main(String[] args) throws IOException {
		System.setIn(Main.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			ans = Integer.MIN_VALUE;
			
			n = Integer.parseInt(br.readLine());
			int size = (int)(Math.log10(n)+1);
			int left = n / (int)Math.pow(10, size-1);
			int right = n % (int)Math.pow(10, size-1);
			divide(1, left, right, 1);
			
			System.out.println(ans);
			sb.append(String.format("#%d %d\n", t, ans));
		}
		System.out.println(sb);
	}
	
	// 곱하기 확정된 수 // 왼쪽수 // 오른쪽 수
	// 1. 곱하면서 왼쪽 초기화
	// 2. 안곱하고, 왼쪽 <-> 오른쪽
	static void divide (int num, int left, int right, int depth) {
		System.out.println(num + " " + left + " " + right + " " + depth);
		if (num < 10 && left == 0 && right == 0) {
			ans = Math.max(ans, depth);
			return;
		}
		

		if (left == 0 && right == 0) {
			int lengthN = (int)(Math.log10(num)+1);
			divide(1, num / (int)Math.pow(10, lengthN-1), num % (int)Math.pow(10, lengthN-1), depth+1);
			return;
		}
		
		if (left == 0) {
			divide (num*right, 0, 0, depth);
			return;
		}
		if (right == 0) {
 			divide(num*left, 0, 0, depth);
			return;
		}

		int lengthR = (int)(Math.log10(right)+1);
		while (lengthR >= 1) {
			divide(num*left*right, 0, 0, depth);
			
			divide(num * left, right / (int)Math.pow(10, lengthR-1), right % (int)Math.pow(10, lengthR-1), depth);
			
			left = left*10 + right / (int)Math.pow(10, lengthR-1);
			right %= (int)Math.pow(10, lengthR-1);
			
			lengthR = (int)(Math.log10(right)+1);
		}
		
	}
}
