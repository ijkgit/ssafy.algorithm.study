import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int t = 1; t <= testCase; t++) {
			String input = br.readLine();
			StringTokenizer st = new StringTokenizer(input);

			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());

			int x1 = 0, y1 = 0, x2 = 0, y2 = 0, ansx = 0, ansy = 0;
			int element = 1;
			L: for (int i = 1; i < 10000; i++) {
				int j = i;
				int k = 1;
				for (int l = 0; l < i; l++) {
					if (p == element) {
						x1 = j;
						y1 = k;
					}
					if (q == element) {
						x2 = j;
						y2 = k;
					}
					if (x1 != 0 && y1 != 0 && x2 != 0 && y2 != 0) {
						ansx = x1+x2;
						ansy = y1+y2;
					}
					if (j == ansx && k == ansy) {
						sb.append("#" + t + " " + element + "\n");
						break L;
					}
					j--;
					k++;
					element++;
					
				}
			}
		}
		System.out.print(sb);
	}
}
