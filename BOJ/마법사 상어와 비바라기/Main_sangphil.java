import java.util.*;
import java.io.*;

/*
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * 1. 첫 시작 구름,
 * 
 * -----반복 시작-----
 * 		2. 8 방향 (구름 이동만 넘나들기 가능)
 * 		3. 대각선 만큼 비 더하기
 * 		4. 2 이상인 칸 (현재 있던 곳은 제외)에서 2뺀 구름
 * -----반복 종료-----
 * 
 * 6. ANS: 모든 칸 더하기
 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * 
 * 기능: 구름 이동기, 구름 대각선 계산기, 구름 선택기
 */

public class Main_sangphil {
	static class Pair {
		int x;
		int y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static final int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	static int[][] arr;
	static boolean[][] visited;
	static int n, m;
	static ArrayDeque<Pair> q = new ArrayDeque<Pair>();
	
	public static void main(String[] args) throws IOException {
		System.setIn(Main_sangphil.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n][n];
		visited = new boolean[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer (br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		q.add(new Pair(n-1, 0));
		q.add(new Pair(n-1, 1));
		q.add(new Pair(n-2, 0));
		q.add(new Pair(n-2, 1));
		for (int i = 0; i < m; i++) {
			visited = new boolean[n][n];
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < q.size(); j++) {
				Pair p = q.poll();
				int nx = (50*n + p.x + dx[a]*b)%n;
				int ny = (50*n + p.y + dy[a]*b)%n;
				arr[nx][ny]++;
				q.add(p);
			}
			
			
			while (!q.isEmpty()) {
				Pair p = q.poll();
				int nx = (50*n + p.x + dx[a]*b)%n;
				int ny = (50*n + p.y + dy[a]*b)%n;
				
				for (int j = 1; j < 8; j+=2) {
					int nnx = nx + dx[j];
					int nny = ny + dy[j];
					if (0 <= nnx && nnx < n && 0 <= nny && nny < n) {
						if (arr[nnx][nny] >= 1) {
							arr[nx][ny]++;
						}
					}
				}
				visited[nx][ny] = true;
			}
			
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					if (!visited[j][k] && arr[j][k] >= 2) {
						q.add(new Pair(j, k));
						arr[j][k] -= 2;
					}
				}
			}
		}
		
		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ans += arr[i][j];
			}
		}
		
		System.out.println(ans);
	}
}