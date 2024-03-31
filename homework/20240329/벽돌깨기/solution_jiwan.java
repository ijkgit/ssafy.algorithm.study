package assignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class 벽돌깨기 {
    static int N, W,H;
    static int[][] map;
    static int[][] mapCpy;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T =Integer.parseInt(br.readLine());
        StringTokenizer st= null;
        for(int tc =1; tc<=T; tc++) {
            ans = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H][W];
            mapCpy = new int[H][W];
            for(int i=0; i<H; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<W; j++ ) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    mapCpy[i][j] = map[i][j];
                }
            }
            
            recursive(0,0,new int[N]);  //idx, k, sel 돌을 어디에 던질지 정함.
            
            System.out.println("#"+tc+" "+ans);
        }
    }
    private static void recursive(int idx, int k, int[] sel) {
        
        if(k == N) {
            //System.out.println(Arrays.toString(sel));
            
            for(int i=0; i<sel.length; i++) {
                BreakWall(sel[i]);
                

                MoveDown();
            }
            //print(map);
            //FOR i
            //sel[i]번째 벽돌을 부시기 -> 바로 부시지 않고 표시해뒀다가 지워야함 
            //연쇄반응
            //아래로 떨구기
            //END FOR
            
            
            //남은 벽돌 개수 세기, //ans 초기화
            ans = Math.min(ans, boxCnt());
            //map원복
            copyMap();
            
            
            return;
        }
        
        for(int i=0; i<W; i++) {
            sel[k] = i;
            recursive(idx, k+1, sel);
        }
    }
    private static void MoveDown() {
    	Deque<Integer> deque = new ArrayDeque<Integer>();
		for (int j = 0; j < W; j++) { 
			for (int i = 0; i < H; i++) { 
				if(map[i][j] > 0) {
					deque.add(map[i][j]);	
				}
			} 
			for(int i= H-1; i>= 0; i--) { 
				if(deque.isEmpty()) {
					map[i][j] = 0;
				} else {
					map[i][j] = deque.pollLast();	
				}
			}
		}
	}
	private static void copyMap() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = mapCpy[i][j];
			}
		}
	}
    private static int boxCnt() {
    	int cnt =0;
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(map[i][j]>0)cnt++;
			}
		}
		return cnt;
	}
	private static void BreakWall(int c) {
        int r=-1;
        for(int i=0; i<H; i++) {
            if(map[i][c]!=0) {
                r = i;
                break;
            }
        }
        if(r==-1)return;
        //r,c 의 좌표는 현재 map[r][c]-> 이제 부시면 됨 
        BFS(r,c);
        
    }
    static int[] dr = {0,0,1,-1};
    static int[] dc = {1,-1,0,0};
    private static void BFS(int r, int c) {
		ArrayDeque<int[]> adq = new ArrayDeque<>();
		adq.add(new int[] {r,c,map[r][c]});
		map[r][c] = 0;
		
		while(!adq.isEmpty()) {
			int[] cur = adq.poll();
			int cr = cur[0];
			int cc = cur[1];
			int cRange = cur[2];
			
			for(int j=1; j<cRange; j++) {
				for(int i=0; i<4; i++) {
					int nr = cr + dr[i] * j;
					int nc = cc + dc[i] * j;
					if(check(nr, nc) || map[nr][nc]==0)continue;
					if(map[nr][nc]>0) {
						adq.add(new int[]{nr,nc,map[nr][nc]});
						map[nr][nc]=0;
						continue;
					}
					
				}
				
			}
			
		}
		
	}
	private static boolean check(int nr, int nc) {
		if(nr<0 || nr>=H || nc<0 || nc>=W) return true;
		return false;
	}
	static void print(int[][] m ) {
        for(int i=0; i<m.length; i++) {
            for(int j=0; j<m[i].length; j++) {
                System.out.print(m[i][j]+" ");
            }
            System.out.println();
        }
    }
}
