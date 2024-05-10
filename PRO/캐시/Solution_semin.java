import java.util.*;

class Solution {
    public int solution(int c, String[] cities) {
        int answer = 0;
        List<String> list = new ArrayList<>();
        L: for(String city: cities){
            city=city.toLowerCase();
            //cashe가 다 차기 전에 중복된 값이 올 수도 o -> 중복 체크를 젤 먼저 한다.
            //remove는 뒤에서부터 해야 함
            for(int i=list.size()-1;i>=0;i--){ 
                if(list.get(i).equals(city)){
                    list.remove(i);
                    list.add(city);
                    answer+=1;
                    continue L;
                }
            }
            //cashe가 덜찼으면 그냥 넣기
            if(list.size()<c){
                list.add(city);
                answer+=5;
                continue L;
            }
            //cashe가 0보다 크면 remove 하고 새 거 넣기
            if(list.size()>0){
                list.remove(0);
                list.add(city);
                answer+=5;
                continue;
            }
            answer+=5; //cashSize=0 인 경우
        }
        return answer;
    }
}