import java.util.*;
import java.io.*;

public class Main_sangphil {
	static class Pair implements Comparable<Pair> {
		int x; int y; int w; int k; int cnt;
		public Pair (int x, int y) {
			this.x=x;
			this.y=y;
		}
		public Pair (int x, int y, int w, int k, int cnt) {
			this.x=x;
			this.y=y;
			this.w=w;
			this.k=k;
			this.cnt=cnt;
		}

		@Override
		public int compareTo(Pair o) {
			int diff = this.cnt - o.cnt;
			if (diff == 0) {
				diff = this.x - o.x;
				if (diff == 0) return this.y - o.y;
				return diff;
			}
			return diff;
		}
	}
	
	static final int[] dx = {1,-1,0,0};
	static final int[] dy = {0,0,1,-1};
	
	static int n,ans;
	static int[][] arr;
	static boolean[][] visited;
	static Pair baby;
	
	public static void main(String[] args) throws IOException {
		// System.setIn(Main.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 9) {
					baby = new Pair(i, j, 2, 0, 0);
				}
			}
		}

		ans = 0;
		
		while (bfs());
		
		System.out.println(ans);
	}
	
	static boolean bfs () {
		PriorityQueue<Pair> q = new PriorityQueue<Pair>() {{add(baby);}};
		visited = new boolean[n][n];
		visited[baby.x][baby.y] = true;
		arr[baby.x][baby.y] = 0;

		while(!q.isEmpty()) {
			Pair p = q.poll();

			if ( 0 < arr[p.x][p.y] && arr[p.x][p.y] < p.w ) {
				p.k++;
				if (p.k == p.w) {
					p.w++;
					p.k = 0;
				}
				ans += p.cnt;
				baby = new Pair(p.x, p.y, p.w, p.k, 0);
				return true;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (0 <= nx && nx < n && 0 <= ny && ny < n) {
					if (arr[nx][ny] <= p.w && !visited[nx][ny]) {
						visited[nx][ny] = true;
						q.add(new Pair(nx, ny, p.w, p.k, p.cnt+1));
					}
				}
			}
		}
		return false;
	}
}