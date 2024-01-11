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

public class HelloViewController implements Initializable {

    private final DatabaseController databaseController = new DatabaseController();
    @FXML
    private AnchorPane anchorPane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ResultSet levelSet = databaseController.fetchLevelData();
            int yOffset = 0;

            while (levelSet.next()) {
                String levelName = levelSet.getString("naam");
                String levelImage = levelSet.getString("foto");
                String primaryColor = levelSet.getString("primary_color");
                String secondaryColor = levelSet.getString("secondary_color");
                String accentColor = levelSet.getString("accent_color");

                System.out.println(levelName + " " + levelImage);

                // Dynamically create a white pane and add it to anchorPane
                Pane whitePane = new Pane();
                whitePane.setStyle("-fx-background-color: white;");
                whitePane.setPrefSize(20, 20);
                whitePane.setLayoutX(anchorPane.getWidth() / 2 - 10); // Center X
                whitePane.setLayoutY(yOffset); // Adjust Y position based on your requirements
                anchorPane.getChildren().add(whitePane);

                // Update yOffset for the next iteration if needed
                yOffset += 150; // Adjust as needed
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
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
    public void onLogoButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homepage.fxml"));
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
}
