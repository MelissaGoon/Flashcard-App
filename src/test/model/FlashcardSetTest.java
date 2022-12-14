package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
// Tests for flashcard set class
public class FlashcardSetTest {

    private Flashcard card1;
    private Flashcard card2;
    private Flashcard card3;
    private FlashcardSet flashcardSetTester;

    @BeforeEach
    void runBefore() {
        card1 = new Flashcard("s1", "a1");
        card2 = new Flashcard("s2", "a2");
        card3 = new Flashcard("s3", "a3");

        flashcardSetTester = new FlashcardSet();
    }

    @Test
    void testConstructor() {
        assertTrue(flashcardSetTester.getFlashcardSet().isEmpty());
    }

    @Test
    void testAddFlashcardSuccess() {
        addAndContainsFlashcard(card1);
        assertEquals(1,flashcardSetTester.sizeFlashcardSet());

        addAndContainsFlashcard(card2);
        assertEquals(2,flashcardSetTester.sizeFlashcardSet());

        addAndContainsFlashcard(card3);
        assertEquals(3,flashcardSetTester.sizeFlashcardSet());
    }


    @Test
    void testAddFlashcardFails() {
        addAndContainsFlashcard(card1);
        assertEquals(1,flashcardSetTester.sizeFlashcardSet());

        Flashcard copyCard1= new Flashcard("s1", "a1");

        assertFalse(flashcardSetTester.addFlashcard(copyCard1));
        assertTrue(flashcardSetTester.containsFlashcard(card1));
        assertEquals(1,flashcardSetTester.sizeFlashcardSet());
    }

    @Test
    void testRemoveFlashcardSuccess() {
        addAndContainsFlashcard(card1);
        addAndContainsFlashcard(card2);

        assertTrue(flashcardSetTester.removeFlashcard(card1.getStatement()));
        assertFalse(flashcardSetTester.containsFlashcard(card1));
        assertEquals(1, flashcardSetTester.sizeFlashcardSet());

        assertTrue(flashcardSetTester.removeFlashcard(card2.getStatement()));
        assertFalse(flashcardSetTester.containsFlashcard(card2));
        assertEquals(0, flashcardSetTester.sizeFlashcardSet());
    }

    @Test
    void testRemoveFlashcardFails() {
        addAndContainsFlashcard(card1);
        assertEquals(1, flashcardSetTester.sizeFlashcardSet());

        assertFalse(flashcardSetTester.removeFlashcard(card2.getStatement()));
        assertEquals(1, flashcardSetTester.sizeFlashcardSet());

        assertFalse(flashcardSetTester.removeFlashcard(card3.getStatement()));
        assertEquals(1, flashcardSetTester.sizeFlashcardSet());
    }

    @Test
    void testRemoveFlashcardEmpty() {
        assertFalse(flashcardSetTester.removeFlashcard(card2.getStatement()));
        assertEquals(0, flashcardSetTester.sizeFlashcardSet());

        assertFalse(flashcardSetTester.removeFlashcard(card3.getStatement()));
        assertEquals(0, flashcardSetTester.sizeFlashcardSet());
    }

    @Test
    void testSeeAllFlashcardsEmpty() {
        assertEquals("", flashcardSetTester.seeAllFlashcards());
    }

    @Test
    void testSeeAllFlashcards() {
        addAndContainsFlashcard(card1);
        addAndContainsFlashcard(card2);
        addAndContainsFlashcard(card3);

        assertEquals("s1---a1\ns2---a2\ns3---a3\n", flashcardSetTester.seeAllFlashcards());
    }

    @Test
    void testIndexOfFlashcard() {
        flashcardSetTester.addFlashcard(card1);
        flashcardSetTester.addFlashcard(card2);

        assertEquals(0, flashcardSetTester.indexOfFlashcard(card1));
        assertEquals(1, flashcardSetTester.indexOfFlashcard(card2));
        assertEquals(-1, flashcardSetTester.indexOfFlashcard(card3));
    }

    @Test
    void testGetFlashcardInSet() {
        flashcardSetTester.addFlashcard(card1);
        flashcardSetTester.addFlashcard(card2);

        assertEquals(card1, flashcardSetTester.getFlashcardInSet(0));
        assertEquals(card2, flashcardSetTester.getFlashcardInSet(1));

    }

    private void addAndContainsFlashcard(Flashcard flashcard) {
        assertTrue(flashcardSetTester.addFlashcard(flashcard));
        assertTrue(flashcardSetTester.containsFlashcard(flashcard));
    }



}
