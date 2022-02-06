package model;

import java.util.ArrayList;
import java.util.Objects;

// Represents a set of flashcards.
public class FlashcardSet {
    private  ArrayList<Flashcard> flashcardSet;

    //EFFECTS: Creates an empty set of flashcards.
    public FlashcardSet() {
        flashcardSet = new ArrayList<>();
    }


    //MODIFIES: this
    //EFFECTS: Adds flashcard to the end of a flashcard set and returns true, unless there exists
    // a flashcard with the same statement, then return false.
    public boolean addFlashcard(Flashcard flashcard) {
        if (!flashcardSet.contains(flashcard)) {
            flashcardSet.add(flashcard);
            return true;
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: Removes flashcard that matches the given statement and returns true, else returns false.
    public boolean removeFlashcard(String statement) {
        for (Flashcard flashcard: flashcardSet) {
            if (Objects.equals(flashcard.getStatement(), statement)) {
                flashcardSet.remove(flashcard);
                return true;
            }
        }
        return false;
    }


    //EFFECTS: Returns string of all the statements and answers in a set with a dash between each statement and answer.
    public String seeAllFlashcards() {
        String dash = "-";

        StringBuilder sb = new StringBuilder();

        for (Flashcard flashcard: flashcardSet) {
            sb.append(flashcard.getStatement());
            sb.append(dash);
            sb.append(flashcard.getAnswer());
            sb.append("\n");
        }
        return sb.toString();
    }

    //EFFECTS: Returns true if flashcard is in a set, false otherwise
    public boolean containsFlashcard(Flashcard flashcard) {
        return flashcardSet.contains(flashcard);
    }

    //EFFECTS: Returns size of the flashcard set
    public int sizeFlashcardSet() {
        return flashcardSet.size();
    }


    public ArrayList<Flashcard> getFlashcardSet() {
        return flashcardSet;
    }


}
