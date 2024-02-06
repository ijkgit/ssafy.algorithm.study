import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	int[][] graph;
	int ans = Integer.MAX_VALUE;
	int[] sel;

	public static void main(String[] args) throws NumberFormatException, IOException {
		new Main().sol();
	}

	private void sol() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());

		graph = new int[n][n];
		StringTokenizer st;
		for (int i = 0; i < graph.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < graph.length; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		sel = new int[n];
		dfs(1, 0, 0);

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

  /*
  문제 포인트
  1. 가지치기 하지 않으면 시간초과
  2. 비용이 0인 경우 방문 불가능 검증하기
  3. 2번의 연장 : 배달 후 회사로 돌아올 때 방문 불가능 한 경우 검증하기
  */
	private void dfs(int k, int visited, int sum) {
		if (sum > ans) return; // 이미 ans 보다 큰 경우 탐색 필요 없음
		
		if (k == graph.length) {
			if (graph[sel[k-1]][0] == 0) { // 마지막 방문지점에서 회사로 돌아올 수 없을 경우 확인
				return;
			}
			else {
				sum += graph[sel[k-1]][0];
			}
			ans = Math.min(ans, sum);
			return;
		}
	
		for (int i = 1; i < graph.length; i++) {
			if ((visited & (1 << i)) == 0 && graph[sel[k-1]][i] != 0) { // 방문여부 확인 및 현재 위치에서 다음 위치로 방문 가능한지 여부 확인
				sel[k] = i;
				sum += graph[sel[k-1]][sel[k]]; 
				dfs(k + 1, visited | (1 << i), sum);
				sum -= graph[sel[k-1]][sel[k]];
			}
		}
	}
}
