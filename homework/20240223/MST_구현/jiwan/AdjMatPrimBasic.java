package day14_mst.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
public class AdjMatPrimBasic {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int[][] map = new int[V][V];
		int[] minEdge = new int[V]; 
		boolean[] visited = new boolean[V];
		/*--------------------------Start of input-----------------------------*/
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			map[n2][n1] = map[n1][n2] = weight;
		}
		/*--------------------------End of input-----------------------------*/
		
		/*--------------------------Start of Prim------------------------------*/
		Arrays.fill(minEdge, Integer.MAX_VALUE); //최소값 갱신을 위해 max로 초기화
		minEdge[0] = 0; //임의의 시작점을 0으로 하겠다.
		int result = 0; // 최소신장트리 비용
		int c;
		System.out.print("방문 :");
		for( c = 0; c<V; c++) {
			//step 1 : 비트리 정점중 최소 간선비용의 정점 찾기 => 최적화 시 힙으로 대체 가능
			int min = Integer.MAX_VALUE;
			int minVertex = -1;
			for(int i=0; i<V; i++) {
				if(!visited[i] && minEdge[i]<min) {
					min = minEdge[i];
					minVertex = i;
				}
			}
			
			if(minVertex == -1)break;
			result += min; //간선 비용 누적
			visited[minVertex] = true; //트리 정점에 포함
			System.out.print(minVertex+" ");
			
			//step 2 : 새롭게 트리 정점에 확장된 정점 기준으로 비트리 정점들과의 간선비용 고려해서 최적으로 업데이트
			for(int i=0; i<V; i++) {
				if(!visited[i] && map[minVertex][i] != 0 && map[minVertex][i]<minEdge[i] ) {
					minEdge[i] = map[minVertex][i];
				}
			}
			
		}
		/*--------------------------End of Prim------------------------------*/
		System.out.println(c==V?result:-1);
		
	}
}
