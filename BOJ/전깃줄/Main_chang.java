import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


class Line implements Comparable<Line>{
	int x,y;

	public Line(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Line [x=" + x + ", y=" + y + "]";
	}

	@Override
	public int compareTo(Line o) {
		return this.x - o.x;
	}
	
}
public class Main {
	static int N;
	static int arr[];
	static int map[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		
		//N^2 LIS
		N = Integer.valueOf(tk.nextToken());
		ArrayList<Line> list = new ArrayList<>();
		int a,b;
		for (int i = 0; i < N; i++) {
			tk = new StringTokenizer(br.readLine());
			a= Integer.valueOf(tk.nextToken());
			b= Integer.valueOf(tk.nextToken());
			
			list.add(new Line(a, b));
			
		}
		Collections.sort(list);
		//System.out.println(list);
		int arr[] = new int[N+1];
		int idx = 0;

		int ans = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= ans; j++) {
				if(arr[j] == 0) {
					//System.out.println(11);
					
					arr[j] = list.get(i).y;
					ans++;
					break;
				}
				else if(arr[j]>list.get(i).y) {
					arr[j] = list.get(i).y;
					break;
				}
				
			}
//			ans = idx < ans ? ans:idx;
			//System.out.println(Arrays.toString(arr));
		}
		
		System.out.println(N-(ans-1));
		
	}

}