package dk.easv.wordsearch.bll;

import dk.easv.wordsearch.data.DataAccess;

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

    private void AddToHistory (SearchResults listToSave) {
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
}
