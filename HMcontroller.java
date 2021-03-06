package Hangman;
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

     boolean isCorrectGuess(char letter){
         return model.isCorrectGuess(letter);
     }

    void setupGame(){
        model.resetGameCode();
        model.resetGuesses();
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
        return  userGuess.length()==1;
    }


}
