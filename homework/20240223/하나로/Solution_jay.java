import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	private int N;
	private ArrayList<ArrayList<Vertex>> graph;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Solution().io();
	}

	private void io() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			int x[] = new int[N];
			int y[] = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				y[i] = Integer.parseInt(st.nextToken());
			}
			
			double E = Double.parseDouble(br.readLine());
			
			graph = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				graph.add(new ArrayList<>());
			}
			
			for (int i = 0; i < N-1; i++) {
				for (int j = i+1; j < N; j++) {
					double weight = E * (Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2));
					graph.get(i).add(new Vertex(j, weight));
					graph.get(j).add(new Vertex(i, weight));
				}
			}
			long ans = (long) Math.round(sol());
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private double sol() {
		boolean[] visited = new boolean[N];
		double[] weight = new double[N];
		Arrays.fill(weight, Double.MAX_VALUE);
		weight[0] = 0;
		
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(new Vertex(0, weight[0]));
		
		int cnt = 0;
		double ans = 0;
		while(!pq.isEmpty()) {
			Vertex v = pq.poll();
			
			if (visited[v.idx]) continue;
			
			ans += v.w;
			visited[v.idx] = true;
			
			if(++cnt == N) break;
			
			for (int i = 0; i < graph.get(v.idx).size(); i++) {
				Vertex next = graph.get(v.idx).get(i);
				if(!visited[next.idx] && next.w < weight[next.idx]) {
					weight[next.idx] = next.w;
					pq.offer(new Vertex(next.idx, weight[next.idx]));
				}
			}
		}
		
		return ans;
	}

	class Vertex implements Comparable<Vertex> {
		int idx;
		double w;

		public Vertex(int idx, double w) {
			super();
			this.idx = idx;
			this.w = w;
		}

		@Override
		public int compareTo(Vertex o) {
			return Double.compare(this.w, o.w);
		}
	}
}
