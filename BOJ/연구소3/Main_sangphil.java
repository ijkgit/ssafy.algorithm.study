import java.util.*;
import java.io.*;

public class Main_sangphil {
	static class Pair {
		int x, y, cnt;
		public Pair (int x, int y) {this.x=x; this.y=y;}
		public Pair (int x, int y, int cnt) {this.x=x; this.y=y; this.cnt=cnt;}
	}
	
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	static ArrayList<Pair> list = new ArrayList<Pair>();
	static int[][] arr;
	static boolean[][] visited;
	static Pair[] sel;
	static int n, m;
	static int ans = Integer.MAX_VALUE;
	static int bin;
	
	
	public static void main(String[] args) throws IOException {
		// System.setIn(Main.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n][n];
		bin = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2) {
					list.add(new Pair(i, j, 0));
				}
				if (arr[i][j] == 0) bin++;
			}
		}
		sel = new Pair[m];

		if (bin == 0) {
			System.out.println(0);
		} else {
			combi(0, 0);
			System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
		}
	}

	static void combi (int depth, int k) {
		if (depth == m) {
			int time = Integer.MAX_VALUE;
			int cnt = 0;
			visited = new boolean[n][n];
			Deque<Pair> q = new ArrayDeque<Pair>();
			for (int i = 0; i < m; i++) {
				q.add(sel[i]);
				visited[sel[i].x][sel[i].y] = true;
			}
			while(!q.isEmpty()) {
				Pair p = q.poll();
				if (p.cnt >= ans) {
					break;
				}
				for (int i = 0; i < 4; i++) {
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];
					if (0 <= nx && nx < n && 0 <= ny && ny < n) {
						if (arr[nx][ny] != 1 && !visited[nx][ny]) {
							if (arr[nx][ny] == 2) {
								q.add(new Pair(nx, ny, p.cnt+1));
							} else {
								cnt++;
								if (cnt == bin) {
									time = p.cnt+1;
									break;
								}
								q.add(new Pair(nx, ny, p.cnt+1));
							}
							visited[nx][ny] = true;
						}
					}
				}
			}
			ans = Math.min(time, ans);
			return;
		}

		if (k == list.size()) {
			return;
		}

		sel[depth] = list.get(k);
		combi(depth+1, k+1);
		combi(depth, k+1);
	}
}