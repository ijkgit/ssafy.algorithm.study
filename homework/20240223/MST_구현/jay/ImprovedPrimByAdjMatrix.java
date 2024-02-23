package edu.ssafy.mst.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ImprovedPrimByAdjMatrix {
	static class Vertex implements Comparable<Vertex> {
		int idx, weight;

		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.weight, o.weight);
		}

		public Vertex(int idx, int weight) {
			super();
			this.idx = idx;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Vertex [idx=" + idx + ", weight=" + weight + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int[][] adjMatrix = new int[V][V];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjMatrix[from][to] = weight;
			adjMatrix[to][from] = weight;
		}

		for (int i = 0; i < adjMatrix.length; i++) {
			System.out.println(Arrays.toString(adjMatrix[i]));
		}

		boolean[] visited = new boolean[V];
		int[] weight = new int[V];
		Arrays.fill(weight, Integer.MAX_VALUE);
		weight[0] = 0;

		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(new Vertex(0, weight[0]));
		
		int res = 0, cnt = 0;
		while(!pq.isEmpty()) {
			Vertex v = pq.poll();
			
			if (visited[v.idx]) continue;
			System.out.println("poll : " + v);
			
			res += v.weight;
			visited[v.idx] = true;
			if (++cnt == V) break;
			
			for (int i = 0; i < V; i++) {
				if (!visited[i] && adjMatrix[v.idx][i] != 0 && adjMatrix[v.idx][i] < weight[i]) {
					weight[i] = adjMatrix[v.idx][i];
					pq.offer(new Vertex(i, weight[i]));
				}
			}
		}
		System.out.println("ans : " + (cnt == V ? res : -1));
	}
}