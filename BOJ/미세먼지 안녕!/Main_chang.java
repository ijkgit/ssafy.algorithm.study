

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main {
	static int R,C,T;
	static int[][] board;
	static int[] aircon;
	//                      상      우    하     좌
	static int[][] dxy = {{-1,0},{0,1},{1,0},{0,-1}};
	
	public static int[][] spread() {
		int[][] cpboard = new int[R][C];
		
		for(int i = 0 ; i<R ; i++) {
			for(int j= 0 ; j<C ; j++) {
				if(board[i][j]>0) {
					int cnt = 0;
					int dust = board[i][j]/5;
					for(int d = 0 ; d<4 ; d++) {
						int ni = i +dxy[d][0];
						int nj = j +dxy[d][1];
						if(0<=ni && ni < R && 0<=nj && nj<C && board[ni][nj] != -1) {
							cnt ++;
							cpboard[ni][nj]+= dust;	
						}
					}
					cpboard[i][j] += board[i][j] -( dust*cnt);
				}	
			}
		}
		cpboard[aircon[0]][0] = -1;
		cpboard[aircon[1]][0] = -1;
		return cpboard;
	}
	public static void onair() {
		int d,x,y;
		//위컨
		d = 0;
		x = aircon[0]-1;
		y = 0;
		
		
		while(true) {
			int nx= x+dxy[d][0];
			int ny= y+dxy[d][1];
			if (0<=nx && nx <= aircon[0] && 0<=ny && ny<C && board[nx][ny] != -1) {
				board[x][y] = board[nx][ny] ;
				x = nx;
				y = ny;
			}else {
				d++;
				if(d==4) {
					board[x][y] = 0;
					break;
				}
			}
			
		}
		//아래컨
		d = 2;
		x = aircon[1]+1;
		y = 0;
		while(true) {
			int nx= x+dxy[d][0];
			int ny= y+dxy[d][1];
			if (aircon[1]<=nx && nx < R && 0<=ny && ny<C && board[nx][ny] != -1) {
				board[x][y] = board[nx][ny] ;
				x = nx;
				y = ny;
			}else {
				d--;
				if(d == -1) {
					
					d= 3;
				}
				if(d==2) {
					board[x][y] = 0;
					break;
				}
			}
			
		}
		
		
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tmp = br.readLine();
		// 한줄 입력 받고 토크나이저로 잘라야함
		//입력
		StringTokenizer tk = new StringTokenizer(tmp);
		R =Integer.valueOf(tk.nextToken()) ;
		C =Integer.valueOf(tk.nextToken()) ;
		T =Integer.valueOf(tk.nextToken()) ;
		//x값만 저장
		aircon = new int[2];
		board = new int[R][C];
		for(int r =0; r<R ; r++) {
			tmp = br.readLine();
			tk = new StringTokenizer(tmp);
			for (int c = 0; c <C; c++) {
				board[r][c] = Integer.valueOf(tk.nextToken());
				if (board[r][c] ==-1) {
					aircon[1] = r;
				}
			}	
		}
		aircon[0] = aircon[1] -1;
		
		
		
		// 좌표 기억x  copyboard 사용{
		for(int t =0; t< T ; t++) {
			board = spread();
			onair();
		}
	int sum = 0;
	for(int r =0; r<R ; r++) {
		
		for (int c = 0; c <C; c++) {
			
			if(board[r][c] > 0 ) {
				sum += board[r][c];
			}
				

		}	
	}
		
		
		System.out.println(sum);
		
	}

}
