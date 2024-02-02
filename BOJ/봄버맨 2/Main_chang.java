package edu.ssafy.im;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int R,C,N;
	static int[][] board ;
	static int[][] dxy = {{0,-1},{0,1},{1,0},{-1,0}};
	public static void boom() {
		for (int i = 0 ; i < R; i++) {
			for(int j=0; j <C ; j++) {
				if(board[i][j] == 1) {
					board[i][j] = 0;
					for(int d = 0; d<4; d++) {
						int dx = dxy[d][0] +i;
						int dy = dxy[d][1] + j;
						if(0<=dx && dx<R && 0<=dy && dy <C) {
							if(board[dx][dy]>1) board[dx][dy] = 0; 
						}
					}
				}
			}
		}		
		
	}
	public static void fill_bomb() {
		for (int i = 0 ; i < R; i++) {
			for(int j=0; j <C ; j++) {
				if(board[i][j] == 0) {
					board[i][j] = 2;
				
				}else if(board[i][j] == 2) {
					board[i][j]--;
				}
			}
		}		
		
	}

	public static void main(String[] args) throws IOException {
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String str = br.readLine();
			StringTokenizer tk = new StringTokenizer(str);
			// r*c 격자판
			R = Integer.valueOf(tk.nextToken());
			C = Integer.valueOf(tk.nextToken());
			N = Integer.valueOf(tk.nextToken());
			board = new int[R][C];
			//폭탄은 남은 터질 시간만큼 숫자를 가짐
			for (int i = 0 ; i < R; i++) {
				String[] tmp = br.readLine().split("");
				for(int j=0; j <C ; j++) {
					if(tmp[j].equals(".")) {
						board[i][j] = 0;
					}else {
						board[i][j] = 2;
					}	
				}
			}

			if(N == 1) {

				for (int i = 0 ; i < R; i++) {
					for(int j=0; j <C ; j++) {
						if (board[i][j]==0)
						sb.append(".");
						else {
							sb.append("O");

						}
						
					}
					sb.append("\n");

				}
				System.out.println(sb);
				return;
			}
			else if (N%2 == 0) {
				fill_bomb();
			}else if(N%4 == 3) {
				boom();
				fill_bomb();
				boom();
			}else if(N%4 == 1) {
				boom();
				fill_bomb();
				boom();
				fill_bomb();
				boom();
			}
			
			

			for (int i = 0 ; i < R; i++) {
				for(int j=0; j <C ; j++) {
					if (board[i][j]==0)
					sb.append(".");
					else {
						sb.append("O");

					}
					
				}
				sb.append("\n");

			}
			System.out.println(sb);
			

			
		}
	
	

}
