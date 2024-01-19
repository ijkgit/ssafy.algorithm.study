package edu.ssafy.im.BOJ.No2477;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main_semin {
    private static final int SIDE = 6;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        List<Pair<Integer, Integer>> len = new ArrayList<>();
        int[] order = new int[SIDE * 2];

        for (int i = 0; i < SIDE; i++) {
            int dir = sc.nextInt();
            int length = sc.nextInt();
            len.add(new Pair<>(dir, length)); // 방향과 길이 저장
            order[i] = dir; // 방향 순서
            order[i + SIDE] = dir; // 연달아 꺾는 것 조사
        }

        int bigSq = 0, smallSq = 0;
        for (int i = 3; i < SIDE * 2; i++) {
            int next = (i + 1) % SIDE;
            int nextOfNext = (i + 2) % SIDE;
            int nextOfNextOfNext = (i + 3) % SIDE;
            if (order[i] == order[nextOfNext] && order[next] == order[nextOfNextOfNext]) {
                smallSq = len.get(next).getValue() * len.get(nextOfNext).getValue();
                bigSq = len.get((i - 1) % SIDE).getValue() * len.get((i - 2) % SIDE).getValue();
                break;
            }
        }
        System.out.println((bigSq - smallSq) * k);
    }

    private static class Pair<T, T1> {
        int direction;
        int length;
        public Pair(int direction, int length) {
            this.direction = direction;
            this.length = length;
        }
        public int getValue() {
            return length;
        }
    }
}
