package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class 쿼드트리 {
	static int N;
	static char[][] map;
	static String str="";
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		str += '(';
		recursive(0,0,N);
		str += ')';
		System.out.println(str);
	}
	private static void recursive(int r, int c,int n) {
		//if( n ==0 )return;
		//System.out.println(r+ " " + c + " " + n);
		/*
		 * 
		1       2
		   ─┼─
		3		4

		 * */
		int half = n/2;
		char schar = map[r][c];
		boolean flag = false;
		for(int i =r; i<half; i++) {
			for(int j=c; j<half; j++) { //1
				if(schar != map[i][j])flag= true;
			}
		}
		if(flag) {
			str += '(';
			//System.out.println("1111111111111111111");
			recursive(r,c,half);
			str += ')';
		}
		else {
			str += schar;
		}
		schar = map[r][c+half];
		flag = false; 
		for(int i =r; i<half; i++) {
			for(int j=half; j<n; j++) { //2
				if(schar != map[i][j])flag= true;
			}
		}
		if(flag) {
			
			str += '(';
			recursive(r,c+half, half);
			str += ')';
		}else {
			str += schar;
		}
		schar = map[r+half][c];
		flag = false; 
		for(int i =half; i<n; i++) {
			for(int j=c; j<half; j++) { //3
				if(schar != map[i][j])flag= true;
			}
		}
		if(flag) {
			str += '(';
			recursive(r+half,c,half);
			str += ')';
		}else {
			str += schar;
		}
		schar = map[r+half][c+half];
		flag = false; 
		for(int i =half; i<n; i++) { //4
			for(int j=half; j<n; j++) {
				if(schar != map[i][j])flag= true;
			}
		}
		if(flag) {
			str += '(';
			recursive(r+half,c+half,half);
			str += ')';
		}else {
			str += schar;
		}

		
	}
}
