import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class C implements Comparable<C>{
	int k,v;
	public C(int k, int v) {
		super();
		this.k = k;
		this.v = v;
	}
	@Override
	public int compareTo(C o) {
		if(this.v == o.v)return this.k - o.k;
		return this.v - o.v;
	}
	
}


public class Main {
	static int R,C,K;
	static int arr[][] = new int[100][100];
	static int r,c;
	static HashMap<Integer, Integer> map = new HashMap<>();
	static ArrayList<C> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer tk = new StringTokenizer(br.readLine());
    	
    	R = Integer.valueOf(tk.nextToken())-1;
    	C = Integer.valueOf(tk.nextToken())-1;
    	K = Integer.valueOf(tk.nextToken());
    	r=3;
    	c=3;
    	for (int i = 0; i < 3; i++) {
    		tk = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.valueOf(tk.nextToken());
			}
		}
    	int ans =  0;

    	for (; ans < 100; ans++) {

    		if(arr[R][C] == K)break;
    		if(r>=c) {
    			rSort();
    		}else {
    			cSort();
    		}	

		}
    	
    	System.out.println(ans>=100? -1:ans);

    	
    }


	private static void rSort() {
		int 최대 = 0;
		int [][]cp = new int[100][100];

		for (int i = 0; i < r; i++) {
			int maxSize = 0;
			map.clear();
			//개수
			for (int j = 0; j < c; j++) {
				if(arr[i][j] == 0)continue;
				if(map.containsKey(arr[i][j])) {
					map.put(arr[i][j], map.get(arr[i][j])+1);
				}else {
					map.put(arr[i][j], 1);
				}
			}
			//정렬
			list.clear();
			for (int key : map.keySet()) list.add(new C(key,map.get(key)));
			Collections.sort(list);
			
			//대입
			maxSize = list.size()*2>100? 100: list.size()*2;
			
			for (int j = 0; j < maxSize; j+=2) {
				cp[i][j] = list.get(j/2).k;
				cp[i][j+1] = list.get(j/2).v;
			}
			최대 = Math.max(maxSize, 최대);
		}
		arr=cp;
		c = 최대;
	}
	private static void cSort() {
		int 최대 = 0;
		int [][]cp = new int[100][100];

		for (int j = 0; j < c; j++) {
			int maxSize = 0;
			map.clear();
			//개수
			for (int i = 0; i < r; i++) {
				if(arr[i][j] == 0)continue;
				if(map.containsKey(arr[i][j])) {
					map.put(arr[i][j], map.get(arr[i][j])+1);
				}else {
					map.put(arr[i][j], 1);
				}
			}
			//정렬
			list.clear();
			for (int key : map.keySet()) list.add(new C(key,map.get(key)));
			Collections.sort(list);
			
			//대입
			maxSize = list.size()*2>100? 100: list.size()*2;
			
			for (int i = 0; i < maxSize; i+=2) {
				cp[i][j] = list.get(i/2).k;
				cp[i+1][j] = list.get(i/2).v;
			}
			최대 = Math.max(maxSize, 최대);
		}
		arr=cp;
		r = 최대;
		
	}
	
	
	private static void print(int[][] arr2) {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(arr2[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		
	}

}