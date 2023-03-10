import java.util.LinkedList;

public class EvalRPN {



    public static int evalRPN(String[] tokens) {
        int total;
        LinkedList<Integer> stack = new LinkedList<>();
        for(String s:tokens){
            if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")){
                int b = stack.poll();
                total = stack.poll();
                switch (s) {
                    case "+": {
                        total += b;
                        break;
                    }
                    case "-": {
                        total -= b;
                        break;
                    }
                    case "*": {
                        total *= b;
                        break;
                    }
                    case "/": {
                        total /= b;
                        break;
                    }
                }
                stack.push(total);
            }else  stack.push(Integer.parseInt(s));
        }
        return stack.getLast();
    }


    public static void main(String[] args) {

        System.out.println(evalRPN(new String[]{"4","3","-"}) == 1);
        System.out.println(evalRPN(new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"}) == 22);
        System.out.println(evalRPN(new String[]{"4","13","5","/","+"}) == 6);
        System.out.println(evalRPN(new String[]{"2","1","+","3","*"}) == 9);
    }






}
