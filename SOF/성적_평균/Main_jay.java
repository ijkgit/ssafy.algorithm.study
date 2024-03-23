import java.io.*;
import java.util.*;

public class Main {
    private static int N, K;
    private static int[] score;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        score = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            score[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            sb.append(sol(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()))).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static String sol(int s, int e) {
        double sum = 0;
        for(int i = s; i < e; i++) {
            sum += score[i];
        }
        return String.format("%.2f", sum/(e-s));
    }
}
