import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;


class Cell {
	int x,y,power,life,dep;
	//life가 power보다 작아지는 순간 번식
	public Cell(int x, int y, int dep, int power, int life) {
		super();
		this.x = x;
		this.y = y;
		this.dep = dep;
		this.power = power;
		this.life = life;
	}
	
}

public class Solution {
	static int N,M,K ;
	static HashSet<Point> DeadCells = new HashSet<>();  //visit
	static HashMap<Point,Integer> NewCells = new HashMap<>(); // 번식할때 p가 큰 cell차지를 위한 map
	static int dxy[][] = {{0,1},{1,0},{-1,0},{0,-1}};
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer tk  = new StringTokenizer(br.readLine());
		int T = Integer.valueOf(tk.nextToken());
		
		for (int tc = 1; tc <= T; tc++) {
			tk  = new StringTokenizer(br.readLine());
			N = Integer.valueOf(tk.nextToken());
			M = Integer.valueOf(tk.nextToken());
			K = Integer.valueOf(tk.nextToken());
			//초기화 및 입력
			Queue<Cell> q = new ArrayDeque<>();
			DeadCells = new HashSet<>();
			NewCells = new HashMap<>();
			for (int i = 0; i <N; i++) {
				tk  = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int tmp = Integer.valueOf(tk.nextToken());
					if(tmp > 0) {
						q.add(new Cell(i,j,1,tmp,tmp*2));
						DeadCells.add(new Point(i,j));
					}
				}
			}
			// k < dep 이때 return
			//newcell은 깊이가 바뀌면서 초기화가 필요
			for (int k = 1; k <=K ; k++) {
				
				NewCells = new HashMap<>();
				//dep 1회씩만 탐색
				while(!q.isEmpty()) {
					Cell cell = q.poll();
					if(cell.dep > k) {
						q.add(cell);
						break;
					}
					cell.life = cell.life - 1;
					//생명력이 힘보다 작아지면(-1되는 순간) 번식
					if(cell.life == cell.power-1) {
						for (int d = 0; d <4; d++) {
							Point p = new Point(cell.x + dxy[d][0],cell.y + dxy[d][1]);
							if(!DeadCells.contains(p)) {
								//그자리에 누가 번식 했나 안했나 확인
								if(NewCells.containsKey(p)) {
									NewCells.put(p, Math.max(NewCells.get(p), cell.power));
								}else {
									NewCells.put(p , cell.power);
								}
							}
						}
					}
					//생명력이 남아있으면 q에 삽입
					if(cell.life >0) {
						q.add(new Cell(cell.x,cell.y, k+1, cell.power,cell.life));
					}
				}//while 끝나는 부분	
				for (Point p: NewCells.keySet()) {
					 DeadCells.add(p);
					 q.add(new Cell(p.x, p.y, k+1, NewCells.get(p) ,NewCells.get(p)*2 ));
				}

			}
			System.out.println("#" + tc + " " +q.size());
			
			
			
		}
		
		
		
	}
}