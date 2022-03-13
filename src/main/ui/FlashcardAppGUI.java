package ui;

import model.FlashcardSet;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FlashcardAppGUI extends JFrame implements ActionListener {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 300;

    private static final String JSON_STORE = "./data/flashcardSet.json";
    private FlashcardSet flashcardSet;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: Constructs new instance of FlashcardAppGUI
    public FlashcardAppGUI() {
        super("Flashcard App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        addComponentsToPane(getContentPane());

        pack();
        setVisible(true);
    }

    // EFFECTS: Adds components to pane in box layout
    private void addComponentsToPane(Container pane) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        // Add title
        JLabel title = new JLabel("Flashcard App");
        title.setFont(new Font("Georgia", Font.PLAIN, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(title);

        // Add buttons
        addButton("See all flashcards", "see", pane);
        addButton("Add flashcard", "add",pane);
        addButton("Remove flashcard","remove",pane);
        addButton("Save","save",pane);
    }

    private void addButton(String title, String command, Container pane) {
        JButton button = new JButton(title);
        button.setActionCommand(command);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(button);
        button.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "see":
                System.out.println("SEE");
                break;
            case "add":
                System.out.println("ADD");
                break;
            case "remove":
                System.out.println("REMOVE");
                break;
            case "save":
                System.out.println("SAVE");
                break;
            default:
                System.out.println("That's not something you can do...");
                break;
        }
    }


}
