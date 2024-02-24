package day14_mst.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;



/*
V E
from to weigh 의 순서

7 11
0 1 31
0 2 31
0 6 31
0 5 60
1 2 21
2 4 46
2 6 25
3 4 34
4 6 51
5 3 18
5 4 40 
*/
public class EdgeListKruskal {
	static class Edge implements Comparable<Edge>{
		int v1,v2,weight;
		Edge(int v1, int v2, int weight){
			this.v1 =v1;
			this.v2 =v2;
			this.weight =weight;
		}
		@Override
		public String toString() {
			return "Edge [v1=" + v1 + ", v2=" + v2 + ", weight=" + weight + "]";
		}
		@Override
		public int compareTo(Edge o) {
			
			return Integer.compare(this.weight, o.weight);
		}
		
		
	}

	static int V;
	static int E;
	static int parents[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V= Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		parents= new int[V];
		ArrayList<Edge> edges = new ArrayList<>();
//		for(int i=0; i<V; i++) {
//			edges[i] = new ArrayList<Edge>();
//		}

		
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edges.add(new Edge(n1,n2,weight));
		}
		
		Collections.sort(edges);
		//System.out.println(edges);
		make();
		long weight = 0;
		int cnt = 0 ;
		for(Edge e : edges) {
			if(!union(e.v1, e.v2))continue; //사이클 발생
			weight += e.weight;
			if(++cnt == V-1) break; //최소신장트리완성
		}
		System.out.println(weight);
		
	}


	private static void make() {
		for(int i=0; i<V; i++) {
			parents[i] = i;
		}
		
	}
	static int find(int a) {
		
		if(a == parents[a]) { //자신의 대표자가 자신이면 =>자신이 루트
			 return a;
		}
		return parents[a] = find(parents[a]); // path compression 경로 압축 
		//우리 트리의 루트를 찾아서 움직이고 저장해
	}
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) {
			return false; //같은 뿌리라 유니온 x a,b가 같은 트리에 속해있다. => union이 불필요
		}
		
		parents[bRoot] = aRoot; 
		return true;
	}
}
