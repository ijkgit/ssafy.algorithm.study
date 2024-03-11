import java.io.*;
import java.util.*;

public class Main_sangphil {
	static class Pair {
		int x, y, d, cnt;
		public Pair (int x, int y, int d, int cnt) {
			this.x=x;
			this.y=y;
			this.d=d;
			this.cnt = cnt;
		}
		@Override
		public boolean equals(Object oo) {
			Pair o = (Pair) oo;
			if (this.x==o.x && this.y==o.y && this.d == o.d) return true;
			return false;
		}
	}
	static final int[] dx = {1,-1,0,0,1,1,-1,-1};
	static final int[] dy = {0,0,1,-1,1,-1,1,-1};
	static final int INF = Integer.MAX_VALUE;

	static Pair end;
	static Pair start;
	
	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		char[][] arr = new char[n][n];
		int[][][] visited = new int[n][n][2];
		
		int s = 0;
		int e = 0;
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < n; j++) {
				arr[i][j] = line.charAt(j);
				visited[i][j][0] = INF;
				visited[i][j][1] = INF;
				if (arr[i][j] == 'B') {
					if (s == 1) {
						if (i != 0 && arr[i-1][j] == 'B') {
							start = new Pair(i, j, 1, 0);
						} else {
							start = new Pair(i, j, 0, 0);
						}
					} s++;
				}
				if (arr[i][j] == 'E') {
					if (e == 1) {
						if (i != 0 && arr[i-1][j] == 'E') {
							end = new Pair(i, j, 1, 0);
						} else {
							end = new Pair(i, j, 0, 0);
						}
					} e++;
				}
			}
		}

		int ans = 0;
		Deque<Pair> q = new LinkedList<Pair>();
		q.add(start);
		visited[start.x][start.y][start.d] = 0;
		while(!q.isEmpty()) {
			Pair p = q.poll();
			if (p.equals(end)) {
				ans = p.cnt;
				break;
			}

			int nx = p.x;
			int ny = p.y;
			for (int i = 0; i < 8; i++) {
				nx = p.x + dx[i];
				ny = p.y + dy[i];
				if (0 > nx || n <= nx || 0 > ny || ny >= n) break;
				if (arr[nx][ny] == '1') break;
				if (i == 7) {
					if (visited[p.x][p.y][(p.d+1)%2] > p.cnt + 1) {
						visited[p.x][p.y][(p.d+1)%2] = p.cnt+1;
						q.add(new Pair(p.x, p.y, (p.d+1)%2, p.cnt+1));
					}
				}
			}
			if (p.d == 0) {
				nx = p.x;
				ny = p.y - 2;
				if ( 0 <= nx && nx < n && 0 <= ny && ny < n && arr[nx][ny] != '1') {
					if (visited[p.x][p.y-1][p.d] > p.cnt+1) {
						visited[p.x][p.y-1][p.d] = p.cnt+1;
						q.add(new Pair (p.x, p.y-1, p.d, p.cnt+1));
					}
				}
				nx = p.x;
				ny = p.y + 2;
				if ( 0 <= nx && nx < n && 0 <= ny && ny < n && arr[nx][ny] != '1') {
					if (visited[p.x][p.y+1][p.d] > p.cnt+1) {
						visited[p.x][p.y+1][p.d] = p.cnt+1;
						q.add(new Pair (p.x, p.y+1, p.d, p.cnt+1));
					}
				}
				nx = p.x-1;
				ny = p.y-1;
				int a = 0; int b = 0;
				for (int i = 0; i < 3; i++) {
					if ( 0 <= nx && nx < n && 0 <= ny + i && ny + i < n && arr[nx][ny+i] != '1') {
						a++;
					}
					if ( 0 <= nx+2 && nx+2 < n && 0 <= ny + i && ny + i < n && arr[nx+2][ny+i] != '1') {
						b++;
					}
				}
				if (a == 3) {
					if (visited[p.x-1][p.y][0] > p.cnt + 1) {
						visited[p.x-1][p.y][0] = p.cnt + 1;
						q.add(new Pair(p.x-1, p.y, p.d, p.cnt+1));
					}
				}
				if (b == 3) {
					if (visited[p.x+1][p.y][0] > p.cnt + 1) {
						visited[p.x+1][p.y][0] = p.cnt + 1;
						q.add(new Pair(p.x+1, p.y, p.d, p.cnt+1));
					}
				}
			} else {
				nx = p.x - 2;
				ny = p.y;
				if ( 0 <= nx && nx < n && 0 <= ny && ny < n && arr[nx][ny] != '1') {
					if (visited[p.x-1][p.y][p.d] > p.cnt+1) {
						visited[p.x-1][p.y][p.d] = p.cnt+1;
						q.add(new Pair (p.x-1, p.y, p.d, p.cnt+1));
					}
				}
				nx = p.x + 2;
				ny = p.y;
				if ( 0 <= nx && nx < n && 0 <= ny && ny < n && arr[nx][ny] != '1') {
					if (visited[p.x+1][p.y][p.d] > p.cnt+1) {
						visited[p.x+1][p.y][p.d] = p.cnt+1;
						q.add(new Pair (p.x+1, p.y, p.d, p.cnt+1));
					}
				}
				nx = p.x-1;
				ny = p.y-1;
				int a = 0; int b = 0;
				for (int i = 0; i < 3; i++) {
					if ( 0 <= nx + i && nx + i < n && 0 <= ny && ny < n) {
						if (arr[nx+i][ny] != '1') a++;
					}
					if ( 0 <= nx+i && nx+i < n && 0 <= ny + 2 && ny + 2 < n) {
						if (arr[nx+i][ny+2] != '1') b++;
					}
				}
				if (a == 3) {
					if (visited[p.x][p.y-1][1] > p.cnt + 1) {
						visited[p.x][p.y-1][1] = p.cnt + 1;
						q.add(new Pair(p.x, p.y-1, 1, p.cnt+1));
					}
				}
				if (b == 3) {
					if (visited[p.x][p.y+1][1] > p.cnt + 1) {
						visited[p.x][p.y+1][1] = p.cnt + 1;
						q.add(new Pair(p.x, p.y+1, 1, p.cnt+1));
					}
				}
			}
		}
		System.out.println(ans);
	}
}