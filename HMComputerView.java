package Hangman;

public class HMComputerView {
    private HMcontroller controller = new HMcontroller();
    private HMSolver solver = new HMStrategySolver(controller.getWordList());
    private int wins=0;

    private void runGame(){
        while (controller.getGameCode()==0){
            char guess = solver.makeGuess(controller.getGuessProgress());
            if (!controller.validateInput(Character.toString(guess))){
               throw new IllegalArgumentException("Solver gave a non-alphabetic character");
            }
            char letter = controller.clean(Character.toString(guess));
            String res = controller.isCorrectGuess(letter);
            int resCode = -1;
            if (res.equals(letter + " has already been guessed")) resCode=0;
            else if (res.equals("Your guess is correct")) resCode=1;
            solver.handleResult(resCode,controller.getGuessProgress());
        }
        if (controller.getGameCode()==1) wins+=1;
    }

    public void startGame(int numGames){
        for (int i=0; i<numGames;i++){
            //System.out.println("Game "+ (i+1));
            controller.setupGame();
            solver.resetSolver();
            runGame();
        }
        double percentage = (((double) wins / (double) numGames)* 100);
        System.out.println(String.format("you won " + wins + " games out of " + numGames + ". Win percentage: %.2f", percentage ));
    }

    public static void main(String[] args) {
        HMComputerView computerView = new HMComputerView();
        computerView.startGame(890);
    }


}
