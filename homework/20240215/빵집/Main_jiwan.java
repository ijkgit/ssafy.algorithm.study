package day9_backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 빵집 {
	static int R;
	static int C;
	static int[] dr = {-1,0,1};
	static int[] dc = {1,1,1};
	static char[][] map;
	static boolean ans ;
	static int cnt ;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for(int i=0;i<R; i++) {
			if(recursive(i,0)) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
	private static boolean recursive(int r, int c) {
		//basis part
		if(c == C-1) {	
			return true;
		}
		//inductive part 
		//backtracking은 inductive part제어
		for(int i=0; i<3; i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			if(check(nr,nc)) /*조건 : 맵밖으로 나가면 안되고, */
			{
				map[nr][nc] = 'x';
				if(recursive(nr, nc))return true;
			}
		}
		return false;
	}
	private static boolean check(int nr, int nc) {
		if(nr>=0 && nr<R && nc<C && map[nr][nc]!='x')return true;
		
		return false;
	}

}
