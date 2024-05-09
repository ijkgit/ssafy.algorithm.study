import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int arr[][];
	static int group[][];
						// 우     상     좌      하
	static int dxy[][] = {{0,1},{-1,0},{0,-1},{1,0}};
	static int rdxy[][] = {{-1,-1},{1,-1},{1,1},{-1,1}};
	
	static HashMap<String, Integer> E;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		
		N = Integer.valueOf(tk.nextToken());
		arr = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			tk = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j]= Integer.valueOf(tk.nextToken());
			}
		}
		int ans = 0;
		for (int i = 0; i < 3; i++) {
			ans += getArtScore();
			arr = rotate();
		}
		ans += getArtScore();
		System.out.println(ans);
		
	}
	private static int[][] rotate() {
		//십자 반시계
		//나머지 시계
		int rarr[][] = new int[N][N];
		int m = N/2;
		rarr[m][m] = arr[m][m];
		//십자 반시계 회전
		for (int k = 1; k <= m; k++) {
			for (int d = 0; d < 4; d++) {
				int x = m + dxy[d][0]*k;
				int y = m + dxy[d][1]*k;
				int nx = x + rdxy[d][0]*k;
				int ny = y + rdxy[d][1]*k;
				rarr[nx][ny] = arr[x][y];
			}
			
		}
		
		
		// 나머지 4구역 회전
		//왼위
		for (int i = 0; i < m; i++) {
			int idx = m-1;
			for (int j = 0; j <m; j++) {
				rarr[i][j]  = arr[idx--][i];
			}
		}
		
		//오위
		for (int i = 0; i < m; i++) {
			int idx = m-1;
			for (int j = m+1; j <N; j++) {
				rarr[i][j]  = arr[idx--][i+m+1];
			}
		}
		
		//왼아int 
		int tmp = 0;
		for (int i = m+1; i < N; i++) {
			int idx = N-1;
			
			for (int j = 0; j <m; j++) {
				rarr[i][j]  = arr[idx--][tmp];
			}tmp++;
		}
		
		//오아
		tmp = m+1;
		for (int i = m+1; i < N; i++) {
			int idx = N-1;
			for (int j = m+1; j <N; j++) {
				rarr[i][j]  = arr[idx--][tmp];
			}tmp++;
		}

		//print(rarr);
		return rarr;
		
	}
	private static int getArtScore() {
		group = new int[N][N];
		E = new HashMap<>();
		HashMap<String,Integer> groupCnt = new HashMap<>();
		HashMap<String,Integer> values = new HashMap<>();
		int cnt = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if(group[i][j]==0) {
					int val = getGroup(i ,j ,++cnt);
					groupCnt.put(cnt+"", val);
					values.put(cnt+"", arr[i][j]);
				}
			}
		}
//		print(group);
//		System.out.println(E.toString());
//		System.out.println(groupCnt.toString());
		
		//그룹 a와 그룹 b의 조화로움은 
		//(그룹 a에 속한 칸의 수 + 그룹 b에 속한 칸의 수 ) x 그룹 a를 이루고 있는 숫자 값 x 그룹 b를 이루고 있는 숫자 값 x 그룹 a와 그룹 b가 서로 맞닿아 있는 변의 수로 정의됩니다.
		
		int artScore = 0;
		for (String key : E.keySet()) {
			String[] keys = key.split(",");
			if(keys.length > 2) System.out.println("뭔가이상");
			//                a칸수                     b칸수                 a의 숫자               b의 숫자              그룹 a와 그룹 b가 서로 맞닿아 있는 변의 수
			artScore += (groupCnt.get(keys[0]) + groupCnt.get(keys[1])) * values.get(keys[0])* values.get(keys[1])* E.get(key);
			//System.out.println("(" + groupCnt.get(keys[0]) +"+"+ groupCnt.get(keys[1]) +")"+ "*"+ values.get(keys[0])+"*"+ values.get(keys[1])+"*"+ E.get(key));
		}
		//System.out.println(artScore);
		return artScore;
	
	}

	private static int getGroup(int i, int j, int cnt) {
		group[i][j] = cnt;
		//HashMap<String, Integer> E;
		int sum = 1;
		for (int d = 0; d < 4; d++) {
			int nx = i + dxy[d][0];
			int ny = j+ dxy[d][1];
			if(check(nx,ny))continue;
			if(arr[nx][ny]==arr[i][j] && group[nx][ny]==0 ) {
				sum += getGroup(nx, ny, cnt);
			}else if(arr[nx][ny]!=arr[i][j] && group[nx][ny]>0 && group[nx][ny]!= group[i][j]) {
				int small = group[nx][ny]< group[i][j] ? group[nx][ny]: group[i][j];
				int big = group[nx][ny] > group[i][j] ? group[nx][ny]: group[i][j] ;
				// key = "1,2"
				String key = small + "," + big;
				//키가 이미있으면 변의 값 ++ 아니면 새로 넣기
				if(E.containsKey(key)) {
					E.put(key, E.get(key)+1);
				}else {
					E.put(key, 1);
				}
			}
		}
		
		return sum;
		
	}
	
	private static boolean check(int nx, int ny) {
		return nx<0 || nx >=N || ny <0 || ny >=N;
	}
	private static void print(int[][] tmp) {
		System.out.println();
		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp.length; j++) {
				System.out.print(tmp[i][j]);
			}
			System.out.println();
		}
		
	}
}