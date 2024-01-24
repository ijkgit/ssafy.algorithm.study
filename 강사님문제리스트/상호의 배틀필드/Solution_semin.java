package SWEA.D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_semin {

    static int x, y, H, W, startX, startY; //(위쪽:0,-1)(아래:0,1)(오른쪽:1,0)(왼쪽:-1,0)
    static char[][] maps;
    static Map<Character, Integer[]> delta = new HashMap<>();
    static Map<Character, Character> shape = new HashMap<>();
    static char currentDir;

    public static void main(String[] args) throws IOException {
        delta = new HashMap<>();
        delta.put('U', new Integer[]{0, -1});
        delta.put('D', new Integer[]{0, 1});
        delta.put('R', new Integer[]{1, 0});
        delta.put('L', new Integer[]{-1, 0});

        shape.put('U', '^');
        shape.put('D', 'v');
        shape.put('L', '<');
        shape.put('R', '>');

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            maps = new char[H][W];

            for (int i = 0; i < H; i++) {
                String line = br.readLine();
                for (int j = 0; j < W; j++) {
                    maps[i][j] = line.charAt(j);
                    setStartPoint(i, j);
                }
            }
            x = startX;
            y = startY;
            int N = Integer.parseInt(br.readLine());
            String commands = br.readLine();
            for (int i = 0; i < N; i++) {
                func(commands.charAt(i));
                //printMap();
                //System.out.println("---------------");
            }
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    sb.append(maps[i][j]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void func(char command) {

        if (command == 'S') {
            int cnt = 1;
            L:
            while (true) {
                int nx = x + delta.get(currentDir)[0] * cnt;
                int ny = y + delta.get(currentDir)[1] * cnt++;
                if (nx < 0 || nx >= W || ny < 0 || ny >= H) {
                    break;
                }
                switch (maps[ny][nx]) {
                    case '#':
                        break L;
                    case '*':
                        maps[ny][nx] = '.';
                        break L;
                    case '.':
                    case '-':
                        break;
                }
            }

        } else {
            currentDir = command;
            int nx = x + delta.get(currentDir)[0];
            int ny = y + delta.get(currentDir)[1];
            //System.out.printf("%c %d %d\n",command,ny,nx);
            if (nx >= 0 && ny >= 0 && nx < W && ny < H) {
                if (maps[ny][nx] == '.') {
                    maps[ny][nx] = shape.get(currentDir);
                    maps[y][x] = '.';
                    x = nx;
                    y = ny;
                }
            }
        }

    }

    private static void setStartPoint(int i, int j) {
        switch (maps[i][j]) {
            case '^':
                currentDir = 'U';
                startX = j;
                startY = i;
                break;
            case 'v':
                currentDir = 'D';
                startX = j;
                startY = i;
                break;
            case '<':
                currentDir = 'L';
                startX = j;
                startY = i;
                break;
            case '>':
                currentDir = 'R';
                startX = j;
                startY = i;
                break;
        }
    }
}
