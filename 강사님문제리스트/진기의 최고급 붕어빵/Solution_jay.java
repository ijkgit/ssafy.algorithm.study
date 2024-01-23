import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= testCase; t++) {
			String input = br.readLine();
			StringTokenizer st = new StringTokenizer(input);
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			input = br.readLine();
			st = new StringTokenizer(input);
			int[] arriveTime = new int[n];
			for (int i = 0; i < n; i++) {
				arriveTime[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arriveTime);
			
			int nowTime = 0;
			int nowCount = 0;
			int maxTime = arriveTime[n-1] + 1;
			int idx = 0;
			L:while(nowTime != maxTime) {
				if (nowTime != 0 && nowTime%m == 0) {
					nowCount += k;
				}
				for (int i = idx; i < n; i++) {
					if(arriveTime[i] == nowTime) {
						if(nowCount != 0) {
							nowCount--;
							idx++;
						}
						else {
							sb.append("#" + t + " Impossible\n");
							break L;
						}
					}
					else {
						break;
					}
				}
				nowTime++;
			}
			
			if (nowTime == maxTime) {
				sb.append("#" + t + " Possible\n");
			}
		}
		System.out.print(sb);
	}
}
