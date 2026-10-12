import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DfsBfsTest {

    private final DfsBfs dfsBfs = new DfsBfs();

    @Test
    void findsShortestDistanceToOppositeCorner() {
        int[][] maps = {
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {0, 0, 0, 0, 1}
        };

        assertEquals(11, dfsBfs.findShortestDistanceInGameMap(maps));
    }

    @Test
    void returnsMinusOneWhenDestinationCannotBeReached() {
        int[][] maps = {
                {1, 0, 1},
                {0, 0, 1},
                {1, 1, 1}
        };

        assertEquals(-1, dfsBfs.findShortestDistanceInGameMap(maps));
    }

    @Test
    void returnsOneWhenStartIsDestination() {
        assertEquals(1, dfsBfs.findShortestDistanceInGameMap(new int[][]{{1}}));
    }
}
