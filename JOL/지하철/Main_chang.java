import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Main {
	static int N, M;
	static int arr[][];
	static boolean visited[];
	static int time = Integer.MAX_VALUE;
	static String ans;
	public static void search(int idx , int sum , String str) {

		if(idx == M) {
			if(time > sum) {
				time = sum;
				ans = str;
			}
			return;
		}
		if(time < sum) {
			return;
		}

		for (int i = 1; i < N+1; i++) {
			if(i != idx && arr[idx][i] > 0 && !visited[i]) {
				visited[i] = true;
				search(i,sum + arr[idx][i], str + i+" ");
				visited[i] = false;
				
			}
			
		}
		
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
		StringTokenizer tk = new StringTokenizer(br.readLine());
		
		N = Integer.valueOf(tk.nextToken());
		M = Integer.valueOf(tk.nextToken());
		arr = new int[N+1][N+1];
		visited = new boolean[N+1];
		for (int i = 1; i <= N; i++) {
			 tk = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.valueOf(tk.nextToken());
			}
		}
		visited[1] = true;
		
		search(1,0,"1 ");
		System.out.println(time);
		System.out.println(ans);
		

	}

}
