import java.util.*;
import java.io.*;

/*
 * 		[BOJ 17135] 캐슬 디펜스
 * 			1. 궁수의 위치 조합 mC3 개를 뽑아서 시뮬레이션
 *          2. 해당 궁수의 위치에서 D 만큼의 bfs 로 돌면서, 데쓰존 만들기
 *          3. 데쓰존 밟는 적이 있으면 적을 죽임
 *          4. 해당 턴에 사용된 데쓰존의 유형은 사용불가처리
 */
public class Main_sangphil {
	static class CustemComparator implements Comparator<Pair> {
		@Override
		public int compare(Pair o1, Pair o2) {
			int diff = o1.turn - o2.turn;
			if (diff == 0) {
				diff = o2.x - o1.x;
				if (diff == 0) return o1.y - o2.y;
				return diff;
			}
			return diff;
		}
	}
	
	static class Pair {
		int x; int y; int cnt; int turn;
		public Pair (int x, int y) {this.x=x; this.y=y;}
		public Pair (int x, int y, int cnt) {this.x=x; this.y=y; this.cnt=cnt;}
		public Pair (int x, int y, int cnt, int turn) {this.x=x; this.y=y; this.cnt=cnt; this.turn=turn;}
	}
	
	static final int[] dx = {1,   -1,0,0};
	static final int[] dy = {0,   0,1,-1};
	
	static PriorityQueue<Pair> enemies = new PriorityQueue<Pair>(new CustemComparator());
	static int N, M, D;
	static int ans = Integer.MIN_VALUE;
	static int[][] arr;
	static int[] sel = new int[3];
	static boolean[][][] deathZone;
	
	public static void main(String[] args) throws IOException {
		// System.setIn(Main.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) enemies.add(new Pair(i, j));
			}
		}
		
		combi (0, 0);
		
		System.out.println(ans);
	}
	
	static void combi (int depth, int k) {
		if (depth == 3) {
			// 데쓰존 생성 (3개 레이어)
			bfs();
			// 데쓰존 밟으면 죽음
			int cnt = shoot();
			
			ans = Math.max(ans, cnt);
			return;
		}
		for (int i = k; i < M; i++) {
			sel[depth] = i;
			combi(depth+1, k+1);
		}
	}
	
	static void bfs() {
		deathZone = new boolean[N][M][3];
		for (int i = 0; i < 3; i++) {
			Deque<Pair> q = new LinkedList<Pair>();
			q.add(new Pair(N-1, sel[i], 1));
			deathZone[N-1][sel[i]][i] = true;
			
			while (!q.isEmpty()) {
				Pair p = q.poll();
				if (p.cnt == D) continue;
				for (int j = 1; j < 4; j++) {
					int nx = p.x + dx[j];
					int ny = p.y + dy[j];
					if (0 <= nx && 0 <= ny && ny < M) {
						if (!deathZone[nx][ny][i]) {
							deathZone[nx][ny][i] = true;
							q.add(new Pair(nx, ny, p.cnt+1));
						}
					}
				}
			}
		}
	}
	
	static int shoot() {
		PriorityQueue<Pair> thisTurnEnemies = new PriorityQueue<>(enemies);
		int cnt = 0;
		Pair[][] visited = new Pair[N+3][3];
        visited[0][0] = new Pair(-1, -1, D+1);
        visited[0][1] = new Pair(-1, -1, D+1);
        visited[0][2] = new Pair(-1, -1, D+1);

		while (!thisTurnEnemies.isEmpty()) {
			Pair p = thisTurnEnemies.poll();
            int t = 0;
            for (int i = 0; i < 3; i++) {
                if (visited[p.turn][i] != null && visited[p.turn][i].x == p.x && visited[p.turn][i].y == p.y) {
                    t++;
                    break;
                }
            }
            if (t != 0) {
                cnt+=t;
                continue;
            }
            if (p.x == N) continue;

			for (int i = 0; i < 3; i++) {
				if (deathZone[p.x][p.y][i]) {
                    int dis = Math.abs(p.y - sel[i]) + (N - p.x);
					if (dis <= D && visited[p.turn+1][i] == null) {
                        visited[p.turn+1][i] = new Pair(p.x+1, p.y, dis);
                    }
                    else if (visited[p.turn+1][i].cnt ==  dis) {
                        if (visited[p.turn+1][i].y > p.y) {
                            visited[p.turn+1][i] = new Pair(p.x+1, p.y, dis);
                        }
                    } else if (visited[p.turn+1][i].cnt > dis) {
                        visited[p.turn+1][i] = new Pair(p.x+1, p.y, dis);
                    }
				}
			}

            thisTurnEnemies.add(new Pair(p.x+1, p.y, 0, p.turn+1));
		}	
		return cnt;
    }
}