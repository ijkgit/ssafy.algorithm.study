import java.util.*;
import java.io.*;

/*
 * 		[BOJ 4963] 섬의 갯수
 * 			1. bfs 탐색 알고리즘을 통해서 순회하면서, 좌표에 방문 표시
 * 			2. 하나의 bfs 로 도달할 수 있는 땅을 같은 섬으로 그룹화 
 */
public class Main_sangphil {
	static class Pair {
		int x; int y;
		public Pair (int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	static final int[] dx = {1,-1,0,0,-1,-1,1,1};
	static final int[] dy = {0,0,1,-1,-1,1,-1,1};
	
	static int n,m,ans;
	static int[][] land;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		// System.setIn(Main.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			if (n+m == 0) break;
			
			ans = 0;
			land = new int[n][m];
			visited = new boolean[n][m];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					land[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if(land[i][j] == 1 && !visited[i][j]) {
						bfs(i,j);
						ans++;
					}
				}
			}
			sb.append(ans).append("\n");
			
		}
		System.out.println(sb);
	}
	
	static void bfs(int x, int y) {
		Deque<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(x,y));
		while (!q.isEmpty()) {
			Pair p = q.poll();
			for (int i = 0; i < 8; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if ( 0<= nx && nx < n && 0 <= ny && ny <m) {
					if (land[nx][ny] == 1 && !visited[nx][ny]) {
						visited[nx][ny] = true;
						q.add(new Pair(nx, ny));
					}
				}
			}
		}
	}
}