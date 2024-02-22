import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int N , arr[][];
	//0 가로 1 세로 2대각
	static int[][] dxy = {{0,1},{1,0},{1,1}};
	static int[][] cases = {{0,2},{1,2},{0,1,2}};
	static int ans =0;
	public static boolean check(int x, int y, int d) {
		int nx = x + dxy[d][0];
		int ny = y + dxy[d][1];
		if(d != 2) {
			if(0<=nx && nx <N && 0<=ny && ny <N && arr[nx][ny]==0) {
				return true;
			}else {
				return false;
			}
		}else {
			for (int t = 0; t <3; t++) {
				nx = x + dxy[t][0];
				ny = y + dxy[t][1];
				if(0<=nx && nx <N && 0<=ny && ny <N && arr[nx][ny]==0) {
				}else {
					return false;
				}
			}
			return true;
		}

	}
	public static void dfs(int x , int y, int d) {

		if(x == N-1 && y ==N-1){
			ans++;
			return;
		}
		
		for (int c = 0; c < cases[d].length; c++) {
			if(check(x,y,cases[d][c])) {
				int nx = x + dxy[cases[d][c]][0];
				int ny = y + dxy[cases[d][c]][1];
				dfs(nx,ny,cases[d][c]);
			}
		}
		

		
	}
	
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk  = new StringTokenizer(br.readLine());
		
		//입력
		N = Integer.valueOf(tk.nextToken());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			 tk  = new StringTokenizer(br.readLine());
			for (int j = 0; j <N; j++) {
				arr[i][j] = Integer.valueOf(tk.nextToken());
			}
		}
		dfs(0,1,0);
		System.out.println(ans);
		
		
		
		
		
		
	}
}