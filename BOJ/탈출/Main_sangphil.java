import java.util.*;
import java.io.*;

public class Main_sangphil {
	static class Pair {
		int x, y;
		char mark;
		public Pair (int x, int y, char mark) {
			this.x=x;
			this.y=y;
			this.mark = mark;
		}
		public Pair (int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	static final int[] dx = {1,-1,0,0};
	static final int[] dy = {0,0,1,-1};
	static final int INF = Integer.MAX_VALUE;
	
	static int t;
	static int r, c;
	static char[][] graph;
	static int[][] dp;
	static ArrayList<Pair> list = new ArrayList<Pair>();
	static Pair start;
	static Pair goal;
	
	public static void main(String[] args) throws IOException {
		// System.setIn(Main.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in));;
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		ArrayDeque<Pair> q = new ArrayDeque<Pair>();
		graph = new char[r][c];
		dp = new int[r][c];
		
		for (int i = 0; i < r; i++) {
			String line = br.readLine();
			for (int j = 0; j < c; j++) {
				graph[i][j] = line.charAt(j);
				if (graph[i][j]=='*') {
					q.add(new Pair(i, j, '*'));
				} else if (graph[i][j] == 'D') {
					goal = new Pair(i,j, 'D');
				} else if (graph[i][j] == 'S') {
					start = new Pair(i,j,'S');
				}
			}
		}
		
		q.add(start);
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			
			for (int i = 0 ; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (0<= nx &&nx < r && 0<=ny && ny < c) {
					if (graph[nx][ny] != '*') {
						
						if (p.mark == '*') {
							if (graph[nx][ny] != 'X' && graph[nx][ny] != 'D') {
								graph[nx][ny] = '*';
								q.add(new Pair(nx, ny, '*'));
							}
						} else {
							if (graph[nx][ny] != 'X') {
                                if (dp[nx][ny] != 0) continue;
								dp[nx][ny] = dp[p.x][p.y] + 1;
								if (graph[nx][ny] == 'D') {
									System.out.println(dp[nx][ny]);
									return;
								}
								q.add(new Pair(nx, ny, 'S'));
							}
						}
					}
				}
			}
		}
		System.out.println("KAKTUS");
	}
}
