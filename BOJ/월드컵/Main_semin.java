import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_semin {
    static int[] arr = {0, 1, 2, 3, 4, 5}; //A,B,C,D,E,F
    static List<int[]> pairs = new ArrayList<>();
    static int[][][] guessBoards;
    static int[] answer = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        guessBoards = new int[4][6][3]; // 4개 추측, 6개국 {승, 무, 패}
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 3; k++) { //Arrays.equals(guessBoards[guess][country], scoreBoard[country] 보다 약간 더 빠릅니당(180ms)
                    guessBoards[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        combination(0, 0, new int[2]); //대진표 만들기
        makeScore(new int[6][3], 0); //6개국 {승, 무, 패}

        System.out.printf("%d %d %d %d", answer[0], answer[1], answer[2], answer[3]);
    }

    private static void makeScore(int[][] scoreBoard, int idx) {
        if (idx == pairs.size()) {
            L:
            for (int guess = 0; guess < 4; guess++) {
                for (int country = 0; country < 6; country++) {
                    for(int k=0;k<3;k++){
                        if(guessBoards[guess][country][k]!=scoreBoard[country][k]){
                            continue L;
                        }
                    }
                }
                answer[guess] = 1;
            }
            return;
        }
        int countryA = pairs.get(idx)[0];
        int countryB = pairs.get(idx)[1];

        //A가 이김
        scoreBoard[countryA][0] += 1;
        scoreBoard[countryB][2] += 1;
        makeScore(scoreBoard, idx + 1);
        scoreBoard[countryA][0] -= 1;
        scoreBoard[countryB][2] -= 1;
        //B가 이김
        scoreBoard[countryA][2] += 1;
        scoreBoard[countryB][0] += 1;
        makeScore(scoreBoard, idx + 1);
        scoreBoard[countryA][2] -= 1;
        scoreBoard[countryB][0] -= 1;
        //무승부
        scoreBoard[countryA][1] += 1;
        scoreBoard[countryB][1] += 1;
        makeScore(scoreBoard, idx + 1);
        scoreBoard[countryA][1] -= 1;
        scoreBoard[countryB][1] -= 1;

    }

    private static void combination(int k, int idx, int[] selected) {
        if (k == 2) {
            pairs.add(selected.clone());
            return;
        }
        if (idx == arr.length) {
            return;
        }
        selected[k] = arr[idx];
        combination(k + 1, idx + 1, selected);
        combination(k, idx + 1, selected);
    }
}
