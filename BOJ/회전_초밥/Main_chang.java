import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class Main {
	static int N, D,K, C;
	static int arr[];
	static int map[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		
		N = Integer.valueOf(tk.nextToken());
		D = Integer.valueOf(tk.nextToken());
		K = Integer.valueOf(tk.nextToken());
		C = Integer.valueOf(tk.nextToken());
		//회전초밥
		arr = new int[N];
		//종류별 저장배열
		map = new int[D+1];
		
		int cnt = 0;
		int ans = 0;
		
		for (int i = 0; i <N; i++) {
			tk = new StringTokenizer(br.readLine());
			arr[i] = Integer.valueOf(tk.nextToken());
		}
		//쿠폰사용
		map[C]++;
		cnt++;
		
		//초기값계산
		for (int i = 0; i < K; i++) {
			int tmp = i%N;
			if (map[arr[tmp]] == 0)cnt++;
			map[arr[tmp]]++;
	
		}

		ans = ans<cnt ? cnt:ans;
		
		//슬라이딩 윈도우
		for (int i = 0; i < N; i++) {
			if (map[arr[i]] == 1)cnt--;
			map[arr[i]]--;
			
			int end = (i+K)%N;
			if (map[arr[end]] == 0)cnt++;
			map[arr[end]]++;
			
			ans = ans<cnt ? cnt:ans;
		}
		
		System.out.println(ans);
	}

}