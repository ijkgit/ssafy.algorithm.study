
import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Main
{

	public static void main(String args[]) 	{
		Scanner sc = new Scanner(System.in);
		int K=sc.nextInt();
		//동,서,남,북 (1,2,3,4)
		int max_with = 0;
		int max_len =0;
		int[] orders = new int[24];	
		for(int i =0; i<12 ;i = i+2) {
			orders[i] = sc.nextInt();
			orders[i+1] = sc.nextInt();
			orders[i+12] = orders[i];
			orders[i+13] = orders[i+1];
			if (orders[i]<3) {
				max_with = Math.max(max_with, orders[i+1]);
			}else {
				max_len = Math.max(max_len, orders[i+1]);
			}
		}

		int small_sq =0;
		for(int i =0; i<20;i++) {
			if (orders [i] == orders[i+4] && orders[i+2] == orders[i+6]) {
				small_sq = orders[i+5] * orders[i+3];
				
				break;
			}

		}
		System.out.println(K* ((max_len * max_with )-small_sq));
		

	}
}