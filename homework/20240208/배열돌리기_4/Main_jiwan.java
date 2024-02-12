
package BOJ;

import java.util.*;
import java.io.*;

public class 배열돌리기4 {

	static int N;
	static int M;
	static int K;
	static int[][] arr;
	static boolean[] visited;
	static int[][] orders;
	static int[] result;
	//static ArrayList<int[]> permutations = new ArrayList<>();
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		orders = new int[K][3]; // 번째 order는 , rcs 순서
		visited = new boolean[K];
		result = new int[K];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			orders[i][0] = r;
			orders[i][1] = c;
			orders[i][2] = s;
		}
		permutation(0);
		//rotate();
		
		System.out.println(min);

	}

	private static void permutation(int idx) {
		// basis
		if (idx == K) {
			// System.out.println(Arrays.toString(result));
			 rotate(result);
			//permutations.add(result);
			return;
		}

		// inductive
		for (int i = 0; i < K; i++) {
			if (visited[i])
				continue;
			// 선택하지 않은경우, 선택했다는 표시를 해준다.
			visited[i] = true;
			// 숫자를 담는다.
			result[idx] = i;
			// 자신을 재귀 호출한다.
			permutation(idx + 1);
			// 선택을 해제한다. => backtracking
			visited[i] = false;

		}

	}

	static void rotate(int[] permuts) {
		int[][] tmp = deepCopy();
//		System.out.println(permutations.size());
//		System.out.println(Arrays.toString(permutations.get(0)));
//		System.out.println(Arrays.toString(permutations.get(1)));
		//.get(1);
		for (int i = 0; i < permuts.length; i++) {
			int r = orders[permuts[i]][0];
			int c = orders[permuts[i]][1];
			int S = orders[permuts[i]][2];
			//System.out.println(r+" "+c+" "+S);
			for (int s = 1; s <= S; s++) {
				// 위
				int upTmp = tmp[r - s][c + s];
				for (int y = c + s; y > c - s; y--) {
					tmp[r - s][y] = tmp[r - s][y - 1];
				}
				// 오른쪽
				int rightTmp = tmp[r + s][c + s];
				for (int x = r + s; x > r - s; x--) {
					tmp[x][c + s] = tmp[x - 1][c + s];
				}
				tmp[r - s + 1][c + s] = upTmp;
				// 아래
				int leftTmp = tmp[r + s][c - s];
				for (int y = c - s; y < c + s; y++) {
					tmp[r + s][y] = tmp[r + s][y + 1];
				}
				tmp[r + s][c + s - 1] = rightTmp;
				// 왼쪽
				for (int x = r - s; x < r + s; x++) {
					tmp[x][c - s] = tmp[x + 1][c - s];
				}
				tmp[r + s - 1][c - s] = leftTmp;
				//print(tmp);
			}
		}
		
		
		//
		getArrSum(tmp);
	}

	

	private static void getArrSum(int [][] tmp) {
		for(int i=0; i<tmp.length; i++) 
		{
			int partialSum =0;
			for(int j=0; j<tmp[i].length; j++) {
				partialSum += tmp[i][j];
			}
			if(min>partialSum) min = partialSum;
		}
	}

	public static int[][] deepCopy() {
		int[][] tmp = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmp[i][j] = arr[i][j];
			}
		}

		return tmp;
	}
	static void print(int [][] m) {
		System.out.println();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(m[i][j]+" ");
			}
			System.out.println();
		}
	}
}
