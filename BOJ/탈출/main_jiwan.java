package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 탈출 {
	static int R,C;
	static char[][] map;
	static int sr,sc;
	static int tr,tc;
	
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	
	static class Water{
		int r ,c;
		Water(int r, int c){
			this.r =r; 
			this.c =c;
		}
		
	}
	static ArrayList<Water> waterList = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				if(map[i][j] == '*') {
					waterList.add(new Water(i,j));
				}
				else if(map[i][j]=='S') {
					sr = i;
					sc = j;
				}else if(map[i][j] == 'D') {
					tr = i;
					tc = j;
				}
			}
		}
		
		int ans = BFS();
		if(ans == -1) {
			System.out.println("KAKTUS");
			return;
		}
		System.out.println(ans);
		
	}
	
	private static int BFS() {
		ArrayDeque<int[]> adq = new ArrayDeque<>(); //너구리 퍼짐
		ArrayDeque<Water> adq2 = new ArrayDeque<>(); //물 퍼짐
		boolean visited[][] = new boolean[R][C];
		for(int i=0; i<waterList.size(); i++) {
			Water w = waterList.get(i);
			adq2.offer(new Water(w.r, w.c));
		}
		adq.add(new int[] {sr,sc,0});
		while(!adq.isEmpty()) {
			//물 이동 >> visited만 초기화 해놓으면 되지 않을까?
			//waterList = temp;4		System.out.println(1)
			//System.out.println(1);
			int size = adq2.size();
			for(int i=0; i<size; i++) {
				
				Water w  = adq2.poll();
				for(int d=0; d<4; d++) {
					int nr = w.r +dr[d];
					int nc = w.c +dc[d];
					
					if(check(nr,nc))continue;
					if(map[nr][nc]=='D')continue;
					if(map[nr][nc]=='X')continue;
//					if(visited[nr][nc])continue;
//					visited[nr][nc]=true;
					map[nr][nc] = '*';
					adq2.add(new Water(nr,nc));
				}
				
				
			}
			
			size = adq.size();
			for(int k=0; k<size; k++) {
				
				int[] curr = adq.poll();
				
				int cr = curr[0];
				int cc = curr[1];
				int cd = curr[2];
				//System.out.println(cr+" "+cc);
				if(cr == tr && cc == tc) {
					return cd;
				}
				
				for(int i=0; i<4; i++) {
					//물
					int nr = cr +dr[i];
					int nc = cc +dc[i];
					
					
					if(check(nr,nc))continue;
					if(visited[nr][nc])continue;
					if(map[nr][nc]=='X')continue;
					if(map[nr][nc]=='*')continue;
					visited[nr][nc]=true;
					adq.offer(new int[] {nr,nc,cd+1});
				}
			}
			//print(map);
		}

		return -1;
	}
	
	private static boolean check(int nr, int nc) {
		if(nr<0|| nr>=R ||nc<0 || nc>=C)return true; 
		return false;
	}
	private static void print(char[][] map) {
		System.out.println();
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				System.out.print(map[i][j]+" " );
			}System.out.println();
		}
	}
}
