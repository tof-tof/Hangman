package Hangman;

import java.util.ArrayList;

public interface HMSolver {

    void resetSolver();

    char makeGuess(String guessProgress);

    void handleResult(int resCode, String guessProgress);//-1 =wrong guess ,0=invalid guess,1=correct
}
