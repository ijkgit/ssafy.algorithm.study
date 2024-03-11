import java.io.*;
import java.util.*;

public class Main_sangphil {
	static String line;
	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++) {
			line = br.readLine();
			sb.append(binSearch(0, line.length()-1, 0)).append("\n");
		}
		System.out.println(sb);
	}

	static int binSearch (int l, int r, int cnt) {
		while (l < r) {
			if (line.charAt(l) != line.charAt(r)) {
				if (cnt == 0) {
					if (binSearch(l+1, r, 1) == 0 || binSearch(l, r-1, 1) == 0) return 1;
				}
				return 2;
			} 
			l++; r--;
		}
		return 0;
	}
}