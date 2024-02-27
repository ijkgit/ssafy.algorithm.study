import java.util.*;
import java.io.*;

/*
 * 		[BOJ 1753] 최단경로
 * 			특정 노드에서의 모든 노드에 대한 최단 경로: 다익스트라 활용
 */
public class Main_sangphil {
	static class Node {
		int n, w;
		public Node(int n, int w) {this.n=n; this.w=w;}
	}
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		// System.setIn(Main.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 0. 초기화
		ArrayList<ArrayList<Node>> graph = new ArrayList<>();
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int I = Integer.parseInt(br.readLine());
		int[] dis = new int[V+1];
		
		for (int i = 0; i < V+1; i++) {
			graph.add(new ArrayList<Node>());
			dis[i] = INF;
		}	
		
		// 1. 인접 리스트에 노드의 관계 그래프 초기화
		int a, b, c;
		for (int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, c));
		}
		
		// 2. 다익스트라 알고리즘 Shoot
		PriorityQueue<Node> q = new PriorityQueue<Node>((o1, o2) -> (o1.w-o2.w));
		dis[I] = 0;
		q.add(new Node(I, 0));
		
		while(!q.isEmpty()) {
			Node n = q.poll();
			for (Node next : graph.get(n.n)) {
				// 갱신될 weight 가 더 적으면, 초기화하고, pq 에 삽입
				if (dis[next.n] > dis[n.n] + next.w) {
					dis[next.n] = dis[n.n] + next.w;
					q.add(new Node(next.n, dis[next.n]));
				}
			}
		}
		
		// 3. 결과 계산 및 출력
		for (int i = 1; i <= V; i++) sb.append(dis[i] == INF ? "INF" : dis[i]).append("\n");
		
		System.out.println(sb);
	}
}