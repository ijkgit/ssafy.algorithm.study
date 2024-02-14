import java.util.*;
import java.io.*;

public class Main_sangphil {
	static class Pair implements Comparable<Pair> {
		int x; int w;
		public Pair (int x, int w) {
			this.x=x;
			this.w=w;
		}
		@Override
		public int compareTo(Pair o){
			return this.w - o.w;
		}
	}
	static final int INF = Integer.MAX_VALUE;
	static int[] dis;
	static String[] his;

	public static void main(String[] args) throws IOException {
		// System.setIn(Main.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] graph = new int[n][n];
		dis = new int[n];
		his = new String[n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
			dis[i] = INF;
		}
		
		PriorityQueue<Pair> q = new PriorityQueue<Pair>(); 
		q.add(new Pair(0, 0));
		dis[0] = 0;
		his[0] = "1 ";
		while (!q.isEmpty()) {
			Pair p = q.poll();
			if (dis[p.x] < p.w) continue;

			for (int i = 0; i < n; i++) {
				int nw = graph[p.x][i] + p.w;
				if (nw < dis[i]) {
					his[i] = his[p.x] + (i+1) + " ";
					dis[i] = nw;
					q.add(new Pair(i, nw));
				}
			}
		}
		System.out.println(dis[m-1]);
		System.out.println(his[m-1]);
	}
}