import java.util.*;
import java.io.*;

public class Main {
    static class Pair {
        int x, y;
        public Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static final int[] dx = {1,-1,0,0};
    static final int[] dy = {0,0,1,-1};

    static int n;
    static int[][] arr;
    static int[][] visited;
    static int[][] visited2;
    static int[][] relation;

    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        
        arr = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        visited = new int[n][n];
        for (int t = 0; t < 4; t++) {
            visited2 = new int[n][n];
            ArrayList<Pair> list = new ArrayList<Pair>();

            int cnt = 1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j] == 0) {
                        int count = bfs(i, j, cnt++);
                        list.add(new Pair(arr[i][j], count));
                    }
                }
            }

            relation = new int[list.size()+1][list.size()+1];
            cnt = 1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j] != 0) {
                        bfs2(i, j, cnt++, visited[i][j]);
                    }
                }
            }

            // combination (리스트사이즈, 2)
            for (int i = 1; i < list.size(); i++) {
                for (int j = i+1; j < list.size() + 1; j++) {
                    ans += (relation[i][i] + relation[j][j]) * list.get(i-1).x * list.get(j-1).x * relation[i][j];                 
                }
            }

            // 돌리기
            rolling();

        }
        System.out.println(ans);
    }

    static void bfs2(int x, int y, int cnt, int nn) {
        Deque<Pair> q = new LinkedList<Pair>();
        q.add(new Pair(x, y));
        visited[x][y] = 0;
        relation[cnt][cnt]++;

        while (!q.isEmpty()) {
            Pair p = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if (visited[nx][ny] == 0) continue;
                    
                    if (visited[nx][ny] == nn) {
                        visited[nx][ny] = 0;
                        relation[cnt][cnt]++;
                        q.add(new Pair(nx, ny));
                    } else {
                        relation[cnt][visited[nx][ny]]++;
                        relation[visited[nx][ny]][cnt]++;
                    }
                }
            }
        }
    }

    static int bfs (int x, int y, int nn) {
        int count = 0;
        Deque<Pair> q = new LinkedList<Pair>();
        q.add(new Pair(x, y));
        visited[x][y] = nn;

        while (!q.isEmpty()) {
            Pair p = q.poll();
            count++;

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (0 > nx || nx >= n || 0 > ny || ny >= n) continue;
                if (visited[nx][ny] != 0) continue;
                if (arr[nx][ny] != arr[x][y]) continue;
                visited[nx][ny] = nn;
                q.add(new Pair(nx, ny));
            }
        }
        return count;
    }

    static void rolling () {
        center();
        partial();
    }

    static void partial () {
        int mid = (int)n/2;
        int[] jumpX = {0, 0, mid+1, mid+1};
        int[] jumpY = {0, mid+1, 0, mid+1};
        int nx = 0;
        int ny = 0;
        for (int i = 0; i < 4; i++) {
            nx = jumpX[i];
            ny = jumpY[i];
            rotate(nx, ny, (int)(n-1)/2);
        }
    }

    static void rotate(int x, int y, int n) {
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = arr[x+i][y+j];
                arr[x+i][y+j] = arr[x+n - i - 1][y+j];
                arr[x+n - i - 1][y+j] = temp;

            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = arr[x+i][y+j];
                arr[x+i][y+j] = arr[x+j][y+i];
                arr[x+j][y+i] = temp;
            }
        }
    }

    static void center() {
        Deque<Integer> q = new LinkedList<Integer>();
        int mid = (int)n/2;
        for (int i = 0; i < n; i++) {
            q.add(arr[i][mid]);
        }
        for (int i = 0; i < n; i++) {
            int shadow = arr[mid][i];
            arr[mid][i] = q.poll();
            if (i == mid) continue;
            arr[n-i-1][mid] = shadow;
        }
    }
}