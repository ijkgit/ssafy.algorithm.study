

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 게리맨더링 {
	static int N;
	static int[] population;
	static int[][] map;
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		population = new int[N+1];
		map = new int[N+1][N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int adj = Integer.parseInt(st.nextToken());
			for(int j=1; j<=adj; j++) {
				int to = Integer.parseInt(st.nextToken());
				map[i][to] = 1;
				map[to][i] = 1;
			}
		}
		
		powerSet(0,0,new boolean[N]);
		if(ans == Integer.MAX_VALUE)System.out.println(-1);
		else System.out.println(ans);
		
	}
	private static void powerSet(int idx, int k, boolean[] sel) {
		if(idx == N) {
			//System.out.println(Arrays.toString(sel));
			int tCnt=0;
			for(int i=0; i<sel.length; i++) {
				if(sel[i])tCnt++;
			}
			int res = BFS(sel, tCnt);
			if(res != -1)
				ans = Math.min(res, ans);
				
			return;
		}
		
		sel[idx] = true;
		powerSet(idx+1, k+1, sel);
		sel[idx] = false;
		powerSet(idx+1,k,sel);
		
	}
	private static int BFS(boolean[] sel, int tCnt) {
		int fCnt = N-tCnt;
		boolean visited[] = new boolean[N+1];
		ArrayDeque<Integer> adq = new ArrayDeque<>();
		for(int i=0; i<sel.length; i++) {
			if(sel[i] ==true) {
				visited[i+1] = true;
				adq.offer(i+1);
				break;
			}
		}
		int localTCnt =0;
		
		while(!adq.isEmpty()) {
			int curr = adq.poll();
			localTCnt ++;
			for(int i=1; i<=N; i++) {
				if(map[curr][i] == 1 && !visited[i] && sel[i-1]) {
					visited[i] = true;
					adq.add(i);
				}
			}
		}
		
		visited = new boolean[N+1];
		int localFCnt =0;
		adq = new ArrayDeque<>();
		
		for(int i=0; i<sel.length; i++) {
			if(sel[i] ==false) {
				visited[i+1] = true;
				adq.offer(i+1);
				break;
			}
		}
		while(!adq.isEmpty()) {
			int curr = adq.poll();
			localFCnt ++;
			for(int i=1; i<=N; i++) {
				if(map[curr][i] == 1 && !visited[i] && !sel[i-1]) {
					visited[i] = true;
					adq.add(i);
				}
			}
		}
		if(tCnt == localTCnt && fCnt == localFCnt) {
			int Tsum = 0;
			int Fsum = 0;
			for(int i=0; i<sel.length; i++) {
				if(sel[i] ==true) {
					Tsum+=population[i+1];
				}
				else Fsum += population[i+1];
			}
			
			return Math.abs(Tsum - Fsum);
		}
		return -1;
		
		
	}
	
}
