import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

class Node{
	int x;
	int y;
	int dep;
	public Node(int x, int y, int dep) {
		super();
		this.x = x;
		this.y = y;
		this.dep = dep;
	}
}



public class Main {
	static int N;
	static int board[][];
	static Point shark = new Point();
	static int dxy[][] = {{0,1},{0,-1},{1,0},{-1,0}};
	
	public static int bfs(int Ssize) {
		Queue<Node> q = new LinkedList<>();
		Point canEat = new Point(N,N);
		int MaxDep = 500;
		q.add(new Node(shark.x, shark.y,0));
		boolean visited[][] = new boolean[N][N];
		visited[shark.x][shark.y] = true;
		
		while(!q.isEmpty()) {
			Node n = q.poll();
			for (int d = 0; d <4; d++) {
				int dx = n.x + dxy[d][0];
				int dy = n.y + dxy[d][1];
				//정상범위 확인
				if(0<=dx && dx<N && 0<= dy && dy<N && !visited[dx][dy] ) {				
					//사이즈 확인
					//먹을 수 있으면 (거리 같은 경우 확인)
					if(board[dx][dy] <Ssize && n.dep <MaxDep && board[dx][dy] >0 ) {
						MaxDep = n.dep +1;
						if(dx < canEat.x) {
							canEat.x =	dx;
							canEat.y = dy;
						}else if(dx == canEat.x && dy < canEat.y) {
							canEat.y = dy;
						}
					//지나가기 가능
					}else if(board[dx][dy] == 0 || board[dx][dy] == Ssize) {
						if(n.dep < MaxDep) {
							visited[dx][dy] = true;
							q.add(new Node(dx,dy,n.dep +1));
						}
					}
				}
				
				
			}
		}
		//먹을게 없으면
		if(MaxDep==500) {

			return 0;
		}else {
			shark.x = canEat.x;
			shark.y = canEat.y;
			board[shark.x][shark.y] = 0;
			return MaxDep;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
		StringTokenizer tk = new StringTokenizer(br.readLine());
		
		N = Integer.valueOf(tk.nextToken());
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			tk = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int tmp = Integer.valueOf(tk.nextToken());
				board[i][j] = tmp;
				if (tmp == 9) {
					shark.x = i;
					shark.y = j;
					board[i][j] = 0;
				}
			}
		}
		int sharkSize = 2;
		int sharkSizeup = 2;
		int day = 0;
		
		while(true) {
			int result = bfs(sharkSize);
			if(result !=0) {
				day += result;
				sharkSizeup--;
				if(sharkSizeup == 0) {
					sharkSize++;
					sharkSizeup = sharkSize;
				}
			}else {
				System.out.println(day);
				return;
			}
		}
		
		
		
		

	}

}
