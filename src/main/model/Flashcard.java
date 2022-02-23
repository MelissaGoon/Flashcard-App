package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a flashcard that has a statement and an answer.
public class Flashcard implements Writable {
    private String statement;
    private String answer;


    //EFFECTS: Constructs a flashcard with a statement and an answer.
    public Flashcard(String statement, String answer) {
        this.statement = statement;
        this.answer = answer;
    }

    public String getStatement() {
        return statement;
    }

    public String getAnswer() {
        return answer;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("statement", statement);
        json.put("answer", answer);
        return json;
    }

}
