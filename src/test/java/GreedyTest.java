import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GreedyTest {

    private final Greedy greedy = new Greedy();

    @Test
    void calculatesMovesForJeroen() {
        assertEquals(56, greedy.joystick("JEROEN"));
    }

    @Test
    void calculatesMovesForJan() {
        assertEquals(23, greedy.joystick("JAN"));
    }

    @Test
    void returnsZeroWhenNameIsAlreadyAllAs() {
        assertEquals(0, greedy.joystick("AAA"));
    }

    @Test
    void usesTheShorterDirectionForLettersNearZ() {
        assertEquals(11, greedy.joystick("JAZ"));
    }
}
