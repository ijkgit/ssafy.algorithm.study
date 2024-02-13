package edu.ssafy.im.BOJ.Bronze.B2.No3040;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	private static final int SIZE = 9;
	int[] arr = new int[SIZE];
	int[] sel = new int[7];
	StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		new Main().io();
	}

	private void io() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < SIZE; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		sol(0, 0);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private void sol(int i, int k) {
		if (sb.length() != 0) {
			return;
		}

		if (k == sel.length) {
			int sum = 0;
			for (int j = 0; j < sel.length; j++) {
				sum += sel[j];
			}
			if (sum == 100) {
				for (int j = 0; j < sel.length; j++) {
					sb.append(sel[j]).append("\n");
				}
			}
			return;
		}
		
		for (int j = i; j < SIZE; j++) {
			sel[k] = arr[j];
			sol(j+1, k+1);
		}
	}
}
