package edu.ssafy.im;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;


class Pair{
	int x,y;
	public Pair(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}
public class Solution {
	static int T,N;
	static int[][] board;
	static int[][] ans;
	static int[][] dxy = {{0,1},{0,-1},{1,0},{-1,0}};


	public static void main(String[] args) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tmp = br.readLine();
		StringTokenizer tk = new StringTokenizer(tmp);

		
		T = Integer.valueOf(tk.nextToken()) ;
		for (int tc = 1; tc <= T; tc++) {
			
			tmp = br.readLine();
			tk = new StringTokenizer(tmp);
			N = Integer.valueOf(tk.nextToken()) ;
			ans = new int[N][N];
			board = new int[N][N];
			
			//board 입력
			for (int i = 0; i < N; i++) {
				String[] s = br.readLine().split("");
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.valueOf(s[j]) ;
				}
			}
			//ans 가중치 초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					ans[i][j] = 99999;
				}
			}
			ans[0][0] = 0;
			
			
			Queue<Pair> q = new LinkedList<Pair>();
			q.add(new Pair(0,0));
			
			while(!q.isEmpty()) {
				Pair now = q.poll();
				int r = now.x;
				int c = now.y;
				for (int d = 0; d <4; d++) {
					int nr = r + dxy[d][0] ;
					int nc = c + dxy[d][1];
					if(0<=nr && nr < N && 0<=nc && nc<N) {
						if(ans[r][c] + board[nr][nc] < ans[nr][nc]) {
							ans[nr][nc] = ans[r][c] + board[nr][nc];
							q.add(new Pair(nr,nc));
						}
					}	
				}
			}
			
			System.out.println("#" + tc + " " + ans[N-1][N-1]);
			
			
			
		}
		
		

	}

}
