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
}
