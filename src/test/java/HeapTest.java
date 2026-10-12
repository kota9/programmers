import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeapTest {

    private final Heap heap = new Heap();

    @Test
    void mixesScovilleValuesUntilAllReachTarget() {
        assertEquals(2, heap.minimumMixesToReachTarget(new int[]{1, 2, 3, 9, 10, 12}, 7));
    }

    @Test
    void returnsMinusOneWhenTargetCannotBeReached() {
        assertEquals(-1, heap.minimumMixesToReachTarget(new int[]{1, 2}, 10));
    }

    @Test
    void returnsZeroWhenAllValuesAlreadyReachTarget() {
        assertEquals(0, heap.minimumMixesToReachTarget(new int[]{10, 12}, 7));
    }
}
