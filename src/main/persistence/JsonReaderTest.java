package persistence;

import model.FlashcardSet;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/oopsNothingHere.json");
        try {
            FlashcardSet fs = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // all is well!
        }
    }

    @Test
    void testReaderEmptyFlashcardSet() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyFlashcardSet.json");
        try {
            FlashcardSet fs = reader.read();
            assertEquals(0, fs.sizeFlashcardSet());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }

    @Test
    void testReaderFlashcardSetWithCards() {
        JsonReader reader = new JsonReader("./data/testReaderFlashcardSetWithCards.json");

        try {
            FlashcardSet fs = reader.read();

            assertEquals(2, fs.sizeFlashcardSet());
            checkFlashcard("s1", "a1", fs.getFlashcardInSet(0));
            checkFlashcard("s2", "a2", fs.getFlashcardInSet(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
