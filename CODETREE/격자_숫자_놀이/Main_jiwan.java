import java.util.*;

//1. 행의 개수가 열의 개수보다 크거나 같은 경우
  // 정렬수행 (출현 빈도가 적은 순서)
  // 횟수가 같다면 해당 숫자가 작은 순서
  // 숫자와 해당하는 숫자의 출현 빈도수를 함께 출력
 

//
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 입력 받기
        int r = scanner.nextInt();
        int c = scanner.nextInt();
        int k = scanner.nextInt();
        
        int[][] board = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = scanner.nextInt();
            }
        }
        
        int time = 0;
        
        while (true) {
            int row = board.length;
            int col = board[0].length;
            
            // 종료 조건 확인
            if ((row >= r && col >= c) && (board[r - 1][c - 1] == k)) {
                break;
            }
            if (time > 100) {
                time = -1;
                break;
            }
            
            // 행과 열 중 더 긴 쪽을 기준으로 정렬
            if (row >= col) {
                board = sortRows(board, row, col);
            } else {
                board = sortCols(board, row, col);
            }
            time++;
        }
       
        System.out.println(time);
    }
    
    // 행 정렬 함수
    public static int[][] sortRows(int[][] A, int row, int col) {
        // 각 행의 숫자 카운트 저장을 위한 리스트
        List<List<Integer>> countList = new ArrayList<>();
        int maxCol = 0;
        
        for (int i = 0; i < row; i++) {
            // 각 숫자의 등장 횟수를 저장하는 리스트
            List<Integer[]> tempList = new ArrayList<>();
            for (int j = 0; j < col; j++) {
                if (A[i][j] == 0) continue;
                boolean found = false;
                // 해당 숫자가 이미 등장한 경우 카운트 증가
                for (Integer[] pair : tempList) {
                    if (pair[0] == A[i][j]) {
                        pair[1]++;
                        found = true;
                        break;
                    }
                }
                // 해당 숫자가 처음 등장하는 경우 리스트에 추가
                if (!found) {
                    tempList.add(new Integer[]{A[i][j], 1});
                }
            }
            
            // 등장 횟수를 기준으로 정렬
            tempList.sort((a, b) -> {
                if (a[1].equals(b[1])) {
                    return a[0].compareTo(b[0]);
                }
                return a[1].compareTo(b[1]);
            });
            
            // 정렬된 리스트를 하나의 리스트로 합침
            List<Integer> sortedList = new ArrayList<>();
            for (Integer[] pair : tempList) {
                sortedList.add(pair[0]);
                sortedList.add(pair[1]);
                maxCol = Math.max(maxCol, sortedList.size());
            }
            countList.add(sortedList);
        }
        
        // 2차원 배열로 변환
        int[][] nextMaps = new int[row][maxCol];
        for (int i = 0; i < countList.size(); i++) {
            for (int j = 0; j < countList.get(i).size(); j++) {
                nextMaps[i][j] = countList.get(i).get(j);
            }
        }
        
        return nextMaps;
    }
    
    // 열 정렬
    public static int[][] sortCols(int[][] A, int row, int col) {
        // 각 열의 숫자 카운트 저장을 위한 리스트
        List<List<Integer>> countList = new ArrayList<>();
        int maxRow = 0;
        
        for (int j = 0; j < col; j++) {
            // 각 숫자의 등장 횟수를 저장하는 리스트
            List<Integer[]> tempList = new ArrayList<>();
            for (int i = 0; i < row; i++) {
                if (A[i][j] == 0) continue;
                boolean found = false;
                // 해당 숫자가 이미 등장한 경우 카운트 증가
                for (Integer[] pair : tempList) {
                    if (pair[0] == A[i][j]) {
                        pair[1]++;
                        found = true;
                        break;
                    }
                }
                // 해당 숫자가 처음 등장하는 경우 리스트에 추가
                if (!found) {
                    tempList.add(new Integer[]{A[i][j], 1});
                }
            }
            
            // 등장 횟수를 기준으로 정렬
            tempList.sort((a, b) -> {
                if (a[1].equals(b[1])) {
                    return a[0].compareTo(b[0]);
                }
                return a[1].compareTo(b[1]);
            });
            
            // 정렬된 리스트를 하나의 리스트로 합침
            List<Integer> sortedList = new ArrayList<>();
            for (Integer[] pair : tempList) {
                sortedList.add(pair[0]);
                sortedList.add(pair[1]);
                maxRow = Math.max(maxRow, sortedList.size());
            }
            countList.add(sortedList);
        }
        
        // 2차원 배열로 변환
        int[][] nextMaps = new int[maxRow][col];
        for (int i = 0; i < countList.size(); i++) {
            for (int j = 0; j < countList.get(i).size(); j++) {
                nextMaps[j][i] = countList.get(i).get(j);
            }
        }
        
        return nextMaps;
    }
}
