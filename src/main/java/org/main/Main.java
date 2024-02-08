package org.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage mainStage;
    private Parent quizMenu;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("/mainMenu.fxml"));
        Parent menuRoot = fxmlLoader.load(getClass().getResource("/quizMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);
        mainStage = stage;
        quizMenu = menuRoot;
    }

    public static void switchScene(String fxmlFile) {
        FXMLLoader loader = new FXMLLoader(Main.class
            .getResource(fxmlFile));
        Parent root;
        try
        {
            root = (Parent)loader.load();
            mainStage.setScene(new Scene(root));
            Controller2 controller2 = (Controller2) loader.getController();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}