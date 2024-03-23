import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, S, T;
    private static ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> reverseList = new ArrayList<>();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++) {
            arrayList.add(new ArrayList<>());
            reverseList.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            arrayList.get(u).add(v);
            reverseList.get(v).add(u);
        }
        
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken()) - 1;
        T = Integer.parseInt(st.nextToken()) - 1;
        
        sb.append(sol());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int sol() {
        // 출근길
        boolean[] v1 = new boolean[N];
        v1[T] = true;
        dfs(S, arrayList, v1);

        boolean[] v2 = new boolean[N];
        dfs(T, reverseList, v2);

        // 퇴근길
        boolean[] v3 = new boolean[N];
        v3[S] = true;
        dfs(T, arrayList, v3);

        boolean[] v4 = new boolean[N];
        dfs(S, reverseList, v4);

        int ans = 0;
        for (int i=0; i<N; i++) {
            if (v1[i] && v2[i] && v3[i] && v4[i]) ans++;
        }

        return ans-2;
    }

    private static void dfs(int current, ArrayList<ArrayList<Integer>> graph, boolean[] v) {
        if(v[current]) return;
        
        v[current] = true;
        for (int next : graph.get(current)) {           
            dfs(next, graph, v);
        }   
    }
}
