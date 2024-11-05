package dk.easv.wordsearch.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataAccess {
    private final String PATH = "./data/";
    private final String FILENAME = "brit";
    private final String HISTORY_FILENAME = "history";

    public DataAccess() {
        CreateFile(FILENAME);
        CreateFile(HISTORY_FILENAME);
    }


    public boolean CreateFile(String fileName) {
        if (fileName == null || fileName.isEmpty())
            fileName = HISTORY_FILENAME;
        java.io.File myDir = new java.io.File(PATH);
        java.io.File myFile = new java.io.File(PATH + fileName + ".txt");
        if (myDir.mkdir()) {
            System.out.println(PATH + " Directory created");
        }

        if (myFile.exists()) {
            System.out.println("File already exists");
            return false;
        }
        else {
            try {
                if (myFile.createNewFile()) {
                    System.out.println(fileName + ".txt File has been created");
                    return true;
                }
                else
                    return false;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public boolean DeleteFile(String fileName) throws IOException {
        File myDir = new File(PATH);
        File myFile = new File(PATH + fileName + ".txt");
        if (myDir.exists()) {
            if (myFile.delete()) {
                System.out.println(fileName + ".txt File has been deleted");
                try{
                    myFile.createNewFile();
                }
                catch(IOException e){
                    System.out.println(fileName + ".txt File could not be created");
                }
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    public boolean AddToFile (String filename, String s) {
        File myFile = new File(PATH + filename + ".txt");
        if (myFile.exists()) {
            FileWriter fw = null;
            try {
                fw = new FileWriter(myFile.getPath(), true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                fw.write("\n");
                fw.write(s);
                fw.flush();
                fw.close();
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public List<String> ReadFile(String fileName) {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH + fileName + ".txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }


    public String getPATH() {
        return PATH;
    }
    public String getFILENAME() {
        return FILENAME;
    }
    public String getHISTORY_FILENAME() {
        return HISTORY_FILENAME;
    }
}
