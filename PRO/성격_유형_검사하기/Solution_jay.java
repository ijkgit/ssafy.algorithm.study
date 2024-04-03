class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        
        int s1 = 0, s2 = 0, s3 = 0, s4 = 0;
        for (int i = 0; i < survey.length; i++) {
            String s = survey[i];
            int c = 4 - choices[i];
            
            if (s.charAt(0) == 'R') s1 += c;
            if (s.charAt(0) == 'T') s1 -= c;
            if (s.charAt(0) == 'C') s2 += c;
            if (s.charAt(0) == 'F') s2 -= c;
            if (s.charAt(0) == 'J') s3 += c;
            if (s.charAt(0) == 'M') s3 -= c;
            if (s.charAt(0) == 'A') s4 += c;
            if (s.charAt(0) == 'N') s4 -= c;
        }
        
        StringBuilder sb = new StringBuilder();
        if (s1 >= 0) sb.append("R");
        else sb.append("T");
        
        if (s2 >= 0) sb.append("C");
        else sb.append("F");
        
        if (s3 >= 0) sb.append("J");
        else sb.append("M");
        
        if (s4 >= 0) sb.append("A");
        else sb.append("N");
        
        answer = sb.toString();
        
        return answer;
    }
}
