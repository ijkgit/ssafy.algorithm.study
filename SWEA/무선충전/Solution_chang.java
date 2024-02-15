import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
class Pair{
	int x;
	int y;
	int cnt;
	public Pair(int x, int y, int cnt) {
		super();
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
	
}



public class Solution {
	static int M,W;
	static int[][][] arr;
	//상 하 좌 우
	static int[][] dxy = {{0,0},{-1,0},{0,1},{1,0},{0,-1}};
	static boolean visit[][];
	static int ans ;
	static Queue<Pair> q;


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tk= new StringTokenizer(br.readLine());
        int T = Integer.valueOf(tk.nextToken());
        
        for (int tc = 1; tc <= T; tc++) {
        	tk= new StringTokenizer(br.readLine());
        	M = Integer.valueOf(tk.nextToken());
        	W = Integer.valueOf(tk.nextToken());
        	int moves[][] = new int[2][M] ;
        	
        	for (int i = 0; i < 2; i++) {
        		tk= new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					moves[i][j] = Integer.valueOf(tk.nextToken());
				}
			}
        	//3차원 [기기][10][10];
        	arr = new int[W+1][10][10];
        	//배열에 충전 입력
        	for (int i = 1; i <=W; i++) {
        		tk= new StringTokenizer(br.readLine());
        		int y = Integer.valueOf(tk.nextToken()) -1 ;
        		int x = Integer.valueOf(tk.nextToken()) -1 ;
        		int c = Integer.valueOf(tk.nextToken()); //사거리
        		int p =Integer.valueOf(tk.nextToken());  //충전량
        		arr[i][x][y] = p; 
//        		System.out.println(Arrays.deepToString(arr[i]));
        		q = new LinkedList<Pair>();
        		q.add(new Pair(x,y,0));
				while(!q.isEmpty()) {
					Pair tmp = q.poll();
					if(tmp.cnt == c) {
						q.clear();
						break;
					}
					for (int d = 1; d < 5; d++) {
						int dx = tmp.x + dxy[d][0];
						int dy = tmp.y + dxy[d][1];
						if(0<=dx && dx <10 && 0<= dy && dy<10 && arr[i][dx][dy] ==0 ) {
							arr[i][dx][dy] = p;
							q.add(new Pair(dx,dy, tmp.cnt +1));
						}
					}
				}
				
			}
        	
        	int sum = 0;
        	//최대값을 구해서 넣고
        	Pair A = new Pair(0,0,0);
        	Pair B = new Pair(9,9,0);
        	int max=0;
			for (int w1 = 0;  w1<= W; w1++) {
				for(int w2 = 0; w2<=W; w2++) {
					if(w1 == w2) {
						if(arr[w1][A.x][A.y]>0 && arr[w2][B.x][B.y] >0) {
							max =Math.max(arr[w1][A.x][A.y], max);
						}
					}else {
						max =Math.max(arr[w1][A.x][A.y]+ arr[w2][B.x][B.y], max);
					}
				}
			}
//			System.out.println(max);
			sum += max;
        	//시간만큼 반복한다
        	
        	for (int m = 0; m < M; m++) {
				A.x = dxy[moves[0][m]][0] + A.x;
				A.y = dxy[moves[0][m]][1] + A.y;
				B.x = dxy[moves[1][m]][0] + B.x;
				B.y = dxy[moves[1][m]][1] + B.y;
				max = 0;
//				System.out.println(B.x +" " +B.y);
				for (int w1 = 0;  w1<= W; w1++) {
					for(int w2 = 0; w2<=W; w2++) {
//						System.out.println("b값 : " + arr[w2][B.x][B.y]);
						if(w1 == w2) {
							if(arr[w1][A.x][A.y]>0 && arr[w2][B.x][B.y] >0) {
								max =Math.max(arr[w1][A.x][A.y], max);
							}
						}else {
							max =Math.max(arr[w1][A.x][A.y]+ arr[w2][B.x][B.y], max);
						}
					}
				}
				
//				System.out.println(max);
				sum += max;
			}


			System.out.println("#" + tc + " " +sum);

		}
 
    }


}

