import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
	static int[][] result;
	static int ans =0;
	
	public static void dfs(int dep) {
		if (dep == 15) {
			ans = 1;
			return ;
		}
		
		// team vs team
		for (int i = 0; i < 5; i++) {
			for (int j = i+1; j < 6; j++) {
				boolean flag = true;
				//win draw lose
				if(result[i][0] > 0 && result[j][2]>0) {
					flag = false;
					result[i][0] --;
					result[j][2] --;
					dfs(dep+1);
					result[i][0] ++;
					result[j][2] ++;
				}if(result[i][1] > 0 && result[j][1] >0) {
					flag = false;
					result[i][1] --;
					result[j][1] --;
					dfs(dep+1);
					result[i][1] ++;
					result[j][1] ++;
				}if(result[i][2] > 0 && result[j][0] >0) {
					flag = false;
					result[i][2] --;
					result[j][0] --;
					dfs(dep+1);
					result[i][2] ++;
					result[j][0] ++;
				}if(flag) {
					return;
				}
				
				
			}	
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < 4; tc++) {
			ans =0;
			result = new int [6][3];
			for (int i = 0; i < 6; i++) {
				result[i][0] = sc.nextInt();
				result[i][1] = sc.nextInt();
				result[i][2] = sc.nextInt();
			}
			dfs(0);
			sb.append(ans).append(" ");
		}
		System.out.println(ans);
		
		

		
	}

}
