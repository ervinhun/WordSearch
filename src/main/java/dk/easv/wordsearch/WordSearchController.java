package dk.easv.wordsearch;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WordSearchController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}