import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Main().io();
	}

	class Chemical implements Comparable<Chemical> {
		int low, high;

		public Chemical(int low, int high) {
			super();
			this.low = low;
			this.high = high;
		}

		@Override
		public int compareTo(Chemical o) {
			return this.high - o.high;
		}

	}

	private void io() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		List<Chemical> chemicalList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int low = Integer.parseInt(st.nextToken());
			int high = Integer.parseInt(st.nextToken());
			chemicalList.add(new Chemical(low, high));
		}
		Collections.sort(chemicalList);
		
		int ans = 0;
		for (int j = 0; j < chemicalList.size(); j++) {
			Chemical c = chemicalList.get(j);
			ans++;
			
			for (int i = j; i < chemicalList.size(); i++) {
				if (c.high >= chemicalList.get(i).low) {
					chemicalList.remove(i);
					i--;
				}
			}
			j--;
		}

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
