import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        StringBuilder sb = new StringBuilder(); 
        for (int test_case = 1; test_case <= T; test_case++) { 
            char[] Bin = br.readLine().toCharArray(); // 이진수를 문자 배열로 변환하여 저장
            char[] Tri = br.readLine().toCharArray(); // 삼진수를 문자 배열로 변환하여 저장
            boolean flag = false; // 두 수가 같아지는 경우를 판별하기 위한 플래그 변수
            long answer = 0; // 결과 값 저장 변수
            for (int i = 0; i < Bin.length; i++) { // 이진수의 각 비트에 대해 반복
                if (Bin[i] == '0') // 현재 비트가 0이면
                    Bin[i] = '1'; // 1로 변경
                else
                    Bin[i] = '0'; // 1이면 0으로 변경

                for (int j = 0; j < Tri.length; j++) { // 삼진수의 각 자리에 대해 반복
                    char temp = Tri[j]; // 현재 삼진수 자리의 값 저장
                    for (char k = '0'; k < '3'; k++) { // 0부터 2까지의 숫자에 대해 반복
                        if (temp == k) // 현재 삼진수 자리의 값이 k와 같으면 건너뜀
                            continue; 
                        Tri[j] = k; // 삼진수 자리의 값을 k로 변경
                        sb.setLength(0); 
                        for (int t = 0; t < Bin.length; t++) // 이진수의 각 비트에 대해 반복하여
                            sb.append(Bin[t]); // StringBuilder에 이진수 추가
                        long bn = Long.parseLong(sb.toString(), 2); // 이진수를 long 형으로 변환하여 저장
                        sb.setLength(0); 
                        for (int t = 0; t < Tri.length; t++) // 삼진수의 각 자리에 대해 반복하여
                            sb.append(Tri[t]); // StringBuilder에 삼진수 추가
                        long tn = Long.parseLong(sb.toString(), 3); // 삼진수를 long 형으로 변환하여 저장
                        if (bn == tn) { // 이진수와 삼진수가 같으면
                            flag = true; // 플래그를 true로 설정하고
                            answer = bn; // 결과 값을 저장하고
                            break; // 반복문 종료
                        }
                    }
                    if (flag) // 플래그가 true이면
                        break; // 반복문 종료
                    Tri[j] = temp; // 삼진수 자리의 값을 원래 값으로 복원
                }
                if (flag) // 플래그가 true이면
                    break; // 반복문 종료
                if (Bin[i] == '0') // 현재 비트가 0이면
                    Bin[i] = '1'; // 1로 변경
                else
                    Bin[i] = '0'; // 1이면 0으로 변경
            }
            System.out.println("#" + test_case + " " + answer); // 결과 출력
        }
    }
}
