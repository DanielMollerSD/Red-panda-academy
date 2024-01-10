package com.example.redpandaacademy;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private final DatabaseController databaseController = new DatabaseController();
    @FXML
    private AnchorPane anchorPane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*try {
            ResultSet levelSet = databaseController.fetchLevelData();
            int yOffset = 0;

            while (levelSet.next()) {
                String levelName = levelSet.getString("naam");
                String levelImage = levelSet.getString("foto");

                // Create a Pane to hold each level
                Pane levelPane = createLevelPane(levelName, levelImage);
                levelPane.setLayoutY(yOffset);

                // Add the levelPane to the center of the BorderPane
                ((Pane) ((BorderPane) anchorPane.getParent()).getCenter()).getChildren().add(levelPane);

                yOffset += 200; // Adjust the vertical gap between levels as needed
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }*/
    }

    @FXML
    private void onLoginButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Fix the case here
        stage.setScene(new Scene(newTemplate, 1920, 1080));
        stage.show();
    }
    @FXML
    private void onBeginnuButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Fix the case here
        stage.setScene(new Scene(newTemplate, 1920, 1080));
        stage.show();
    }
    @FXML
    private void onHebaccountButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Fix the case here
        stage.setScene(new Scene(newTemplate, 1920, 1080));
        stage.show();
    }
    @FXML
    public void onLogoButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homepage.fxml"));
        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Fix the case here
        stage.setScene(new Scene(newTemplate, 1920, 1080));
        stage.show();
    }
    @FXML
    public void onTestButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Fix the case here
        stage.setScene(new Scene(newTemplate, 1920, 1080));
        stage.show();
    }
    @FXML
    public void onTermsButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("terms.fxml"));
        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Fix the case here
        stage.setScene(new Scene(newTemplate, 1920, 1080));
        stage.show();
    }
    @FXML
    public void onFaqButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("faq.fxml"));
        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Fix the case here
        stage.setScene(new Scene(newTemplate, 1920, 1080));
        stage.show();
    }
    @FXML
    public void onContactButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("contact.fxml"));
        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Fix the case here
        stage.setScene(new Scene(newTemplate, 1920, 1080));
        stage.show();
    }
    @FXML
    public void onTest2ButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account.fxml"));
        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Fix the case here
        stage.setScene(new Scene(newTemplate, 1920, 1080));
        stage.show();
    }
    @FXML
    public void onTest3ButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile.fxml"));
        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Fix the case here
        stage.setScene(new Scene(newTemplate, 1920, 1080));
        stage.show();
    }

    private Pane createLevelPane(String levelName/*, String levelImage*/) {

        // Create a Pane to hold the level information
        Pane levelPane = new Pane();
        levelPane.setPrefSize(760, 170);
        levelPane.setStyle("-fx-background-color: linear-gradient(to bottom, #d2d6f1, #acb0cc); -fx-background-radius: 20; -fx-border-color: black; -fx-border-radius: 20; -fx-border-width: 1;");

        // Create ImageView for level image
        String levelImage = "/img/Evil%20Wizard.png";
        ImageView imageView = new ImageView(new Image(getClass().getResource(levelImage).toExternalForm()));

        imageView.setFitHeight(140);
        imageView.setFitWidth(140);
        imageView.setLayoutX(14);
        imageView.setLayoutY(15);

        // Create Text for level name
        Text levelText = new Text(levelName);
        levelText.setFill(Color.WHITE);
        levelText.setLayoutX(170);
        levelText.setLayoutY(75);
        levelText.setFont(Font.font("Berlin Sans FB Demi Bold", 45));

        // Create Button for level start
        Button startButton = new Button("Start");
        startButton.setLayoutX(606);
        startButton.setLayoutY(111);
        startButton.setMinWidth(53);
        startButton.setStyle("-fx-background-color: #684fa2; -fx-background-radius: 20; -fx-border-color: black; -fx-border-radius: 20;");
        startButton.setFont(Font.font("Berlin Sans FB Demi Bold", 27));

        // Add components to levelPane
        levelPane.getChildren().addAll(imageView, levelText, startButton);

        return levelPane;
    }
}
