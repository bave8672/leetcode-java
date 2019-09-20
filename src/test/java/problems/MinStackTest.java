package problems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static  org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("MinStack Test")
public class MinStackTest {
    @Test
    @DisplayName("1")
    void exampleTestCase() {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        assertEquals(minStack.getMin(), -3);
        minStack.pop();
        assertEquals(minStack.top(), 0);
        assertEquals(minStack.getMin(), -2);
    }
}
