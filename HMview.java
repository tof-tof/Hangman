package Hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HMview {

    private HMcontroller controller = new HMcontroller();

    private void printGuessOutcome(char letter){
        int isCorrect=controller.isCorrectGuess(letter);
        if (isCorrect==1){
            System.out.println("Your guess is correct");
        }
        else if (isCorrect==0){
            System.out.println(letter + " has already been guessed");
        }
        else {
            System.out.println("There are no "+letter+"'s in the word");
        }
    }

    private void printResult(){
        System.out.println("The word now looks like this: "+ controller.getGuessProgress());
        System.out.println("Letters Guessed: "+ controller.getGuessedLetters());
        System.out.println(controller.guessesLeft());
        System.out.print("Your Next Guess: ");
    }

    private void runGame() throws IOException {
        while (controller.getGameCode()==0){
            printResult();
            InputStreamReader streamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String userGuess = bufferedReader.readLine();
            while (!controller.validateInput(userGuess)){
                System.out.println("Error: a single letter should be guessed");
                System.out.print("Your Guess: ");
                userGuess = bufferedReader.readLine();
            }
            printGuessOutcome(controller.clean(userGuess));
        }
        if (controller.getGameCode()==1){
            System.out.println("You guessed the word: "+ controller.getWordPicked());
        }
        else if (controller.getGameCode()==-1){
            System.out.println("You are completely hung");
            System.out.println("The word was: "+ controller.getWordPicked());
            System.out.println("You lose.");
        }
    }

    public void startGame() throws IOException {
        System.out.println("Welcome to Hangman");
        controller.setupGame();
        runGame();
        System.out.print("Run again? (Y/N)");
        InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        String replay = bufferedReader.readLine();
        while (replay.toUpperCase().equals("Y")){
            controller.setupGame();
            runGame();
            System.out.print("Run again? (Y/N)");
            replay = bufferedReader.readLine();
        }
    }

    public static void main(String[] args) throws IOException {
        HMview view = new HMview();
        view.startGame();
    }
}
