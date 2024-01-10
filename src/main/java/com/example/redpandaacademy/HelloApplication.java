package com.example.redpandaacademy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
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

        stage.setFullScreenExitHint(""); // Set the exit hint to an empty string

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
