package Hangman;

import java.io.IOException;
import java.util.ArrayList;

class HMcontroller {

    private HMmodel model = new HMmodel();

    String getGuessProgress(){
        return  new String(model.getGuessProgress());
    }

    String getWordPicked(){
        return  new String(model.getWordPicked());
    }

    int getGameCode(){
        return model.getGameCode();
    }

    ArrayList<Character> getGuessedLetters(){ return model.getGuessedLetters(); }

    String isCorrectGuess(char letter){
         return model.isCorrectGuess(letter);
     }

     ArrayList<String> getWordList(){return model.getWordList();}

    void setupGame(){
        model.resetGameCode();
        model.resetGuesses();
        model.resetGuessedLetters();
        model.setWordPicked();
        model.ResetGuessProgress();
    }




    String guessesLeft(){
        if(model.getGuesses()==1){
            return "You only have 1 guess left";
        }
        else {
            return "You have "+model.getGuesses()+" guesses left";
        }
    }



    public boolean gameEnded(){
        return model.getGameCode() != 0;
    }

    char clean(String userGuess){
        return userGuess.toUpperCase().charAt(0);
    }

    boolean validateInput(String userGuess){
        return  userGuess.length()==1 && (Character.isLetter(userGuess.charAt(0)));
    }


}
