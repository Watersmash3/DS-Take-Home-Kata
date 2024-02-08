package org.main;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
}
