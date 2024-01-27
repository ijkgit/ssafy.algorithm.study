import java.util.*;
import java.io.*;

class Solution_sangphil {
    static int[] countList;
    static int N;
    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int t = 0; t < T; t++) {
            sb.append("#").append(t+1).append(" ");
            N = Integer.parseInt(br.readLine());
            
            countList = new int[201];

            for (int i = 0; i < N; i++) {
                int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                for (int j = 0; j < 2; j++) {
                    if (arr[j] % 2 == 1) {
                        arr[j] = (arr[j]-1)/2;
                    } else {
                        arr[j] = (arr[j]-2)/2;
                    }
                }
                Arrays.sort(arr);
                for (int j = arr[0]; j <= arr[1]; j++) {
                    countList[j]++;
                }
            }
            Arrays.sort(countList);

            sb.append(countList[200]).append("\n");
        }
        System.out.println(sb);
    }
    
}