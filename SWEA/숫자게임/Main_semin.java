import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**시초 떠요... 😊 
 * 정답을 봤는데, 지금의 저에게는 불가능한 풀이입니다. ㅎㅎㅎ */
public class Main_semin {

	static int size, Ans;
	static Set<Integer> newNums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			int number = Integer.parseInt(br.readLine());
			Ans = 0;
			recursive(number, 0);
			sb.append(Ans).append("\n");
		}
		System.out.println(sb);
	}

	private static void recursive(int number, int depth) {
		List<Character> nums = new ArrayList<>();
		for (Character ch : Integer.toString(number).toCharArray()) {
			nums.add(ch);
		}
		if (nums.size() <= 1) {
			Ans = Math.max(depth, Ans);
			return;
		}

		newNums = new HashSet<>();
		for (int i = 2; i <= nums.size(); i++) {
			size = i; // 1~n-1
			int[] idxs = new int[size];
			idxs[size - 1] = nums.size() - 1; // 맨 끝 인덱스 넣기
			idxCombination(0, 0, idxs, nums);
		}
		for (int num : newNums) {
			recursive(num, depth + 1);
		}
	}

	private static void idxCombination(int idx, int k, int[] idxs, List<Character> nums) {
		if (k == size - 1) { // 인덱스의 조합
			int mul = calculateMul(idxs, nums);
			newNums.add(mul);
			return;
		}
		if (idx == nums.size() - 1) { // 쪼개는 개수는 숫자 -1
			return;
		}
		idxs[k] = idx;
		idxCombination(idx + 1, k + 1, idxs, nums);
		idxCombination(idx + 1, k, idxs, nums);
	}

	private static int calculateMul(int[] idxs, List<Character> nums) {
		int mul = 1;
		String temp = "";
		int cnt = 0; // split 할 기준
		for (int i = 0; i < nums.size(); i++) {
			if (i < idxs[cnt]) {
				temp += nums.get(i);
			}
			if (i == idxs[cnt]) {
				temp += nums.get(i);
				mul *= Integer.valueOf(temp);
				temp = "";
				cnt++; // 기준을 업데이트 한다.
			}
		}
		return mul;
	}
}
