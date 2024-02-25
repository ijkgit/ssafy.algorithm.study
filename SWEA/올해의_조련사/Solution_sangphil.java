import java.io.*;

public class Solution_sangphil {
    static String[] arr;
    static int ans, n;

    public static void main(String[] args) throws IOException {
        // System.setIn(Solution.class.getResourceAsStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            sb.append(String.format("#%d ", t));
            n = Integer.parseInt(br.readLine().trim());
            arr = new String[n];

            for (int i = 0; i < n; i++) {
                arr[i] = br.readLine().trim();
            }

            int left = 0;
            int right = n-1;
            for (int i = 0; i < n; i++) {
                if (arr[left].compareTo(arr[right]) == 0) {
                    int l = left + 1;
                    int r = right - 1;
                    while (true) {
                        if (l >= r) {
                            sb.append(arr[left++]);
                            break;
                        } else {
                            if (arr[l].compareTo(arr[r]) == 0) {
                                l++; r--;
                            } else if (arr[l].compareTo(arr[r]) < 0) {
                                sb.append(arr[left++]);
                                break;
                            } else {
                                sb.append(arr[right--]);
                                break;
                            }
                        }
                    }
                } else if (arr[left].compareTo(arr[right]) < 0) {
                    sb.append(arr[left++]);
                } else {
                    sb.append(arr[right--]);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }    
}