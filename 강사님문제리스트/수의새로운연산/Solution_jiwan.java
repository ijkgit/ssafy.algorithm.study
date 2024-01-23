package problem_solving;

import java.util.*;
import java.io.*;



public class Solution_jiwan {
	static int graph[][]=new int[301][301];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// TODO Auto-generated method stub
		int number = 1;
        for (int i = 0; i <= 300; i++) {
            for (int j = i; j >= 0; j--) {
                graph[i-j][j] = number++;
                //System.out.println();
                //System.out.println(number);
            }
        }
        int test_case = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= test_case; tc++) {
            
            StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int x_index = 0; 
            int y_index = 0;
            int x_index2 = 0; 
            int y_index2 = 0;
            
            for (int i = 0; i <= 300; i++) {
                for (int j = 0; j <= 300; j++) {
                    if(graph[i][j] == x) { // 첫 숫자 위치를 저장
                        x_index = i;
                        y_index = j;
                    }
                    if(graph[i][j] == y) { // 두번째 숫자 위치를 저장
                        x_index2 = i;
                        y_index2 = j;
                    }
                }
            }
            // 계산
            int x_resultIndex = x_index + x_index2 + 1;
            int y_resultIndex = y_index + y_index2 + 1;
            
            System.out.println("#" + tc + " " + graph[x_resultIndex][y_resultIndex]);
        } // end of tc
	}

}
