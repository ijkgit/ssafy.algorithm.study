import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int a,b;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.valueOf(tk.nextToken());
		int M = Integer.valueOf(tk.nextToken());
		// 4byte int의 경우 N=32000에서 최대 3900MB 이상의 메모리가 필요함 인접행렬 x
		//boolean[][] arr= new boolean[N+1][N+1];
		boolean[] visited = new boolean[N+1];
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N+1; i++) {
			list.add(new ArrayList<Integer>());
		}
		
		int[] inDegree = new int[N+1];
		Queue<Integer> q = new LinkedList<>();
		
		for (int i = 0; i < M; i++) {
			tk = new StringTokenizer(br.readLine());
			a = Integer.valueOf(tk.nextToken());
			b = Integer.valueOf(tk.nextToken());
			list.get(a).add(b);
			inDegree[b] ++;
		}
		for (int i = 1; i <= N; i++) {
			if(inDegree[i] == 0&& !visited[i]) {
				q.add(i);
				while(!q.isEmpty()) {
					int tmp =q.poll();
					sb.append(tmp).append(" ");
					for(int node :list.get(tmp)) {
						inDegree[node]--;
						if(inDegree[node]==0) {
							q.add(node);
						}
					}
				}
			}
		}
		
		System.out.println(sb);

		
	}

}
