package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlashcardTest {
    private Flashcard testFlashcard1;
    private Flashcard testFlashcard2;

    @BeforeEach
    void runBefore(){
        testFlashcard1 = new Flashcard("Name of my cat", "Laila");
        testFlashcard2 = new Flashcard("What's the capital of Canada?", "Ottawa");
    }

    @Test
    void testConstructor(){
        assertEquals("Name of my cat", testFlashcard1.getStatement());
        assertEquals("Laila", testFlashcard1.getAnswer());
    }





}
