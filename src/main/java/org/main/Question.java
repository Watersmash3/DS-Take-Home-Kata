package org.main;

import java.util.Collections;
import java.util.List;

/**
 * Represents a single question in a quiz.
 */
public class Question {
    private String question;
    private String type;
    private String difficulty;
    private String category;
    private String correctAnswer;
    private List<String> possibleAnswers;
    private String correct = "unknown";

    /**
     * Constructs a question with the provided attributes.
     * @param question the text of the question.
     * @param type the type of the question (e.g., multiple choice, true/false).
     * @param difficulty the difficulty level of the question.
     * @param category the category of the question.
     * @param correctAnswer the correct answer to the question.
     * @param possibleAnswers the list of possible answers to the question.
     */
    public Question(String question, String type, String difficulty, String category, String correctAnswer, List<String> possibleAnswers) {
        this.question = question;
        this.type = type;
        this.difficulty = difficulty;
        this.category = category;
        this.correctAnswer = correctAnswer;

        // Shuffle the list of possible answers for better randomness
        Collections.shuffle(possibleAnswers);

        this.possibleAnswers = possibleAnswers;
    }

    /**
     * Gets the text of the question.
     * @return the question text.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Gets the type of the question.
     * @return the question type.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the difficulty level of the question.
     * @return the question difficulty.
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Gets the category of the question.
     * @return the question category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the correct answer to the question.
     * @return the correct answer.
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Gets the list of possible answers to the question.
     * @return the list of possible answers.
     */
    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    /**
     * Gets the correctness state of the question.
     * @return "unknown" if the correctness is not determined, "correct" if correct, "incorrect" if incorrect.
     */
    public String isCorrect() {
        return correct;
    }

    /**
     * Sets the correctness state of the question.
     * @param correctState the correctness state to set ("correct" or "incorrect").
     */
    public void setCorrect(String correctState) {
        this.correct = correctState;
    }
}
