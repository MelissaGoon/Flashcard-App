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
                seeFlashcardSet();
                break;
            case "m":
                modifyFlashcardSet();
                break;
            case "p":
                //TODO: implement playGame
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
        card2 = new Flashcard("What's the colour of magic?", "Octarine");
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

    //EFFECTS: Displays current flashcard set.
    private void seeFlashcardSet() {
        System.out.print(flashcardSet.seeAllFlashcards());
    }

    //EFFECTS: Prompts user to either add or remove a card.
    private void modifyFlashcardSet() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("a") || selection.equals("r"))) {
            System.out.println("a -> Add a flashcard to the set");
            System.out.println("r -> Remove a flashcard from the set");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("a")) {
            goAddFlashcard();
        } else {
            goRemoveFlashcard();
        }
    }

    //MODIFIES: this
    //EFFECTS: Adds a flashcard into the set
    private void goAddFlashcard() {
        System.out.print("Add a statement:");

        input.nextLine();
        String statement = input.nextLine();
        System.out.println("Statement entered");

        System.out.print("Add an answer:");
        String answer = input.nextLine();
        System.out.println("Answer entered");

        Flashcard flashcard = new Flashcard(statement, answer);
        if (flashcardSet.addFlashcard(flashcard)) {
            System.out.print("Card added successfully!");
        } else {
            System.out.print("That card already exists...");
        }
    }

    //MODIFIES:this
    //EFFECTS: Removes flashcard with given statement from set
    private void goRemoveFlashcard() {
        if (flashcardSet.sizeFlashcardSet() == 0) {
            System.out.println("This set is already empty!");
        } else {
            System.out.print("Type the statement for the card you want to remove:");

            input.nextLine();
            String statement = input.nextLine();

            if (flashcardSet.removeFlashcard(statement)) {
                System.out.println("Flashcard removed successfully!");
            } else {
                System.out.println("Unable to remove: Flashcard does not exist");
            }
        }

    }



}
