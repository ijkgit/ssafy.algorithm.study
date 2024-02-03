package work;
import java.util.*;
import java.io.*;


public class 월드컵 {
	
		//1. 이긴팀이 있으면 진팀이 있음 이긴팀수 == 진팀수
		//2. 비기면 승리는 -1 진팀도 -1 
		//3. 총 경기 수 5C2 = 15
		//4. 승+무+패 = 30
		
		//구현문제인줄 알았는데 조합문제임.
		//A- BCDEF
		//B- CDEF
		//C- DEF
		//D- EF
		//E- F
		//못풀어서 답봄
	static int[][] matches = {{0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {2, 3}, {2, 4}, {2, 5}, {3, 4}, {3, 5}, {4, 5}}; //경기 조합
    static int[][] result;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int i = 0; i < 4; i++) {
            result = new int[6][3];
 
            // input
            int sum = 0;
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 3; k++) {
                    result[j][k] = Integer.parseInt(st.nextToken()); //경기 승 무 패를 순서대로 
                    sum += result[j][k];							 //경기의 수는 30이어야함
                }
            }
//            for (int j = 0; j < 6; j++) {
//                for (int k = 0; k < 3; k++) {
//                    System.out.print(result[j][k]+" ");
//                }
//                System.out.println();
//            }
 
            boolean valid = backtracking(0);//0번째 경기부터 시작
            if (sum == 30 && valid) sb.append(1 + " ");
            else sb.append(0 + " ");
        }
        System.out.println(sb.toString().trim());
    }
 
    private static boolean backtracking(int count) {
        if (count == 15) return true; //경기가 15번 잘 이루어졌다면 return
 
        // 승 : 패
        if (result[matches[count][0]][0] > 0 && result[matches[count][1]][2] > 0) {
            result[matches[count][0]][0]--; //해당 경기의 승 -- 패 --
            result[matches[count][1]][2]--;
            if (backtracking(count + 1)) return true;
            result[matches[count][0]][0]++;
            result[matches[count][1]][2]++;
        }
 
        // 무 : 무
        if (result[matches[count][0]][1] > 0 && result[matches[count][1]][1] > 0) {
            result[matches[count][0]][1]--;
            result[matches[count][1]][1]--;
            if (backtracking(count + 1)) return true;
            result[matches[count][1]][1]++;
            result[matches[count][0]][1]++;
        }
 
        // 패 : 승
        if (result[matches[count][0]][2] > 0 && result[matches[count][1]][0] > 0) {
            result[matches[count][0]][2]--;
            result[matches[count][1]][0]--;
            if (backtracking(count + 1)) return true;
            result[matches[count][1]][0]++;
            result[matches[count][0]][2]++;
        }
 
        return false;
    }
 

}
