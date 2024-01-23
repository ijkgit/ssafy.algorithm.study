package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Solution {
	static int N, M,K;
	static int ans  = -1;
	static int[] arr;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in)	;
		int t = sc.nextInt();
		conti :
		for(int tc = 1; tc <= t; tc++) {
			//N명, M초에 K개 붕어빵
			//전처리
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			arr = new int[N];
			for(int i=0; i < N; i++) {
				arr[i] = sc.nextInt();
			}
			Arrays.sort(arr);
			
			int lat = 0;
			int time = 0;
//			HashMap<Integer,Integer> map = new HashMap<>();
//			for(int i : arr) {
//				if(map.containsKey(i)) {
//					map.put(i, map.get(i) +1);
//				}else {
//					map.put(i, 1);
//				}
//			}
			for(int i : arr) {
				lat += ((i-time)/M)*K;
				time = i;
//				System.out.println("#" + tc + " " + lat +" "+ i);
				if(lat <= 0) {
					System.out.println("#" + tc + " " + "Impossible");
					continue conti;
				}
				lat = lat --;
			
			}
			
			System.out.println("#" + tc + " " + "Possible");
		}
		
	}

}
