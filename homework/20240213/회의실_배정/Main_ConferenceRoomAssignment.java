import java.util.*;
import java.io.*;

/*
 * 		[BOJ 1931] 회의실 배정
 * 			회의의 종료시간을 기준으로 오름차순 정렬하고,
 * 			범주를 넘는지 여부를 체크하면서 그리디하게 갱신
 */
public class Main_ConferenceRoomAssignment {
	static class Info implements Comparable<Info> {
		int s;
		int e;
		public Info (int s, int e) {
			this.s = s;
			this.e = e;
		}
		@Override
		public int compareTo(Info o) {
			int diff = this.e - o.e;
			if (diff == 0) return this.s - o.s;
			return diff;
		}
	}
	
	public static void main (String[] args) throws IOException {
		// System.setIn(Main_ConferenceRoomAssignment.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		Info[] arr = new Info[n];
		
		StringTokenizer st = null;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(arr);
		
		int t = 0;
		int ans = 0;
		for (Info x : arr) {
			if (x.s >= t) {
				ans++;
				t = x.e;
			}
		}
		System.out.println(ans);
	}
}