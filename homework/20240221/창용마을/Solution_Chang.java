import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Queue;
import java.util.StringTokenizer;


public class Solution {
	static int N ,M;
	static int arr[][];
	static boolean visit[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		int T = Integer.valueOf(tk.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			//입력 및 전처리
			tk = new StringTokenizer(br.readLine());
			N = Integer.valueOf(tk.nextToken());
			arr = new int[N+1][N+1];
			M = Integer.valueOf(tk.nextToken());
			for (int i = 0; i < M; i++) {
				tk = new StringTokenizer(br.readLine());
				int a = Integer.valueOf(tk.nextToken());
				int b = Integer.valueOf(tk.nextToken());
				arr[a][b] = 1;
				arr[b][a] = 1;
						
			}
			visit = new boolean[N+1];
			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				if(!visit[i]) {
					visit[i] = true;
					dfs(i);
					cnt++;
				}
			}
			System.out.println("#" + tc + " " +cnt);
			
		}
		


	}

	private static void dfs(int i) {
		for (int j = 1; j <= N; j++) {
			if(arr[i][j] == 1 && !visit[j]) {
				visit[j] = true;
				dfs(j);
			}	
		}
	}


}



