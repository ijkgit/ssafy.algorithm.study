package day1_recursion.recursive.problems;
import java.io.*;
import java.util.*;
//unsolved
//최적경로랑 비슷한 것 같은데 최적경로를 공부하고 풀 예정입니다. 아직 제대로 이해하지 못한 것 같아요

public class 해밀턴순환회로 {
	
	static StringTokenizer st;
	static int N;
	static int[][] map;
	static boolean visited[];
	static int min=Integer.MAX_VALUE;;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		map=new int[N][N];
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		
		visited=new boolean[N];
		recursive(0,0,0);
		if (min==Integer.MAX_VALUE) {
			System.out.println(0);
			return;
		} 
		System.out.println(min);
	
	}

	private static void recursive(int idx, int cnt, int price) {
		//basis
		
		if(cnt == N-1) {
			min = Math.min(price, min);
			return;
		}
		
		
		
		//inductive
		for(int i=1;i<N;i++) {
			
			if(!visited[i] && map[idx][i]!=0) {
				visited[i] = true;
				recursive(i, cnt+1, price + map[idx][i] );
				visited[i] =false;
			}
		}
	}
}
