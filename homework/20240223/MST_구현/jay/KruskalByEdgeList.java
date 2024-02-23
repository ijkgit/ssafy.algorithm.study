package edu.ssafy.mst.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KruskalByEdgeList {
	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		Edge[] edgeList = new Edge[E];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		}
		
		Arrays.sort(edgeList);
		for(Edge edge: edgeList) System.out.println(edge);
		
		int[] set = makeSet(V);
		
		int cnt = 0, weight = 0;
		for(Edge edge : edgeList) {
			if(!unionSet(edge.from, edge.to, set)) continue;
			System.out.println("add : " + edge);
			weight += edge.weight;
			if(++cnt == V-1) break;
		}
		
		System.out.println("ans : " + weight);
	}
	private static int[] makeSet(int V) {
		int[] set = new int[V];
		for (int i = 0; i < set.length; i++) {
			set[i] = i;
		}
		return set;
	}
	
	private static int findSet(int a, int[] set) {
		if (a == set[a]) return a;
		return set[a] = findSet(set[a], set);
	}
	
	private static boolean unionSet(int a, int b, int[] set) {
		int c = findSet(a, set);
		int d = findSet(b, set);
		if (c == d) return false;
		
		set[b] = a;
		return true;
	}
}
