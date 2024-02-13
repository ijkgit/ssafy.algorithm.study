import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

class Pair implements Comparable<Pair>{
	int low;
	int hi;
	public Pair(int low, int hi) {
		super();
		this.low = low;
		this.hi = hi;
	}
	
	@Override
	public int compareTo(Pair p) {
		return this.low - p.low;

	}
	
}

public class Main {
	static int N, L;
	static ArrayList<Pair> arr;
	
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in) ;
		N = sc.nextInt();
		arr = new ArrayList<>();
		for (int i = 0; i <N; i++) {
			arr.add(new Pair( sc.nextInt(), sc.nextInt()));
		}
		Collections.sort(arr);
		int cnt = 1;
		int hi = arr.get(0).hi;
		int low = arr.get(0).low;
		
		for (int i = 0; i < N; i++) {
			if(hi < arr.get(i).low) {
				hi = arr.get(i).hi;
				low = arr.get(i).low;
				cnt++;
				continue;
			}
			if(hi > arr.get(i).hi) {
				hi = arr.get(i).hi;
			}
			if(low < arr.get(i).low) {
				low = arr.get(i).low;
			}
		}
		System.out.println(cnt);
		
		
	}
}