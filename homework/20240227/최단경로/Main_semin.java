import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_semin {

	static class Vertex implements Comparable<Vertex> {
		int node, weight;

		Vertex(int v, int w) {
			this.node = v;
			this.weight = w;
		}

		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return "Vertex [node=" + node + ", weight=" + weight + "]";
		}

	}

	static List<Vertex>[] adjList;
	static int[] distance;
	static PriorityQueue<Vertex> pq;
	static int INF = 300000000;
	static int V, E, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());

		adjList = new ArrayList[V + 1];
		for (int i = 0; i <= V; i++) {
			adjList[i] = new ArrayList<>();

		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjList[u].add(new Vertex(v, w));
		}

		dijkstra();
		for (int i = 1; i <= V; i++) {
			System.out.println(distance[i] == INF ? "INF" : distance[i]);
		}
	}

	private static void dijkstra() {
		distance = new int[V + 1];
		Arrays.fill(distance, INF);
		distance[K] = 0;
		
		pq = new PriorityQueue<>();
		pq.add(new Vertex(K, 0));

		while (!pq.isEmpty()) {
			Vertex v = pq.poll();
			for (Vertex u : adjList[v.node]) {
				if (distance[u.node] > distance[v.node] + u.weight) {
					distance[u.node] = distance[v.node] + u.weight;
					pq.offer(new Vertex(u.node, distance[u.node])); // 가중치로 정렬하니까 weight 업데이트 해줘야 함.
				}
			}
		}
	}
}
