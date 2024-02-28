import java.util.*;
import java.io.*;

public class Solution_sangphil {
    static TreeSet<Integer> tree;
    static int ans, n;

    public static void main(String[] args) throws IOException {
        // System.setIn(Solution_sangphil.class.getResourceAsStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            ArrayDeque<Character> q = new ArrayDeque<Character>();
            tree = new TreeSet<Integer>();
            String line = br.readLine();
            for (int i = 0; i < n; i++) {
                q.add(line.charAt(i));
            }
            int divider = n/4;
            for (int i = 0; i < n; i++) {
                int size = tree.size();
                String str = "0x"+q.peek();
                q.add(q.poll());
                for (int j = 1; j < n; j++) {
                    if (j % divider == 0) {
                        tree.add(Integer.decode(str));
                        str = "0x";
                    }
                    str += q.peek();
                    q.add(q.poll());
                }

                if (size == tree.size()) break;
                q.add(q.poll());
            }

            for (int i = 1; i < k; i++) {
                tree.pollLast();
            }
            
            sb.append(String.format("#%d %d\n", t, tree.pollLast()));
        }
        System.out.println(sb);
    }    
}