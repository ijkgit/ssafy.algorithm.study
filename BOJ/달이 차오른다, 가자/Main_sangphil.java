import java.util.*;
import java.io.*;

public class Main_sangphil {	
	static class Pair {
		int x; int y; int w; int bit;
		public Pair (int x, int y, int w, int bit) {
			this.x=x; this.y=y; this.w = w; this.bit=bit;
		}
	}
	static final int[] dx = {1,-1,0,0};
	static final int[] dy = {0,0,1,-1};
	
	static int n,m;
	static char[][] graph;
	static boolean[][][] visited;
	static Pair start;
	
	public static void main(String[] args) throws IOException {
		// System.setIn(Main_sangphil.class.getResourceAsStream("myInput.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		graph = new char[n][m];
		visited = new boolean[n][m][65];
		
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < m; j++) {
				graph[i][j] = line.charAt(j);
				if (graph[i][j] == '0') {
					start = new Pair(i,j,0,0);
				}
			}
		}
		
		ArrayDeque<Pair> q = new ArrayDeque<Pair>() {{add(start);}};
		visited[start.x][start.y][start.bit] = true;
		int ans = -1;
		while (!q.isEmpty()) {
			Pair p = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				int nbit = p.bit;
				if (0 <= nx && nx < n && 0 <= ny && ny < m) {
					if (graph[nx][ny] == '1') {
						ans = p.w;
						System.out.println(ans+1);
						return;
					}
					char next = graph[nx][ny];
					if (next != '#') {				
						if (Character.isUpperCase(next)) {
							if ((nbit & 1<<(next-'A')) == 0) {
								continue;
							}
							nbit = p.bit | (1<< (next-'A'));
						} else if (Character.isLowerCase(next)){
							if ((nbit & (1<< (next-'a'))) == 0) {
								nbit = p.bit | (1<< (next-'a'));
							}
						} 
						if (visited[nx][ny][nbit]) {
							continue;
						}
						visited[nx][ny][nbit] = true;
						q.add(new Pair(nx, ny, p.w+1, nbit));
						
					}
				}
			}		
		}
		System.out.println(ans);
	}
}