import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int board[][];
	static List<Point> list;
	static int dxy[][] = {{0,1},{0,-1},{1,0},{-1,0}};
	static int maxCnt, minLine;
	
	// x, y에서 연결이 가능여부
	public static boolean isPosible(int x, int y, int d) {
		int dx= x;
		int dy = y;
		while(true) {
			dx = dx + dxy[d][0];
			dy = dy + dxy[d][1];
			if(0<=dx && dx<N && 0<= dy && dy<N && board[dx][dy] == 0) {
				continue;
			}else {
				break;
			}
		}
		
		if(dx == -1 || dx ==N || dy ==-1 || dy ==N) {
			return true;
		}else {
			return false;
		}

	}
	
	//전선연결 반환값은 전선개수
	public static int connecting(int x, int y, int d) {
		int dx= x;
		int dy = y;
		int cnt = 0;
		while(true) {
			dx = dx + dxy[d][0];
			dy = dy + dxy[d][1];
			if(0<=dx && dx<N && 0<= dy && dy<N && board[dx][dy] == 0) {
				cnt++;
				board[dx][dy] = -1;
			}else {
				break;
			}
		}
		
		
		return cnt;

	}
	//전선 철수
	public static void unconnecting(int x, int y, int d) {
		int dx= x;
		int dy = y;
		while(true) {
			dx = dx + dxy[d][0];
			dy = dy + dxy[d][1];
			if(0<=dx && dx<N && 0<= dy && dy<N && board[dx][dy] !=1) {
				board[dx][dy] = 0;
			}else {
				break;
			}
		}
	}
	public static void dfs(int idx , int cnt, int sum) {
		if(idx == list.size()) {
			if(cnt > maxCnt) {
				maxCnt = cnt;
				minLine = sum;
			}else if (cnt == maxCnt) {
				minLine = Math.min(sum, minLine);
			}
			return;		
		}
		
		Point p = list.get(idx);
		int x = p.x;
		int y = p.y;
		for (int d = 0; d < 4; d++) {
			//전선 연결이 가능한 경우만
			if (isPosible(x, y, d)) {
				int len = connecting(x, y, d);
				dfs(idx+1, cnt+1 , sum + len);
				unconnecting(x, y, d);
				
			//연결이 불가능할 경우
			}else {
				dfs(idx+1, cnt, sum);
			}
			
		}
	}

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tk= new StringTokenizer(br.readLine());
        int T = Integer.valueOf(tk.nextToken());

        for (int tc = 1; tc <= T; tc++) {
        	tk= new StringTokenizer(br.readLine());
        	N= Integer.valueOf(tk.nextToken());
        	board = new int[N][N];
        	list = new ArrayList<>();
        	for (int i = 0; i < N; i++) {
        		tk= new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int tmp = Integer.valueOf(tk.nextToken());	
					board[i][j] = tmp;
					//이미 연결된 코어를 제외함
					if(tmp == 1 && 1<= i && i < N-1 && 1 <= j && j < N-1) {
						list.add(new Point(i,j));
					}
				}
			}
        	
        	maxCnt = 0;
        	minLine = Integer.MAX_VALUE;
        	dfs(0,0,0);
        	System.out.println("#"+tc + " " + minLine);
        	
        	
        }
    }
}

