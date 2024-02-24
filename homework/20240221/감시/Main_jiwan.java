package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 감시 {
	static class CCTV{
		int y,x;
		int id;
		public CCTV(int y, int x, int id) {
			this.y = y;
			this.x = x;
			this.id = id;
		}
	}
	static int N;
	static int M;
	static int [][]map;
	static int [][]copyMap;
	static int ans = Integer.MAX_VALUE; //최소크기
	static ArrayList<CCTV> cctvs;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cctvs = new ArrayList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]>0 && map[i][j]<6) {
					cctvs.add(new CCTV(i,j,map[i][j]));
				}
			}
		}
		
		
		recursive(0,map);
		
		System.out.println(ans);
	}
	static int[][][] mode = {
			{{0}},
			{{0},{1},{2},{3}},
			{{2,3},{0,1}},
			{{0,3},{1,3},{1,2},{0,2}},
			{{0,2,3},{0,1,3},{1,2,3},{0,1,2}},
			{{0,1,2,3}}
	};
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
;
	private static void recursive(int idx,int [][] map) {
		if(idx == cctvs.size()) {
			
			int count= getAns(map);//////!!!!!!!
			//print(map);
			ans = Math.min(ans, count);
			return;
		}
		
		int id = cctvs.get(idx).id;
		int y = cctvs.get(idx).y;
		int x = cctvs.get(idx).x;
		
		for(int i=0; i<mode[id].length; i++) {
			int [][] copyMap = new int[N][M];
			for(int j=0; j<N; j++) {
				copyMap[j] = map[j].clone();
			}
			
			for(int j=0; j<mode[id][i].length; j++) {
				int dir = mode[id][i][j];
				int ny = y+dr[dir];
				int nx = x+dc[dir];
				
				while(true) {
					if(nx < 0 || nx >= M || ny < 0 || ny >= N) {
						break;
					}
					if(map[ny][nx]==6)break;
					copyMap[ny][nx] = -1;
					nx += dc[dir];
					ny += dr[dir];
				}
			}
			recursive(idx+1, copyMap);
		}
		
		
	}
	
	private static void print(int[][] map) {
		System.out.println();
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

	public static int getAns(int[][] map) {
		int cnt = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
}
