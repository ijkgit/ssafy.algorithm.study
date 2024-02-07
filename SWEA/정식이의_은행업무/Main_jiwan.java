import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= T; test_case++) {
			char[] Bin = br.readLine().toCharArray();
			char[] Tri = br.readLine().toCharArray();
			boolean flag = false;
			long answer = 0;
			for (int i = 0; i < Bin.length; i++) {
				if (Bin[i] == '0')
					Bin[i] = '1';
				else
					Bin[i] = '0';

				for (int j = 0; j < Tri.length; j++) {
					char temp = Tri[j];
					for (char k = '0'; k < '3'; k++) {
						if (temp == k)
							continue;
						Tri[j] = k;
						sb.setLength(0);
						for (int t = 0; t < Bin.length; t++)
							sb.append(Bin[t]);
						long bn = Long.parseLong(sb.toString(), 2);
						sb.setLength(0);
						for (int t = 0; t < Tri.length; t++)
							sb.append(Tri[t]);
						long tn = Long.parseLong(sb.toString(), 3);
						if (bn == tn) {
							flag = true;
							answer = bn;
							break;
						}

					}
					if (flag)
						break;
					Tri[j] = temp;
				}
				if (flag)
					break;
				if (Bin[i] == '0')
					Bin[i] = '1';
				else
					Bin[i] = '0';
			}
			System.out.println("#" + test_case + " " + answer);

		}
	}
}
