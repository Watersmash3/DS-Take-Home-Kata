package org.main;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Controller {
    @FXML
    private ComboBox<String> numberOfQuestionsDropdown;
    @FXML
    private ComboBox<String> categoryDropdown;
    @FXML
    private ComboBox<String> difficultyDropdown;
    @FXML
    private ComboBox<String> typeDropdown;
    @FXML
    private VBox quizVbox;
    private List<ComboBox> comboBoxList;
    private UIControl uiControl;
    private boolean mainMenuInit = false;

    /**
     * Initializes the controller.
     * @throws IOException if an I/O error occurs.
     */
    @FXML
    public void initialize() throws IOException {
        this.uiControl = new UIControl();
        this.uiInitialize();
        this.comboBoxList = new ArrayList<>();

        // Adds comboBoxes to the list
        comboBoxList.add(numberOfQuestionsDropdown);
        comboBoxList.add(categoryDropdown);
        comboBoxList.add(difficultyDropdown);
        comboBoxList.add(typeDropdown);

        this.uiControl.setComboBoxList(comboBoxList);

        if (!mainMenuInit) {
            this.uiControl.initializeMainMenu();
            mainMenuInit = true;
        }
    }

    /**
     * Starts the game by creating a quiz based on selected options.
     * @throws IOException if an I/O error occurs.
     */
    @FXML
    public void startGame() throws IOException {
        URL quizAPIURL = QuizAPIParser.createAPIURL(
            this.uiControl.getQuestionsValue(),
            this.uiControl.getCategoryValue(),
            this.uiControl.getDifficultyValue(),
            this.uiControl.getTypeValue());

        Quiz quiz = new Quiz(quizAPIURL);
        this.uiControl.createQuiz(quiz);
    }

    /**
     * Initializes the UI control.
     */
    public void uiInitialize() {
        this.uiControl.setController(this);
        this.uiControl.setQuizVbox(this.quizVbox);
    }

    /**
     * Retrieves the list of categories from the quiz API parser.
     * @return the list of categories.
     */
    public Collection<? extends String> getCategoryList() {
        return QuizAPIParser.getCategoryList();
    }

    /**
     * Sets the stage for the controller.
     * @param stage the JavaFX stage.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
