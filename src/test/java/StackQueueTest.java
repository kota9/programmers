import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackQueueTest {

    private final StackQueue stackQueue = new StackQueue();

    @Test
    void removesOnlyAdjacentDuplicateNumbers() {
        assertArrayEquals(new int[]{1, 3, 0, 1}, stackQueue.removeAdjacentDuplicates(new int[]{1, 1, 3, 3, 0, 1, 1}));
    }

    @Test
    void groupsFeaturesByTheFirstFeatureCompletionDate() {
        assertArrayEquals(new int[]{2, 1}, stackQueue.countFeatureDeployments(
                new int[]{93, 30, 55}, new int[]{1, 30, 5}));
    }

    @Test
    void recognizesBalancedParentheses() {
        assertTrue(stackQueue.hasValidParentheses("()()"));
        assertTrue(stackQueue.hasValidParentheses("(())()"));
    }

    @Test
    void rejectsUnbalancedParentheses() {
        assertFalse(stackQueue.hasValidParentheses(")()("));
        assertFalse(stackQueue.hasValidParentheses("(()("));
    }

    @Test
    void solution() {
        assertEquals(2, stackQueue.countDeliverableBoxes(new int[]{4, 3, 1, 2, 5}));
        assertEquals(5, stackQueue.countDeliverableBoxes(new int[]{5, 4, 3, 2, 1}));
    }
}
