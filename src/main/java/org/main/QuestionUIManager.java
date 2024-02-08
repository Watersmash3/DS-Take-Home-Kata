package org.main;

import javafx.scene.layout.HBox;

/**
 * Manages the UI components for displaying a single question.
 */
public class QuestionUIManager {
    Question question; // The question object
    HBox questionHBox; // The HBox containing the UI components for the question

    /**
     * Constructs a QuestionUIManager with the given question and HBox.
     * @param question The question object.
     * @param questionHBox The HBox containing the UI components for the question.
     */
    public QuestionUIManager(Question question, HBox questionHBox) {
        this.question = question;
        this.questionHBox = questionHBox;
    }

    /**
     * Sets the HBox containing the UI components for the question.
     * @param questionHBox The new HBox to be set.
     */
    public void setQuestionHBox(HBox questionHBox) {
        this.questionHBox.getChildren().clear();
        this.questionHBox.getChildren().add(questionHBox);
    }

    /**
     * Gets the question object.
     * @return The question object.
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * Gets the HBox containing the UI components for the question.
     * @return The HBox containing the UI components for the question.
     */
    public HBox getQuestionHBox() {
        return questionHBox;
    }
}
