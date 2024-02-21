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
	static int N ;

	static StringBuilder sb = new StringBuilder();
	static int arr[][];
	static int nowColor[];
	static int oriColor[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		
		
		int T = Integer.valueOf(tk.nextToken());
		
		
		nextTc:
		for (int tc = 1; tc <= T; tc++) {
			//입력 및 전처리
			tk = new StringTokenizer(br.readLine());
			N = Integer.valueOf(tk.nextToken());
			tk = new StringTokenizer(br.readLine());
			nowColor = new int[N];
			oriColor = new int[N];
			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				nowColor[i] = Integer.valueOf(tk.nextToken());
				oriColor[i] = nowColor[i];
			}
			for (int i = 0; i < N; i++) {
				tk = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.valueOf(tk.nextToken());
				}
			}
			for (int i = 0; i < N+1; i++) {
				if(colorChange(i)) {
					sb.append("#").append(tc).append(" ").append(i).append("\n");
					continue nextTc;
				}
			}
			
		}
		
		System.out.println(sb);

	}
	private static boolean colorChange(int i) {
		return makeCase(i,0,0);
	}

	private static boolean makeCase(int target , int idx , int cnt) {

		if(target == cnt) {
			if(isPosible()) {
				return true;
			}else {
				return false;
			}
		}
		if(idx == N) {
			return false;
		}
		
		//안바꾸고
		if(makeCase(target, idx+1, cnt)) {
			return true;
		}
		//바꿀떄
		for (int i = 1; i <= 4; i++) {
			if(oriColor[idx] != i) {
				nowColor[idx] = i;
				if(makeCase(target, idx+1, cnt+1)) {
					return true;
				}
				nowColor[idx] = oriColor[idx];
			}
		}
		return false;
		
		
	}
	private static boolean isPosible() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(arr[i][j] ==1) {
					if(nowColor[i] == nowColor[j]) {
						return false;
					}
				}
			}
		}
		return true;
		
	}


}



