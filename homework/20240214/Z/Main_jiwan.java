package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Z {
	static int targetR;
	static int targetC;
	static int size; 
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		targetR = Integer.parseInt(st.nextToken());
		targetC = Integer.parseInt(st.nextToken());
		
		recursive(targetR,targetC,1<<n); /*sr, sc, number*/
		System.out.println(ans);
		
	}
	
	/*
	 * 
	 1       2
	 	─┼─
	 4		 3
	 * */
	private static void recursive(int r, int c, int n) {
		if( n ==0 )return;
		//System.out.println(r+ " " + c + " " + n);
		
		int half = n/2;
		if(r<half && c <half) {
			ans += 0;
			//System.out.println("1사분면");
			recursive(r,c,half);
		}else if(r<half && c>=half) {
			ans += half*half;
			//System.out.println("2사분면");
			recursive(r,c-half,half);
			//2
		}else if(r>=half && c >= half) {
			ans += half*half*3;
			//System.out.println("3사분면");
			recursive(r-half, c-half, half);
			//3
		}else if(r>= half && c<half) {
			ans+= half*half*2;
			//System.out.println("4사분면");
			recursive(r-half, c, half);
			//4
		}
		
	}
	
	

	
	
}
