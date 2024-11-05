package dk.easv.wordsearch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WordSearchApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WordSearchApplication.class.getResource("word-search-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 750);
        stage.setTitle("Word search!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}