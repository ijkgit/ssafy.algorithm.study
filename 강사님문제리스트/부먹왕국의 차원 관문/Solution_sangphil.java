import java.util.*;
import java.io.*;

class Solution_sangphil {
    static int[] arr;
    static int N, D;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int t = 0; t < T; t++) {
            sb.append("#").append(t+1).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            
            // int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            st = new StringTokenizer(br.readLine());

            int ans = 0;
            int sum = 0;
            int curr = 0;

            for (int i = 0; i < N; i++) {
                int temp = Integer.parseInt(st.nextToken());
                if ( temp == 0) {
                    sum ++;
                } else {
                    ans += sum/D;
                    sum = 0;
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
    
}