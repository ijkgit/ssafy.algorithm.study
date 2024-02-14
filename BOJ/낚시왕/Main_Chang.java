import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;


class Shark implements Comparable<Shark>{
	// rc 위치
	int r;
	int c;
	//속력
	int s;
	//방향
	int d;
	//인덱스
	int z;
	int realsize;
	public Shark(int r, int c, int s, int d, int z, int realsize) {
		super();
		this.r = r;
		this.c = c;
		this.s = s;
		this.d = d;
		this.z = z;
		this.realsize = realsize;
	}
	@Override
	public int compareTo(Shark o) {
		
		return this.z - o.z;
	}
	
}

public class Main {
	static int R,C,M;
	//위 오 아래 왼
	static int dxy[][] = {{-1,0},{0,1},{1,0},{0,-1}};
	static int changeD[] = {0,0,2,1,3};
	static ArrayList<Shark> sharks ;
	static int board[][] ;
	static boolean deadSharks[];
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		R = Integer.valueOf(tk.nextToken());
		C =  Integer.valueOf(tk.nextToken());
		M = Integer.valueOf(tk.nextToken());
		sharks = new ArrayList<>();
		board = new int[R][C];
		deadSharks = new boolean[M+1];
		//0인덱스를 채울 상어
		sharks.add(new Shark(0,0,0,0,0,0));
		//전처리 및 입력
		for (int m = 0; m < M; m++) {
			tk = new StringTokenizer(br.readLine());
			int r= Integer.valueOf(tk.nextToken()) -1;
			int c = Integer.valueOf(tk.nextToken()) -1;
		
			int s= Integer.valueOf(tk.nextToken());
			int d= changeD[Integer.valueOf(tk.nextToken())] ;
			int z= Integer.valueOf(tk.nextToken());


			sharks.add(new Shark(r,c,s,d,z,z));
		}
		// 크기를 인덱스로 가지는 sharks list 생성
		Collections.sort(sharks);
		for (int i = 1; i < sharks.size(); i++) {
			sharks.get(i).z = i;
			board[sharks.get(i).r][sharks.get(i).c] = i;
		}

		deadSharks[0] = true;
		int sum= 0;
		for (int i = 0; i < C; i++) {
			sum += catchShark(i);
			moveShark();

			
		}
		System.out.println(sum);
		
	}
	
	public static int catchShark(int n) {
		for (int i = 0; i < R; i++) {
			if(board[i][n]>0) {
				int tmp = board[i][n];
				board[i][n] = 0;
				deadSharks[tmp] = true;
				return sharks.get(tmp).realsize;
			}
		}
		return 0;
	}
	
	public static void moveShark() {
		board = new int[R][C];
		// 큰상어 먼저 이동
		for (int i = M; i >= 1; i--) {
			if(!deadSharks[i]) {
				//편의를 위한 s 선언
				//위 오 아래 왼
				Shark s =sharks.get(i);
				int dx = s.r + ( s.s * dxy[s.d][0]);
				int dy = s.c + ( s.s * dxy[s.d][1]);
				int cnt = 0;
				if(0>dx || dx>=R) {
					
					while(!(0<=dx && dx<R)) {
						cnt++;
						if(dx>=R) {
							dx =dx -(R-1);
						}else {
							dx =dx +(R-1);
						}
						s.d =(s.d +2) %4;
					}
					if(cnt%2 ==1) {
						dx= (R-1) - Math.abs(dx);
					}
					
						
					
					
				}
				if(0>dy || dy>=C) {
					
					while(!(0<=dy && dy<C)) {
						cnt++;
						if(dy>=C) {
							dy =dy -(C-1);
						}else {
							dy =dy +(C-1);
						}
						s.d =(s.d +2) %4;
					}
					if(cnt%2 ==1) {
						dy = (C-1) - Math.abs(dy);
					}
					
					
					
				}
				if(board[dx][dy] >0) {
					deadSharks[i] = true;
				}else {
					board[dx][dy] = s.z;
					sharks.get(i).r =dx;
					sharks.get(i).c =dy;
					sharks.get(i).d =s.d;
					
				}

				
				
				
 
			}
		}
	}

}







