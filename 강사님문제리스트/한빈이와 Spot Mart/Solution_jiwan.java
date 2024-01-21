import java.util.*;
import java.io.*;


class Solution_jiwan
{
	public static void main (String[] args) {
	    
		Scanner scanner = new Scanner(System.in);
		int TC = scanner.nextInt();
		for (int tc = 1; tc <= TC; tc++) {
			int N = scanner.nextInt(); // 과자 봉지의 개수
			int M = scanner.nextInt(); // 과자 봉지 무게 합 제한
			
			int[] snacks = new int[N];
			for (int n = 0; n < N; n++)
				snacks[n] = scanner.nextInt();
			
			int max = 0;
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					if (snacks[i] + snacks[j] <= M)
						max = Math.max(max,  snacks[i] + snacks[j]);
				}
			}
			
			System.out.print("#" + tc + " ");
			if (max == 0)
				System.out.println(-1);
			else
				System.out.println(max);
		}
	}
}