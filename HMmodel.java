package Hangman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class HMmodel {

    private HMLexicon lexicon = new HMFileLexicon();
    private char[] wordPicked;
    private char[] guessProgress;
    private int gameCode =0; //-1= game lost; 0 = game in play; 1=game won
    private int guesses =8; //at end of testing, change to 8
    private ArrayList<Character> guessedLetters= new ArrayList<Character>();

    int getGameCode(){
        return gameCode;
    }
    char[] getWordPicked(){ return wordPicked; }
    char[] getGuessProgress() { return guessProgress; }
    void resetGuessedLetters(){guessedLetters = new ArrayList<Character>();}
    void resetGameCode(){ gameCode=0; }
    void resetGuesses(){guesses =8;}
    int getGuesses(){ return guesses; }

    ArrayList<Character> getGuessedLetters(){ return guessedLetters; }

    void setWordPicked(){
        int randomInt = (int) (Math.random() * lexicon.getWordCount());
        this.wordPicked = lexicon.getWord(randomInt).toCharArray();
        //System.out.println("after swp "+ new String(wordPicked));
    }

    void ResetGuessProgress(){
        guessProgress=wordPicked.clone();
        Arrays.fill(guessProgress, '-');
        //System.out.println("after rgp "+new String(wordPicked));
    }

    private void setGameCode(){
        if (Arrays.equals(wordPicked, guessProgress)) {
            gameCode = 1;
        } else if (guesses == 0) {
            gameCode = -1;
        }
    }

    String isCorrectGuess(char letter){//0 = guessed before, -1=false (incorrect Guess) 1 = true (correct guess)
        if (guessedLetters.contains(letter)){
            return letter + " has already been guessed" ;
        }
        guessedLetters.add(letter);
        String correctGuess = "There are no "+letter+"'s in the word" ;
        for (int i = 0; i < wordPicked.length; i++) {
            if (wordPicked[i] == letter) {
                guessProgress[i] = letter;
                correctGuess = "Your guess is correct";
            }
        }
        if (correctGuess.equals("There are no " + letter + "'s in the word")){guesses-=1;}
        setGameCode();
        return correctGuess;
    }


}
