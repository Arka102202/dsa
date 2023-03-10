import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ExpressionAddOperators {
    static List<String> list = new ArrayList<>();
    public static List<String> addOperators(String num, int target) {
        list.clear();
        generate(0, num, target, "", 0, 0);
        return list;
    }
    static void generate(int index, String num, int target, String expression, long pre, long total) {
        if (index == num.length() && total == target)
            list.add(expression);
        else {
            for (int i = 1; i <= num.length() && (index <= num.length() - i); i++) {
                String s = num.substring(index, index + i);
                if ((i >= 2 && s.charAt(0) != '0') || i == 1) {
                    long n = Long.parseLong(s);
                    if(index == 0) generate(index + i, num, target, expression.concat(s), n, total + n);
                    else{
                        generate(index + i, num, target, expression.concat("+").concat(s), n, total + n);
                        generate(index + i, num, target, expression.concat("-").concat(s), -n, total - n);
                        generate(index + i, num, target, expression.concat("*").concat(s), pre * n, total - pre + pre * n);
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        System.out.println(addOperators("9999999999", 81));
        System.out.println(addOperators("123456789", 45));
        System.out.println(addOperators("3456237490", 9191));
        System.out.println(addOperators("232", 8));
        System.out.println(addOperators("123", 6));
    }
    static long solve(String e) {
        LinkedList<Long> intStack = new LinkedList<>();
        LinkedList<Character> opStack = new LinkedList<>();
        for (int i = 0; i < e.length(); i++) {
            if (e.charAt(i) >= '0' && e.charAt(i) <= '9') {
                int start = i;
                i++;
                while (i < e.length() && e.charAt(i) >= '0' && e.charAt(i) <= '9') {
                    i++;
                }
                intStack.add(Long.parseLong(e.substring(start, i)));
                i--;
            } else if (e.charAt(i) == '+' || e.charAt(i) == '-') opStack.add(e.charAt(i));
            else {

                long a = intStack.removeLast();
                i++;
                int b, start = 0;
                if (e.charAt(i) >= '0' && e.charAt(i) <= '9') {
                    start = i;
                    i++;
                    while (i < e.length() && e.charAt(i) >= '0' && e.charAt(i) <= '9') {
                        i++;
                    }
                }
                b = Integer.parseInt(e.substring(start, i));
                i--;
                intStack.add(a * b);
            }
        }
        long a = intStack.get(0);
        for (int i = 1; i < intStack.size(); i++) {
            if (opStack.get(i - 1) == '+') a += intStack.get(i);
            else a -= intStack.get(i);
        }
        return a;
    }

    static int solve(LinkedList<Integer> intStack, LinkedList<Character> opStack) {
        ListIterator<Integer> iterator = intStack.listIterator();
        ListIterator<Character> iterator1 = opStack.listIterator();

        int j = 0;
        while (iterator1.hasNext()) {
            if (iterator1.next() == '*') {
                int k = j;
                while (k > 0) {
                    iterator.next();
                    k--;
                }
                int a = iterator.next();
                iterator.remove();
                int b = iterator.next();
                iterator.remove();
                iterator.add(a * b);
                iterator1.remove();
                iterator = intStack.listIterator(j);
                j = 0;
            } else {
                j++;
            }
        }

        int a = intStack.get(0);
        for (int i = 1; i < intStack.size(); i++) {
            if (opStack.get(i - 1) == '+') a += intStack.get(i);
            else a -= intStack.get(i);
        }

        return a;

    }

}