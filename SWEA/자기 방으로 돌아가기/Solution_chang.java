package edu.ssafy.im;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main {
	static int T;
	static int[] board;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tmp = br.readLine();
		// 한줄 입력 받고 토크나이저로 잘라야함
		//입력
		StringTokenizer tk = new StringTokenizer(tmp);
		T =Integer.valueOf(tk.nextToken()) ;
		
		for (int tc = 1; tc <= T; tc++) {
			tmp = br.readLine();
			tk = new StringTokenizer(tmp);
			int N = Integer.valueOf(tk.nextToken());
			board = new int[201];
			for (int n = 0; n < N; n++) {
				tmp = br.readLine();
				tk = new StringTokenizer(tmp);
				int s = Integer.valueOf(tk.nextToken());
				int e = Integer.valueOf(tk.nextToken());
				s= (s+1)/2;
				e =(e+1)/2;
				for (int i = Math.min(s, e); i <= Math.max(s, e); i++) {
					board[i] += 1;
				}
				
			}
			int ans = 0;
			for (int i = 0; i < 201; i++) {
				ans = Math.max(board[i], ans);
			}
			
			
			System.out.println("#" + tc +" " +ans);
		}

	
		
		

		
	}

}
