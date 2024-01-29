package work;
import java.io.*;
import java.util.*;

public class 보급로 {
	
	static int [][]road;
	static int N;
	static int dx[] = {0,0,1,-1};
	static int dy[] = {1,-1,0,0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
	
		for(int test_case=1; test_case<=T; test_case++) {
			N= Integer.parseInt(br.readLine());
			road = new int[N][N];
			
			for(int i = 0; i<N; i++) {
				String input  = br.readLine();
				char[] in = input.toCharArray();
				for(int j=0; j<input.length(); j++) {
					road[i][j] = in[j]-'0';
				}
			}
			
			System.out.println("#"+test_case+" "+BFS(0,0));
		}
		
		
	}
	static int BFS(int sy, int sx) {
		//boolean visited[][] = new boolean[N][N];
		int overhead[][] = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j =0; j<N; j++) {
				overhead[i][j] = Integer.MAX_VALUE;
			}
		}
		
		ArrayDeque<Node> adq = new ArrayDeque<>();
		
		adq.add(new Node(sy, sx, 0));
		//visited[sy][sx] = true;
		overhead[sy][sx] = 0;
		int min = Integer.MAX_VALUE;
		while(!adq.isEmpty()) {
			Node node = adq.poll();
			int curx = node.x;
			int cury = node.y;
			int curDist = node.dist;
			//System.out.println("curr :"+cury+" "+curx);
			if(curx==N-1 && cury==N-1) {
				min = Math.min(min, curDist);
				continue;
			}
			
			for(int i=0; i<4; i++) {
				int ny = cury +dy[i];
				int nx = curx +dx[i];
				if(ny<0 || ny>=N || nx<0||nx>=N )continue; 
				//System.out.println("next :"+ny+" "+nx);
				
				int nd =  curDist+ road[ny][nx];
				if(overhead[ny][nx]>nd) {
					overhead[ny][nx] = nd;
					adq.add(new Node(ny, nx, nd));
					//visited[ny][nx] = true;	
				}
				
				
			}
		}
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(overhead[i][j]+" ");
//			}
//			System.out.println();
//		}
		return min;
		
	}
	static class Node{
		int y,x,dist;
		Node(int y, int x, int dist){
			this.y = y;
			this.x = x;
			this.dist = dist;
		}
	}
}
