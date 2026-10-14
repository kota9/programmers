import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SubarrayTest {

    private final Subarray subarray = new Subarray();

    @Test
    void findsMaximumContiguousSubarraySum() {
        assertEquals(6, Subarray.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    @Test
    void findsMaximumSumWhenEveryNumberIsNegative() {
        assertEquals(-2, Subarray.maxSubArray(new int[]{-5, -2, -8}));
    }

    @Test
    void findsMaximumPulseSubsequenceSum() {
        assertEquals(10, subarray.continuousPulse(new int[]{2, 3, -6, 1, 3, -1, 2, 4}));
    }

    @Test
    void findsMaximumSumForSubarrayOfExactLength() {
        assertEquals(5, Subarray.maxSubArrayExactK(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}, 3));
    }

    @Test
    void findsMaximumSumForSubarrayAtLeastTheMinimumLength() {
        assertEquals(6, Subarray.maxSubArrayAtLeastK(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}, 3));
    }

    @Test
    void rejectsInvalidExactLength() {
        assertThrows(IllegalArgumentException.class,
                () -> Subarray.maxSubArrayExactK(new int[]{1, 2}, 0));
    }

    @Test
    void rejectsMinimumLengthLargerThanArray() {
        assertThrows(IllegalArgumentException.class,
                () -> Subarray.maxSubArrayAtLeastK(new int[]{1, 2}, 3));
    }

    @Test
    void solution() {
        assertEquals(3, subarray.calculateMinimumRoomCount(new String[][]{
                {"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}
        }));
        assertEquals(3, subarray.calculateMinimumRoomCountByChatGPT(new String[][]{
                {"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}
        }));
    }
}
