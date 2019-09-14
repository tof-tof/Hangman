package Hangman;

import acm.util.ErrorException;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class HMFileLexicon implements HMLexicon{
    private ArrayList<String> wordList = new ArrayList<String>();


    HMFileLexicon() {
        String fileName = "C:\\Users\\tofun\\OneDrive - Nexus365\\portfolio work\\src\\Hangman\\HangmanLexicon.txt";
        /*
        Scanner s = new Scanner(new File(fileName));
        while (s.hasNext()){
            wordList.add(s.next());
        }
        s.close();
         */
        try {
            BufferedReader hangmanWords = new BufferedReader(new FileReader(fileName));
            while(true) {
                String line = hangmanWords.readLine();
                if(line == null) break;
                wordList.add(line);
            }
            hangmanWords.close();
        } catch (IOException ex) {
            throw new ErrorException(ex);
        }
    }

    @Override
    public int getWordCount() {
        return wordList.size();
    }

    @Override
    public String getWord(int index) {
        if (index<wordList.size()) {
            return wordList.get(index);
        }
        else throw new IllegalArgumentException("getWord: Illegal index");
    }

    public ArrayList<String> getWordList(){
        return wordList;
    }

}
