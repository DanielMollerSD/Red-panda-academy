package com.example.redpandaacademy;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloViewController implements Initializable {

    private final DatabaseController databaseController = new DatabaseController();
    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ResultSet levelSet = databaseController.fetchLevelData();
            int yOffset = 200;
            int xOffset = 0;
            int levelIndex = 0;

            while (levelSet.next()) {

                if (levelIndex % 2 == 0) {
                    xOffset = 0;
                } else {
                    xOffset = 760;
                }

                // Watch out! Hard coded 1 in useraccountID
                ResultSet progressData = databaseController.fetchProgressData(levelSet.getInt("leerprogrammaID"), 1);

                int totalQuestions = 0;
                int correctAnswers = 0;

                while (progressData.next()) {
                    totalQuestions = progressData.getInt("totalQuestions");
                    correctAnswers = progressData.getInt("correctAnswers");
                }

                String levelName = levelSet.getString("naam");
                String levelImage = levelSet.getString("foto");
                URL imageUrl = getClass().getResource(levelImage);
                String primaryColor = levelSet.getString("primary_color");
                String secondaryColor = levelSet.getString("secondary_color");
                String accentColor = levelSet.getString("accent_color");

                // Level container
                Pane levelPane = new Pane();
                levelPane.setStyle("-fx-background-color: linear-gradient(to bottom, " + primaryColor + ", " + secondaryColor + "); -fx-background-radius: 20; -fx-border-color: black; -fx-border-radius: 20; -fx-border-width: 1;");
                levelPane.setPrefSize(760, 170);
                levelPane.setLayoutY(yOffset);
                levelPane.setLayoutX(xOffset);

                //picture frame
                Pane imagePane = new Pane();
                imagePane.setStyle("-fx-background-color: " + accentColor + "; -fx-background-radius: 10; -fx-border-color: black; -fx-border-radius: 10;");
                imagePane.setPrefSize(140, 140);
                imagePane.setLayoutY(15);
                imagePane.setLayoutX(15);

                //picture
                if (imageUrl != null) {
                    String imageUrlString = imageUrl.toExternalForm();
                    ImageView imageView = new ImageView(new Image(imageUrlString));
                    imageView.setFitHeight(140);
                    imageView.setFitWidth(140);
                    imagePane.getChildren().add(imageView);
                } else {
                    System.err.println("Image resource not found: " + levelImage);
                }

                // Level name
                Text levelNameText = new Text(levelName);
                levelNameText.setFill(Color.WHITE);
                levelNameText.setLayoutX(170);
                levelNameText.setLayoutY(80);
                levelNameText.setStrokeType(StrokeType.OUTSIDE);
                levelNameText.setStrokeWidth(0.0);
                levelNameText.setFont(Font.font("Berlin Sans FB Demi Bold", 45));

                // Progress bar
                Pane ProgressBar = new Pane();
                ProgressBar.setStyle("-fx-background-color: white; -fx-background-radius: 20; -fx-border-color: black; -fx-border-radius: 20;");
                ProgressBar.setPrefSize(420, 45);
                ProgressBar.setLayoutY(110);
                ProgressBar.setLayoutX(170);

                // Calculate width progressbar
                System.out.println(totalQuestions);
                System.out.println(correctAnswers);
                int size = 0;
                if (totalQuestions > 0 && correctAnswers > 0) {size = (420 / totalQuestions) * correctAnswers;}
                if (size <= 53) {size = 53;}

                // Colored progress bar
                Pane coloredProgressBar = new Pane();
                coloredProgressBar.setStyle("-fx-background-color: " + accentColor + "; -fx-background-radius: 20; -fx-border-color: black; -fx-border-radius: 20;");
                coloredProgressBar.setPrefSize(size, 45);
                coloredProgressBar.setLayoutY(110);
                coloredProgressBar.setLayoutX(170);

                //Start button
                Button startButton = new Button("Start");
                startButton.setLayoutX(600);
                startButton.setLayoutY(110);
                startButton.setMinWidth(140);
                startButton.setMinHeight(45);
                startButton.setStyle("-fx-background-color: " + accentColor + "; -fx-background-radius: 20; -fx-border-color: black; -fx-border-radius: 20; -fx-text-fill: white;");
                startButton.setFont(Font.font("Berlin Sans FB Demi Bold", 27));
                startButton.setOnAction(event -> {
                    // Add logic to handle level start
                });

                // Add all sub-panes and elements to the main levelPane
                levelPane.getChildren().addAll(imagePane, levelNameText, ProgressBar, coloredProgressBar, startButton);

                // Add the levelPane to the anchorPane
                anchorPane.getChildren().add(levelPane);

                // next level 200px lower
                yOffset += 200;
                levelIndex++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onLoginButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 1920, 1080));
        stage.show();
    }

    @FXML
    public void onLogoButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homepage.fxml"));
        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 1920, 1080));
        stage.show();
    }

    @FXML
    public void onTermsButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("terms.fxml"));
        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 1920, 1080));
        stage.show();
    }

    @FXML
    public void onFaqButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("faq.fxml"));
        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 1920, 1080));
        stage.show();
    }

    @FXML
    public void onContactButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("contact.fxml"));
        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 1920, 1080));
        stage.show();
    }
}