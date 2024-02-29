import java.util.*;
import java.io.*;

public class Solution_sangphil {
	static class Pair {
		int x, y, t, life;
		public Pair (int x, int y, int t, int life) {this.x=x; this.y=y; this.t=t; this.life=life;}
	}
	static final int[] dx = {1,-1,0,0};
	static final int[] dy = {0,0,1,-1};
	
	static StringBuilder sb = new StringBuilder();
	static PriorityQueue<Pair> q;
	static int[][] board;
	static boolean[][] visited;
	static int n, m, k;
	
	public static void main(String[] args) throws IOException {
		// System.setIn(Solution.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			board = new int[300 + n][300 + m];
			visited = new boolean[300 + n][300 + m];
			q = new PriorityQueue<Pair>(new Comparator<Pair>() {
				@Override
				public int compare (Pair o1, Pair o2) {
					int diff = o1.t - o2.t;
					if (diff == 0) return o2.life - o1.life;
					return diff;
				}
			});
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					board[150+i][150+j] = Integer.parseInt(st.nextToken());
					if (board[150+i][150+j] != 0) {
						q.add(new Pair(150+i, 150+j, 0, board[150+i][150+j]));
						visited[150+i][150+j] = true;
					}
				}
			}
			while(!q.isEmpty()) {
				Pair p = q.poll();
				// 종료 조건
				if (p.t == k) {
					q.add(p);
					break;
				}
				if (board[p.x][p.y] == 0) {
					// 활성화 상태
					board[p.x][p.y] --;
					for (int i = 0; i < 4; i++) {
						int nx = p.x + dx[i];
						int ny = p.y + dy[i];
						if (!visited[nx][ny]) {
							board[nx][ny] = p.life;
							q.add(new Pair(nx, ny, p.t+1, p.life));
							visited[nx][ny] = true;
						}
					}
				}
				// 비활성화 상태
				if (board[p.x][p.y] > 0) {
					board[p.x][p.y]--;
					q.add(new Pair(p.x, p.y, p.t+1, p.life));
				}
				else if (board[p.x][p.y] < 0) {
					if (p.life == 1) {
					} else {
						q.add(new Pair(p.x, p.y, p.t+1, p.life-1));					
					}
				}
			}
			sb.append(String.format("#%d %d\n", t, q.size()));
		}
		System.out.println(sb);
	}
}