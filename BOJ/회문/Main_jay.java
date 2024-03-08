import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 투포인터
 * 
 * 세가지 경우의 수가 존재
 * 1. 처음과 끝이 같은 경우 -> 그대로 다음 인덱스 탐색
 * 2. 처음이 다른 경우 -> 처음 인덱스만 증가, 카운트 증가
 * 3. 끝이 다른 경우 -> 끝 인덱스만 감소, 카운트 증가
 * 
 * 문자열의 인덱스를 서로 좁혀가면서 계속 탐색
 * 
 * 종료 조건
 * 1. 제거된 문자열이 2 이상인 경우 => 일반 문자열
 * 2. 처음과 끝이 만난 경우 => 문자열 전체 탐색 끝
 */

public class Main {
	private static int N;
	private static String string;
	private static char[] stringArray;
	private static int length;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			string = br.readLine();
			length = string.length();
			stringArray = new char[length];
			stringArray = string.toCharArray();
			sb.append(find(0, length-1, 0)).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static int find(int start, int end, int cnt) {
		if (cnt >= 2) return 2;
		if (start >= end) return cnt;
		
		if (stringArray[start] == stringArray[end]) return find(start+1, end-1, cnt);
		else return Math.min(find(start+1, end, cnt+1), find(start, end-1, cnt+1));
	}
}
