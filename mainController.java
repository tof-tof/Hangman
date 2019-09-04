package Hangman;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.Arrays;

public class mainController {
    private HangmanLexicon lexicon = new HangmanLexicon();
    private char[] wordPicked;
    private char[] guessProgress;
    private int gameCode =0; //-1= game lost; 0 = game in play; 1=game won
    private  int guesses =2; //at end of testing, change to 8

    private void setWordPicked(){
        int randomInt = (int)(10.0 * Math.random());
        this.wordPicked = lexicon.getWord(randomInt).toCharArray();
    }

    private void ResetGuessProgress(){
        guessProgress=wordPicked.clone();
        Arrays.fill(guessProgress, '-');
    }

    private void updateGameState(char letter) {
        boolean correctGuess = false;
        for (int i = 0; i < wordPicked.length; i++) {
            if (wordPicked[i] == letter) {
                guessProgress[i] = letter;
                correctGuess = true;
            }
        }
        if (correctGuess) {
           System.out.println("This guess is correct");
        }
        else {
            System.out.println("There are no "+letter+"'s in the word");
            guesses -= 1;
        }
        if (Arrays.equals(wordPicked, guessProgress)) { //game won? equality could cause trouble, use .equals if it does
            gameCode = 1;
        } else if (guesses == 0) {
            gameCode = -1;
        }
    }

    private void runGame() throws IOException {
        while (gameCode==0){
            printResult();
            InputStreamReader streamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String userGuess = bufferedReader.readLine();
            while (userGuess.length()!=1){
                System.out.println("Error: single character should be guessed");
                System.out.print("Your Guess: ");
                userGuess = bufferedReader.readLine();
            }
            char cleanChar = userGuess.toUpperCase().charAt(0);
            updateGameState(cleanChar);
        }
        String wordPickedString = new String(wordPicked);
        if (gameCode==1){
            System.out.println("You guessed the word: "+ wordPickedString);
            gameCode=0; //for next play
        }
        else if (gameCode==-1){
            System.out.println("You are completely hung");
            System.out.println("The word was: "+ wordPickedString);
            System.out.println("You lose.");
            gameCode=0;
        }
    }

    private void printResult(){
        String guessProgressString = new String(guessProgress);
        System.out.println("The word now looks like this: "+ guessProgressString);
        if(guesses==1){
            System.out.println("You only have 1 guess left");
        }
        else {
            System.out.println("You have "+guesses+" guesses left");
        }
        System.out.print("Your Next Guess: ");
    }

    private static void setupGame(mainController controller){
        //String wordpickedstring = new String(wordPicked);
        controller.setWordPicked();
        //System.out.println(wordpickedstring);
        controller.ResetGuessProgress();
        //System.out.println(wordpickedstring);
    }
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Hangman");
        mainController controller = new mainController();
        setupGame(controller);
        controller.runGame();
        System.out.print("Run again? (Y/N)");
        InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        String replay = bufferedReader.readLine();
        while (replay.toUpperCase().equals("Y")){
            setupGame(controller);
            controller.runGame();
            System.out.print("Run again? (Y/N)");
            replay = bufferedReader.readLine();
        }
    }
}
