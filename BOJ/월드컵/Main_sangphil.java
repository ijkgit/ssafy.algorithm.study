import java.io.*;
import java.util.*;

public class Main_sangphil {
	static StringBuilder sb;
	static int[][] input = new int[4][18];
	static int[] arr = new int[18];
	static int ans;

	public static void main(String[] args) throws IOException {
		// System.setIn(Main_sangphil.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 18; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
        }
		
		for (int i = 0; i < 4; i++) {
			ans = 0;
			combi(0, 0, 0, i);
			System.out.print(ans + " ");
		}
	}
	
	static void combi(int i, int k, int depth, int c) {
		if (depth == 15) {
			ans = 1;
			for (int j = 0; j < input[c].length; j++) {
				if (input[c][j] != 0) {
					ans = 0;
					return;
				}
			}
			return;
		}
		if (k == 5) {
			return;
		}
		
		for (int j = k+1; j < 6; j++) {
			// i vs j - i 팀 승리
			if (input[c][3*i] > 0 && input[c][3*j+2] > 0) {					
				input[c][3*i]--;
				input[c][3*j+2]--;
				if (j == 5) {
					combi(i+1, i+1, depth+1, c);
				} else {					
					combi(i, j, depth+1, c);
				}
				input[c][3*i]++;
				input[c][3*j+2]++;
			}
			
			// 무승부
			if (input[c][3*i+1] > 0 && input[c][3*j+1] > 0) {
				input[c][3*i+1]--;
				input[c][3*j+1]--;
				if (j == 5) {
					combi(i+1, i+1, depth+1,c);
				} else {					
					combi(i, j, depth+1,c);
				}
				input[c][3*i+1]++;
				input[c][3*j+1]++;
			}
			// i vs j - j 팀 승리
			if (input[c][3*i+2] > 0 && input[c][3*j] > 0) {					
				input[c][3*i+2]--;
				input[c][3*j]--;
				if (j == 5) {
					combi(i+1, i+1, depth+1, c);
				} else {					
					combi(i, j, depth+1, c);
				}
				input[c][3*i+2]++;
				input[c][3*j]++;
			}
		}
	}
}