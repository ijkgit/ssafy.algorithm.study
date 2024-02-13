import java.util.*;
import java.io.*;
 
public class Solution_sangphil {
	static boolean[] total;
	static int[] arr;
	static int[] brr;
	static int[] sel;
	static boolean[] visited;
	static int x, y;

    public static void main (String[] args) throws IOException {
        // System.setIn(Solution_GyuAndInCardGame.class.getResourceAsStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
        	total = new boolean[19];
        	arr = new int[9];
        	brr = new int[9];
        	sel = new int[9];
        	visited = new boolean[9];
        	x = 0;
        	y = 0;
        	
        	st = new StringTokenizer(br.readLine());
        	for (int i = 0; i < 9; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				total[arr[i]] = true;
			}
        	
        	int k = 0;
        	for (int i = 1; i <= 18; i++) {
				if (!total[i]) brr[k++] = i;
			}
        	
        	shoot(0);

            sb.append(String.format("#%d %d %d\n", t, x, y));
        }
 
        System.out.println(sb);
    }
    
    static void shoot (int depth) {
    	if (depth == 9) {
    		int a = 0;
    		int b = 0;
    		for (int i = 0; i < 9; i++) {
    			if (sel[i] > brr[i]) {
					a += sel[i] + brr[i];
				}
    			else if (sel[i] < brr[i]) {
    				b += sel[i] + brr[i];
				}
			}
    		if (a > b) {
    			x++;
    		} else if (a < b) {
    			y++;
    		}
    		return;
    	}
    	for (int i = 0; i < 9; i++) {
			if (!visited[i]) {
				visited[i] = true;
				sel[depth] = arr[i];
				shoot(depth+1);
				visited[i] = false;
			}
		}
    }

}