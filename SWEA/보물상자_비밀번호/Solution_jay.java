import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Solution().io();
	}

	private int n;
	private int k;
	private Map<String, Integer> map;
	private int SIZE;
	private String s;

	private void io() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			SIZE = n / 4;
			map = new HashMap<>();
			s = br.readLine();
			
			sb.append("#").append(t).append(" ").append(sol()).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private int sol() {
		getRotateSum();
		for (int i = 0; i < SIZE-1; i++) {
			rotate();
			getRotateSum();
		}
		
		int idx = 0, ans = 0;
		for (String key : sort()) {
			idx++;
			ans = map.get(key);
			if (idx == k) {
				break;
			}
		}
		
		return ans;
	}
	
	private List<String> sort() {
		List<String> keySet = new ArrayList<>(map.keySet());
		keySet.sort((o1, o2) -> map.get(o2) - map.get(o1));
		return keySet;
	}
	
	private void rotate() {
		char[] c = s.toCharArray();
		char tmp = c[c.length - 1];
		for (int i = c.length-1; i > 0; i--) {
			c[i] = c[i-1];
		}
		c[0] = tmp;
		s = String.valueOf(c);
	}
	
	private void getRotateSum() {
		String n;
		for (int i = 0; i < s.length(); i += SIZE) {
			n = s.substring(i, i+SIZE);
			map.put(n, getHexSum(n));
		}
		
		
	}
	
	private int getHexSum(String n) {
		int sum = 0;
		int i = SIZE-1;
		for(int c : n.toCharArray()) {
			if ('0' <= c && c <= '9') c -= '0';
			else c -= 'A' - 10;
			int x = (int) Math.pow(16, i--);
			sum += x * c;
		}
		return sum;
	}
	
}
