package edu.ssafy.im.BOJ.Gold.G5.No1759;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/*
    생각이란 것을 하고 풀었다면 이렇게 풀지 않았을텐데...
    자음과 모음을 왜 분리했을까...... ㅠㅠ
 */
public class Main {
    private int L;
    private int C;
    private List<Character> consonants;
    private List<Character> vowels;
    private int[] conSel;
    private StringBuilder sb = new StringBuilder();
    private int[] vowSel;
    ArrayList<String> resList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        consonants = new ArrayList<>();
        vowels = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            char c = st.nextToken().charAt(0);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowels.add(c);
            } else {
                consonants.add(c);
            }
        }

        sol();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private void sol() {
        consonants.sort((o1, o2) -> o1 - o2);
        vowels.sort((o1, o2) -> o1 - o2);
        for (int i = 2; i <= L - 1; i++) {
            conSel = new int[i];
            vowSel = new int[L-i];
            consonantsPermutation(0, 0);
        }
        resList.sort((o1, o2) -> o1.compareTo(o2));
        for (String res: resList) {
            sb.append(res).append("\n");
        }
    }

    private void consonantsPermutation(int k, int v) {
        if (k == conSel.length) {
            vowelsPermutation(0, 0);
            return;
        }

        for (int i = 0; i < consonants.size(); i++) {
            if ((v & (1 << i)) == 0) {
                conSel[k] = i;
                consonantsPermutation(k + 1, v |= 1 << i);
            }
        }
    }

    private void vowelsPermutation(int k, int v) {
        if (k == vowSel.length) {
            makePassword();
            return;
        }

        for (int i = 0; i < vowels.size(); i++) {
            if ((v & (1 << i)) == 0) {
                vowSel[k] = i;
                vowelsPermutation(k + 1, v |= 1 << i);
            }
        }
    }

    private void makePassword() {
        char[] res = new char[L];
        int idx1 = 0, idx2 = 0;
        for (int i = 0; i < L; i++) {
            if(idx1 == vowSel.length) {
                res[i] = consonants.get(conSel[idx2++]);
                continue;
            } else if(idx2 == conSel.length){
                res[i] = vowels.get(vowSel[idx1++]);
                continue;
            }
            res[i] = vowels.get(vowSel[idx1]) < consonants.get(conSel[idx2]) ? vowels.get(vowSel[idx1++]) : consonants.get(conSel[idx2++]);
        }
        resList.add(String.valueOf(res));
    }
}
