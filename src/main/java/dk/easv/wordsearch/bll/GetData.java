package dk.easv.wordsearch.bll;

import dk.easv.wordsearch.data.DataAccess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetData {

    private List<String> lisOfWords;
    //private DataAccess da;

    public GetData() {
        lisOfWords = new ArrayList<String>();
        DataAccess da = new DataAccess();
    }


    public List<String> returnData(String filename) {
        List<String> data = new ArrayList<String>();
        DataAccess da = new DataAccess();

        data = da.ReadFile(filename);
        return data;
    }

    public List<String> getInitialWords() {
        DataAccess da = new DataAccess();

        lisOfWords = da.ReadFile(da.getFILENAME());
        if(lisOfWords != null && !lisOfWords.isEmpty()) {
            return lisOfWords;
        }
        return null;
    }

    public void AddToHistory (SearchResults listToSave) {
        DataAccess dataAccess = new DataAccess();
        String fileName = dataAccess.getHISTORY_FILENAME();
        /**if (listToSave != null)
            for (Search l : listToSave)
                toSave += l.toString() + "\n";*/
        if (dataAccess.AddToFile(fileName, listToSave.toString()))
            System.out.println("Data has been saved to " + fileName);
        else
            System.out.println("ERROR during saving the data");
    }

    public int FindWord (String word) {
        System.out.println("Findword word: " + word + " " + lisOfWords.size());
        int index = -1;
        if (lisOfWords != null && !lisOfWords.isEmpty() && lisOfWords.contains(word.toLowerCase()))
            index = lisOfWords.indexOf(word);
        System.out.println("Findword index: " + index);
        return index;
    }

    public String getHistoryFileName() {
        DataAccess da = new DataAccess();
        return da.getHISTORY_FILENAME();
    }

    public boolean clearHistory() throws IOException {
        DataAccess da = new DataAccess();
        if (da.DeleteFile(da.getHISTORY_FILENAME()))
            return true;
        return false;
    }

    public List<String> startingWithLetter (String c) {
        String finalC = c.substring(0).toLowerCase();
        return lisOfWords.stream().filter(word -> word.startsWith(finalC)).toList();
    }

    public int returnSizeOfWords() {
        return lisOfWords.size();
    }
}
