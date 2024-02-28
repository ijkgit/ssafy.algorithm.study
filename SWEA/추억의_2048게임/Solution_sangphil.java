import java.util.*;
import java.io.*;

public class Solution_sangphil {
    static HashMap<String, Integer> map = new HashMap<String, Integer>(){{put("up",0);put("down",1);put("left",2);put("right",3);}};
    static String[] arr;
    static int ans, n;

    public static void main(String[] args) throws IOException {
        // System.setIn(Solution.class.getResourceAsStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            sb.append(String.format("#%d\n", t));

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = map.get(st.nextToken());
            ArrayList<ArrayDeque<Integer>> list = new ArrayList<ArrayDeque<Integer>>();
            for (int i = 0; i < n; i++) list.add(new ArrayDeque<Integer>());
            int[][] ans = new int[n][n];

            if (d == 0 || d == 1) {
                for (int i = 0; i < n; i++) {
                    st = new StringTokenizer(br.readLine());
                    for (int j = 0; j < n; j++) {
                        int num = Integer.parseInt(st.nextToken());
                        if (num != 0) list.get(j).add(num);
                    }
                }

                if (d == 0) {
                    for (int i = 0; i < n; i++) {
                        int j = 0;
                        while (!list.get(i).isEmpty()) {
                            if (ans[j][i] == 0) {
                                ans[j][i] = list.get(i).poll();
                            } else {
                                if (ans[j][i] == list.get(i).peek()) {
                                    ans[j][i] += list.get(i).poll();;
                                } 
                                j++;
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < n; i++) {
                        int j = n-1;
                        while (!list.get(i).isEmpty()) {
                            if (ans[j][i] == 0) {
                                ans[j][i] = list.get(i).pollLast();
                            } else {
                                if (ans[j][i] == list.get(i).getLast()) {
                                    ans[j][i] += list.get(i).pollLast();;
                                }
                                j--;
                            }
                        }
                    }
                }

            } else {
                for (int i = 0; i < n; i++) {
                    st = new StringTokenizer(br.readLine());
                    for (int j = 0; j < n; j++) {
                        int num = Integer.parseInt(st.nextToken());
                        if (num != 0) list.get(i).add(num);
                    }
                }

                if (d == 2) {
                    for (int i = 0; i < n; i++) {
                        int j = 0;
                        while (!list.get(i).isEmpty()) {
                            if (ans[i][j] == 0) {
                                ans[i][j] = list.get(i).poll();
                            } else {
                                if (ans[i][j] == list.get(i).peek()) {
                                    ans[i][j] += list.get(i).poll();;
                                }
                                j++;
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < n; i++) {
                        int j = n-1;
                        while (!list.get(i).isEmpty()) {
                            if (ans[i][j] == 0) {
                                ans[i][j] = list.get(i).pollLast();
                            } else {
                                if (ans[i][j] == list.get(i).getLast()) {
                                    ans[i][j] += list.get(i).pollLast();;
                                }
                                j--;
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    sb.append(ans[i][j] + " ");
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }    
}