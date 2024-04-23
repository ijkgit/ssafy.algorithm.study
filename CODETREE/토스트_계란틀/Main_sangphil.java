import java.util.*;
import java.io.*;

public class Main {
	static class Pair {
		int x;
		int y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static final int[] dx = {1,-1,0,0};
	static final int[] dy = {0,0,1,-1};
	
	static int[][] arr;
	static int[][] visited;
	static int n, l, r;
	static HashMap<Integer, Integer> map = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		// System.setIn(Main.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		arr = new int[n][n];
		visited = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer (br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = 0;
		while (true) {
			int cnt = 1;
			visited = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (visited[i][j] == 0) {
						bfs(i, j, cnt++);
					}
				}
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					arr[i][j] = map.get(visited[i][j]);
				}
			}
			if (visited[n-1][n-1] == n*n) {
				break;
			}
			ans++;
		}
		
		System.out.println(ans);
    }
    
	static void bfs(int x, int y, int num) {
		int count = 1;
		int sum = arr[x][y];
		ArrayDeque<Pair> q = new ArrayDeque<Pair>();
		visited[x][y] = num;
		q.add(new Pair(x, y));
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			for (int i = 0; i < dx.length; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if (0 <= nx && nx < n && 0 <= ny && ny <n) {
					if (visited[nx][ny] == 0) {
						int diff = Math.abs(arr[p.x][p.y] - arr[nx][ny]);
						if (l <= diff && diff <= r) {
							q.add(new Pair(nx, ny));
							visited[nx][ny] = num;
							sum += arr[nx][ny];
							count++;
						}
					}
				}
			}
		}
		map.put(num, (int)sum/count);
	}
}