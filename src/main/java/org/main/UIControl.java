package org.main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javafx.geometry.Pos.*;
import static javafx.scene.paint.Color.WHITE;

public class UIControl {
    private Controller controller;
    private ComboBox<String> numberOfQuestionsDropdown;
    private List<String> numberOfQuestionsList = new ArrayList<>();
    private ComboBox<String> categoryDropdown;
    private List<String> categoryList = new ArrayList<>();
    private ComboBox<String> difficultyDropdown;
    private List<String> difficultyList = new ArrayList<>();
    private ComboBox<String> typeDropdown;
    private List<String> typeList = new ArrayList<>();
    private List<QuestionUIManager> questionUIManagers = new ArrayList<>();
    private VBox quizVbox;

    public void initializeMainMenu() {
        // Create the lists that will be used to pass into the items
        setUpComboBoxLists();

        // Number of Questions Dropdown
        this.numberOfQuestionsDropdown.getItems().addAll(numberOfQuestionsList);
        this.numberOfQuestionsDropdown.getSelectionModel().selectFirst();

        // Category Dropdown
        this.categoryDropdown.getItems().addAll(categoryList);
        this.categoryDropdown.getSelectionModel().selectFirst();

        // Difficulty Dropdown
        this.difficultyDropdown.getItems().addAll(difficultyList);
        this.difficultyDropdown.getSelectionModel().selectFirst();

        // Type Dropdown
        this.typeDropdown.getItems().addAll(typeList);
        this.typeDropdown.getSelectionModel().selectFirst();

        AnchorPane.setBottomAnchor(this.quizVbox, 0.0);
        AnchorPane.setTopAnchor(this.quizVbox, 0.0);
        AnchorPane.setRightAnchor(this.quizVbox, 0.0);
        AnchorPane.setLeftAnchor(this.quizVbox, 0.0);
    }

    public void createQuiz(Quiz quiz) throws IOException {

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background-color: #201f21;");

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: #201f21;");
        anchorPane.setPrefWidth(1020);

        HBox hBox = new HBox();
        hBox.setAlignment(TOP_CENTER);
        hBox.setMinWidth(1920);
        AnchorPane.setBottomAnchor(hBox, 0.0);
        AnchorPane.setTopAnchor(hBox, 0.0);
        AnchorPane.setRightAnchor(hBox, 0.0);
        AnchorPane.setLeftAnchor(hBox, 0.0);

        scrollPane.setContent(anchorPane);
        anchorPane.getChildren().add(hBox);

        VBox quizBox = new VBox();
        quizBox.setAlignment(CENTER);
        quizBox.setPrefWidth(832);

        hBox.getChildren().add(quizBox);

        List<Question> questions = quiz.getQuestionList();

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            HBox questionHBox = createQuestionHBox();
            QuestionUIManager questionUIManager = new QuestionUIManager(question, questionHBox);
            VBox questionVBox = createQuestionVBox();
            questionHBox.getChildren().add(questionVBox);

            HBox headerHbox = createQuestionHeader(question.getCategory(), question.getDifficulty());
            questionVBox.getChildren().add(headerHbox);

            Text questionText = createQuestionText(question.getQuestion());
            questionVBox.getChildren().add(questionText);

            HBox radioButtonsHBox = createRadioButtonHBox(question.getType(), questionUIManager);

            questionVBox.getChildren().add(radioButtonsHBox);

            quizBox.getChildren().add(questionHBox);
            questionUIManagers.add(questionUIManager);
        }

