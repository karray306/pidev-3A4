package com.esprit.veltun.gui;

import com.esprit.veltun.util.MyConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class eventUi extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("velo/search/search.fxml"));
        primaryStage.setTitle("Gestion des vélos");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
