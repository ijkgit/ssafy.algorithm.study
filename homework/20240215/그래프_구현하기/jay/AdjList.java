package edu.ssafy.graph.homework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class AdjList {
	static class Node {
		int to;
		Node next;
		int weight;
		public Node(int to, Node next, int weight) {
			super();
			this.to = to;
			this.next = next;
			this.weight = weight;
		}
		@Override
		public String toString() {
			return "Node [to=" + to + ", next=" + next + ", weight=" + weight + "]";
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st =  new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		Node[] adjList = new Node[V];
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[from] = new Node(to, adjList[from], weight);
		}
		
		for (Node n : adjList) {
			sb.append(n).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
