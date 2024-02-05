package problem_solving;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main_sangphil {
	static StringBuilder sb = new StringBuilder();
	static int R, C, N;
	static BigInteger fullBit;
	static BigInteger bitSet = new BigInteger("0", 2);

	// 터짐 -> 추가 -> 대기 -> 대기 >> (반복)
	public static void main(String[] args) throws IOException {
		// System.setIn(Main.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < R*(C+2); i++) {
			if (i % (C+2) == 0 || i % (C+2) == C+1) {
				sb.append("0");
			} else {
				sb.append("1");
			}
		}
		fullBit = new BigInteger(sb.toString(), 2);
		
		sb = new StringBuilder();

		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				if (line.charAt(j) == 'O') {
					bitSet = bitSet.or(new BigInteger("1", 2).shiftLeft(i*(C+2)+j+1));
				}
			}
		}

		if (N == 1) {
			print(bitSet);
			return;
		}
		if (N % 2 == 0) {
			print(fullBit);
			return;
		}
		
		int t = 0;
		while (t <= N % 4 + 1) {
			bitSet = fullBit.and(fullBit.xor(bitSet.or(bitSet.shiftLeft(1)).or(bitSet.shiftRight(1)).or(bitSet.shiftLeft(C+2)).or(bitSet.shiftRight(C+2))));
			t += 2;
		}

		print(bitSet);
	}

	static void print(BigInteger b) {
		int in = 0;
		for (int i = 0; i < R; i++) {
			in++;
			for (int j = 1; j < C+1; j++) {
				if (b.and(new BigInteger("1").shiftLeft(in)).equals(BigInteger.ZERO)) {
					sb.append(".");
				} else {
					sb.append("O");
				}
				in++;
			}
			in++;
			sb.append("\n");
		}
		System.out.println(sb);
	}
}