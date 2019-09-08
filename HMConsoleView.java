package Hangman;
import acm.program.*;

import java.io.IOException;

public class HMConsoleView extends ConsoleProgram {
    private HangmanCanvas canvas;
    private HMcontroller controller = new HMcontroller();
    public static final int WINDOW_WIDTH = 1100;
    public static final int WINDOW_HEIGHT = 800;



    @Override
    public void init() {
        super.init();
        canvas = new HangmanCanvas();
        add(canvas);
        setFont("DialogInput-14");

    }

    private void printGuessOutcome(char letter){
        String res = controller.isCorrectGuess(letter);
        if (res.equals("There are no "+letter+"'s in the word" )){
            canvas.noteIncorrectGuess();
        }
        println(res);

    }

    private void printResult(){
        println("The word now looks like this: "+ controller.getGuessProgress());
        canvas.displayWord(controller.getGuessProgress());
        println("Letters Guessed: "+ controller.getGuessedLetters());
        canvas.displayGuessedLetters(controller.getGuessedLetters());
        println(controller.guessesLeft());
        print("Your Next Guess: ");
    }

    private void runGame(){
        while (controller.getGameCode()==0){
            printResult();
            String userGuess= readLine();
            while (!controller.validateInput(userGuess)){
                showErrorMessage("Error: a single letter should be guessed");
                print("Your Guess: ");
                userGuess = readLine();
            }
            printGuessOutcome(controller.clean(userGuess));
        }
        if (controller.getGameCode()==1){
            println("You guessed the word: "+ controller.getWordPicked());
        }
        else if (controller.getGameCode()==-1){
            println("You are completely hung");
            println("The word was: "+ controller.getWordPicked());
            println("You lose.");
        }
        canvas.showFinishedWord(controller.getGameCode(),controller.getWordPicked());
    }

    public void run(){
        setTitle("Hangman");
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        println("Welcome to Hangman");
        canvas.reset();
        controller.setupGame();
        runGame();
        print("Run again? (Y/N)");
        String replay = readLine();
        while (!replay.toUpperCase().equals("N")){
            if (replay.toUpperCase().equals("Y")) {
                controller.setupGame();
                canvas.reset();
                runGame();
            }
            else {
                showErrorMessage("Error: Enter Y or N ");
            }
            print("Run again? (Y/N)");
            replay = readLine();
        }
        exit();
    }

    public static void main(String[] args){
        new HMConsoleView().start(args);
    }
}
