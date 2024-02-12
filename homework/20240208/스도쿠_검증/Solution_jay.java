import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        int test_case = Integer.parseInt(br.readLine());
 
        L: for (int t = 1; t <= test_case; t++) {
            int[][] graph = new int[9][9];
            for (int r = 0; r < 9; r++) {
                String row = br.readLine();
                StringTokenizer st = new StringTokenizer(row);
                for (int c = 0; c < 9; c++) {
                    graph[r][c] = Integer.parseInt(st.nextToken());
                }
            }
 
            for (int r = 0; r < 9; r++) {
                boolean[] status = new boolean[9];
                for (int c = 0; c < 9; c++) {
                    status[graph[r][c]-1] = true;
                }
                for (int i = 0; i < 9; i++) {
                    if(!status[i]) {
                        sb.append("#" + t + " 0\n");
                        continue L;
                    }
                }
            }
 
            for (int i = 0; i < 9; i += 3) {
                boolean[] status = new boolean[9];
                for (int r = i; r < i + 3; r++) {
                    for (int c = i; c < i + 3; c++) {
                        status[graph[r][c]-1] = true;
                    }
                }
                for (int j = 0; j < 9; j++) {
                    if(!status[j]) {
                        sb.append("#" + t + " 0\n");
                        continue L;
                    }
                }
            }
 
            for (int r = 0; r < 9; r++) {
                boolean[] status = new boolean[9];
                for (int c = 0; c < 9; c++) {
                    status[graph[c][r]-1] = true;
                }
                for (int i = 0; i < 9; i++) {
                    if(!status[i]) {
                        sb.append("#" + t + " 0\n");
                        continue L;
                    }
                }
            }
 
            sb.append("#" + t + " 1\n");
        }
        System.out.println(sb);
    }
}
