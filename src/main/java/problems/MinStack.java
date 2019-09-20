package problems;

import java.util.Stack;

/**
 * https://leetcode.com/problems/min-stack/
 *
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 *     push(x) -- Push element x onto stack.
 *     pop() -- Removes the element on top of the stack.
 *     top() -- Get the top element.
 *     getMin() -- Retrieve the minimum element in the stack.
 *
 *
 *
 * Example:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 */
class MinStack {
    private int min = Integer.MAX_VALUE;
    private final Stack<Integer> stack = new Stack();

    public void push(int number) {
        // add an additional reference to the previous minimum behind the next minimum in the stack
        if (number <= min) {
            stack.push(min);
            min = number;
        }
        stack.push(number);
    }

    //
    public void pop() {
        // if this is a minimum, also pop off the ref to the previous minimum
        if (stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}