import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[2 * n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n * 2; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> q = new LinkedList<Integer>();
        boolean[] visited = new boolean[2 * n];
        
        int start = 0;
        int end = n-1;
        int s = 0;
        int cnt = 0;

        while (cnt < k) {
            start = (2 * n + start - 1) % (2*n);
            end = (2 * n + end - 1) % (2*n);

            int size = q.size();
            for (int i = size; i > 0; i--) {
                int num = q.poll();
                //int next = (num + 1) % (2*n);
                if (num != end) q.add(num);
                else visited[num] = false;
            }
            
            size = q.size();
            for (int i = size; i > 0; i--) {
                int num = q.poll();
                int next = (num + 1) % (2*n);
                if (next == end && arr[next] >= 1) {
                    arr[next] --;
                    visited[num] = false;
                    if (arr[next] == 0) cnt++;
                    continue;
                }
                if (!visited[next] && arr[next] >= 1) {
                    visited[next] = true;
                    visited[num] = false;
                    arr[next]--;
                    q.add(next);
                    if (arr[next] == 0) cnt++;
                } else q.add(num);
            }
            
            
            if (arr[start] >= 1) {
                arr[start]--;
                q.add(start);
                visited[start] = true;
                if (arr[start] == 0) cnt++;
            }
            
            s ++;
            
        }
        System.out.println(s);
    }
}