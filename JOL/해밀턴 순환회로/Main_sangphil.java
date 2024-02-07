import java.util.*;
import java.io.*;

public class Main_sangphil {
	static int N;
	static int ans = Integer.MAX_VALUE;
	static int[][] graph;
	static int[] sel;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		// System.setIn(Main_sangphil.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		graph = new int[N][N];
		sel = new int[N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 1, 0);

		System.out.println(ans == Integer.MAX_VALUE ? 0 : ans);
	}

	static void dfs (int ind, int depth, int total) {
		if (depth == N) {
			int dis = graph[sel[N-1]][0];
			if (dis == 0) return;
			ans = Math.min(ans, total + dis);
			return;
		}
		visited[ind] = true;
		
		for (int i = 0; i < N; i++) {
			if (!visited[i] && graph[ind][i] != 0 && total+graph[ind][i] < ans) {
				sel[depth] = i;				
				dfs(i, depth+1, total + graph[ind][i]);			
			}
		}
		visited[ind] = false;
	}
}
