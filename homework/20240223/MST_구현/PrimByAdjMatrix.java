package edu.ssafy.mst.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PrimByAdjMatrix {
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
		int[] minWeight = new int[V];
		Arrays.fill(minWeight, Integer.MAX_VALUE);
		minWeight[0] = 0;
		int res = 0, cnt = 0;
		for (cnt = 0; cnt < V; cnt++) {
			int min = Integer.MAX_VALUE;
			int minVertex = -1;
			
			for (int i = 0; i < V; i++) {
				if(!visited[i] && minWeight[i] < min) {
					min = minWeight[i];
					minVertex = i;
				}
			}
			
			if (minVertex == -1) break;
			res += min;
			visited[minVertex] = true;
			
			for (int i = 0; i < V; i++) {
				if (!visited[i] && adjMatrix[minVertex][i] != 0 && adjMatrix[minVertex][i] < minWeight[i]) {
					System.out.println("update : idx - " + i + " weight - " + adjMatrix[minVertex][i]);
					minWeight[i] = adjMatrix[minVertex][i];
				}
			}
		}
		
		System.out.println("ans : " + (cnt == V ? res : -1));
	}
}
