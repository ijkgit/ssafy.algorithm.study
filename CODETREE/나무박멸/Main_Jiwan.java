package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
	static int n, m, k, c;// 격자의 크기, 박멸이 진행되는 년 수, 제초제의 확산 범위, 제초제가 남아있는 년 수
	static int[][] board;

	static class Point {
		int r, c;
	}

	static int dr[] = { 1, -1, 0, 0 };
	static int dc[] = { 0, 0, 1, -1 };
	static int ans = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		board = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				// 벽은 -1
			}
		}

		for (int i = 0; i < m; /* 기간 */ i++) {
			// 1. 성장
			for (int l = 0; l < n; l++) {
				for (int k = 0; k < n; k++) {
					if(board[l][k]<-10)board[l][k]++;
					if(board[l][k]==-10)board[l][k]=0;
				}
			}
			
			for (int l = 0; l < n; l++) {
				for (int k = 0; k < n; k++) {
					
					if (board[l][k] > 0) { // 나무이면
						int growth = 0;
						for (int j = 0; j < 4; j++) {
							int nr = l + dr[j];
							int nc = k + dc[j];
							if (check(nr, nc))
								continue;
							if(board[nr][nc]<-10)continue;
							if (board[nr][nc] > 0)
								growth++;
						}
						board[l][k] += growth;
					}
				}	
			}
			// print(board);
			// 2. 번식
			번식();
			// print(board);
			// 3. 제초제 뿌림 : 가장 많이 죽는 칸
			제초제뿌림();
			//print(board);
		}
		System.out.println(ans);
	}

	static int k_r[] = { 1, -1, 1, -1 };
	static int k_c[] = { 1, -1, -1, 1 };

	private static void 제초제뿌림() {
		// 1. 가장 많이 죽는 칸 찾기
		int g_maxKilled = -1;
		int r = -1; 
		int c = -1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int killed = 0;
				for (int d = 0; d < 4; d++) {
					for (int l = 1; l <= k; l++) {
						int nr = i + k_r[d] * l;
						int nc = j + k_c[d] * l;
						boolean isWallorNoTree = false;
						if (check(nr, nc))
							continue;

						if (board[nr][nc] >= 0) {
							killed += board[nr][nc];
						} else if (board[nr][nc] == -1 ) {
							isWallorNoTree = true;
							if (isWallorNoTree)
								break;
						}
					}
				}
				
				if(killed > g_maxKilled) {
					g_maxKilled = killed;
					r= i;
					c= j;
				}

			}
		}
		ans += g_maxKilled;
		// 2. 뿌리기
		if(r!=-1 || c!=-1 || g_maxKilled!=-1) {
			
			for (int d = 0; d < 4; d++) {
				for (int l = 1; l <= k; l++) {
					int nr = r + k_r[d] * l;
					int nc = c + k_c[d] * l;
					boolean isWallorNoTree = false;
					if (check(nr, nc))
						continue;

					if (board[nr][nc] >= 0) {
						board[nr][nc] = -10+(-c);
					} else if (board[nr][nc] == -1 || board[nr][nc] == 0) {
						isWallorNoTree = true;
						if (isWallorNoTree)
							break;
					}
				}
			}
		}

	}

	private static void 번식() {// 칸 중 벽, 다른 나무, 제초제 모두 없는 칸에 번식을 진행
		int[][] sub = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sub[i][j] = board[i][j];
			}
		}
		for (int l = 0; l < n; l++) {
			for (int k = 0; k < n; k++) {
				if (board[l][k] > 0) { // 나무이면
					int 번식가능자리 = 0;
					for (int j = 0; j < 4; j++) {
						int nr = l + dr[j];
						int nc = k + dc[j];
						if (check(nr, nc))
							continue;
						
						if (board[nr][nc] == -1)
							continue;
						if(board[nr][nc]<-10)continue;
						번식가능자리++;
					}
					
					int 새끼나무 = board[l][k] / 번식가능자리;
					for (int j = 0; j < 4; j++) {
						int nr = l + dr[j];
						int nc = k + dc[j];
						if (check(nr, nc))
							continue;
						
						if (board[nr][nc] == -1)
							continue;
						/* 제초제 처리 continue; */
						if(board[nr][nc]<-10)continue;
						sub[nr][nc] += 새끼나무;
					}

				}
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = sub[i][j];
			}
		}
	}

	private static void print(int[][] m) {
		System.out.println();
		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n; j++) {
				System.out.print(board[i][j] + "   ");

				// 벽은 -1
			}
			System.out.println();
		}

	}

	private static boolean check(int nr, int nc) {
		if (nr < 0 || nr >= n || nc < 0 || nc >= n)
			return true;
		return false;
	}
}
