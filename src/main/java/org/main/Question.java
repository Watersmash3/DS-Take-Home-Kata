package org.main;

import java.util.Collections;
import java.util.List;

public class Question {
    private String question;
    private String type;
    private String difficulty;
    private String category;
    private String correctAnswer;
    private List<String> possibleAnswers;
    private String correct = "unknown";

    public Question(String question, String type, String difficulty, String category, String correctAnswer, List<String> possibleAnswers) {
        this.question = question;
        this.type = type;
        this.difficulty = difficulty;
        this.category = category;
        this.correctAnswer = correctAnswer;
        Collections.shuffle(possibleAnswers);
        this.possibleAnswers = possibleAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public String getType() {
        return type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getCategory() {
        return category;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public String isCorrect() {
        return correct;
    }

    public void setCorrect(String correctState) {
        this.correct = correctState;
    }
}
