package edu.ssafy.mst.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PrimByAdjList {
	static class Vertex implements Comparable<Vertex> {
		int idx, weight;
		
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
		int[] minWeight = new int[V];
		Arrays.fill(minWeight, Integer.MAX_VALUE);
		minWeight[0] = 0;
		int res = 0, cnt = 0;
		for (cnt = 0; cnt < V; cnt ++) {
			int min = Integer.MAX_VALUE;
			int minVertex = -1;
			
			for (int i = 0; i < V; i++) {
				if (!visited[i] && minWeight[i] < min) {
					min = minWeight[i];
					minVertex = i;
				}
			}
			
			if (minVertex == -1) break;
			res += min;
			visited[minVertex] = true;
			
			for (int i = 0; i < adjList.get(minVertex).size(); i++) {
				Vertex cur = adjList.get(minVertex).get(i);
				if (!visited[cur.idx] && cur.weight < minWeight[cur.idx]) {
					System.out.println("update : " + cur);
					minWeight[cur.idx] = cur.weight;
				}
			}
		}
		System.out.println("ans : " + (cnt == V ? res : -1));
	}
}
