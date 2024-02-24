package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class 창용마을무리의개수 {
	static int[] people;
	static int[] height;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc= 1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			people = new int[N+1];
			height = new int[N+1];
			for(int i=1; i<=N; i++) {
				people[i] = i;
			}
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a,b);
				//System.out.println(Arrays.toString(people));
			}
			HashSet<Integer> h = new HashSet<>();
			for(int i=1; i<=N; i++) {
				h.add(find(people[i]));
			}
			//System.out.println(h);
			
			System.out.println("#"+tc+" "+h.size());
		}
	}
	
	private static void union(int org, int change) {
		int a = find(org);
		int b = find(change);
		if(a!=b) {
			//set[a] = b; //a 조직의 대빵을 b조직으로 바꿈
			//rank			
			//
			if(height[b] > height[a]) {
				people[a]=b; //???????????????????
			}else {
				people[b]=a;
				if(height[b]==height[a]) {
					height[a]++;
				}
			}
		}
	}
	private static int find(int org) {
		if(people[org] == org)return people[org];
		else{ //path compression
			return people[org] = find(people[org]);
		}
		
	}
}
