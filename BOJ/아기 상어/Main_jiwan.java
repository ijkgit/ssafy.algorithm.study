package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
//문제를 읽다보니 dfs가 먼저 떠올랐음
//dfs를 활용하고 dxy를 잘 줘서 우선순위를 만족할 수 있지 않나? 라고 생각했었음 XXX
//거리, 가장 위, 가장 왼쪽 
//512MB => 넉넉하게 사용가능
//2초 완탐가능
public class 아기상어 {
	static int N;
	static int [][]map;
	static int [][]dist;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	static int size =2; //아기상어의 크기 
	static int fish = 0; //아기상어가 먹은 물고기
	
	static int minDistX;
	static int minDistY;
	static int minDist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		int sx = 0;
		int sy = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					sy =i;
					sx =j;
					map[i][j] = 0 ;
				}
			}
		}
		//BFS를 여러번 돌려야겠다고 생각 >> 한번 먹으러 찾아가고 다음걸 먹어야함.
		int time =0; 
		while(true) {
			minDistX= Integer.MAX_VALUE;
			minDistY = Integer.MAX_VALUE;
			minDist = Integer.MAX_VALUE;
			dist = new int[N][N];
			
			BFS(sy, sx);
			
			if((minDistX == minDistY)&&(minDistY==Integer.MAX_VALUE))break; //종료조건 : 먹을 물고기 X
			fish++; 
			map[minDistY][minDistX] =0;
			sy = minDistY;
			sx = minDistX;
			time += dist[minDistY][minDistX];
			if(fish == size) {
				size++;
				fish = 0;
			}
		
		}
		System.out.println(time);
		
		
		
	}
	private static void BFS(int sy, int sx) {
		ArrayDeque<int[]> adq = new ArrayDeque<>();
		adq.add(new int[] {sy, sx});
		while(!adq.isEmpty()) {
			int[] curr = adq.poll();
			int cury = curr[0];
			int curx = curr[1];
//			print(dist);
//			print(map);
			for(int i=0; i<4; i++) {
				int ny = cury+dr[i];
				int nx = curx+dc[i];
				
				if(outMap(ny,nx)||cannotMove(ny,nx))continue;//맵 밖, 움직임조건x =>같거나 0일때만이동, 먹이조건 x continue
				if(dist[ny][nx]==0) {
					dist[ny][nx] = dist[cury][curx]+1; // 거리 누적
					if(canEat(ny,nx)) {//먹을수 있을때
						if(minDist>dist[ny][nx]) {//더 가까운 물고기
							minDist = dist[ny][nx];
							minDistX = nx;
							minDistY = ny;
						} else if(minDist == dist[ny][nx]) { //최소거리 물고기 많을때
							if(minDistY == ny) {
								if(minDistX>nx) { //왼쪽이 작음
									minDistY = ny;
									minDistX = nx;
								}
							}else if(minDistY >ny) { //위쪽이 작음
								minDistY = ny;
								minDistX = nx;
							}
						}
							
					}
					adq.add(new int[] {ny, nx});
				}
				
				
			}
			
		}
	}
	private static boolean canEat(int ny, int nx) {
		if(map[ny][nx] !=0 && map[ny][nx]<size)return true;
		return false;
	}
	private static boolean cannotMove(int ny, int nx) {
		if(map[ny][nx]>size)return true;
		return false;
	}
	private static boolean outMap(int ny, int nx) {
		if(ny<0||ny>=N||nx<0||nx>=N)return true;
		return false;
	}
	static void print(int [][] m) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(m[i][j]);
			}
			System.out.println();
		}
		
	}
}
