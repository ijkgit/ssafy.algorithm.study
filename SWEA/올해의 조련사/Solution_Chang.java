import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Queue;
import java.util.StringTokenizer;


public class Solution {
	static int N ;
	static char[] arr;
	static StringBuilder sb = new StringBuilder();
	static int tmp[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		
		
		int T = Integer.valueOf(tk.nextToken());
		
		
		
		for (int tc = 1; tc <= T; tc++) {
			//입력 및 전처리
			tk = new StringTokenizer(br.readLine());
			N = Integer.valueOf(tk.nextToken());
			tmp = new int [0][N];

			arr = new char[N];
			for (int i = 0; i < N; i++) {
				tk = new StringTokenizer(br.readLine());
				arr[i] = tk.nextToken().charAt(0);
			}
			
			
			
			
			sb.append("#").append(tc).append(" ");
			search(0,N-1, 0);
			int start =0, end =N-1 , tmp = 0;
			
			
			sb.append("\n");


		}
		System.out.println(sb);

	}

	private static void search(int start, int end, int tmp) {
		
		while(true) {
			if(start >= end) {
				sb.append(arr[start]);
				return;
			}
			if(arr[start] < arr[end]) {
				sb.append(arr[start]);
				start++;

			}else if(arr[start] > arr[end]) {
				sb.append(arr[end]);
				end--;

			}else {//같은경우
				int left = 0 , cnt =0;
				int right = 0 ;
				while (left==right && end-start >=cnt) {
					left += arr[start+cnt];
					right += arr[end-cnt];
					cnt ++;
				}
				if(left<right) {

						sb.append(arr[start]);

					start++;
				}else {

					sb.append(arr[end]);
					
					end--;
				}
				
			}
		}
		

		
	}



}



