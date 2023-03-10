import java.util.LinkedList;

public class MinStack {
    LinkedList<Integer> stack;
    public MinStack() {
        stack = new LinkedList<>();
    }
    public void push(int val) {
        stack.push(val);
    }
    public void pop() {
        Integer val = stack.pop();
    }
    public int top() {
        return stack.getFirst();
    }
    public int getMin() {
        int minVal = Integer.MAX_VALUE;
        for(int i:stack) {
            if(i < minVal) minVal = i;
        }
        return minVal;
    }

    public static void main(String[] args) {

        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-4);
        minStack.push(1);
        minStack.push(0);
        minStack.push(2);
        minStack.push(9);
        minStack.push(0);
        minStack.push(-1);
        minStack.push(-3);
        minStack.push(4);
        minStack.push(7);
        System.out.println(minStack.getMin()); // return -3
        minStack.pop();
        System.out.println(minStack.top());
        minStack.stack.remove(Integer.valueOf(-4));// return 0
        System.out.println(minStack.getMin()); // return -2



    }



}
