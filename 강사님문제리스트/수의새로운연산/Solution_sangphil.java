import java.util.*;
import java.io.*;

public class Solution_sangphil {
	static int T, p, q, ans;
	
	public static void main(String[] args) throws IOException {
		//System.setIn(Solution_sangphil.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			p = Integer.parseInt(st.nextToken());
			q = Integer.parseInt(st.nextToken());
			ans = 0;
			
			
			int total = 1;	// 가장 근접한 1열에 놓일 값
			int weight = 0; // 가장 근접한 행 값 
			while (total + weight <= p) {
				total += weight;
				weight += 1;
			}
			int diff = p - total;
			int y = weight - diff;
			int x = 1 + diff;

			
			total = 1;
			weight = 0;
			while (total + weight <= q) {
				total += weight;
				weight += 1;
			}
			diff = q - total;
			y += weight - diff;
			x += 1 + diff;

			
			int totalY = x + y - 1; // 가장 근접한 y 값
			
			for (int i = 1; i < totalY; i++) {
				ans += i; 
			}
			ans ++; // 가장 근접한 y 좌표를 갖는 실제 값
			ans += totalY - y;
			
			System.out.printf("#%d %d\n", t+1, ans);
		}
	}
}