package problem_solving;
import java.util.*;
import java.io.*;

/*
 * 		[BOJ 1992] 쿼드트리
 * 			1. 재귀를 활용한 영상 분할
 * 				- 재귀 들어가는 순서대로 괄호 추가
 * 				- 같으면 그냥 sb 에 삽입 // 다르면 괄호 추가
 */
public class Main_sangphil {
	static StringBuilder sb = new StringBuilder();
	static int[][] arr;
	static int n;
	
	public static void main (String[] args) throws IOException {
		// System.setIn(Main.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < n; j++) {
				arr[i][j] = Character.getNumericValue(line.charAt(j));
			}
		}
		
		shoot(0, 0, n);
		
		System.out.println(sb);
	}
	
	static void shoot (int x, int y, int w) {
		int tmp = check(x, y, w);
		if (tmp != 2) {
			sb.append(tmp);
			return;
		}
		sb.append("(");
		w /= 2;
		shoot(x, y, w);
		shoot(x, y+w, w);
		shoot(x+w, y, w);
		shoot(x+w, y+w, w);
		sb.append(")");
	}
	
	static int check (int x, int y, int w) {
		int res = arr[x][y];
		for (int i = x; i < x+w; i++) {
			for (int j = y; j < y+w; j++) {
				if (arr[i][j] != res) {
					return 2;
				}
			}
		}
		return res;
	}
}