import java.util.*;
import java.io.*;

public class Solution_sangphil {
    static char sampleB[] = {'0','1'};
	static char sampleT[] = {'0','1','2'};
	static int N, ans;
    static int transB;
    static int transT;
    static char[] arr;
    static char[] brr;
    static char[] arrr;
    static char[] brrr;

    public static void main (String[] args) throws IOException {
        // System.setIn(Solution.class.getResourceAsStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
         
        for (int t = 1; t <= T; t++) {
            arr = br.readLine().toCharArray();
			brr = br.readLine().toCharArray();

            shoot();

            sb.append(String.format("#%d %d\n", t, ans));
        }

        System.out.println(sb);
    }
    
    static void shoot () {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < brr.length; j++) {
                for (int k = 0; k < sampleB.length; k++) {
                    arrr = arr.clone();
                    arrr[i] = sampleB[k];
                    for (int h = 0; h < sampleT.length; h++) {
                        brrr = brr.clone();
                        brrr[j] = sampleT[h];
                        
                        transB = Integer.parseInt(String.valueOf(arrr), 2);
                        transT = Integer.parseInt(String.valueOf(brrr), 3);
                        
                        if(transB == transT) {
                            ans = transB;
                            return;
                        }
                    }
                }
                
            }
        }
    }
}

