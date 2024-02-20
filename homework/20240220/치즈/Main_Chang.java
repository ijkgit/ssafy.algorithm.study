import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static boolean[][] visit;
	static int[][] arr;
	static ArrayList<Point> melted;
 	static int dxy [][] ={{-1,0},{0,1},{1,0},{0,-1}};
	static int N,M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		N = Integer.valueOf(tk.nextToken());
		M = Integer.valueOf(tk.nextToken());
		arr = new int[N][M];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			tk = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] =  Integer.valueOf(tk.nextToken());
				if(arr[i][j] == 1) {
					cnt++;
				}
			}
		}
		int time = 0;
		while(true) {
			visit = new boolean[N][M];
			melted = new ArrayList<>();
			findMeltingCheeze(0,0);
			for (Point p :melted) {
				arr[p.x][p.y] = 0;
			}
			
			time++;
			if(cnt - melted.size() == 0 ) {
				break;
			}else {
				cnt = cnt - melted.size();
			}
		}
		System.out.println(time);
		System.out.println(cnt);
	}
	private static void findMeltingCheeze(int x, int y) {
		for (int d = 0; d < 4; d++) {
			int dx = dxy[d][0] + x;
			int dy = dxy[d][1] + y;
			if(0<=dx && dx < N && 0<= dy && dy <M && !visit[dx][dy]) {
				visit[dx][dy] = true;
				if(arr[dx][dy] == 1) {
					melted.add(new Point(dx,dy));
				}else {
					findMeltingCheeze(dx, dy);
				}
			}
		}
		
	}



}