        this.quizVbox.getChildren().clear();
        this.quizVbox.getChildren().add(scrollPane);
    }

    private HBox createRadioButtonHBox(String type, QuestionUIManager questionUIManager) {
        HBox hbox = new HBox();
        hbox.setPrefWidth(830);
        hbox.setPrefHeight(80);

        ToggleGroup toggleGroup = new ToggleGroup();

        StackPane stackPane = new StackPane();
        stackPane.setAlignment(CENTER_LEFT);
        stackPane.setMinWidth(410);
        stackPane.setPrefHeight(150);

        VBox vbox = new VBox();
        vbox.setMinWidth(410);
        vbox.setPrefHeight(77);

        stackPane.getChildren().add(vbox);

        StackPane stackPane2 = new StackPane();
        stackPane2.setAlignment(CENTER_LEFT);
        stackPane2.setMinWidth(410);
        stackPane2.setPrefHeight(150);

        VBox vbox2 = new VBox();
        vbox2.setMinWidth(410);
        vbox2.setPrefHeight(77);

        stackPane2.getChildren().add(vbox2);

        if (type.equals("boolean")) {
            RadioButton radioButton1 = createRadioButton("True", toggleGroup, questionUIManager);
            vbox.getChildren().add(radioButton1);
            RadioButton radioButton2 = createRadioButton("False", toggleGroup, questionUIManager);
            vbox2.getChildren().add(radioButton2);
        } else {
            RadioButton radioButton3 = createRadioButton(questionUIManager.getQuestion().getPossibleAnswers().get(0), toggleGroup, questionUIManager);
            vbox.getChildren().add(radioButton3);
            RadioButton radioButton4 = createRadioButton(questionUIManager.getQuestion().getPossibleAnswers().get(1), toggleGroup, questionUIManager);
            vbox.getChildren().add(radioButton4);
            RadioButton radioButton5 = createRadioButton(questionUIManager.getQuestion().getPossibleAnswers().get(2), toggleGroup, questionUIManager);
            vbox2.getChildren().add(radioButton5);
            RadioButton radioButton6 = createRadioButton(questionUIManager.getQuestion().getPossibleAnswers().get(3), toggleGroup, questionUIManager);
            vbox2.getChildren().add(radioButton6);
        }

        hbox.getChildren().addAll(stackPane, stackPane2);

        return hbox;
    }

    private RadioButton createRadioButton(String s, ToggleGroup toggleGroup, QuestionUIManager questionUIManager) {
        RadioButton radioButton = new RadioButton();
        radioButton.setText(s);
        radioButton.setPrefHeight(35);
        radioButton.setPrefWidth(410);
        radioButton.setTextFill(WHITE);
        radioButton.setWrapText(true);
        radioButton.setToggleGroup(toggleGroup);
        radioButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("test");
                HBox hBox = new HBox();
                hBox.setPrefWidth(684);
                hBox.setPrefHeight(100);
                hBox.setAlignment(CENTER);

                if (radioButton.getText().equals(questionUIManager.getQuestion().getCorrectAnswer())) {
                    hBox.setStyle("-fx-background-color: #21a332");
                    Text text = new Text("Correct");
                    hBox.getChildren().add(text);
                } else {
                    hBox.setStyle("-fx-background-color: #eb4034");
                    Text text = new Text("Incorrect");
                    hBox.getChildren().add(text);
                }
                questionUIManager.setQuestionHBox(hBox);
            }
        });

        return radioButton;
    }

    private Text createQuestionText(String question) {
        Text text = new Text("Question: " + question);
        text.setFill(WHITE);
        text.setFont(Font.font("Comic Sans MS", 16));

        return text;
    }

    private HBox createQuestionHeader(String category, String difficulty) {
        HBox hbox = new HBox();
        hbox.setAlignment(CENTER);
        hbox.setPrefHeight(35);
        hbox.setPrefWidth(820);
        hbox.setSpacing(40);
        hbox.setStyle("-fx-border-color: #201f21;");

        Text text = new Text("Category: " + category);
        text.setFill(WHITE);
        text.setFont(Font.font("Comic Sans MS", 16));
        Text text2 = new Text("Difficulty: " + difficulty);
        text2.setFill(WHITE);
        text2.setFont(Font.font("Comic Sans MS", 16));
        hbox.getChildren().addAll(text, text2);
        return hbox;
    }

    private VBox createQuestionVBox() {
        VBox vBox = new VBox();
        vBox.setPrefHeight(98);
        vBox.setPrefWidth(830);
        return vBox;
    }

    private HBox createQuestionHBox() {
        HBox hBox = new HBox();
        hBox.setPrefHeight(155);
        hBox.setPrefWidth(832);
        hBox.setStyle("-fx-border-color: #ffffff;");
        return hBox;
    }

    private void setUpComboBoxLists() {
        // Setting up number of Questions
        numberOfQuestionsList.add("Random");
        for (int i = 10; i <= 50; i += 5) {
            numberOfQuestionsList.add(String.valueOf(i));
        }

        // Setting up category List
        categoryList.add("Random");
        categoryList.addAll(controller.getCategoryList()
            .stream()
            .map(s -> s.substring(s.indexOf('_') + 1))
            .toList());

        // Setting up Difficulty List
        String[] difficulties = {"Random", "Easy", "Medium", "Hard"};
        difficultyList.addAll(List.of(difficulties));

        // Setting up Type List
        String[] types = {"Random", "Multiple Choice", "True / False"};
        typeList.addAll(List.of(types));

    }




    public String getQuestionsValue() {
        return this.numberOfQuestionsDropdown.getValue().toLowerCase();
    }

    public String getCategoryValue() {
        String categoryValue = this.categoryDropdown.getValue().toLowerCase();
        return categoryValue.substring(categoryValue.indexOf('_') + 1).replace(' ', '_').trim();
    }

    public String getDifficultyValue() {
        return this.difficultyDropdown.getValue().toLowerCase().trim();
    }

    public String getTypeValue() {
        String typeValue = this.typeDropdown.getValue().toLowerCase().trim();
        if (typeValue.equals("random")) {
            return typeValue;
        } else if (typeValue.equals("multiple choice")) {
            return "multiple";
        } else {
            return "boolean";
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setComboBoxList(List<ComboBox> comboBoxList) {
        this.numberOfQuestionsDropdown = comboBoxList.get(0);
        this.categoryDropdown = comboBoxList.get(1);
        this.difficultyDropdown = comboBoxList.get(2);
        this.typeDropdown = comboBoxList.get(3);
    }

    public static void createAlert(String message, boolean showAndWait) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        if (showAndWait) {
            alert.showAndWait();
        } else {
            alert.show();
        }
    }

    public void setQuizVbox(VBox quizVbox) {
        this.quizVbox = quizVbox;
    }
}
