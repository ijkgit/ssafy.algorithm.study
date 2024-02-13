import java.util.*;
import java.io.*;

public class Main_sangphil {

	public static void main (String[] args) throws IOException {
		// System.setIn(Main_SevenTinyHumans.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int sum = 0;
		int[] arr = new int[9];
		for (int i = 0; i < 9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			sum += arr[i];
		}
		
		int a = 0;
		int b = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = i+1; j < 9; j++) {
				if (sum - arr[i] - arr[j] == 100) {
					a = i;
					b = j;
					break;
				}
			}
		}
		
		for (int i = 0; i < 9; i++) {
			if (i != a && i != b) {
				System.out.println(arr[i]);
			}
		}
	}
}