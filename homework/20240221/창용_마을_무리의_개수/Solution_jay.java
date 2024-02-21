import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    private int n;
    private int m;
    private int[] set;
    private int[] height;

    public static void main(String[] args) throws IOException {
        new Solution().io();
    }

    private void io() throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            makeSet();

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                unionSet(a, b);
            }

            sb.append("#").append(t).append(" ").append(countSet()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private void makeSet() {
        set = new int[n];
        height = new int[n];
        for (int i = 0; i < n; i++) {
            set[i] = i;
        }
    }

    private int findSet(int org) {
        if (set[org] == org) return org;
        else return set[org] = findSet(set[org]);
    }
    
    private void unionSet(int org, int target) {
        int a = findSet(org);
        int b = findSet(target);
        
        if (a != b) {
            if(height[a] > height[b]) {
                set[b] = a;
            } else {
                set[a] = b;
                if(height[a] == height[b]) height[b]++;
            }
        }
    }
    
    private int countSet() {
        int[] cntArr = new int[n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            findSet(i); // 모든 자식을 1레벨로 레벨링
        }
        for (int i = 0; i < n; i++) {
            cntArr[set[i]]++;
        }
        for (int i = 0; i < n; i++) {
            if(cntArr[i] != 0) cnt++;
        }
        return cnt;
    }
}
