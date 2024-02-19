package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 연구소3 {
	//바이러스 : 활성, 비활성
	//바이러스를 활성상태로 바꿔서 퍼트리고 최소가 되는 시간
	//1초 
	static int N;
	static int M;
	static int[][] map;
	static class Virus{
		int y, x;
		Virus(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	//static Virus[] viruses; //몇개를 배열로 담을지 모르니까. 우선은 ArrayList로 관리해야함.
	static ArrayList<Virus> viruses = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2)viruses.add(new Virus(i,j));
			}
		}
		recursive(0,0,new int[M]);
		//0:빈칸
		//1:벽
		//2:바이러스
		//idea 1 : 바이러스의 조합을 골라야한다.
		//바이러스의 조합을 골라서 bfs를 하고 최소를 고른다.
		//그러려면? -> virus를 담는 객체가 필요하다.
		//512MB로 나름 널널한 메모리 복사를 맘껏해도 괜찮겠군.
	}
	private static void recursive(int idx, int k, int sel[]) {
		// TODO Auto-generated method stub
		if(idx == sel.length) {
			System.out.println(Arrays.toString(sel));
			//좌표 뽑았고 이제 BFS돌리면 되겠네 근데 동시에 돌려야하는데 어떻게?
			
			BFS(sel);
			print(map);
			return;
		}
		if(k == viruses.size()) {return;}
		sel[idx] = k;
		recursive( idx+1,  k+1,sel);
		recursive( idx, k+1,sel);
	}
	static int[] dy = {1,0,-1,0};
	static int[] dx = {0,1,0,-1};
	private static void BFS(int[] sel) {
		// TODO Auto-generated method stub
		ArrayDeque<int[]> adq = new ArrayDeque<>();
		for(int i=0; i<sel.length; i++) {
			int y = viruses.get(sel[i]).y;
			int x = viruses.get(sel[i]).x;
			adq.offer(new int[] {y,x}); //3 is initial distance 
		}
		//임의로 count 3부터 시작 012 사용중
		//1      1
		//23     2
		//4567   3
		
		int dist = 3;
		while(!adq.isEmpty()) {
			int size = adq.size();
			for(int s=0; s< size; s++) {
				int [] curr =  adq.poll();
				int cury = curr[0];
				int curx = curr[1];
				//int dist = curr[2];
				map[cury][curx] = dist;
				
				
				for(int i=0; i<4; i++) {
					int ny = cury+dy[i];
					int nx = cury+dx[i];
					if(checkOutside(ny,nx)||map[ny][nx]!=0)continue; //밖이면 xx
					//if((map[ny][nx] < map[curx][cury]) || map[ny][nx]==0)
					//if()
					//map[cury][curx] = dist+1;
					adq.offer(new int[] {ny, nx});
				}
			
			}
			dist+=1;
		}
		
	}
	
	private static boolean checkOutside(int ny, int nx) {
		// TODO 밖인지 체크 밖이면 true 
		if(ny<0 || ny>=N || nx<0||nx>=N)return true;
		return false;
	}
	private static void print(int[][] m) {
		System.out.println();
		for (int i = 0; i < m.length; i++) {
			for(int j=0; j<m[i].length; j++) {
				System.out.print(m[i][j]+" ");
			}
			System.out.println();
		}
	}
}
