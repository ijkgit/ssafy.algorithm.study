import java.util.*;
import java.io.*;

public class Solution_sangphil {
    static int T, N, M, K;
    static String ans;
    static int[] arr;
    
    public static void main(String[] args) throws IOException {
        // System.setIn(Solution_sangphil.class.getResourceAsStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        T = Integer.parseInt(br.readLine());
        
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            
            arr = new int[N];
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            
            // 오름차순 정렬
            Arrays.sort(arr);
            
            int inventory = 0;  // 재고 
            int count = 0;      // 이미 사간 손님 수
            ans = "Possible";
            
            // 예약자 올 시간 / M = 그동안 붕어빵 만드는 횟수
            // 총 만든거 - 그동안 왔던 손님 = 재고
            for (int curr : arr) {
                inventory = (curr / M) * K;
                
                if (inventory - count <= 0) {
                    ans = "Impossible";
                    break;
                }
                count++;
            }
        
            System.out.printf("#%d %s\n", t+1, ans);
        }
    }
}