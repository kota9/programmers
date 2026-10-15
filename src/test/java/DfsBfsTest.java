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

    @Test
    void countsSeparateComputerNetworks() {
        int[][] computers = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };

        assertEquals(2, dfsBfs.countConnectedNetworks(3, computers));
    }

    @Test
    void countsAllConnectedComputersAsOneNetwork() {
        int[][] computers = {
                {1, 1, 0},
                {1, 1, 1},
                {0, 1, 1}
        };

        assertEquals(1, dfsBfs.countConnectedNetworks(3, computers));
    }

    @Test
    void countsSingleComputerAsOneNetwork() {
        assertEquals(1, dfsBfs.countConnectedNetworks(1, new int[][]{{1}}));
    }

    @Test
    void findsMaximumNumberOfDungeonsForStandardExample() {
        int[][] dungeons = {
                {80, 20},
                {50, 40},
                {30, 10}
        };

        assertEquals(3, dfsBfs.findMaximumDungeonCount(80, dungeons));
    }

    @Test
    void returnsZeroWhenFatigueIsTooLowForEveryDungeon() {
        int[][] dungeons = {
                {80, 20},
                {50, 40}
        };

        assertEquals(0, dfsBfs.findMaximumDungeonCount(10, dungeons));
    }

    @Test
    void returnsZeroWhenThereAreNoDungeons() {
        assertEquals(0, dfsBfs.findMaximumDungeonCount(80, new int[0][2]));
    }

    @Test
    void doesNotReuseDungeonSearchStateAcrossCalls() {
        int[][] dungeons = {
                {80, 20},
                {50, 40},
                {30, 10}
        };

        assertEquals(3, dfsBfs.findMaximumDungeonCount(80, dungeons));
        assertEquals(0, dfsBfs.findMaximumDungeonCount(10, dungeons));
    }

    @Test
    void findsMinimumTowerDifferenceForStandardExample() {
        int[][] wires = {
                {1, 3},
                {2, 3},
                {3, 4},
                {4, 5},
                {4, 6},
                {4, 7},
                {7, 8},
                {7, 9}
        };

        assertEquals(3, dfsBfs.findMinimumTowerDifference(9, wires));
    }

    @Test
    void findsEqualTowerGroupsWhenNetworkCanBeSplitInHalf() {
        int[][] wires = {
                {1, 2},
                {2, 3},
                {3, 4}
        };

        assertEquals(0, dfsBfs.findMinimumTowerDifference(4, wires));
    }

    @Test
    void findsMinimumTowerDifferenceForUnbalancedNetwork() {
        int[][] wires = {
                {1, 2},
                {2, 7},
                {3, 7},
                {3, 4},
                {4, 5},
                {6, 7}
        };

        assertEquals(1, dfsBfs.findMinimumTowerDifference(7, wires));
    }
}
