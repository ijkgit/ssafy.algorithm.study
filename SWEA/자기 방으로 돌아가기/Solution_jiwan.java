import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	//길이 겹쳐있다고 생각.
    //길에 ++ 후  sort 해결
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());		
		
		for(int test_case = 1; test_case<=T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int count = 0;
			int[] way = new int[201];
			
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = (Integer.parseInt(st.nextToken())+1)/2;
				int y = (Integer.parseInt(st.nextToken())+1)/2;
				
				if(x<y) {
					for(int j=x; j<=y; j++) {
						way[j]++;
					}
				}else {
					for(int j=y; j<=x; j++) {
						way[j]++;
					}
				}
			}
			Arrays.sort(way);
			count = way[200];		
			sb.append("#"+test_case+" "+count+"\n");
		}
		
		System.out.println(sb);
	}

}
