import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
	static int[][] board ;
	static boolean[] visited;
	
	static int N, ans = Integer.MAX_VALUE;
	public static void dfs(int sum, int idx ,int dep) {
		if(dep == N) {
			
			if(board[idx][0] > 0) {
				ans = Math.min(ans, sum+board[idx][0]);
			}
			return;
		}
		if(sum >= ans){
			return;
		}
		
		
		for (int j = 0; j < N; j++) {
			if(board[idx][j] >=1 && !visited[j]) {
				visited[j] = true;
				dfs(sum+board[idx][j],j,dep+1);
				visited[j] = false;
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		board = new int[N][N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <N; j++) {
			
				board[i][j] = sc.nextInt();
			}
		}
		visited[0] = true;
		dfs(0,0,1);
		
		System.out.println(ans);
		
		

	}

}
