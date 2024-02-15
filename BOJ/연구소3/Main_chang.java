import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;


class ActV{
	int x;
	int y;
	int dep;
	public ActV(int x, int y, int dep) {
		super();
		this.x = x;
		this.y = y;
		this.dep = dep;
	}	
}


public class Main {
	static int N;
	static int M;
	static int ans = Integer.MAX_VALUE;
	static int[][] board;
	static int cp[][];
	static ArrayList<Point> vis = new ArrayList<>();
	static int zCount;
	static int dxy[][] = {{0,1},{1,0},{-1,0},{0,-1}};
	
	public static void activateV(int cnt, int dep, String act) {
		if(cnt == M) {
			String tmp[] = act.split(" ");
			spreadV(tmp);
			return;
		}
		if(dep == vis.size()) {
			return;
		}
		activateV(cnt, dep+1, act);
		activateV(cnt+1, dep+1, act + dep+" ");

	}
	
	private static void spreadV(String tmp[]) {
		makecopy();
		//빈곳의 수
		int life = zCount;
		//활성화 된 바이러스 큐에 넣기
		Queue<ActV> q = new LinkedList<>();
		for(String Idx : tmp) {
			int idx = Integer.valueOf(Idx);
			q.add(new ActV( vis.get(idx).x,vis.get(idx).y,0));
			cp[vis.get(idx).x][vis.get(idx).y] = 1;
		}
		
		//bfs탐색
		while(!q.isEmpty()) {
			ActV virus = q.poll();
			if (virus.dep > ans) {
				return;
			}
			for (int[] d : dxy) {
				int nx = d[0] + virus.x;
				int ny = d[1] + virus.y;
				if(0<=nx && nx < N && 0<=ny && ny < N) {
					if(cp[nx][ny] == 0) {
						cp[nx][ny] = 1;
						life--;
						if(life == 0 ) {
							ans = Math.min(ans, virus.dep+1);
							return;
						}
						q.add(new ActV(nx, ny, virus.dep+1));
						
					}else if(cp[nx][ny] == 2) {
						cp[nx][ny] = 1;
						q.add(new ActV(nx, ny, virus.dep+1));
					}
				}
				
			}
		}
		
		
		
		
	}

	private static void makecopy() {
		cp = new int[N][N];
		for (int i = 0; i < N; i++) {
			cp[i] = board[i].clone();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		N = Integer.valueOf(tk.nextToken());
		M = Integer.valueOf(tk.nextToken());
		board = new int[N][N];

		for (int i = 0; i < N; i++) {
			tk = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int tmp  = Integer.valueOf(tk.nextToken());
				board[i][j] = tmp;
				if(tmp == 2) {
					vis.add(new Point(i,j));
				}else if(tmp == 0) {
					zCount++;
				}
			}
		}
		if(zCount==0) {
			System.out.println(0);
			return;
		}
		
		activateV(0, 0, "");
		System.out.println(ans>200000?-1:ans);
	}
	

}







