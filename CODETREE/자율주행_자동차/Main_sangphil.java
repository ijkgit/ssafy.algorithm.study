import java.util.*;
import java.io.*;

public class Main {
	static class Pair {
		int x, y, cnt, d;
		public Pair (int x, int y) {this.x=x;this.y=y;}
		public Pair (int x, int y, int d) {this.x=x;this.y=y;this.d=d;}
	}
	
	static final int[] dx = {-1,0,1,0};
	static final int[] dy = {0,1,0,-1};
	
	static int[][] arr;
	static int direction;
	static Pair robot;
	static int n, m;
	static int ans = Integer.MIN_VALUE;
	
	
	public static void main(String[] args) throws IOException {
		// System.setIn(Main.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		st = new StringTokenizer(br.readLine());
		robot = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 1);
		direction = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(shoot(robot.x, robot.y, direction));
	}
	
	
	static int shoot (int x, int y, int direction) {
		Deque<Pair> q = new LinkedList<Pair>() {{add(new Pair(x, y, direction));}};
		arr[x][y] = 2;
		int cnt = 1;
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			for (int i = 1; i < 6; i++) {
				int nx = p.x + dx[(8 + p.d-i)%4];
				int ny = p.y + dy[(8 + p.d-i)%4];
				if (i == 5) {
					nx = p.x - dx[p.d];
					ny = p.y - dy[p.d];
				}
				
				if (0 <= nx && nx < n && 0 <= ny && ny < m) {
					if (i == 5 && arr[nx][ny] != 1) {
						q.add(new Pair(nx, ny, p.d));
					}
					if (i != 5 && arr[nx][ny] == 0) {
						arr[nx][ny] = 2;
						q.add(new Pair(nx, ny, (8 + p.d-i)%4));
						cnt++;
						break;
					}
				}
			}
		}
		return cnt;
	}
}