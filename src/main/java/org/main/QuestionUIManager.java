package org.main;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class QuestionUIManager {
    Question question;
    HBox questionHBox;

    public QuestionUIManager(Question question, HBox questionHBox) {
        this.question = question;
        this.questionHBox = questionHBox;
    }

    public void setQuestionHBox(HBox questionHBox) {
        this.questionHBox.getChildren().clear();
        this.questionHBox.getChildren().add(questionHBox);
    }

    public Question getQuestion() {
        return question;
    }

    public HBox getQuestionHBox() {
        return questionHBox;
    }
}
