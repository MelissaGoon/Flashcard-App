package ui;

import model.Event;
import model.EventLog;
import model.Flashcard;
import model.FlashcardSet;
import model.exceptions.LogException;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Creates and shows the Flashcard App GUI
public class FlashcardAppGUI extends JFrame implements ActionListener {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 365;

    private static final String JSON_STORE = "./data/flashcardSet.json";
    private FlashcardSet flashcardSet;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: Constructs new instance of FlashcardAppGUI
    public FlashcardAppGUI() {
        super("Flashcard App");
        flashcardSet = new FlashcardSet();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        addComponentsToPane(getContentPane());

        // Automatically load set
        try {
            flashcardSet = jsonReader.read();
            System.out.println("Loaded flashcard set from:" + JSON_STORE);

        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

        try {
            printLogOnClose(EventLog.getInstance());
        } catch (LogException e) {
            System.out.println("System Error");
            e.printStackTrace();
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // EFFECTS: Prints EventLog on closing
    private void printLogOnClose(EventLog el) throws LogException {
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                for (Event next: el) {
                    System.out.println(next.toString());
                }

                System.exit(0);
            }
        });
    }

    // EFFECTS: Adds components to pane in box layout
    private void addComponentsToPane(Container pane) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        // Add title
        ImageIcon title = createImageIcon("flashcard app title.png");
        JLabel titleimage = new JLabel(title);
        titleimage.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(titleimage);

        // Add buttons
        addButton("See all flashcards", "see", pane);
        addButton("Add flashcard", "add", pane);
        addButton("Remove flashcard", "remove", pane);
        addButton("Save", "save", pane);

        // Add image
        ImageIcon grass = createImageIcon("bottom grass.png");
        JLabel bottomimage = new JLabel(grass);
        bottomimage.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(bottomimage);
    }

    // EFFECTS: Adds buttons to pane
    private void addButton(String title, String command, Container pane) {
        JButton button = new JButton(title);
        button.setActionCommand(command);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(button);
        button.addActionListener(this);
    }


    // Processes button commands on main menu.
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "see":
                showFlashcards();
                break;
            case "add":
                addFlashcardDialog();
                break;
            case "remove":
                if (flashcardSet.sizeFlashcardSet() == 0) {
                    emptySetDialog("Nothing to remove!");
                } else {
                    removeFlashcardDialog();
                }
                break;
            case "save":
                saveDialog();
                break;
            default:
                System.out.println("How did this happen?");
                break;
        }
    }


    //EFFECTS: Shows flashcards in set in a message dialog
    private void showFlashcards() {
        JOptionPane.showMessageDialog(null,
                flashcardSet.seeAllFlashcards(),
                "Your flashcards",
                JOptionPane.PLAIN_MESSAGE);
    }

    // EFFECTS: Saves flashcard set and shows a success/fail dialog
    private void saveDialog() {
        try {
            jsonWriter.open();
            jsonWriter.write(flashcardSet);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null,
                    "Flashcard set saved successfully to:" + JSON_STORE,
                    "Save",
                    JOptionPane.PLAIN_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to write to file:" + JSON_STORE,
                    "Save",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Partially taken from: https://stackoverflow.com/questions/6555040/multiple-input-in-joptionpane-showinputdialog
    //MODIFIES: this
    //EFFECTS: Creates and shows dialog that allows users to enter a flashcard into the set
    private void addFlashcardDialog() {
        JTextField statement = new JTextField();
        JTextField answer = new JTextField();
        Object[] message = {
                "Statement:", statement,
                "Answer:", answer
        };

        int option = JOptionPane.showConfirmDialog(null, message,
                "Add Flashcard", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            Flashcard flashcard = new Flashcard(statement.getText(), answer.getText());

            if (flashcardSet.addFlashcard(flashcard)) {
                JOptionPane.showMessageDialog(null,
                        "Flashcard added successfully!",
                        "Add Successful",
                        JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Unable to add flashcard: This flashcard already exists!",
                        "Add Failed",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

    }

    //EFFECTS: Creates and shows message dialog for when user tries to do something they can't to empty set
    private void emptySetDialog(String title) {
        JOptionPane.showMessageDialog(null,
                "This set is empty!",
                title,
                JOptionPane.WARNING_MESSAGE);
    }

    // Partially taken from: https://stackoverflow.com/questions/6555040/multiple-input-in-joptionpane-showinputdialog
    //MODIFIES: this
    //EFFECTS: Creates and shows dialog that allows users to remove an existing flashcard from the set
    private void removeFlashcardDialog() {
        JTextField statement = new JTextField();
        Object[] message = {
                "Statement of flashcard you'd like to remove:", statement,
        };

        int option = JOptionPane.showConfirmDialog(null, message,
                "Remove Flashcard", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            if (flashcardSet.removeFlashcard(statement.getText())) {
                JOptionPane.showMessageDialog(null,
                        "Flashcard removed successfully!",
                        "Remove Successful",
                        JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Unable to remove flashcard: This flashcard does not exist!",
                        "Remove Failed",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }


    // Taken from: How To Use Icons (author unknown) found at
    // https://docs.oracle.com/javase/tutorial/uiswing/components/icon.html
    // EFFECTS: Creates image icon from given path, or null if no image was found
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = FlashcardAppGUI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }


}
