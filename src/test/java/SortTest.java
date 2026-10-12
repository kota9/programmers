import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SortTest {

    private final Sort sort = new Sort();

    @Test
    void returnsRequestedRanksFromSortedSlices() {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};

        assertArrayEquals(new int[]{5, 6, 3}, sort.findKthNumbers(array, commands));
    }
}
