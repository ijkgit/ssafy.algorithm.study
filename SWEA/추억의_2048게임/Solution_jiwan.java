
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static int map[][];
	static int copyMap[][];
	static int T;
	static int N;
	static String order;
	
	static void move() {
		switch(order) {
		case "up":
			for (int j = 0; j < N; j++) {
				for (int i = 0; i < N - 1; i++) {
					if (map[i][j] == 0) continue;
					
					int k = i + 1;
					while (k < N - 1 && map[k][j] == 0) k++;
					
					if (map[i][j] == map[k][j]) {
						map[i][j] *= 2;
						map[k][j] = 0;
					}
				}
				int idx = 0;
				for (int i = 0; i < N; i++) {
					if (map[i][j] != 0) copyMap[idx++][j] = map[i][j];
				}
			}
			break;
		case "down":
			for (int j = 0; j < N; j++) {
				for (int i = N - 1; i > 0; i--) {
					if (map[i][j] == 0) continue;
					
					int k = i - 1;
					while (k > 0 && map[k][j] == 0) k--;
					
					if (map[i][j] == map[k][j]) {
						map[i][j] *= 2;
						map[k][j] = 0;
					}
				}
				int idx = N - 1;
				for (int i = N - 1; i >= 0; i--) {
					if (map[i][j] != 0) copyMap[idx--][j] = map[i][j];
				}
			}
			break;
		case "left":
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - 1; j++) {
					if (map[i][j] == 0) continue;
					
					int k = j + 1;
					while (k < N - 1 && map[i][k] == 0) k++;
					
					if (map[i][j] == map[i][k]) {
						map[i][j] *= 2;
						map[i][k] = 0;
					}
				}
				int idx = 0;
				for (int j = 0; j < N; j++) {
					if (map[i][j] != 0) copyMap[i][idx++] = map[i][j];
				}
			}
			break;
		case "right":
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j > 0; j--) {
					if (map[i][j] == 0) continue;
					
					int k = j - 1;
					while (k > 0 && map[i][k] == 0) k--;
					
					if (map[i][j] == map[i][k]) {
						map[i][j] *= 2;
						map[i][k] = 0;
					}
				}
				int idx = N - 1;
				for (int j = N - 1; j >= 0; j--) {
					if (map[i][j] != 0) copyMap[i][idx--] = map[i][j];
				}
			}
			break;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			order = st.nextToken();
			
			map = new int[N][N];
			copyMap = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			move();
			
			bw.write("#" + test_case);
			bw.newLine();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bw.write(copyMap[i][j] + " ");
				}
				bw.write('\n');
			}
		}
		bw.flush();
		
	}
}
