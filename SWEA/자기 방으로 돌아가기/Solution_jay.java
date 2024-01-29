import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Integer>> array = new ArrayList();
        for (int t = 1; t <= testCase; t++) {
            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                String input = br.readLine();
                StringTokenizer st = new StringTokenizer(input);

                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());

                if (s > e) {
                    int tmp = s;
                    s = e;
                    e = tmp;
                }
                int si, ei;
                if (s % 2 == 0) {
                    si = s / 2 - 1;
                } else {
                    si = s / 2;
                }
                if (e % 2 == 0) {
                    ei = e / 2 - 1;
                } else {
                    ei = e / 2;
                }

                ArrayList<Integer> list = new ArrayList<>();
                list.add(si);
                list.add(ei);
                array.add(list);
            }
            Collections.sort(array, Comparator.comparing(list -> list.get(0)));
            int hour = 0;
            while (array.size() != 0) {
                boolean arr[] = new boolean[200];
                for (int k=0; k<array.size(); k++) {
                    ArrayList<Integer> list = array.get(k);
                    boolean status = true;
                    for (int i = list.get(0); i <= list.get(1); i++) {
                        if (arr[i]) {
                            status = false;
                            break;
                        }
                    }
                    if (status) {
                        for (int j = list.get(0); j <= list.get(1); j++) {
                            arr[j] = true;
                        }
                        array.remove(k);
                        k--;
                    }
                }
                hour++;
            }
            sb.append("#" + t + " " + hour + "\n");
        }
        System.out.print(sb);
    }
}
