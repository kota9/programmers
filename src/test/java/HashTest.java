import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HashTest {

    private final Hash hash = new Hash();

    @Test
    void findsUnfinishedParticipantWhenNamesRepeat() {
        String[] participants = {"mislav", "stanko", "mislav", "ana"};
        String[] completions = {"stanko", "ana", "mislav"};

        assertEquals("mislav", hash.findUnfinishedParticipant(participants, completions));
    }

    @Test
    void selectsMaximumNumberOfPokemonKinds() {
        assertEquals(2, hash.maxPokemonKinds(new int[]{3, 1, 2, 3}));
    }

    @Test
    void selectsAvailableKindsWhenThereAreTooFew() {
        assertEquals(2, hash.maxPokemonKinds(new int[]{3, 3, 3, 2, 2, 2}));
    }
}
