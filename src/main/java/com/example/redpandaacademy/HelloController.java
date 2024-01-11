package com.example.redpandaacademy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController  {

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
}
