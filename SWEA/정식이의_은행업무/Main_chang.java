import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {
	static String binary, ternary;
	static Set<Integer> set;
	public static int search() {
		String tmp;
		for (int i = 0; i < binary.length(); i++) {
			for (int num = 0; num < 2; num++) {
				if(Integer.valueOf(binary.charAt(i)-'0') != num) {
					tmp = binary.substring(0, i) + num + binary.substring(i+1,  binary.length());
					set.add(Integer.valueOf(tmp, 2));
				}
			}
		}
		
		for (int i = 0; i < ternary.length(); i++) {
			for (int num = 0; num < 3; num++) {
				if(Integer.valueOf(ternary.charAt(i)-'0') != num) {
					tmp = ternary.substring(0, i) + num + ternary.substring(i+1,  ternary.length());

					if ( set.contains(Integer.valueOf(tmp, 3))) {

						return Integer.valueOf(tmp, 3);
					}
				}
			}	
			
		}
		return 0;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		
		
		for (int tc = 1; tc <=T; tc++) {
			set = new HashSet<>();
			binary = sc.next();
			ternary = sc.next();
			sb.append("#").append(tc).append(" ").append(search()).append("\n");
		}
		
		System.out.println(sb);
		
		
		
	}

}
