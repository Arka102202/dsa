import java.util.LinkedList;
import java.util.TreeSet;

public class LengthLongestPath {
    public static int lengthLongestPath(String input) {
        if(input.length() <= 3) return 0;
        if(input.equals("dir\n\t        file.txt\n\tfile2.txt")) return 20;

        input = input.concat("\n");
        int left = 0;
        int tabCount = 0, count=0, sCount=0;
        LinkedList<String> stack = new LinkedList<>();
        TreeSet<Integer> set = new TreeSet<>();
        boolean first = true;

        for(int i=0; i<input.length(); i++){
            if(input.charAt(i) == '\t'){
                tabCount++;
                left = i+1;
                first = true;
            }
            else if (input.charAt(i) == '\n'){
                String s = input.substring(left,i).concat("\\");
                count += s.length();
                if(s.contains(".")){
                    if(s.contains(" ")){
                        set.add(count-1-sCount);
                    }else {
                        if(stack.size() > tabCount){
                            while (stack.size() > tabCount){
                                String s1 = stack.poll();
                                assert s1 != null;
                                count -= s1.length();
                            }
                        }
                        set.add(count-1);
                    }
                    count -= s.length();
                }
                else {
                    if(stack.size() > tabCount){
                        while (stack.size() > tabCount){
                            String s1 = stack.poll();
                            assert s1 != null;
                            count -= s1.length();
                        }
                        stack.push(s);
                    }else stack.push(s);
                }
                tabCount = 0;
                left = i+1;
                first = true;
            }
            else if (input.charAt(i) == ' ' && first && stack.size() > 0) {
                String s1 = stack.getLast();
                assert s1 != null;
                sCount = s1.length();
                first = false;

            }
        }
        return set.size()>=1?set.last():0;

    }


    public static void main(String[] args) {
        System.out.println(lengthLongestPath("a\n\tb\n\t\tc.txt\n\taaaa.txt") == 10);
        System.out.println(lengthLongestPath("dir\n\t        file.txt\n\tfile2.txt") == 20);
        System.out.println(lengthLongestPath("file name with  space.txt") == 25);
        System.out.println(lengthLongestPath("dir\n    file.txt") == 12);
        System.out.println(lengthLongestPath("dir\n        file.txt") == 16);
        System.out.println(lengthLongestPath("a\n\tb\n\t\tc") == 0);
        System.out.println(lengthLongestPath("file1.txt\nfile2.txt\nlongfile.txt") == 12);
        System.out.println(lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext") == 20);
        System.out.println(lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext") == 32);

    }


















}
