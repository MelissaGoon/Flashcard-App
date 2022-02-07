package ui;

import model.Flashcard;
import model.FlashcardSet;

import java.util.Scanner;

// Flashcard application
public class FlashcardApp {

    private Scanner input;
    private FlashcardSet flashcardSet;
    private Flashcard card1;
    private Flashcard card2;


    //EFFECTS: Runs Flashcard application
    public FlashcardApp() {
        runFlashcard();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runFlashcard() {
        boolean keepGoing = true;
        String command = null;

        initial();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nSee you later!");

    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        switch (command) {
            case "s":
                System.out.println("seeFlashcardSet();");
                break;
            case "m":
                System.out.println("modifyFlashcardSet();");
                break;
            case "p":
                System.out.println("playGame();");
                break;
            default:
                System.out.println("That's not something you can do...");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes flashcard set with some flashcards in it
    private void initial() {
        flashcardSet = new FlashcardSet();
        card1 = new Flashcard("The meaning of life, the universe and everything", "42");
        card2 = new Flashcard("What's the colour of magic?", "octarine");
        flashcardSet.addFlashcard(card1);
        flashcardSet.addFlashcard(card2);

        input = new Scanner(System.in);
        input.useDelimiter("\n");

    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nChoose from:");
        System.out.println("\ts -> See flashcard set");
        System.out.println("\tm -> Modify flashcard set");
        System.out.println("\tp -> Play game");
        System.out.println("\tq -> Quit");
    }

}
