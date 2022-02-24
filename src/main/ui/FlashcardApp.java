package ui;

import model.Flashcard;
import model.FlashcardSet;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Flashcard application
public class FlashcardApp {
    private static final String JSON_STORE = "./data/flashcardSet.json";
    private Scanner input;
    private FlashcardSet flashcardSet;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


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
            case "f":
                seeFlashcardSet();
                break;
            case "m":
                modifyFlashcardSet();
                break;
            case "p":
                playGame();
                break;
            case "s":
                saveFlashcardSet();
                break;
            case "l":
                loadFlashcardSet();
                break;
            default:
                System.out.println("That's not something you can do...");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes flashcard set
    private void initial() {
        flashcardSet = new FlashcardSet();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        input = new Scanner(System.in);
        input.useDelimiter("\n");

    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nChoose from:");
        System.out.println("\tf -> See flashcard set");
        System.out.println("\tm -> Modify flashcard set");
        System.out.println("\tp -> Play game");
        System.out.println("\ts -> Save flashcard set");
        System.out.println("\tl -> Load flashcard set");
        System.out.println("\tq -> Quit");
    }

    //EFFECTS: Displays current flashcard set.
    private void seeFlashcardSet() {
        System.out.print(flashcardSet.seeAllFlashcards());
    }

    //EFFECTS: Prompts user to either add or remove a card.
    private void modifyFlashcardSet() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("a") || selection.equals("r") || selection.equals("c"))) {
            System.out.println("a -> Add a flashcard to the set");
            System.out.println("r -> Remove a flashcard from the set");
            System.out.println("c -> Cancel");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("a")) {
            goAddFlashcard();
        } else if (selection.equals("r")) {
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



    //EFFECTS: Runs game that prompts users to fill in the answer for each statement.
    private void playGame() {
        int countCorrect = 0;

        if (flashcardSet.sizeFlashcardSet() == 0) {
            System.out.println("There's nothing in this set...");
        } else {
            input.nextLine();
            for (Flashcard f: flashcardSet.getFlashcardSet()) {
                int index = flashcardSet.indexOfFlashcard(f) + 1;

                System.out.println("Question " + index + ":" + f.getStatement());

                String answer = input.nextLine();

                if (answer.equals(f.getAnswer())) {
                    System.out.println("Correct!");
                    ++countCorrect;
                } else {
                    System.out.println("Incorrect");
                }
            }

            System.out.println("Score:\n" + countCorrect + "/" + flashcardSet.sizeFlashcardSet());
        }
    }

    //EFFECTS: Saves flashcard set to file
    private void saveFlashcardSet() {
        try {
            jsonWriter.open();
            jsonWriter.write(flashcardSet);
            jsonWriter.close();
            System.out.println("Flashcard set saved successfully to:" + JSON_STORE);

        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: Loads flashcard set from file
    private void loadFlashcardSet() {
        try {
            flashcardSet = jsonReader.read();
            System.out.println("Loaded flashcard set from:" + JSON_STORE);

        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

    }



}
