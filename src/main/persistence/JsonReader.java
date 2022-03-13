package persistence;
/*
 * Sections of code taken from:
 * Title: JsonSerializationDemo
 * Author: Paul Carter
 * Date: 22/2/2022
 * Availability: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

import model.Flashcard;
import model.FlashcardSet;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads flashcard set from JSON data stored in file
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads flashcard set from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FlashcardSet read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFlashcardSet(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses flashcard set from JSON object and returns it
    private FlashcardSet parseFlashcardSet(JSONObject jsonObject) {
        FlashcardSet fs = new FlashcardSet();
        addFlashcards(fs, jsonObject);
        return fs;
    }

    // MODIFIES: fs
    // EFFECTS: parses flashcards from JSON object and adds them to flashcard set
    private void addFlashcards(FlashcardSet fs, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("flashcardSet");
        for (Object json : jsonArray) {
            JSONObject nextFlashcard = (JSONObject) json;
            addFlashcard(fs, nextFlashcard);
        }
    }

    // MODIFIES: fs
    // EFFECTS: parses flashcard from JSON object and adds it to flashcard set
    private void addFlashcard(FlashcardSet fs, JSONObject jsonObject) {
        String statement = jsonObject.getString("statement");
        String answer = jsonObject.getString("answer");

        Flashcard flashcard = new Flashcard(statement, answer);
        fs.addFlashcard(flashcard);
    }
}
