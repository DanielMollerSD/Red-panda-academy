package com.example.redpandaacademy;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


public class GameController implements Initializable {

    @FXML
    private Text textQuestion;
    @FXML
    private Text textQuestionNumber;
    @FXML
    private Button ButtonA;
    @FXML
    private Button ButtonB;
    @FXML
    private Button ButtonC;
    @FXML
    private Button ButtonD;

    @FXML
    private Pane AnswerBorderA;
    @FXML
    private Pane AnswerBorderB;
    @FXML
    private Pane AnswerBorderC;
    @FXML
    private Pane AnswerBorderD;

    private final String DEFAULT_COLOR = "#a6a6a6";
    private final String SELECTED_COLOR = "#91ff59";
    private final String BORDER_STYLES = "-fx-border-width: 5px; -fx-border-radius: 15px;";

    private DatabaseController databaseController;
    private MicrobitController microbitController;
    private ResultSet questionsResultSet;
    private String answer= "";
    private String correctAnwer= "";
    private int questionCounter = 1;
    private Thread microbitThread;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the database controller
        databaseController = new DatabaseController();
        microbitController = new MicrobitController(this);

        microbitThread = new Thread(microbitController);
        microbitThread.setDaemon(true); // Set it as a daemon thread so it stops when the main program exits
        microbitThread.start();
    }
    public void initializeWithData(int leerprogrammaID) {
        try {
            questionsResultSet = databaseController.fetchQuestionData(leerprogrammaID);
            displayNextQuestion();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayNextQuestion() {
        try {
            if (questionsResultSet.next()) {
                String question = questionsResultSet.getString("vraag");
                String answer1 = questionsResultSet.getString("antwoord1");
                String answer2 = questionsResultSet.getString("antwoord2");
                String answer3 = questionsResultSet.getString("antwoord3");
                String answer4 = questionsResultSet.getString("antwoord4");
                String goodAnswer = questionsResultSet.getString("goedAntwoord");

                Platform.runLater(() -> {
                    textQuestionNumber.setText("Vraag " + questionCounter);
                    textQuestion.setText(question);
                    ButtonA.setText(answer1);
                    ButtonB.setText(answer2);
                    ButtonC.setText(answer3);
                    ButtonD.setText(answer4);
                    correctAnwer = goodAnswer;
                    questionCounter++;
                });

            } else {
                System.out.println("No more questions");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkAnswer() {
        if (correctAnwer.isEmpty()) {
            System.out.println("First question/no answer yet");
        } else if (correctAnwer.equals(answer)) {
            System.out.println("Correct");
        } else {
            System.out.println("Not correct");
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

    // Micro:bit event handling methods
    public void onMicrobitButtonAClick(String data) {
        handleButtonClicked(AnswerBorderA);
        answer = data;
    }
    public void onMicrobitButtonBClick(String data) {
        handleButtonClicked(AnswerBorderB);
        answer = data;
    }
    public void onMicrobitButtonCClick(String data) {
        handleButtonClicked(AnswerBorderC);
        answer = data;
    }
    public void onMicrobitButtonDClick(String data) {
        handleButtonClicked(AnswerBorderD);
        answer = data;
    }
    public void onMicrobitButtonSClick(String data) {
        displayNextQuestion();
        checkAnswer();
    }


    private void handleButtonClicked(Pane clickedPane) {
        resetColors();
        // Change the color and apply border styles to the clicked pane
        if (clickedPane != null) {
            clickedPane.setStyle("-fx-background-color: " + SELECTED_COLOR + ";");
        } else {
            System.err.println("Clicked pane is null. Check your FXML file.");
        }
    }

    private void resetColors() {
        AnswerBorderA.setStyle("-fx-background-color: " + DEFAULT_COLOR + ";");
        AnswerBorderB.setStyle("-fx-background-color: " + DEFAULT_COLOR + ";");
        AnswerBorderC.setStyle("-fx-background-color: " + DEFAULT_COLOR + ";");
        AnswerBorderD.setStyle("-fx-background-color: " + DEFAULT_COLOR + ";");
    }
}
