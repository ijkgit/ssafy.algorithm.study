



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main {

	static int arr[][], dist[][];
	static int visited[] ;
	static int N;
	
	public static void setDist() {
		//최단거리 테이블 갱신
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				//양방향이면
				if(arr[i][j] == 1 && arr[j][i] == 1) {
					dist[i][j] = 0;
					dist[j][i] = 0;
				// 단방향 이면
				}else if (arr[i][j] == 1 && arr[j][i] ==0){
					dist[i][j] = 0;
					dist[j][i] = 1;					
				}

			}
		}
		//노드를 거치는 경우 고려하여 갱신
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		
		
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tmp = br.readLine();
		StringTokenizer tk = new StringTokenizer(tmp);
		StringBuilder sb = new StringBuilder();
		
	
				
		N = Integer.valueOf(tk.nextToken());
		int M = Integer.valueOf(tk.nextToken());
		
		arr = new int[N][N];
		// arr[i][i] = 1 처리
		for (int i = 0; i < N; i++) {
			arr[i][i] = 1;
		}
		//dist inf 처리
		dist = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <N; j++) {
				dist[i][j] = 1000000000;
			}
			
		}

		// 길정보 입력
		for (int i = 0; i < M; i++) {
			tmp = br.readLine();
			tk = new StringTokenizer(tmp);
			int u = Integer.valueOf(tk.nextToken()) -1 ;
			int v = Integer.valueOf(tk.nextToken()) -1 ;
			int b = Integer.valueOf(tk.nextToken());
			if(b == 1) {
				arr[u][v] = 1;
				arr[v][u] = 1;
			}else {
				arr[u][v] = 1;
				arr[v][u] = 0;
			}
		}
		
		//  질문정보 입력 질문은 최대 30000개까지 가능하므로
		// 거리정보를 모두 갱신해 두어야 한다.
		setDist();
		tmp = br.readLine();
		tk = new StringTokenizer(tmp);
		int K = Integer.valueOf(tk.nextToken());

		for (int i = 0; i <K; i++) {
			tmp = br.readLine();
			tk = new StringTokenizer(tmp);
			int start = Integer.valueOf(tk.nextToken()) -1;
			int end =Integer.valueOf(tk.nextToken()) -1 ;
			sb.append(dist[start][end]).append("\n");
			
		}
			
		System.out.println(sb);

	}

}
