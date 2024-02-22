import java.util.*;
import java.io.*;

public class Solution_sangphil {
	static class Pair {
		int x, y, w;
		public Pair (int x, int y, int w) {this.x=x; this.y=y; this.w=w;};
	}
	static class Info {
		int x, y, d, p;
		public Info (int y, int x, int d, int p) {this.x=x; this.y=y; this.d=d; this.p=p;}
	}
	
	static int[] dx = {0, -1,0,1,0};
	static int[] dy = {0, 0,1,0,-1};
	
	static Pair[] list;
	static boolean[][][] zone;
	static Info[] spec;
	static int[][] cmd = new int[2][100];
	static boolean[] reserved;
	static int ans, m, a;
	
	public static void main(String[] args) throws IOException {
		// System.setIn(Main.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		for (int t = 1; t <= T; t++) {
			list = new Pair[] { new Pair(0, 0, 0), new Pair(9, 9, 0)};
			ans = 0;
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			zone = new boolean[10][10][a];
			spec = new Info[a];
			
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					cmd[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < a; i++) {
				st = new StringTokenizer(br.readLine());
				spec[i] = new Info (Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken()));
			}
			Arrays.sort(spec, (o1,o2)->(o2.p-o1.p));
			
			for (int i = 0; i < a; i++) {
				bfs(spec[i].x, spec[i].y, spec[i].d, i);
			}
			
			reserved = new boolean[a];
			for (int j = 0; j < 2; j++) {
				 list[j].x += dx[0];
				 list[j].y += dy[0];
				 for (int k = 0; k < a; k++) {
					 if (zone[list[j].x][list[j].y][k]) {
						 if (!reserved[k]) {
							 reserved[k] = true;
							 ans += spec[k].p;
							 break;
						 }
					 }
				 }
			}
			
			for (int i = 0; i < m; i++) {
				reserved = new boolean[a];
				int A = 0;
				int B = 0;
				for (int j = 0; j < 2; j++) {
					 list[j].x += dx[cmd[j][i]];
					 list[j].y += dy[cmd[j][i]];
					 for (int k = 0; k < a; k++) {
						 if (zone[list[j].x][list[j].y][k]) {
							 if (!reserved[k]) {
								 reserved[k] = true;
								 A += spec[k].p;
								 break;
							 }
						 }
					 }
				}
				reserved = new boolean[a];
				for (int j = 1; j >= 0; j--) {
					 for (int k = 0; k < a; k++) {
						 if (zone[list[j].x][list[j].y][k]) {
							 if (!reserved[k]) {
								 reserved[k] = true;
								 B += spec[k].p;
								 break;
							 }
						 }
					 }
				}
				ans += Math.max(A, B);
			}
			sb.append(String.format("#%d %d\n", t, ans));
		}
		System.out.println(sb);
	}

	
	static void bfs (int x, int y, int d, int order) {
		Deque<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(x, y, d));
		zone[x][y][order] = true;
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			if (p.w < 0) continue;
			for (int i = 1; i < 5; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (0 <= nx && nx < 10 && 0 <= ny && ny < 10) {
					if (!zone[nx][ny][order]) {
						zone[nx][ny][order] = true;
						q.add(new Pair(nx, ny, p.w-1));
					}
				}
			}
		}
	}
}