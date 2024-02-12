import java.util.*;
import java.io.*;

/*
 * 		[BOJ 14502] 연구소
 * 			1. 연구소에 brute 하게 벽을 설치 (3개 까지) 
 * 			2. bfs 탐색 알고리즘을 통해서 순회하면서, 바이러스를 확산
 * 			2. 모든 바이러스를 확산 시키고, 안전지대 크기 측정
 */
public class Main_sangphil {
	static class Pair {
		int x; int y;
		public Pair (int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	static final int[] dx = {1,-1,0,0};
	static final int[] dy = {0,0,1,-1};
	
	static int n,m,ans;
	static int[][] graph;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		// System.setIn(Main_ResearchLab.class.getResourceAsStream("input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		ans = 0;
		graph = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		brute(0);

		System.out.println(ans);
	}

	static void brute(int order) {
		if (order == 3) {
			int temp = bfs();
			ans = Math.max(ans, temp);
			return;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (graph[i][j] == 0) {
					graph[i][j] = 1;
					brute(order + 1);
					graph[i][j] = 0;
				}
			}
		}
	}
	
	static int bfs() {
		Deque<Pair> q = new LinkedList<Pair>();

		int[][] tmp = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				tmp[i][j] = graph[i][j];
				if (graph[i][j] == 2) {
					q.add(new Pair(i, j));
				}
			}
		}

		while (!q.isEmpty()) {
			Pair p = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if ( 0 <= nx && nx < n && 0 <= ny && ny <m) {
					if (tmp[nx][ny] == 0) {
						tmp[nx][ny] = 2;
						q.add(new Pair(nx, ny));
					}
				}
			}
		}
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (tmp[i][j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
}