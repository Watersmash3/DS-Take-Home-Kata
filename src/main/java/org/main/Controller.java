package org.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Controller {
    // FXML Elements --
    // Quiz Dropdown Boxes
    @FXML
    private Stage stage;
    @FXML
    private ComboBox<String> numberOfQuestionsDropdown;
    @FXML
    private ComboBox<String> categoryDropdown;
    @FXML
    private ComboBox<String> difficultyDropdown;
    @FXML
    private ComboBox<String> typeDropdown;
    @FXML
    private Button startGameButton;
    private List<ComboBox> comboBoxList;
    private UIControl uiControl;
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

        this.uiControl.initializeMainMenu();

//        URL url = new URL("https://opentdb.com/api_category.php");
//        try (InputStream is = url.openStream(); JsonReader rdr = Json.createReader(is)) {
//            JsonObject obj = rdr.readObject();
//            JsonArray results = obj.getJsonArray("trivia_categories");
//            for (JsonObject result : results.getValuesAs(JsonObject.class)) {
//                System.out.println("------------");
//                System.out.println(result.getJsonNumber("id"));
//                System.out.println(result.getString("name"));
//                System.out.println("------------");
//            }
//        }
    }

    @FXML
    public void startGame() {
        URL quizAPIURL = QuizAPIParser.createAPIURL(
            this.uiControl.getQuestionsValue(),
            this.uiControl.getCategoryValue(),
            this.uiControl.getDifficultyValue(),
            this.uiControl.getTypeValue());

        // Create Quiz by passing in the url to quiz

        // Pass quiz into UI to create the quiz

        // Send UI the stage to change to the quiz screen.
        // Send quiz into the UI to create the Quiz UI
    }

    public void uiInitialize() {
        this.uiControl.setController(this);
    }

    public Collection<? extends String> getCategoryList() {
        return QuizAPIParser.getCategoryList();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
