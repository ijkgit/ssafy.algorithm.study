

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution {
	
	static int N;
	static int map[][];
	static int dr[] = {1,0,-1,0};
	static int dc[] = {0,1,0,-1};
	static int max = Integer.MIN_VALUE;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int test_case =1; test_case<=T; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			max = Integer.MIN_VALUE;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			for(int time = 0; time<=100; time++) {
				int isCnt =0;
				visited = new boolean[N][N];
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(map[i][j] == time)map[i][j]=-1; //marking
						
						
					}
				}
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(!visited[i][j] && map[i][j]!=-1)
						{
							BFS(i,j);
							isCnt++;
							//print(map);
						}
					}
				}
				
				max = Math.max(isCnt, max);
			}
			System.out.println("#"+test_case+" "+max);
			
		}
	}
	private static void BFS(int sy, int sx) {
		
		ArrayDeque<int []>adq = new ArrayDeque<>();
		
		adq.offer(new int[] {sy,sx});
		visited[sy][sx] = true;
		while(!adq.isEmpty()) {
			int[] curr = adq.poll();
			int cury = curr[0];
			int curx = curr[1];
			for(int i=0; i<4; i++) {
				int ny = cury + dr[i];
				int nx = curx + dc[i];
				
				if(ny<0 || ny>=N || nx<0 || nx>=N)continue;
				if(visited[ny][nx])continue;
				if(map[ny][nx]==-1)continue;
				adq.offer(new int[] {ny,nx});
				visited[ny][nx] =true;
			}
		}
	}
	static void print(int [][]m) {
		for(int i=0; i<m.length; i++) {
			for(int j=0; j<m[i].length; j++) {
				System.out.print(m[i][j]+" ");
			}
			System.out.println();
		}
	}
}
