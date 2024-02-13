package problem_solving;

import java.util.*;
import java.io.*;

/*
 * 		[BOJ 17143] 낚시왕
 * 			
 * 
 * 
 */
public class Main_sangphil {
	static class Shark implements Comparable<Shark> {
		int x; int y; int s; int d; int z; int numbering; int turn;
		public Shark (int x, int y, int s, int d, int z, int numbering, int turn) {
			this.x=x; this.y=y; this.s=s; this.d=d; this.z=z; this.numbering=numbering; this.turn=turn;
		}
		@Override
		public int compareTo(Shark o) {
			int diff = this.turn - o.turn;
			if (diff == 0) return o.z - this.z;
			return diff;
		}
	}
	
	static final int[] dx = {-1,1,0,0};
	static final int[] dy = {0,0,1,-1};
	
	static PriorityQueue<Shark> q = new PriorityQueue<Shark>();
	static Set<Integer> set = new HashSet<Integer>();
	static int[][] graph;
	static int R, C, M;
	static int catching;
	static int highNum;
	static int z;
	static int ans = 0;
	
	
	public static void main (String[] args) throws IOException {
		// System.setIn(Main_sangphil.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		catching = 0;
		highNum = R;
		
		graph = new int[R][C];
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			Shark shark = new Shark(Integer.parseInt(st.nextToken())-1,
					Integer.parseInt(st.nextToken())-1,
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())-1,
					Integer.parseInt(st.nextToken()),
					i,
					0
					);
			if (shark.d <= 1) {
				shark.s %= 2*(R-1);
			} else {
				shark.s %= 2*(C-1);
			}
			q.add(shark);
			if (shark.y == 0) {
				if (shark.x < highNum) {
					highNum = shark.x;
					catching = shark.numbering;
					z = shark.z;
				}
			}
		}
		set.add(catching);
		ans += z;
		for (int i = 1; i < C; i++) {
			shoot (i);
		}
		
		System.out.println(ans);
	}
	
	static void shoot (int col) {
		graph = new int[R][C];
		z = 0;
		highNum = R;
		for (int i = 0; i < q.size(); i++) {
			Shark shark = q.poll();
			if (set.contains(shark.numbering)) {
				shark.turn++;
				q.add(shark);
				continue;
			}
			
			int nx = shark.x;
			int ny = shark.y;
			for (int j = 0; j < shark.s; j++) {
				nx += dx[shark.d];
				ny += dy[shark.d];
				if (!(0 <= nx && nx < R && 0 <= ny && ny < C)) {
					if (shark.d % 2 == 0) {
						shark.d++;
					} else {
						shark.d--;
					}
					nx += dx[shark.d] * 2;
					ny += dy[shark.d] * 2;
				}
			}
			if (graph[nx][ny] == 0) {
				graph[nx][ny] = shark.z;
			} else {
				set.add(shark.numbering);
			}
			shark.x = nx;
			shark.y = ny;

			if (shark.y == col) {
				if (shark.x < highNum) {
					highNum = shark.x;
					catching = shark.numbering;
					z = shark.z;
				}
			}
			shark.turn++;
			
			q.add(shark);
		}
		set.add(catching);
		ans += z;
	}
}