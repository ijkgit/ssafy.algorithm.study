package day14_mst.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class SWEA하나로 {
	
	static class Vertex implements Comparable <Vertex>{
		int no;
		long weight;

		public Vertex(int no, long weight) {
			super();
			this.no = no;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Vertex [no=" + no + ", weight=" + weight + "]";
		}

		@Override
		public int compareTo(Vertex o) {
			
			return Double.compare(this.weight, o.weight);
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			long [][]map = new long[N][N];
			int[] x = new int[N];
			int[] y = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				y[i] = Integer.parseInt(st.nextToken());
			}
			double e = Double.parseDouble(br.readLine());
			
			for(int i =0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(i==j)continue;
					long dx = x[i] - x[j];
					long dy = y[i] - y[j];
					long weight = dx * dx + dy * dy;
					//System.out.println(weight);
					map[i][j] = weight;
					map[j][i] = weight;
				}
			}
			
			//print(map);
			long[] minEdge = new long[N]; 
			boolean[] visited = new boolean[N];
			PriorityQueue<Vertex> pq = new PriorityQueue<>();
			Arrays.fill(minEdge, Long.MAX_VALUE); //최소값 갱신을 위해 max로 초기화
			minEdge[0] = 0; // 임의의 시작점 0을 위해 처리
			pq.offer(new Vertex(0,minEdge[0]));
			double result = 0; // 최소신장트리 비용
			int c=0;
			
			while(!pq.isEmpty()) {
				//step 1 : 비트리 정점중 최소 간선비용의 정점 찾기 => 최적화 시 힙으로 대체 가능

//				
				Vertex minVertex = pq.poll();
				
//				
				if(visited[minVertex.no])continue;
				result += minVertex.weight; //간선 비용 누적
				visited[minVertex.no] = true; //트리 정점에 포함
				
				if(++c==N)break;
				
				//step 2 : 새롭게 트리 정점에 확장된 정점 기준으로 비트리 정점들과의 간선비용 고려해서 최적으로 업데이트
				for(int i=0; i<N; i++) {
					if(!visited[i] && map[minVertex.no][i] != 0 && 
							map[minVertex.no][i]<minEdge[i] ) {
						minEdge[i] = map[minVertex.no][i];
						pq.offer(new Vertex(i, minEdge[i]));
						
					}
				}
				
				
			}
			long res = Math.round(result*e);
			System.out.println("#"+tc+" "+res);
			
		}

		
	}
	private static void print(long[][] map) {
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		
	}
}
