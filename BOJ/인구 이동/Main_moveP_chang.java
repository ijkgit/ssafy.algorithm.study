
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

class Pair{
	int x,y;
	Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int N, L, R;
	static int [][] board ;
	static int [][] visited ;
	static int [][] dxy = {{0,1},{0,-1},{1,0},{-1,0}};
	static Stack<Pair> stack = new Stack<Pair>();

	
	public static int dfs(int x, int y) {
		int nx ,ny;
		int ans = board[x][y];
		stack.add(new Pair(x,y));
		visited[x][y] = 1;
		

		for(int d = 0 ; d<4; d++) {
			nx = x + dxy[d][0];
			ny = y + dxy[d][1];
			if (0<=nx && nx < N && 0<= ny && ny<N && 
					visited[nx][ny] ==0 && Math.abs(board[x][y]- board[nx][ny]) >= L && Math.abs(board[x][y]- board[nx][ny]) <= R ) {
				


				ans += dfs(nx,ny);
			}
		}
		
		return ans;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		board = new int[N][N];
		
		for(int i = 0 ; i<N ; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = sc.nextInt();
			}
		}
		Pair tmp;
		boolean moved = false;
		int day = 0;		
		while (true) {
			visited = new int[N][N];
			
			for(int i = 0 ; i<N ; i++) {
				for (int j = 0; j < N; j++) {
					if(visited[i][j] == 0 ) {
						int sum = dfs(i,j);
						int avg  = sum / stack.size();
						
						tmp = stack.pop();
						board[tmp.x][tmp.y] = avg;
						while(!stack.isEmpty()) {
							
							moved = true;
							tmp = stack.pop();
							board[tmp.x][tmp.y]  = avg;
						}	
					}
				}
			}
			if (!moved) break;
			moved = false;
			day ++;
		
		}
		System.out.println(day);

	}

}
