import java.util.*;
import java.io.*;
public class BOJ_2477_SEO {
	static int k;
	//Big Area - Small Area = answer
	private static final String[] code = new String[]{"231414", "231424", "232314", "231314"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        
        List<Node> arr = new ArrayList<>();
        
        for (int i = 0; i < 6; i++) {
            String input = br.readLine();
            StringTokenizer st = new StringTokenizer(input);
            int input1 = Integer.parseInt(st.nextToken());
            int input2 = Integer.parseInt(st.nextToken());
            arr.add(new Node(input1, input2));
        }

        int direct = directionCheck(arr);

        switch (direct) {
            case 0:
                System.out.println((arr.get(0).len * arr.get(1).len - arr.get(3).len * arr.get(4).len) * k);
                break;
            case 1:
                System.out.println((arr.get(1).len * arr.get(2).len - arr.get(4).len * arr.get(5).len) * k);
                break;
            case 2:
                System.out.println((arr.get(4).len * arr.get(5).len - arr.get(1).len * arr.get(2).len) * k);
                break;
            case 3:
                System.out.println((arr.get(0).len * arr.get(5).len - arr.get(2).len * arr.get(3).len) * k);
                break;
            default:
                break;
        }
    }
    static class Node {
        int dir, len;
        Node(int dir, int len) {
            this.dir = dir;
            this.len = len;
        }
    }
    public static int directionCheck(List<Node> arr) {
        StringBuilder sb = new StringBuilder();
        boolean check = false;

        for (int i = 0; i < code.length; i++) {
            for (int j = 0; j < 6; j++) {
                for (Node node : arr) {
                    sb.append(node.dir);
                }
                if (code[i].equals(sb.toString())) {
                	check = true;
                    break;
                } else {
                    sb.setLength(0);
                    arr.add(arr.get(0));
                    arr.remove(0);
                }
            }
            if (check) {
                return i;
            }
        }
        return -1;
    }

   

}
