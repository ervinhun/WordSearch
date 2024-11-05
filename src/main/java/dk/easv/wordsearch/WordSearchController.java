package dk.easv.wordsearch;

import dk.easv.wordsearch.bll.GetData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class WordSearchController {
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnLoad;
    @FXML
    private ListView lstWords;
    @FXML
    private ListView lstWordA;
    @FXML
    private ListView lstWordB;
    @FXML
    private Label lblWordA;
    @FXML
    private Label lblWordB;
    @FXML
    private ListView lstHistory;
    @FXML
    private Button btnClearHist;
    @FXML
    private Label lblTotalWords;
    @FXML
    private Label lblSearchRes;

    ObservableList<String> wordsObservableList;
    ObservableList<String> startsWithAObservableList;
    ObservableList<String> startsWithNObservableList;


    public WordSearchController() {
    }

    @FXML
    private void setBtnSearch(ActionEvent event) {
        String search = txtSearch.getText();
        if (search.equals("")) {

        }
    }
    @FXML
    private void loadInitial(ActionEvent event) {
        fillInWords();
        btnLoad.setVisible(false);
    }

    private void fillInWords() {
        GetData getData = new GetData();
        List<String> data = getData.getInitialWords();
        wordsObservableList = FXCollections.observableArrayList(data);
        lstWords.setItems(wordsObservableList);
        lblTotalWords.setText(wordsObservableList.size() + "");
        List<String> tmp = data.stream().filter(word -> word.startsWith("a")).toList();
        startsWithAObservableList = FXCollections.observableArrayList(tmp);
        lstWordA.setItems(startsWithAObservableList);
        tmp = data.stream().filter(word -> word.startsWith("n")).toList();
        startsWithNObservableList = FXCollections.observableArrayList(tmp);
        lstWordB.setItems(startsWithNObservableList);

    }


    private void startsWithLetter (Character c) {
        if (Character.isLetter(c)) {
            GetData getData = new GetData();

            List<String> wordsStartingWithA = getData.getInitialWords().stream()
                    .filter(word -> word.startsWith("A"))
                    .toList();
            startsWithAObservableList = FXCollections.observableArrayList(wordsStartingWithA);
            lstWordA.setItems(startsWithAObservableList);
        }
        else {
            GetData getData = new GetData();

            List<String> wordsStartingWithN = getData.getInitialWords().stream()
                    .filter(word -> word.startsWith("A"))
                    .toList();
            startsWithNObservableList = FXCollections.observableArrayList(wordsStartingWithN);
            lstWordB.setItems(startsWithNObservableList);
        }
    }


}