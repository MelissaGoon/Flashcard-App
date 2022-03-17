package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a set of flashcards.
public class FlashcardSet implements Writable {
    private  ArrayList<Flashcard> flashcardSet;

    //EFFECTS: Creates an empty set of flashcards.
    public FlashcardSet() {
        flashcardSet = new ArrayList<>();
    }


    //MODIFIES: this
    //EFFECTS: Adds flashcard to the end of a flashcard set and returns true, unless there exists
    // a flashcard with the same statement, then return false.
    public boolean addFlashcard(Flashcard flashcard) {
        if (flashcardSet.isEmpty()) {
            flashcardSet.add(flashcard);
            return true;
        }
        for (Flashcard f: flashcardSet) {
            if (f.getStatement().equalsIgnoreCase(flashcard.getStatement())) {
                return false;
            }
        }
        flashcardSet.add(flashcard);
        return true;
    }


    //MODIFIES: this
    //EFFECTS: Removes flashcard that matches the given statement and returns true, else returns false.
    public boolean removeFlashcard(String statement) {
        for (Flashcard flashcard: flashcardSet) {
            if (flashcard.getStatement().equalsIgnoreCase(statement)) {
                flashcardSet.remove(flashcard);
                return true;
            }
        }
        return false;
    }


    //EFFECTS: Returns string of all the statements and answers in a set with a dashes between
    // each statement and answer.
    public String seeAllFlashcards() {
        String dash = "---";

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

    //EFFECTS: Returns index of flashcard in set
    public int indexOfFlashcard(Flashcard flashcard) {
        return flashcardSet.indexOf(flashcard);
    }

    //EFFECTS: Returns flashcard at specified index from set
    public Flashcard getFlashcardInSet(int index) {
        return flashcardSet.get(index);
    }

    public ArrayList<Flashcard> getFlashcardSet() {
        return flashcardSet;
    }




    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("flashcardSet", flashcardSetToJson());
        return json;
    }
    
    // EFFECTS: Returns flashcard set as JSON array
    private JSONArray flashcardSetToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Flashcard f : flashcardSet) {
            jsonArray.put(f.toJson());
        }

        return jsonArray;
    }


}
