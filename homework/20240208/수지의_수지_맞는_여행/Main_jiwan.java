package BOJ;

import java.util.*;
import java.io.*;

public class 수지의수지맞는여행 {
	static int []dr = {1,0,-1,0};
	static int []dc = {0,1,0,-1};
	static int max = Integer.MIN_VALUE;	
	static int R; 
	static int C;
	static char[][] map;
	static boolean [][]visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int test_case =1; test_case<=T; test_case++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new char[R][C];
			visited = new boolean[R][C];
			for(int i=0; i<R; i++) {
				map[i] = br.readLine().toCharArray();
			}
			int dist = 1; 
			String curChar = new String(""+map[0][0]);
			visited[0][0] = true;
			move(0,0,dist, curChar); // r c dist curChar
			System.out.println("#"+test_case + " "+max);
			max = Integer.MIN_VALUE;
		}
	
	}
	private static void move(int r, int c, int dist, String curChar) {
		//basis 
		//if(curChar == nextChar) 
		//
		max = Math.max(dist, max);
		
		//inductive
		for(int i=0; i<4; i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			if(outMap(nr, nc)) continue; //맵 밖이면, continue;
			if(visited[nr][nc])continue;
			char nextChar = map[nr][nc];
			boolean flag =false; 
			char[] temp = curChar.toCharArray();
			for(int j = 0; j<curChar.length(); j++) {
				if(temp[j] == nextChar)flag=true;
			}
			if(flag)continue;	
			
			
			visited[nr][nc] = true;
			move(nr, nc, dist+1, curChar+nextChar);
			visited[nr][nc] =false; 
		}
		
	}
	private static boolean outMap(int nr, int nc) {
		if(nr<0 || nr>=R||nc<0||nc>=C) return true;
		return false;
	}
}
