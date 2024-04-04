import java.util.*;
import java.io.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        Map<Character, Integer> map = new HashMap<>(); 
		map.put('R',0);
        map.put('T',0);
        map.put('C',0);
        map.put('F',0);
        map.put('J',0);
        map.put('M',0);
        map.put('A',0);
        map.put('N',0);
		
        for(int i=0;i<survey.length;i++){
            if(choices[i]==4){
                continue;
            }
            if(choices[i]<4){
                char ch = survey[i].charAt(0);
                map.replace(ch, map.get(ch)+4-choices[i]);
                continue;
            }
            if(choices[i]>4){
                char ch = survey[i].charAt(1);
                 map.replace(ch, map.get(ch)+choices[i]-4);
            }
        }
        
        String ans= map.get('R')>=map.get('T')?"R":"T";
        ans+=map.get('C')>=map.get('F')?"C":"F";
        ans+=map.get('J')>=map.get('M')?"J":"M";
        ans+=map.get('A')>=map.get('N')?"A":"N";
        return ans;
    }
}