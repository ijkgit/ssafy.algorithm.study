import java.util.*;
import java.io.*;

public class Solution_sangphil {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int t = 0; t < T; t++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int r = sc.nextInt();
            int c = sc.nextInt();

            int rowDistance = Math.abs(x - r);
            int colDistance = Math.abs(y - c);

            int max = Math.max(rowDistance, colDistance) * 2;

            System.out.printf("#%d %d\n", t+1, Math.abs(rowDistance - colDistance) % 2 == 1 ? max - 1 : max);
        }
        
    }
    
}
