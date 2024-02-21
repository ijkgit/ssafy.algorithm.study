import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static boolean[][] visit;
	static int[][] arr;
	static int[][] cp;

 	static int dxy [][] ={{-1,0},{0,1},{1,0},{0,-1},};
 	static int cctvOrder[][][] = {
 			{{}},
 			//1번
 			{{0},{1},{2},{3}},
 			//2번
 			{{0,2},{1,3}},
 			//3번
 			{{0,1},{1,2},{2,3},{3,0}},
 			//4번
 			{{0,1,2},{1,2,3},{2,3,0},{3,0,1}},
 			//5번
 			{{0,1,2,3}}
 			
 	};
	static int N,M, zCnt;
	static ArrayList<Point> cctvs = new ArrayList<>();
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		N = Integer.valueOf(tk.nextToken());
		M = Integer.valueOf(tk.nextToken());
		arr = new int[N][M];
		cp = new int[N][M];
		for (int i = 0; i < N; i++) {
			 tk = new StringTokenizer(br.readLine());
			 for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.valueOf(tk.nextToken());
				if(arr[i][j] == 0 ) {
					zCnt++;
				}else if(arr[i][j]>0 && arr[i][j]<6) {
					cctvs.add(new Point(i,j));
				}
			}
		}
		makeCase(0,"");
		System.out.println(ans);

	}

	private static void makeCase(int idx, String str) {
		if(idx == cctvs.size()) {
			//cctv별 방향을 정하고 탐색
			watch(str);
			
			return;
		}
		
		Point p = cctvs.get(idx);
		for (int i = 0; i < cctvOrder[arr[p.x][p.y]].length ; i++) {
			makeCase(idx+1, str+i);
		}
		
	}

	private static void watch(String str) {
		// TODO Auto-generated method stub
		makeCopy();
		int cnt = zCnt;
		for (int i = 0 ; i < str.length() ; i++) {
			Point p = cctvs.get(i);
			int cctvNum = cp[p.x][p.y];
			int order = Integer.valueOf(str.charAt(i) -'0');
			
			for (int d :cctvOrder[cctvNum][order]) {
				int nx = p.x;
				int ny = p.y;
				while(true) {
					nx += dxy[d][0];
					ny += dxy[d][1];
					if(0<=nx && nx <N && 0<=ny && ny <M) {
						if(cp[nx][ny] ==0) {
							cp[nx][ny] = -1;
							cnt--;
						}else if(cp[nx][ny] == 6){
							break;
						}else {
							continue;
						}
					}else {
						break;
					}
					
				}

			}
		
		}
		

		ans = Math.min(cnt, ans);
		
		
		
	}


	

	private static void makeCopy() {
		// TODO Auto-generated method stub
		for (int i = 0; i <N; i++) {
			cp[i] = arr[i].clone();
		}
	}




}
