package assignment05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListStackTest {
    private LinkedListStack<String> stack;

    @BeforeEach
    void setUp() {
        stack = new LinkedListStack<>();
    }

    @Test
    public void testPush() {
        stack.push("A");
        assertEquals(1, stack.size());
        assertEquals("A", stack.peek());

        stack.push("B");
        assertEquals(2, stack.size());
        assertEquals("B", stack.peek());
    }

    @Test
    public void testPop() {
        stack.push("A");
        stack.push("B");

        assertEquals("B", stack.pop());  // Last element pushed is the first to be popped
        assertEquals(1, stack.size());
        assertEquals("A", stack.peek());

        assertEquals("A", stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPopFromEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> stack.pop());
    }

    @Test
    public void testPeek() {
        stack.push("A");
        stack.push("B");

        assertEquals("B", stack.peek());  // The top element should be "B"

        stack.pop();
        assertEquals("A", stack.peek());  // After popping "B", "A" should be the top
    }

    @Test
    public void testPeekFromEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> stack.peek());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(stack.isEmpty());

        stack.push("A");
        assertFalse(stack.isEmpty());

        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testClear() {
        stack.push("A");
        stack.push("B");
        assertEquals(2, stack.size());

        stack.clear();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    public void testSize() {
        assertEquals(0, stack.size());

        stack.push("A");
        assertEquals(1, stack.size());

        stack.push("B");
        assertEquals(2, stack.size());

        stack.pop();
        assertEquals(1, stack.size());

        stack.pop();
        assertEquals(0, stack.size());
    }

}
