package persistence;

import model.Flashcard;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkFlashcard(String statement, String answer, Flashcard flashcard) {
        assertEquals(statement, flashcard.getStatement());
        assertEquals(answer, flashcard.getAnswer());
    }

}
