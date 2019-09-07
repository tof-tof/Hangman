package Hangman;

import acm.graphics.*;

import java.awt.*;
import java.util.ArrayList;

public class HangmanCanvas extends GCanvas {
    /** Resets the display so that only the scaffold appears */
    GLabel showWord = new GLabel("");
    GLabel showLetters = new GLabel("");


    void reset() {
        removeAll();
        GLine scaffoldVertical = new GLine(corner.getX(),corner.getY(),corner.getX(), corner.getY()+SCAFFOLD_HEIGHT);
        GLine scaffoldHorizontal = new GLine(corner.getX(),corner.getY(),corner.getX()+BEAM_LENGTH, corner.getY());
        add(scaffoldVertical);
        add(scaffoldHorizontal);
        showWord.setFont(wordFont);
        showWord.setColor(Color.BLACK);
        showLetters.setLabel("[]");
        showLetters.setFont(lettersFont);
        add(showWord,wordPos);
        add(showLetters,LettersPos);
    }
    /**
     * Updates the word on the screen to correspond to the current
     * state of the game. The argument string shows what letters have
     * been guessed so far; unguessed letters are indicated by hyphens.
     */
    public void displayWord(String word) {
        showWord.setLabel(word);
    }

    public void showFinishedWord(int gameCode, String word){
        if(gameCode==1){
            showWord.setColor(Color.GREEN);
        }
        else if (gameCode==-1){
            showWord.setColor(Color.red);
        }
        displayWord(word);
    }

    public void displayGuessedLetters(ArrayList<Character> guessedLetters){
        showLetters.setLabel(String.valueOf(guessedLetters));
    }
    /**TODO
     * Updates the display to correspond to an incorrect guess by the
     * user. Calling this method causes the next body part to appear
     * on the scaffold and adds the letter to the list of incorrect
     * guesses that appears at the bottom of the window.
     */
    public void noteIncorrectGuess(char letter) {
        /* You fill this in */
    }
    /* Constants for the simple version of the picture (in pixels) */
    private Font wordFont = new Font("Monospaced", Font.BOLD,40);
    private Font lettersFont = new Font("Monospaced", Font.PLAIN,25);
    private static final GPoint corner = new GPoint(75,40);
    private static final int SCAFFOLD_HEIGHT = 360;
    private static final GPoint wordPos = new GPoint(corner.getX(),corner.getY()+SCAFFOLD_HEIGHT+50);
    private static final GPoint LettersPos = new GPoint(10,wordPos.getY()+30);
    private static final int LINE_WIDTHS = 20;
    private static final int BEAM_LENGTH = 144;
    private static final int ROPE_LENGTH = 18;
    private static final int HEAD_RADIUS = 36;
    private static final int BODY_LENGTH = 144;
    private static final int ARM_OFFSET_FROM_HEAD = 28;
    private static final int UPPER_ARM_LENGTH = 72;
    private static final int LOWER_ARM_LENGTH = 44;
    private static final int HIP_WIDTH = 36;
    private static final int LEG_LENGTH = 108;
    private static final int FOOT_LENGTH = 28;

}
