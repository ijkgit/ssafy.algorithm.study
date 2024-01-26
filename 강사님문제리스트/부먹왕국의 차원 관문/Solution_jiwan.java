import java.io.*;
import java.util.*;

public class Solution_jiwan {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        StringBuilder result = new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int[] cities = new int[N];

            
           

            int idx = 0;
             int portal = 0;
            for (int i = 0; i < N; i++) {
                cities[i] = Integer.parseInt(st.nextToken());
                if (cities[i] == 0) idx += 1;
                else{
                    idx=0;
                }
                if(idx==D) {
                    portal++;
                    idx=0;
                }
            }

           

            result.append("#").append(test_case).append(" ").append(portal).append("\n");
        }

        System.out.println(result.toString());
    }
}
