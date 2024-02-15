import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static int N,K;
	static String[] str;


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tk= new StringTokenizer(br.readLine());
        int T = Integer.valueOf(tk.nextToken());
        
        for (int tc = 1; tc <= T; tc++) {
        	tk= new StringTokenizer(br.readLine());
        	N = Integer.valueOf(tk.nextToken());
        	K = Integer.valueOf(tk.nextToken());
        	Set<Integer> set = new HashSet<>();
        	ArrayList<Integer> list = new ArrayList<>();
        	str = new String[100];
        	
        	tk= new StringTokenizer(br.readLine());
        	String[] A = tk.nextToken().split("");
        	for (int i = 0; i <N; i++) {
				str[i] = A[i];
			}
        	//pop append for문
        	for (int i = 0; i < N; i++) {
        		//문자열 자르기
        		for (int j = i; j < N +i; j = j+N/4) {
					String tmp = "";
					//문자열 더하기
					for (int k = 0; k < N/4; k++) {
						tmp = tmp +str[j+k];
					}
					int in = Integer.valueOf(tmp,16);
					if(!set.contains(in)) {
						set.add(in);
						list.add(in);
					}
					
				}
        		str[N+i] = str[i];
			}
        	list.sort(Comparator.reverseOrder());
        	System.out.println("#" + tc+" " +list.get(K-1));
        	
        	
        	
		}
        
    }
}

