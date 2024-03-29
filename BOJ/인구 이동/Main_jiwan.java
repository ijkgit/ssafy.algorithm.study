import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

//O(N^3(N+E)) ~= 10^7 => 맞는지 검증 필요. 도와줘 팀원들! 
public class Main {	
	static boolean visited[][];
	static int canMove[][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,1,-1};
	static int N;
	static int L;
	static int R;
	static int graph[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		graph = new int[N][N];
		
		canMove= new int[N][N];
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루 동안 연다.
		
		int moveCnt =0;
		while(true) {
			
			visited= new boolean[N][N];
			canMove= new int[N][N];
			boolean isConnected=false;
			boolean flag= false;
			//3. visited초기화
			
			//1. canMove 등록- bfs를 활용해서 움직일 수 있는 좌표를 계산
			int moveFlag = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j]) {
						isConnected =  BFS(i,j);
						if(isConnected == true)flag = true;
					}
				}
			}
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<N; j++) {
//					System.out.print(graph[i][j]+ " ");
//					}
//				System.out.println();
//				
//			}
			if(!flag) {
				break;
			}
			moveCnt++;
			
			//canMove가 모두 0이라면 종료.
			
		}
		System.out.println(moveCnt);
		
		
		
	}
	private static boolean BFS(int startY, int startX ) {
		ArrayDeque<Node> adq = new ArrayDeque<Node>();
		ArrayDeque<Node> backup = new ArrayDeque<>();
		adq.add(new Node(startY, startX));
		backup.add(new Node(startY, startX));
		visited[startY][startX] = true;
		
		
		int connectionCnt=0;
		int sum =  graph[startY][startX]; //이어지는 곳 까지 누적값
		
		
		while(!adq.isEmpty()) {
			Node n = adq.poll();
			int curX,curY,curVal ;
			curX = n.x;
			curY = n.y;
			curVal = graph[n.y][n.x];
			
			for(int i =0; i<4; i++) {
				int ny, nx;
				ny =  curY+dy[i];
				nx = curX+dx[i];
				
				if(ny>=N||ny<0||nx<0||nx>=N||visited[ny][nx])continue;
				int nv = graph[ny][nx];
				if(Math.abs(curVal -nv)>=L && Math.abs(curVal-nv)<=R) {
					visited[ny][nx]=true;
					connectionCnt++;
					sum += nv;
					adq.add(new Node(ny,nx));
					backup.add(new Node(ny,nx));
				}
				
			}
		}
		if(connectionCnt ==0) return false;
		while(!backup.isEmpty()) {
			Node n = backup.poll();
			graph[n.y][n.x] = sum/ (connectionCnt+1);
		}
		
		return true;
		
		
		
		
	}
	static class Node{
		int y,x;
		Node(int y, int x){
			this.y=y;
			this.x=x;
			//this.value =value;
			
		}
	}

}
