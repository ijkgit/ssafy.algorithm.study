

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class 다리만들기 {
	static int N;
	static int [][]map;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,-1,0,1};
	static int ans = Integer.MAX_VALUE;
	static boolean[][] visited;
	static int landNum = 2;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		StringTokenizer st ;
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[N][N];
		//1. bfs로 marking
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 1) {
					marking(i,j);
				}
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] >= 2) {
					visited = new boolean[N][N];
					makeBridge(i,j);
				}
			}
		}
		System.out.println(ans);
		
	}
	private static void makeBridge(int y, int x) {
		ArrayDeque<int[]> adq = new ArrayDeque<>();
		adq.offer(new int[] {y,x,0});
		int currNum = map[y][x];
		visited[y][x] = true;
		while(!adq.isEmpty()) {
			int[] curr= adq.poll();
			int cury = curr[0];
			int curx = curr[1];
			int bridgeLength = curr[2];
			for(int i=0; i<4; i++) {
				int ny = cury+dr[i];
				int nx = curx+dc[i];
				if(ny<0 || ny>=N || nx<0 || nx>=N)continue;
				if(visited[ny][nx])continue;
				if(map[ny][nx] == currNum)continue;
				visited[ny][nx]=true;
				if(map[ny][nx]==0)adq.offer(new int[] {ny,nx,bridgeLength+1});
				else ans = Math.min(ans, bridgeLength);
			}
		}
	}
	private static void marking(int y, int x) {
		ArrayDeque<int []> adq = new ArrayDeque<>();
		adq.offer(new int[] {y,x});
		visited[y][x] = true;
		map[y][x] = landNum;
		while(!adq.isEmpty()) {
			int [] curr = adq.poll();
			int cury = curr[0];
			int curx = curr[1];
			for(int i = 0; i<4; i++) {
				int ny = cury+dr[i];
				int nx = curx+dc[i];
				if(ny<0 || ny>=N || nx<0 || nx>=N)continue;
				if(visited[ny][nx])continue;
				if(map[ny][nx]!=1)continue;
				visited[ny][nx]=true;
				map[ny][nx] = landNum;
				adq.offer(new int[] {ny,nx});
			}
			
			
		}
		landNum++;
	}
	
}
