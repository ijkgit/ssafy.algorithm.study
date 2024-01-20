
import java.util.Scanner;

public class Solution {

	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc =0 ; tc < T ; tc ++) {
			int[] start = new int[2];
			int[] end = new int[2];
			int x_diff = Integer.MIN_VALUE;
			int y_diff = Integer.MIN_VALUE;
			int ans = 0;
			
			start[0] = sc.nextInt();
			start[1] = sc.nextInt();
			end[0] = sc.nextInt();
			end[1] = sc.nextInt();
			x_diff = Math.max(Math.abs(start[0] - end[0]),x_diff);
			y_diff = Math.max(Math.abs(start[1] - end[1]), y_diff);
			
			ans =Math.max(x_diff,y_diff) * 2;
			
			if ((x_diff+y_diff) %2 ==1) {
				ans = ans -1;
			}
			
			System.out.printf("#%d %d\n", tc+1, ans);
		}
	

	}
	
}
