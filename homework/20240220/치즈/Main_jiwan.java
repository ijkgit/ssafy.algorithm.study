package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 치즈 {
	static int N;
	static int M;
	static int [][]map;
	static ArrayList<int[]>surface;
	public static void main(String[] args) throws IOException {
		//치즈가 녹는것 : 위아래왼위 중 하나라도 공기면 녹음
		//	단 치즈가 구멍나있을때는 안녹다가 열려야 녹음
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cheese =0;
		for (int i = 0; i < map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
					cheese += map[i][j];
			}
		}
		int time = 0;
		if(cheese == 0) { //맵에 치즈가 없는 경우
			System.out.println(time);
			System.out.println(cheese);
			return;
		}
		
		int backup = 0;
		while(true) {
			
			cheese = 0;
			for (int i = 0; i < map.length; i++) {
				for(int j=0; j<map[i].length; j++) {
						cheese += map[i][j];
				}
			}
			if(cheese ==0)break;
			BFS(0,0);
			time ++;
			backup = cheese;
						
			
		}
		System.out.println(time);
		System.out.println(backup);
	}

//	private static boolean mapCheck() {
//		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<M; j++) {
//				if(map[i][j]==1) {return false;}
//			}
//		}
//		return true;
//	}
	
	
	static int []dy = {0,1,0,-1};
	static int []dx = {1,0,-1,0};
	private static void BFS(int sy, int sx) {
		ArrayDeque<int[] > adq =new ArrayDeque<>();
		adq.offer(new int[] {sy, sx});
		boolean visited[][] = new boolean[N][M];
		visited[sy][sx] = true;
		surface = new ArrayList<>(); 
		while(!adq.isEmpty()) {
			int[] curr = adq.poll();
			int cury = curr[0];
			int curx = curr[1];
			for(int i=0; i<4; i++) {
				int ny = cury+dy[i];
				int nx = curx+dx[i];
				if(ny<0 || ny>=N || nx <0 || nx>=M || visited[ny][nx])continue;
				if(map[ny][nx]==1) {
					surface.add(new int[] {ny, nx});
					continue;
				}
				visited[ny][nx] = true;
				adq.offer(new int[] {ny,nx});
			}
		}
		for(int i=0; i<surface.size(); i++) {
			int y = surface.get(i)[0];
			int x = surface.get(i)[1];
			map[y][x] = 0;
			//surface.remove(i);
			//i--;
		}
		
	}
	static void print(int[][] m) {
		System.out.println();
		for(int i=0; i<m.length; i++) {
			for(int j=0; j<m[i].length; j++) {
				System.out.print(m[i][j]+" ");
			}
			System.out.println();
		}
		
	}
}











