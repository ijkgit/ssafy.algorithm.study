package edu.ssafy.chap01;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		// 과자는 두봉지만 사야함
		t_case:
		for(int tc = 1 ; tc <= T ;tc ++) {
			
			int N= sc.nextInt();
			int M = sc.nextInt();
			int[] cookies = new int[N];

			for(int i =0; i<N ; i++) {
				cookies[i] = sc.nextInt();
			}
			//정렬 후 무거운 과자부터
			Arrays.sort(cookies);
			int max = -1;
			p :
			for (int i=N-1 ; i >= 1 ; i--) {
				if (cookies[i]> M) {
					continue;
				}
				for(int j = i-1; j>=0 ; j--) {					
					if(cookies[i] + cookies[j] == M) {
						System.out.printf("#%d %d\n",tc,cookies[i] + cookies[j]);
						continue t_case;
					}else if (cookies[i] + cookies[j] < M) {

						max = Math.max(max, cookies[i] + cookies[j]);
					}
				}
			}	
			
			System.out.printf("#%d %d\n",tc, max);
		}
			
	}
		
}
