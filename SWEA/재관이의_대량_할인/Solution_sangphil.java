import java.util.*;
import java.io.*;

/*
 * 		[SWEA 4050] 재관이의 대량 할인
 * 			- 그리디 알고리즘 (Optimal)
 * 			- 정렬 해두고, 3번 째 방문되는 숫자를 제외하고 ans 에 더해줌
 */
public class Solution_sangphil {
	public static void main(String[] args) throws IOException {
		// System.setIn(Main.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine());
			int[] arr = new int[n];
			
			// 0. init
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			// 1. 정렬
			Arrays.sort(arr);
			
			
			// 2. 3번 째 원소를 스킵하고 모조리 더하기
			int ans = 0;
			int i = 1;
			while (i <= n) {
				if (i % 3 != 0) ans += arr[n-i];
				i++;
			}
			
			System.out.println("#"+t+ " " + ans);
		}
	}
}