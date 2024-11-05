package dk.easv.wordsearch;

import dk.easv.wordsearch.bll.GetData;
import dk.easv.wordsearch.bll.SearchResults;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
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
    ObservableList<String> historyObservableList;
    GetData getData;


    public WordSearchController() {
    }


    @FXML
    private void loadInitial(ActionEvent event) {
        getData = new GetData();
        fillInWords();
        loadHistory();
        btnLoad.setVisible(false);
        btnSearch.setDisable(false);
        txtSearch.setDisable(false);
        btnClearHist.setDisable(false);
        //lstWords.setPrefHeight(getData.returnSizeOfWords()*12);
    }

    @FXML
    private void loadHistory() {
        List<String> data = getData.returnData(getData.getHistoryFileName());
        historyObservableList = FXCollections.observableList(data);
        lstHistory.setItems(historyObservableList);
    }

    @FXML
    private void clearHistory(ActionEvent event) throws IOException {
        if (getData.clearHistory())
            historyObservableList.clear();
    }
    @FXML
    private void search(ActionEvent event) {
        int index = -1;
        SearchResults result;
        //GetData getData = new GetData();
        if(!txtSearch.getText().equals("")) {
            index = getData.FindWord(txtSearch.getText());
        }
        if (index != -1) {
            result = new SearchResults(txtSearch.getText(), true);
            lstWords.getSelectionModel().select(index);
            lstWords.scrollTo(index);
        }
        else
            result = new SearchResults(txtSearch.getText(), false);

        txtSearch.setText("");
        lblSearchRes.setText(result.lblIfFound());
        getData.AddToHistory(result);
        historyObservableList.add(result.toString());
    }

    private void fillInWords() {
        //GetData getData = new GetData();
        wordsObservableList = FXCollections.observableArrayList(getData.getInitialWords());
        lstWords.setItems(wordsObservableList);
        lblTotalWords.setText(wordsObservableList.size() + "");
        //List<String> tmp = data.stream().filter(word -> word.startsWith("a")).toList();
        startsWithAObservableList = FXCollections.observableArrayList(getData.startingWithLetter("a"));
        lstWordA.setItems(startsWithAObservableList);
        //tmp = data.stream().filter(word -> word.startsWith("n")).toList();
        startsWithNObservableList = FXCollections.observableArrayList(getData.startingWithLetter("n"));
        lstWordB.setItems(startsWithNObservableList);
    }


}