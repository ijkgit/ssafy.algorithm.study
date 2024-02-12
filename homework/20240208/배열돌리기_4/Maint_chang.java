import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;
import java.util.StringTokenizer;

class Order{
	int r;
	int c;
	int s;
	public Order(int r, int c, int s) {
		super();
		this.r = r;
		this.c = c;
		this.s = s;
	}
	
}


public class Main {
	static int N,M,K;
	static int arr[][] , copy[][];
	static ArrayList<Order> orders = new ArrayList<Order>();
	static boolean visitOrder[] ;
	//  우하좌상
	static int dxy[][] = {{0,1},{1,0},{0,-1},{-1,0}};
	static int ans = Integer.MAX_VALUE;
	
	// per 연산 결과값 순서로 배열돌리기 + 배열 최솟값 리턴
	public static int rotation(String seq[]) {
		resetCopy();
		
		//돌리기
		for(String 변수이름뭐로하지 : seq) {
			int p = Integer.valueOf(변수이름뭐로하지);
			Order order =orders.get(p);
			int r = order.r;
			int c = order.c;
			int s = order.s;
			while(true) {
				int x = r-s;
				int y = c-s;
				int tmp = copy[x][y];
				int d = 0;
				while(true) {
					int dx = x+dxy[d][0];
					int dy = y+dxy[d][1];
					if(r-s <= dx && dx <=r+s && c-s <= dy && dy <=c+s) {
						int swp =copy[dx][dy] ;
						copy[dx][dy] = tmp;
						tmp =swp;
						x = dx;
						y = dy;		
					}else d++;
					if(d == 4)break;
				}
				s--;
				if(s==0)break;	
			}
		}

		//더하기	
		int re = Integer.MAX_VALUE;
		for (int i = 1; i <=N; i++) {
			int sum =0;
			for (int j = 1; j <= M; j++)sum+= copy[i][j];
			re = Math.min(re, sum);
		}
		return re;	
	}
	
	//순열뽑기
	public static void permu(String str, int dep) {
		if(dep == K) {
			String[] tmp = str.split("");
			ans =Math.min(ans, rotation(tmp)) ;
			return;
		}
		
		// order 개수는 K
		for (int i = 0; i < K; i++) {
			if(!visitOrder[i]) {
				visitOrder[i] = true;
				permu(str+i, dep+1);
				visitOrder[i] = false;
			}
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		
		// 배열 NM 연산 수 K
		N = Integer.valueOf(tk.nextToken());
		M = Integer.valueOf(tk.nextToken());
		K =Integer.valueOf(tk.nextToken());
		arr = new int[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			tk = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				arr[i][j] = Integer.valueOf(tk.nextToken());
			}
		}
		for (int k = 0; k < K; k++) {
			tk = new StringTokenizer(br.readLine());
			// 중앙 rc +- s;
			int r = Integer.valueOf(tk.nextToken());
			int c = Integer.valueOf(tk.nextToken());
			int s =Integer.valueOf(tk.nextToken());
			visitOrder = new boolean[K];
			orders.add(new Order(r,c,s));

		}
		permu("",0);
		System.out.println(ans);
	
	}
	
	public static void resetCopy() {
		copy = new int[N+1][M+1];
		for (int i = 0; i < N+1; i++) {
			for (int j = 0; j < M+1; j++) {
				copy[i][j] = arr[i][j];
			}
		}
	}

}
