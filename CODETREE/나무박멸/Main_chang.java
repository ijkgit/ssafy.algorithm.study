import java.awt.PageAttributes;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,M,K,C;
    static int arr[][];
    static int dxy[][] = {{0,1},{1,0},{-1,0},{0,-1},{-1,-1},{1,1},{-1,1},{1,-1}};
    static int ans;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tk = new StringTokenizer(br.readLine());
        
        N = Integer.valueOf(tk.nextToken()); // N*N
        M = Integer.valueOf(tk.nextToken()); // 시뮬횟수
        K = Integer.valueOf(tk.nextToken()); // 제초제 범위
        C = Integer.valueOf(tk.nextToken()); // 제초제 년수
        arr = new int[N+2][N+2];
        for (int i = 0; i < N+2; i++) {
			Arrays.fill(arr[i], -99999);  
		}
        
        for (int i = 1; i < N+1; i++) {
        	tk = new StringTokenizer(br.readLine());
			for (int j = 1; j < N+1; j++) {
				arr[i][j] = Integer.valueOf(tk.nextToken()); 
				if(arr[i][j] == -1)arr[i][j] = -99999;
			}
		}
        ans = 0;
        //print();
        for (int tc = 0; tc < M; tc++) {
			//인접한 네 개의 칸 중 나무가 있는 칸의 수만큼 나무가 성장합니다. 성장은 모든 나무에게 동시에 일어납니다.

        	growUp();
        	//print();
        	//기존에 있었던 나무들은 인접한 4개의 칸 중 벽, 다른 나무, 제초제 모두 없는 칸에 번식을 진행합니다. 
        	//이때 각 칸의 나무 그루 수에서 총 번식이 가능한 칸의 개수만큼 나누어진 그루 수만큼 번식이 되며,
        	//나눌 때 생기는 나머지는 버립니다. 번식의 과정은 모든 나무에서 동시에 일어나게 됩니다.
        	spread();
        	//print();
        	//각 칸 중 제초제를 뿌렸을 때 나무가 가장 많이 박멸되는 칸에 제초제를 뿌립니다. 
        	//나무가 없는 칸에 제초제를 뿌리면 박멸되는 나무가 전혀 없는 상태로 끝이 나지만, 
        	//나무가 있는 칸에 제초제를 뿌리게 되면 4개의 대각선 방향으로 k칸만큼 전파되게 됩니다.
        	//단 전파되는 도중 벽이 있거나 나무가 아얘 없는 칸이 있는 경우, 그 칸 까지는 제초제가 
        	//뿌려지며 그 이후의 칸으로는 제초제가 전파되지 않습니다.
        	//제초제가 뿌려진 칸에는 c년만큼 제초제가 남아있다가 c+1년째가 될 때 사라지게 됩니다. 
        	//제초제가 뿌려진 곳에 다시 제초제가 뿌려지는 경우에는 새로 뿌려진 해로부터 다시 c년동안 제초제가 유지됩니다.
        	
        	herbicide();
        	//print();
        	for (int i = 1; i <= N ; i++) {
				for (int j = 1; j <= N; j++) {
					if(arr[i][j] <0)arr[i][j]++;
				}
			}
        	//print();
        	
		}
        System.out.println(ans);
        
    }

	private static void herbicide() {
		Point p = new Point();
		int max = 0 ;
		//가장 살상력 좋은 곳 찾음
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < N+1; j++) {
				if(arr[i][j]<=0)continue;
				int cal =  calc(i,j);
				//System.out.println(cal);
				if (max < cal) {
					p.x = i;
					p.y = j;
					max = cal;
				}
			}
		}
		if(p.x ==0) {
			return;
		}

		drop(p.x,p.y);
		
		ans+=max;
	}

	private static void drop(int i, int j) {
		//System.out.println("boooooom");
		//System.out.println((i-1) + " "+(j-1));
		arr[i][j] = -(C+1);
		int dx,dy;
		for (int d = 4; d < 8; d++) {
			int cnt = 1;
			while(true) {
				dx = dxy[d][0]*cnt + i;
				dy = dxy[d][1]*cnt + j;
				if(cnt > K)break;
				if(dx <1 || dx >N ||dy<1 || dy>N)break;
				if(arr[dx][dy] < -800)break;
				if(arr[dx][dy] ==0) {arr[dx][dy] = -(C+1);break;}
				else if(arr[dx][dy] >0){
					arr[dx][dy] = 0;
					arr[dx][dy] = -(C+1);
				}else  {
					arr[dx][dy] = -(C+1);
				}
				 //이부분 다시쳌
				cnt++;
			}
		}

		
	}
	private static void print() {
		for (int i = 1; i < N+1; i++) {
			System.out.println();
			for (int j = 1; j < N+1; j++) {
				if(arr[i][j] <-8000) {System.out.print("X ");}
				else {
				System.out.print(arr[i][j] + " ");}
			}
		}
		System.out.println();
	}

	private static int calc(int i, int j) {
		int sum = arr[i][j];
		int dx,dy;
		for (int d = 4; d < 8; d++) {
			int cnt = 1;
			while(true) {
				dx = dxy[d][0]*cnt + i;
				dy = dxy[d][1]*cnt + j;
				if(arr[dx][dy] <=0 || cnt > K)break;
				sum += arr[dx][dy];
				cnt++;
			}
		}
		return sum;
	}

	private static void spread() {
		int tmp[][] = new int[N+2][N+2];
		
		for (int i = 0; i < N+2; i++)tmp[i] = arr[i].clone();
		
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < N+1; j++) {
				if(arr[i][j]<=0)continue;
				
				int cnt = 0;
				int nx,ny;
				for (int d = 0; d < 4; d++) {
					nx = dxy[d][0] + i;
					ny = dxy[d][1] + j;
					if(arr[nx][ny] == 0)cnt++;
				}for (int d = 0; d < 4; d++) {
					nx = dxy[d][0] + i;
					ny = dxy[d][1] + j;	
					if(arr[nx][ny] == 0)tmp[nx][ny] += arr[i][j]/cnt;
				}
			}
		}
		arr = tmp;
		
	}

	private static void growUp() {
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < N+1; j++) {
				int cnt = 0;
				if(arr[i][j]<=0)continue;
				for (int d = 0; d < 4; d++) {
					int dx = i + dxy[d][0];
					int dy = j + dxy[d][1];
					if(arr[dx][dy] > 0)cnt++;
				}
				arr[i][j] += cnt;
			}
		}
		
	}
}