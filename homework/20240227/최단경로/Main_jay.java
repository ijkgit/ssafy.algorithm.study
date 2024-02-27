import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Main().sol();
	}

	private int V;
	private int E;
	private int K;
	private ArrayList<ArrayList<Vertex>> graph;
	private StringBuilder sb = new StringBuilder();

	private void sol() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine()) - 1; // zero base
		
		graph = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken()) - 1;
			int v2 = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			graph.get(v1).add(new Vertex(v2, w));
		}
		
		dijkstra();
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private void dijkstra() {
		int[] d = new int[V];
		Arrays.fill(d, Integer.MAX_VALUE);
		d[K] = 0;
		
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(new Vertex(K, d[K]));
		
		while(!pq.isEmpty()) {
			Vertex cur = pq.poll();
			
			if(cur.w > d[cur.v]) continue;
			
			for (int i = 0; i < graph.get(cur.v).size(); i++) {
				Vertex next = graph.get(cur.v).get(i);
				if(cur.w + next.w < d[next.v]) {
					d[next.v] = cur.w + next.w;
					pq.offer(new Vertex(next.v, d[next.v]));
				}
			}
		}

    // 경로 출력
		for (int i = 0; i < V; i++) {
			if(d[i] == Integer.MAX_VALUE) sb.append("INF\n");
			else sb.append(d[i]).append("\n");
		}
	}
	
	class Vertex implements Comparable<Vertex>{
		int v, w;

		public Vertex(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.w - o.w;
		}
	}
}
