package work;

import java.util.*;

import java.io.*;

public class Ladder1 {

    static int [][]ladders = new int[100][100];
    static int n =100;
    static int dx[] = {-1,1,0};
    static int dy[] = {0,0,-1};//왼오 > 위 우선순위
    static int ex;
    static int ey;
    public static void main(String[] args) throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
        
        for(int test_case=1; test_case<=10; test_case++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	 int T = Integer.parseInt(st.nextToken());
        	 for(int i=0; i<n; i++) {
                 st = new StringTokenizer(br.readLine());
                 for(int j=0; j<n; j++) {
                     ladders[i][j] = Integer.parseInt(st.nextToken());
                     if(ladders[i][j]==2) {
                         ey = i;
                         ex = j;
                         //System.out.println(ey+" "+ ex);
                     }
                 }
             }


             int res = BFS(ey, ex); //뒤에서 시작, 시작점 리턴
             System.out.println("#"+test_case+" "+res);

        }
       

    }
    private static int BFS(int startY, int startX) {
    	boolean visited[][] = new boolean[n][n];
    	int ansX=0;
        ArrayDeque<Node> adq = new ArrayDeque<Node>();
       
       adq.add(new Node(startY, startX));
       visited[startY][startX]=true;

       while(true) {

            Node no = adq.poll();
            int curX = no.x;
            int curY = no.y;
            visited[curY][curX]=true;
            if(curY == 0) {
            	ansX = curX;
            	return ansX;	
            }
            
            
            for(int i=0; i<3; i++) {
                int ny = curY + dy[i];
                int nx = curX + dx[i];
                //System.out.println(ny+" "+nx);
                if(ny < 0 || ny >= n || nx<0 || nx >= n || visited[ny][nx] ||ladders[ny][nx]==0 )continue;
                
                adq.addLast(new Node(ny,nx));
                visited[ny][nx]=true;
                

            }


        }
       //return ansX;
        
    }
    
    
    static class Node{
        int y,x;
        Node(int y, int x){
            this.y =y;
            this.x =x;
        }
    }
}

