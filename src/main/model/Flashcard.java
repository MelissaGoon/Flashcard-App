package model;

// Represents a flashcard that has a statement and an answer.
public class Flashcard {
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


}
