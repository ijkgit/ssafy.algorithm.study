import java.util.*;
import java.io.*;

/*
 * 		[정올 1828] 냉장고
 * 			1. Temper 객체에 온도 바운더리 값을 초기화
 * 			2. Temper 객체가 담긴 배열을 최저온도를 기준으로 오름차순으로 정렬
 * 				- 최저온도가 같다면, 최고온도를 기준으로 오름차순 정렬
 * 			3. 이전까지의 최고 온도 보다 최저 온도가 더 높으면 냉장고 1개 추가
 * 				- 만약, 최고 온도가 더 작은 것이 들어오면, 최고온도의 upper 바운더리를 축소
 */
public class Main_sangphil {
	static class Temper implements Comparable<Temper> {
		int l; int h;
		public Temper(int l, int h) {
			this.l=l;
			this.h=h;
		}
		@Override
		public int compareTo(Temper o) {
			int diff = this.l - o.l;
			if (diff == 0) return this.h - o.h;
			return diff;
		}
	}

	public static void main (String[] args) throws IOException {
		// System.setIn(Main.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Temper[] arr = new Temper[n];
		StringTokenizer st = null;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = new Temper(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(arr);

		int t = -271;
		int cnt = 0;
		for (Temper x : arr) {
			if (x.l > t) {
				cnt++;
				t = x.h;
			} else {
				if (x.h < t) {
					t = x.h;
				}
			}
		}
		System.out.println(cnt);
	}
}