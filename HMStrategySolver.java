package Hangman;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HMStrategySolver implements HMSolver{
    private ArrayList<String> lexicon;
    private ArrayList<String> wordOptions;
    private Boolean allSameLength = false;
    private Map<Character, Integer> letterFreq = new HashMap<Character, Integer>();
    private Character lastGuess;//Forces Invariant: makeGuess is always called before handleResult

    HMStrategySolver(ArrayList<String> wordList){
        lexicon=wordList;
        for (Character character: "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) letterFreq.put(character, 0);
    }

    public void resetSolver(){
        wordOptions =lexicon;
        allSameLength=false;
        for (Character character: "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) letterFreq.put(character, 0);
        lastGuess = null;
    }
    private void setLetterFreq(){
        for (Character character: letterFreq.keySet()) letterFreq.put(character, 0);
        for (String word: wordOptions){
            ArrayList<Character> pointedChars = new ArrayList<Character>();
            for (Character character: word.toCharArray()){
                if (!pointedChars.contains(character)&& letterFreq.containsKey(character)){
                    letterFreq.replace(character, letterFreq.get(character)+1);
                    pointedChars.add(character);
                }
            }
        }
    }
    private Character findMaxFreqChar(){
        Iterator<Character> letterFreqIt = letterFreq.keySet().iterator();
        //System.out.println(letterFreq);
        if (!letterFreqIt.hasNext()) throw new NullPointerException("logical error: looking for the next letter to guess, but there are no letters left to guess");
        Character maxChar = letterFreqIt.next();
        for (Character c: letterFreq.keySet()){
            if (letterFreq.get(c)>letterFreq.get(maxChar)) maxChar =c;
        }
        return maxChar;
    }

    public char makeGuess(String guessProgress) {
        if (!allSameLength) narrowByWordLength(guessProgress.length());
        lastGuess = findMaxFreqChar();
        return lastGuess;

    }

    public void handleResult(int resCode, String guessProgress) {
        letterFreq.remove(lastGuess);
        if (resCode==1) {
            ArrayList<Integer> charPlaces = new ArrayList<Integer>();
            char[] currentState = guessProgress.toCharArray();
            for (int i=0; i<currentState.length;i++){
                if (currentState[i]==lastGuess) charPlaces.add(i);
            }
            narrowByIn(charPlaces);
        }
        else if (resCode==-1) narrowByNotIn();
        else{
            throw new IllegalArgumentException("Class invariant broken. Logical error, same letter guessed twice");
        }
    }

    private void narrowByIn(ArrayList<Integer> charplaces){
        ArrayList<String> newwordOptions = new ArrayList<String>();
        for (String word : wordOptions) {
            char[] wordArray = word.toCharArray();
            boolean valid = true;
            int i = 0;
            while (i<charplaces.size()&&valid){
                if (wordArray[charplaces.get(i)] != lastGuess) valid=false;
                i++;
            }
            if (valid) newwordOptions.add(word);
        }
        wordOptions=newwordOptions;
        setLetterFreq();
    }

    private void narrowByNotIn(){
        ArrayList<String> newwordOptions = new ArrayList<String>();
        for (String word : wordOptions) {
            if (!word.contains(String.valueOf(lastGuess))) {
                newwordOptions.add(word);
            }
        }
        wordOptions=newwordOptions;
        setLetterFreq();
    }


    private void narrowByWordLength(int wordlength) {
        ArrayList<String> newwordOptions = new ArrayList<String>();
        for (String word : wordOptions) {
            if (word.length() == wordlength) {
                newwordOptions.add(word);
            }
        }
        wordOptions=newwordOptions;
        allSameLength=true;
        setLetterFreq();
    }
}
