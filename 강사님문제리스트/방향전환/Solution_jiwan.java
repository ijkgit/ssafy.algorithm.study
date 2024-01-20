import java.util.*;
import java.io.*;


class Solution_jiwan
{
	//4방탐색하면서, 가로이동과 세로이동을 번갈아 해야함
	//최소 몇번이동해야하는지 구해야함
	//음수가 들어올수도 있음.  -100 ≤ x1, y1, x2, y2 ≤ 100
	//양수로 transform
	//-100 ~ 100 -> 0 201
	//근데 격자위에서 움직이는 것이라면, 최소격자만 남겨둘 수 있음.
	//대각 움직임 1번은 2번으로 고정. => 가로세로든 세로가로든 상관이 없음.
	
	//static int []dx = {1,-1,0,0};
	//static int []dy = {0,0,-1,1};
	//static int [][]graph;
	static int startX; 
	static int startY;
	static int endX; 
	static int endY;
	//static boolean toggle1;
	//static boolean toggle2;
	public static void main(String args[]) throws Exception
	{
	
		//Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw  = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T;
		T= Integer.parseInt(st.nextToken());
		

		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(br.readLine());
			startX = Integer.parseInt(st.nextToken());
			startY = Integer.parseInt(st.nextToken());
			endX = Integer.parseInt(st.nextToken());
			endY = Integer.parseInt(st.nextToken());
			
			int moveX = Math.abs(endX - startX);
			int moveY = Math.abs(endY - startY);
			
			int totalMove = 0;
			while(true) {
				if(moveX==0 || moveY==0)break;
				moveX--;
				moveY--;
				totalMove +=2;
			}
			int absTotal  = moveX+moveY;
			
			for(int i=1; i<=absTotal; i++) {
				if(i%2==0)totalMove+=3;
				else totalMove+=1;
			}
			
			bw.write("#"+test_case+" "+totalMove+'\n' );
			
			//BFS(startY, startX)
			

		}
		bw.flush();
	}
}