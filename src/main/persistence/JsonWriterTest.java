package persistence;

import model.Flashcard;
import model.FlashcardSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    private FlashcardSet fs;
    private Flashcard card1;
    private Flashcard card2;

    @BeforeEach
    void runBefore() {
        fs = new FlashcardSet();
        card1 = new Flashcard("s1", "a1");
        card2 = new Flashcard("s2", "a2");
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IO Exception was expected");
        } catch (IOException e) {
            // all is well
        }
    }

    @Test
    void testWriterEmptyFlashcardSet() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFlashcardSet.json");
            writer.open();
            writer.write(fs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFlashcardSet.json");
            fs = reader.read();
            assertEquals(0, fs.sizeFlashcardSet());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterFlashcardSetWithCards() {
        try {
            fs.addFlashcard(card1);
            fs.addFlashcard(card2);
            JsonWriter writer = new JsonWriter("./data/testWriterFlashcardSetWithCards.json");
            writer.open();
            writer.write(fs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterFlashcardSetWithCards.json");
            fs = reader.read();
            assertEquals(2, fs.sizeFlashcardSet());
            checkFlashcard("s1", "a1", fs.getFlashcardInSet(0));
            checkFlashcard("s2", "a2", fs.getFlashcardInSet(1));


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
