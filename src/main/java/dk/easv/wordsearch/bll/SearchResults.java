package dk.easv.wordsearch.bll;

public class SearchResults {
    String word;
    boolean found;


    public SearchResults(String word, boolean found) {
        this.word = word;
        this.found = found;
    }


    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public boolean isFound() {
        return found;
    }
    public void setFound(boolean found) {
        this.found = found;
    }

    public String lblIfFound() {
        return found ? (word + " found") : (word + " NOT Found");
    }

    @Override
    public String toString() {
        return "Search for [" + word + ", " + (found ? " has found result" : " has not found result") + "]";
    }
}
