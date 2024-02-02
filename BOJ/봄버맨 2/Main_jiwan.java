package test;

import java.awt.Point;
import java.io.*;
import java.util.*;



public class 봄버맨2 {
	static int R;
	static int C;
	static int N;
	static char map[][];
	static ArrayList<MyPoint> Bomb;
	final static int[][] dxy = {{1,0},{-1,0},{0,1},{0,-1}};
	static class MyPoint extends Point{
		int mtime;
		MyPoint(int R, int C, int time){
			super(R,C);
			this.mtime = time;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		Bomb = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		// 빈 칸은 '.'로, 폭탄은 'O'로 주어진다.
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				if(map[i][j] == 'O')
					Bomb.add(new MyPoint(i,j,0));
			}	
		}
//		if(N%2==0) {
//			setBomb(0);
//			for(int i=0; i<R; i++) {
//				for(int j=0; j<C; j++) {
//					sb.append(map[i][j]);
//				}
//				sb.append('\n');
//			}
//			System.out.println(sb);
//			return;
//		}
		for(int time=0; time<=N; time++) {
			
			int curTime = 0;
			if(time%2==0 && time!=0) {
				setBomb(time);
				curTime = time;
			}
			
			if(time%2==1 && time>1) {
				ExplodeBomb(time-3);
			}
			sb.append("\n#").append(time+"\n");
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					sb.append(map[i][j]);
				}
				sb.append('\n');
			}
			
			
		}
//		for(int i=0; i<R; i++) {
//			for(int j=0; j<C; j++) {
//				sb.append(map[i][j]);
//			}
//			sb.append('\n');
//		}
		
		System.out.println(sb);	
	}
	private static void ExplodeBomb(int time) {
		
		
		for(int i=0; i<Bomb.size(); i++) {
			
			MyPoint mp = (MyPoint) Bomb.get(i);
			if(mp.mtime == time) {
				Explode(mp.x, mp.y, time); //폭발
				Bomb.remove(i);
				i--;
			}
			
		}
		
	}
	
	private static void Explode(int x, int y, int time) {
		
		map[x][y] = '.';
		for(int i=0; i<4; i++) {
			int nx=x+dxy[i][0];	
			int ny=y+dxy[i][1];
			if(check(nx,ny)) {
				map[nx][ny] = '.';

				for(int j=0; j<Bomb.size(); j++) {
					MyPoint b=Bomb.get(j);
					if(b.x==nx &&b.y==ny&&b.mtime == time+2) {
						Bomb.remove(j);
						j--;
					}
				}
			}
		}
		
		
	}
	private static boolean check(int nx, int ny) {
		if(nx<0||nx>=R||ny<0||ny>=C)
			return false;
		return true;
	}
	
	private static void setBomb(int time) {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] != 'O') {
					map[i][j] = 'O';
					Bomb.add(new MyPoint(i,j,time));
				}
					
			}
		}
		
	}
}
