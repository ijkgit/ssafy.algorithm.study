
import java.util.*;
import java.io.*;
public class Solution_jiwan {
/*
문자	의미
.	평지(전차가 들어갈 수 있다.)
*	벽돌로 만들어진 벽
#	강철로 만들어진 벽
-	물(전차는 들어갈 수 없다.)
^	위쪽을 바라보는 전차(아래는 평지이다.)
v	아래쪽을 바라보는 전차(아래는 평지이다.)
<	왼쪽을 바라보는 전차(아래는 평지이다.)
>	오른쪽을 바라보는 전차(아래는 평지이다.)
*/
/*문자	동작
U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
*/
	/*





각각의 문자는 위의 게임 맵 구성 요소 표에 있는 문자들만 포함하며, 전차는 단 하나만 있다.



각각의 문자는 위의 사용자가 넣을 수 있는 입력의 종류를 나타내는 표에 있는 문자들만 포함된다.
*/
	static char gameMap[][];
	public static void main(String[] args) throws IOException {
		
		//첫 번째 줄에 테스트 케이스의 수 T가 주어진다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(st.nextToken());
		
		for(int test_case=1; test_case<=T; test_case++) {
			//각 테스트 케이스의 첫 번째 줄에는 두 정수 H, W (2 ≤ H, W ≤ 20) 이 공백으로 구분되어 주어진다.
			int H,W;
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			//이는 게임 맵의 높이가 H, 너비가 W임을 나타낸다.
			gameMap = new char[H][W];
			
			//다음 H개의 각각의 줄에는 길이가 W인 문자열이 주어진다.
			for(int i=0; i<H; i++) {
				gameMap[i] = br.readLine().toCharArray();
					
			}
			//다음 줄에는 사용자가 넣을 입력의 개수를 나타내는 정수 N(0 < N ≤ 100) 이 주어진다.
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			
			//다음 줄에는 길이가 N인 문자열이 주어진다.
			char[] userInput = br.readLine().toCharArray();
			int tankX = 0;
			int tankY = 0;
			int direction = 0; //1U,2D,3L,4R
			L:
			for(int y=0; y<gameMap.length; y++) {
				for(int x=0; x<gameMap[y].length; x++) {
					if(isTank(y,x) ) {
						tankY =y;
						tankX =x;
						if(gameMap[y][x]=='^')direction =1;
						else if(gameMap[y][x]=='v')direction =2;
						else if(gameMap[y][x]=='<')direction =3;
						else {
							direction=4;
						}
						break L;
					}
				}
			}
			for(int i=0; i<userInput.length; i++) {
				
				switch(userInput[i]) {
				case 'U':
					gameMap[tankY][tankX]='^';
					direction=1;
					if(tankY-1>=0 && isLand(tankY-1,tankX)) {
						gameMap[tankY][tankX]='.';
						gameMap[tankY-1][tankX]='^';
						tankY -=1;
					}
						
					break;
				case 'D':
					gameMap[tankY][tankX]='v';
					direction=2;
					if(tankY+1<H&&isLand(tankY+1,tankX)) {
						gameMap[tankY][tankX]='.';
						gameMap[tankY+1][tankX]='v';
						tankY+=1;
					}
						
					break;
				case 'L':
					gameMap[tankY][tankX]='<';
					direction =3;
					if(tankX-1>=0&&isLand(tankY,tankX-1)) {
						gameMap[tankY][tankX]='.';
						gameMap[tankY][tankX-1]='<';
						tankX-=1;
					}
					break;
				case 'R':
					gameMap[tankY][tankX]='>';
					direction=4;
					if(tankX+1<W&&isLand(tankY,tankX+1)) {
						gameMap[tankY][tankX]='.';
						gameMap[tankY][tankX+1]='>';
						tankX+=1;
					}
					break;
				case 'S':
					switch(direction) {
					case 1: //^
						for(int loop=tankY-1; loop>=0; loop--) {
							if(gameMap[loop][tankX]=='#')break;
							if(gameMap[loop][tankX]=='*') {
								gameMap[loop][tankX]='.';
								break;
							}
						}
						break;
					case 2: //v
						for(int loop=tankY+1; loop<H; loop++) {
							if(gameMap[loop][tankX]=='#')break;
							if(gameMap[loop][tankX]=='*') {
								gameMap[loop][tankX]='.';
								break;
							}
						}
						break;
					case 3: //<
						for(int loop=tankX-1; loop>=0; loop--) {
							 if(gameMap[tankY][loop]=='#')break;
							if(gameMap[tankY][loop]=='*') {
								gameMap[tankY][loop]='.';
								break;
							}
						}
						break;
					case 4: //>
						for(int loop=tankX+1; loop<W; loop++) {
							 if(gameMap[tankY][loop]=='#')break;
							if(gameMap[tankY][loop]=='*') {
								gameMap[tankY][loop]='.';
								break;
							}
						}
						break;
					}

					break;
				}
				
			}
			System.out.print("#"+test_case+" ");
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					bw.write(gameMap[i][j]);
				}
				bw.write('\n');
			}
		}
		bw.flush();
	}
	private static boolean isLand(int y, int x) {
		if(gameMap[y][x]=='.') {
			return true;
		}
		return false;
	}
	private static boolean isTank(int y, int x) {
		if(gameMap[y][x]=='<' || gameMap[y][x]=='>' ||gameMap[y][x]=='v'||gameMap[y][x]=='^')
			return true;
		return false;
	}
}
