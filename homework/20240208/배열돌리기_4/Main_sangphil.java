import java.util.*;
import java.io.*;

/*
 * 		[BOJ 17406] 배열 돌리기 4
 * 			1. 돌릴 수 있는 연산의 모든 순열을 만들기
 * 			2. 만들어진 순열의 순서대로 시뮬레이션 돌리기
 * 			3. 최솟값 구하기
 */
public class Main_sangphil {
	static class Pair {int x; int y; int w; public Pair(int x, int y, int w) {this.x=x;this.y=y;this.w=w;} public Pair(int x, int y) {this.x=x;this.y=y;}}
	static final int[] dx = {0,1,0,-1};
	static final int[] dy = {1,0,-1,0};
	static int N, M, K, ans;
	static int[][] arr;
	static int[][] tmp;
	static Pair[] oper;
	static boolean[] visited;
	static int[] sel;

	public static void main (String[] args) throws IOException {
		// System.setIn(Main_ArraySpin_4.class.getResourceAsStream("input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		tmp = new int[N][M];
		ans = Integer.MAX_VALUE;
		oper = new Pair[K];
		visited = new boolean[K];
		sel = new int[K];
		ans = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            oper[i] = new Pair(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()));
        }

		permutation(0);

		System.out.println(ans);
	}

	static void permutation(int depth) {
		if (depth == K) {
			// 임시 배열 만들고
			makeTemp();
			// 순서대로 돌리고
			relocation();
			// 최소값 뽑고
			int min = extractMin();
			// 정답 갱신
			ans = Math.min(ans, min);
			return;
		}
		
		for (int i = 0; i < K; i++) {
			if (!visited[i]) {
				visited[i] = true;
				sel[depth] = i;
				permutation(depth + 1);
				visited[i] = false;
			}
		}
	}
	
	static int extractMin() {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j < M; j++) {
				sum += tmp[i][j];
			}
			min = Math.min(min, sum);
		}
		return min;
	}

	static void spin(int x, int y, int r) {
		int nx = x-r;
		int ny = y-r;
		int a = tmp[nx][ny];
		int b = 0;
		int i = 0;
		int d = 0;
		for (i = 0; i < r*2; i++) {
			if (d == 4) break;
			nx += dx[d];
			ny += dy[d];
			b = tmp[nx][ny];
			tmp[nx][ny] = a;
			a = b;
			if (i == r*2-1) {
				i = -1;
				d++;
			}
		}
	}

	static void relocation () {
		for (int ind : sel) {
			Pair p = oper[ind];
			for (int r = 1; r <= p.w; r++) {
				spin(p.x, p.y, r);
			}
		}
	}

	static void makeTemp() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmp[i][j] = arr[i][j];
			}
		}
	}	
}