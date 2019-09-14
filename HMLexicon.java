package Hangman;

import java.util.ArrayList;

public interface HMLexicon {
    ArrayList<String> getWordList();
    int getWordCount();
    String getWord(int index);
}
