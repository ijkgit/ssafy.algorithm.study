package edu.ssafy.mst.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ImprovedPrimByAdjList {
	static class Vertex implements Comparable<Vertex> {
		int idx;
		int weight;
		
		public Vertex(int idx, int weight) {
			super();
			this.idx = idx;
			this.weight = weight;
		}
		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.weight, o.weight);
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
		ArrayList<ArrayList<Vertex>> adjList = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			adjList.add(new ArrayList<>());
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList.get(from).add(new Vertex(to, weight));
			adjList.get(to).add(new Vertex(from, weight));
		}
		
		int from = 0;
		for (ArrayList<Vertex> vx : adjList) {
			System.out.println("from : " + from++);
			for (Vertex v : vx) System.out.println(v);
		}
		
		boolean[] visited = new boolean[V];
		int[] minEdge = new int[V];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[0] = 0;
		
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.add(new Vertex(0, minEdge[0]));
		int res = 0, cnt = 0;
		while(!pq.isEmpty()) {
			Vertex minVertex = pq.poll();
			if(visited[minVertex.idx]) continue;
			
			System.out.println("poll : " + minVertex);
			
			res += minVertex.weight;
			visited[minVertex.idx] = true;
			
			if(++cnt == V) break;
			
			for (int i = 0; i < adjList.get(minVertex.idx).size(); i++) {
				Vertex next = adjList.get(minVertex.idx).get(i);
				if(!visited[next.idx] && next.weight < minEdge[next.idx]) {
					minEdge[next.idx] = next.weight;
					pq.offer(new Vertex(next.idx, minEdge[next.idx]));
				}
			}
		}
		
		System.out.println("ans : " + (cnt == V ? res : -1));
	}
}