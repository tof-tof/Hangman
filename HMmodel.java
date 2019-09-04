package Hangman;

import java.util.ArrayList;
import java.util.Arrays;

class HMmodel {

    private HangmanLexicon lexicon = new HangmanLexicon();
    private char[] wordPicked;
    private char[] guessProgress;
    private int gameCode =0; //-1= game lost; 0 = game in play; 1=game won
    private int guesses =2; //at end of testing, change to 8
    private ArrayList<Character> guessedLetters= new ArrayList<Character>();

    int getGameCode(){
        return gameCode;
    }
    char[] getWordPicked(){ return wordPicked; }
    char[] getGuessProgress() { return guessProgress; }
    void resetGuessedLetters(){guessedLetters = new ArrayList<Character>();}
    void resetGameCode(){ gameCode=0; }
    void resetGuesses(){guesses =2;}
    int getGuesses(){ return guesses; }

    ArrayList<Character> getGuessedLetters(){ return guessedLetters; }

    void setWordPicked(){
        int randomInt = (int)(10.0 * Math.random());
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

    int isCorrectGuess(char letter){//0 = guessed before, -1=false (incorrect Guess) 1 = true (correct guess)
        if (guessedLetters.contains(letter)){
            return 0;
        }
        guessedLetters.add(letter);
        int correctGuess = -1;
        for (int i = 0; i < wordPicked.length; i++) {
            if (wordPicked[i] == letter) {
                guessProgress[i] = letter;
                correctGuess = 1;
            }
        }
        if (correctGuess==-1){guesses-=1;}
        setGameCode();
        return correctGuess;
    }


}
