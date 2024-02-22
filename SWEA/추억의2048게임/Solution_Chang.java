import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static int N,K;
	static int[][] arr;
	
	//상 하 좌 우
	static int[][] dxy = {{-1,0},{1,0},{0,-1},{0,1}};

	

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tk= new StringTokenizer(br.readLine());
        int T = Integer.valueOf(tk.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
        	tk= new StringTokenizer(br.readLine());
        	N = Integer.valueOf(tk.nextToken());
        	arr = new int[N][N];
        	String order = tk.nextToken();
        	for (int i = 0; i < N; i++) {
        		tk= new StringTokenizer(br.readLine());
        		for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.valueOf(tk.nextToken());
				}
			}
        	int[][] copy = new int[N][N];
        	
        	switch(order) {
        	//상0 하 1 좌 2 우3
        	//case마다 for문 순회 순서 중요
        	case "up":
        		for (int j = 0; j < N; j++) {
        			int level = 0;
        			int tmp = 0;
        			for (int i = 0; i < N; i++) {
        				if(arr[i][j] == 0) {
        					continue;
        				}
        				else if( tmp == arr[i][j]) {
        					level--;
        					copy[level][j] += arr[i][j];
        					tmp = 0;
        					arr[i][j] = 0;
        					level++;
        					
        				}else {
        					tmp = arr[i][j];
        					copy[level][j] = arr[i][j];
        					level++;
        				}
					}
				}
        		break;
        	case "down":
        		for (int j = 0; j < N; j++) {
        			int level = N-1;
        			int tmp = 0;
        			for (int i = N-1; i >=0; i--) {
        				if(arr[i][j] == 0) {
        					continue;
        				}
        				else if( tmp == arr[i][j]) {
        					level++;
        					copy[level][j] += arr[i][j];
        					tmp = 0;
        					arr[i][j] = 0;
        					level--;
        					
        				}else {
        					tmp = arr[i][j];
        					copy[level][j] = arr[i][j];
        					level--;
        				}
					}
				}	
        		break;
        	//정순
        	case "left":
        		for (int i = 0; i < N; i++) {
        			int level = 0;
        			int tmp = 0;
        			for (int j = 0; j < N; j++) {
        				if(arr[i][j] == 0) {
        					continue;
        				}
        				else if( tmp == arr[i][j]) {
        					level--;
        					copy[i][level] += arr[i][j];
        					tmp = 0;
        					arr[i][j] = 0;
        					level++;
        					
        				}else {
        					tmp = arr[i][j];
        					copy[i][level] = arr[i][j];
        					level++;
        				}
					}
				}	
        		break;
        	case "right":
        		for (int i = 0; i < N; i++) {
        			int level = N-1;
        			int tmp = 0;
        			for (int j = N-1; j >=0; j--) {
        				if(arr[i][j] == 0) {
        					continue;
        				}
        				else if( tmp == arr[i][j]) {
        					level++;
        					copy[i][level] += arr[i][j];
        					tmp = 0;
        					arr[i][j] = 0;
        					level--;
        					
        				}else {
        					tmp = arr[i][j];
        					copy[i][level] = arr[i][j];
        					level--;
        				}
					}
				}	
        		break;
        	
        	}
        	
        	sb.append("#").append(tc).append("\n");

        	for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(copy[i][j]).append(" ");
				}
				sb.append("\n");
				
			}
        
        	
        	
        	
      	
		}
        System.out.println(sb);
        
    }
}

