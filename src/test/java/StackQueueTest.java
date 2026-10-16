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
    void countDeliverableBoxes() {
        assertEquals(2, stackQueue.countDeliverableBoxes(new int[]{4, 3, 1, 2, 5}));
        assertEquals(5, stackQueue.countDeliverableBoxes(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    void calculateMinimumRoomCount() {
        assertEquals(3, stackQueue.calculateMinimumRoomCount(new String[][]{
                {"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}
        }));
        assertEquals(3, stackQueue.calculateMinimumRoomCountByChatGPT(new String[][]{
                {"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}
        }));
    }

    @Test
    void findsPrintOrderByPriorityAndOriginalLocation() {
        assertEquals(1, stackQueue.findPrintOrder(new int[]{2, 1, 3, 2}, 2));
        assertEquals(5, stackQueue.findPrintOrder(new int[]{1, 1, 9, 1, 1, 1}, 0));
        assertEquals(1, stackQueue.findPrintOrder(new int[]{2, 3, 3}, 1));
    }

}
