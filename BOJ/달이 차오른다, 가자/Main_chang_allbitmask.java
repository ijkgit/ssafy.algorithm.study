import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

class Pair{
	int dep;
	//이러면 xy도 비트마스킹이 가능하다
	int x;
	int y;
	//비트마스킹
	int key;


	public Pair(int dep ,int x, int y,int key) {
		super();
		this.dep = dep;
		this.x = x;
		this.y = y;
		this.key = key;
	}
	
}

public class Main {
	static int[][] dxy = {{0,1},{0,-1},{1,0},{-1,0}};
	static boolean[][][] visited ;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());

		
		int N = Integer.valueOf(tk.nextToken());
		int M = Integer.valueOf(tk.nextToken());
		char[][] board = new char[N][M];
		Queue<Pair> q = new LinkedList<>();
		visited = new boolean[N][M][64];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j <M ; j++) {
				board[i][j] = s.charAt(j);
				if(board[i][j]=='0') {
					//pair (dep,i,j,key)
					visited[i][j][0] = true;
					q.add(new Pair(0,i,j,0));
					board[i][j] = '.';
				}
			}

		}

		while(!q.isEmpty()) {
			Pair minsik = q.poll();					
			for (int d = 0; d < 4; d++) {
				int dx = minsik.x + dxy[d][0];
				int dy = minsik.y + dxy[d][1];

				if(0<=dx && dx <N && 0<=dy && dy<M
					&& !visited[dx][dy][minsik.key]) {
					
					switch(board[dx][dy]) {
					case '.':
						q.add(new Pair(minsik.dep +1,dx,dy,minsik.key));
						visited[dx][dy][minsik.key] = true;
						break;
					//ABCDEF
					//543210	
					//키 먹으면 방문여부 초기화
					case 'a':
						//키 안가지고있으면 초기화
						q.add(new Pair(minsik.dep +1,dx,dy, 
								minsik.key | (1<< 5)));
						visited[dx][dy][minsik.key] = true;
						visited[dx][dy][minsik.key | (1<< 5)] = true;
						break;	
					case 'b':
						q.add(new Pair(minsik.dep +1,dx,dy, 
								minsik.key | (1<< 4)));
						visited[dx][dy][minsik.key] = true;
						visited[dx][dy][minsik.key | (1<< 4)] = true;
						break;
					case 'c':
						q.add(new Pair(minsik.dep +1,dx,dy, 
								minsik.key | (1<< 3)));
						visited[dx][dy][minsik.key] = true;
						visited[dx][dy][minsik.key | (1<< 3)] = true;
						break;
					case 'd':
						q.add(new Pair(minsik.dep +1,dx,dy,  
								minsik.key | (1<< 2)));
						visited[dx][dy][minsik.key] = true;
						visited[dx][dy][minsik.key | (1<< 2)] = true;
						break;
					case 'e':
						q.add(new Pair(minsik.dep +1,dx,dy,  
								minsik.key | (1<< 1)));
						visited[dx][dy][minsik.key] = true;
						visited[dx][dy][minsik.key | (1<< 1)] = true;
						break;
					case 'f':
						q.add(new Pair(minsik.dep +1,dx,dy, 
								minsik.key | (1<< 0)));
						visited[dx][dy][minsik.key] = true;
						visited[dx][dy][minsik.key | (1<< 0)] = true;
						break;
					//키가 있으면 지나갈 수 있음
					case 'A':
						//키가 있으면
						if((minsik.key & (1<<5)) != 0) {
							q.add(new Pair(minsik.dep +1,dx,dy, 
									 minsik.key));
							visited[dx][dy][minsik.key] = true;
						}
						
						break;
					case 'B':
						//키가 있으면
						if((minsik.key & (1<<4)) != 0) {
							q.add(new Pair(minsik.dep +1,dx,dy, 
									 minsik.key));
							visited[dx][dy][minsik.key] = true;
						}
						break;
					case 'C':
						//키가 있으면
						if((minsik.key & (1<<3)) != 0) {
							q.add(new Pair(minsik.dep +1,dx,dy, 
									 minsik.key));
							visited[dx][dy][minsik.key] = true;
						}
						break;
					case 'D':
						//키가 있으면
						if((minsik.key & (1<<2)) != 0) {
							q.add(new Pair(minsik.dep +1,dx,dy, 
									 minsik.key));
							visited[dx][dy][minsik.key] = true;
						}
						break;
					case 'E':
						//키가 있으면
						if((minsik.key & (1<<1)) != 0) {
							q.add(new Pair(minsik.dep +1,dx,dy, 
									 minsik.key));
							visited[dx][dy][minsik.key] = true;
						}
						break;
					case 'F':
						//키가 있으면
						if((minsik.key & (1<<0)) != 0) {
							q.add(new Pair(minsik.dep +1,dx,dy, 
									 minsik.key));
							visited[dx][dy][minsik.key] = true;
						}
						break;
					case '1':
						System.out.println(minsik.dep +1);
						return;
						
					}
				}
					
			}
		}
		System.out.println(-1);
}
}
	
		
		

		
		
		
	


