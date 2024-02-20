import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static char[][][] arr;
	static boolean[][] visit;
	static int dxy [][] ={{-1,0},{0,1},{1,0},{0,-1}};
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		N = Integer.valueOf(tk.nextToken());
		arr = new char[2][N][N];
		for (int i = 0; i < N; i++) {
			tk = new StringTokenizer(br.readLine());
			String tmp = tk.nextToken();
			for (int j = 0; j < N; j++) {
				arr[0][i][j] = tmp.charAt(j);
				if(arr[0][i][j] == 'G') {
					arr[1][i][j] = 'R';
				}else {
					arr[1][i][j] =arr[0][i][j];
				}
			}
		}
		int a =0 , b=0;
		visit = new boolean[N][N];
		for (int i = 0; i <N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visit[i][j]) {
					visit[i][j] = true;
					dfs(i,j,0);
					a++;
				}
			}
		}
		visit = new boolean[N][N];
		for (int i = 0; i <N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visit[i][j]) {
					visit[i][j] = true;
					dfs(i,j , 1);
					b++;
				}
			}
		}
		System.out.println(a+" "+b);
	}

	private static void dfs(int x, int y , int isBlind) {
		// TODO Auto-generated method stub
		for (int d = 0; d <4; d++) {
			int dx = dxy[d][0] + x;
			int dy = dxy[d][1] + y;
			if(0<=dx && dx <N && 0<=dy && dy < N && !visit[dx][dy] &&arr[isBlind][dx][dy] == arr[isBlind][x][y]) {
				visit[dx][dy] = true;
				dfs(dx, dy, isBlind);
			}
		}
		
	}

}
