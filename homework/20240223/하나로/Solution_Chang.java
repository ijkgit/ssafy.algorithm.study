import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
 

/// 인접리스트로 문제를 풀다가 계속 오답이 떠서 인접행렬로 바꿔서 풀었습니다
/// 틀리던 이유는 compareTo 에 Double.compare(this.tex, o.tex); 를 사용하지 않고
///  (int)this.tex -(int)o.tex;를 사용해서 틀렸었습니다.

class Dpoint implements Comparable<Dpoint>{
	int y;
	double tex;
	public Dpoint(int y, double tex) {
		super();
		this.y = y;
		this.tex = tex;
	}
	@Override
	public int compareTo(Dpoint o) {
		// TODO Auto-generated method stub
		return Double.compare(this.tex, o.tex);
	}

}
public class Solution {
    static int[][] arr;

    static int[][] dxy = {{-1,0},{0,1},{1,0},{0,-1},{-1,-1},{1,-1},{1,1},{-1,1}};
    static int N;
    static double L;
    static ArrayList<Point> Ilands ;
    static ArrayList<ArrayList<Double>> edges;
    static double ans;
    static double gr[][];
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.valueOf(tk.nextToken());
        for (int tc = 1; tc <= T; tc++) {
        	tk = new StringTokenizer(br.readLine());
            N = Integer.valueOf(tk.nextToken());
            arr = new int[2][N];
            Ilands = new ArrayList<Point>();
            for (int i = 0; i < 2; i++) {
            	tk = new StringTokenizer(br.readLine());
            	for (int j = 0; j < N; j++) {
					arr[i][j]= Integer.valueOf(tk.nextToken()); 
				}
    		}
            for (int i = 0; i < N; i++) {
            	Ilands.add(new Point(arr[0][i],arr[1][i]));
				
			}
            
            tk = new StringTokenizer(br.readLine());
            L = Double.valueOf(tk.nextToken());
            edges = new ArrayList<ArrayList<Double>>();
            for (int i = 0; i <N; i++) {
    			edges.add(new ArrayList<Double>());
    		}
            ans = 0;
            MakeEdge();
            MST();
            //String.format("%.0f", ans)
            sb.append("#").append(tc).append(" ").append(String.format("%.0f", ans)).append("\n");


		}
        System.out.println(sb);
 
    }
	private static void MST() {
		boolean visit[] = new boolean[N];
		visit[0] = true;
		int cnt = 1 ;
		Queue<Dpoint> pq = new PriorityQueue<Dpoint>();
		for (int i = 0; i < N; i++) {
//			if(edges.get(0).get(i) >0) {
//				pq.add(new Dpoint(i, edges.get(0).get(i)));
//			}	
			pq.add(new Dpoint(i, gr[0][i]));

		}
		while (true) {
			Dpoint a = pq.poll();
			if(visit[a.y]) {
				continue;
			}
			cnt++;
			ans += a.tex;
			if(cnt == N) {
				break;
			}
			for (int j = 0; j < N; j++) {
				if(gr[a.y][j] >0) {
					pq.add(new Dpoint(j, gr[a.y][j]));
				}	
			}
//			System.out.println(ans);
			visit[a.y] = true;
		}
		
	}
	private static void MakeEdge() {
		gr = new double[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				Point A = Ilands.get(i);
				Point B = Ilands.get(j);
				double tex = (Math.pow(Math.abs(A.x - B.x ), 2) + Math.pow(Math.abs(A.y - B.y ), 2)) *L;
				gr[i][j] = tex;
				gr[j][i] = tex;
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Point A = Ilands.get(i);
				Point B = Ilands.get(j);
				double tex = (Math.pow(Math.abs(A.x - B.x ), 2) + Math.pow(Math.abs(A.y - B.y ), 2)) *L;
				edges.get(i).add(tex) ;
			}
		}
		
	}

	
 
}
 
