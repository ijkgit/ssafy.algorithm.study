import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair{
	int x,y,dep;

	public Pair(int x, int y, int dep) {
		super();
		this.x = x;
		this.y = y;
		this.dep = dep;
	}
	
	
}

public class Main {
	static int N,M,D;
	static int arr[][];
	static int ori[][];
	static int ans = 0;
	static HashSet<Point> enermy;
	static int[][] dxy = {{0,-1},{-1,0},{0,1}};
	
	//병사 행진
	public static HashSet<Point> mach(HashSet<Point> cp) {
		arr = new int[N][M];
		HashSet<Point> re = new HashSet<>();
		ArrayList<Point> out = new ArrayList<Point>();
		for (Point p : cp) {
			if(p.x +1 <N) {
				arr[p.x+1][p.y] = 1;
				re.add(new Point(p.x+1,p.y));
			}else {
				out.add(p);
			}
		}

		return re;
	}
	
	//쏘기
	public static void shooting(String tmp) {
		String archer[] = tmp.split(" ");
		HashSet<Point> cp = new HashSet<>();
		ArrayList<Point> killed = new ArrayList<>();
		cp.addAll(enermy);
		int kill =0;
		//게임이 끝날때까지 반복
		while(true) {
			int cnt = cp.size();
			killed = new ArrayList<>();
			//병사마다 타겟 찾기
			nextArcher:
			for (int i = 0; i < 3; i++) {
				int x = N;
				int y = Integer.valueOf(archer[i]);
				int k = 1;
				Queue<Pair> q = new ArrayDeque<>();
				boolean visit[][] = new boolean[N][M];
				q.add(new Pair(x-1, y, 1));
				visit[x-1][y] = true;
				while(!q.isEmpty()) {
					Pair u = q.poll();
					if(u.dep > D) {
						continue;
					}
					if(arr[u.x][u.y] == 1) {
						killed.add(new Point(u.x,u.y));
						continue nextArcher;
					}
					for (int d = 0; d < 3; d++) {
						int nx = u.x + dxy[d][0];
						int ny = u.y + dxy[d][1];
						if(0<=nx && nx< N &&0<=ny && ny<M && !visit[nx][ny]) {
							visit[nx][ny] = true;
							q.add(new Pair(nx,ny,u.dep+1));
						}
					}
				}

			}
			//적 죽이기
			for (int i = 0; i <killed.size(); i++) {
				Point deadman = killed.get(i);
				cp.remove(deadman);
				arr[deadman.x][deadman.y] = 0;
			}
			kill = kill+ (cnt - cp.size());
			//적 행진
			cp = mach(cp);
			if(cp.size() == 0) {
				break;
			}
			
			
		}

		ans = Math.max(kill, ans);

	}
	//조합 찾기
	public static void setArcher(int idx , int cnt , String tmp) {
		if(cnt == 3) {
			copyarr();
			shooting(tmp);
			return;
		}
		if(idx ==M) {
			return;
		}
		setArcher(idx+1, cnt, tmp);
		setArcher(idx+1, cnt+1, tmp+idx+" ");

	}
	
	private static void copyarr() {
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			arr[i] = ori[i].clone();
		}

		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		N = Integer.valueOf(tk.nextToken());
		M= Integer.valueOf(tk.nextToken());
		D = Integer.valueOf(tk.nextToken());
		ori = new int[N][M];
		enermy = new HashSet<>();
		for (int i = 0; i < N; i++) {
			 tk = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				ori[i][j] = Integer.valueOf(tk.nextToken());
				if(ori[i][j] == 1) {
					enermy.add(new Point (i,j));
				}
			}
		}
		setArcher(0, 0, "");
		System.out.println(ans);
	}

}

