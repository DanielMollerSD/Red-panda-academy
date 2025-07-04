package com.example.redpandaacademy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        DatabaseController databaseController = new DatabaseController();

        // Fetch and display user data
        try {
            ResultSet resultSet = databaseController.fetchUserData();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("useraccountID") + " " + resultSet.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                databaseController.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        scene.getStylesheets().add(getClass().getResource("/css/homepage.css").toExternalForm());
        setFullscreen(stage, scene);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private void setFullscreen(Stage stage, Scene scene) {
        stage.setFullScreen(true);

        // Disable the default fullscreen exit hint
        stage.setFullScreenExitHint("");

        stage.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                setFullscreen(stage, newScene);
            }
        });
    }


    public static void main(String[] args) {
        launch();
    }
}