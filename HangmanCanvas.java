package Hangman;

import acm.graphics.*;

import java.awt.*;
import java.util.ArrayList;

public class HangmanCanvas extends GCanvas {
    /** Resets the display so that only the scaffold appears */
    private GLabel showWord = new GLabel("");
    private GLabel showLetters = new GLabel("");
    private int partsVisible= 0;


    void reset() {
        removeAll();
        GLine scaffoldVertical = new GLine(corner.getX(),corner.getY(),corner.getX(), corner.getY()+SCAFFOLD_HEIGHT);
        GLine scaffoldHorizontal = new GLine(corner.getX(),corner.getY(),corner.getX()+BEAM_LENGTH, corner.getY());
        GLine rope = new GLine(corner.getX()+BEAM_LENGTH,corner.getY(),ropeEnd.getX(),ropeEnd.getY());
        add(scaffoldVertical);
        add(scaffoldHorizontal);
        add(rope);
        partsVisible=0;
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
    void displayWord(String word) {
        showWord.setLabel(word);
    }

    void showFinishedWord(int gameCode, String word){
        if(gameCode==1){
            showWord.setColor(Color.GREEN);
        }
        else if (gameCode==-1){
            showWord.setColor(Color.red);
        }
        displayWord(word);
    }

    void displayGuessedLetters(ArrayList<Character> guessedLetters){
        showLetters.setLabel(String.valueOf(guessedLetters));
    }
    /**TODO
     * Updates the display to correspond to an incorrect guess by the
     * user. Calling this method causes the next body part to appear
     * on the scaffold and adds the letter to the list of incorrect
     * guesses that appears at the bottom of the window.
     */
    public void noteIncorrectGuess() {
        /* You fill this in*/
        if (partsVisible==0){
            GOval head = new GOval(ropeEnd.getX()-HEAD_RADIUS,ropeEnd.getY(), HEAD_RADIUS*2,HEAD_RADIUS*2);
            add(head);
        }
        else if (partsVisible==1){
            GLine body = new GLine(ropeEnd.getX(),ropeEnd.getY()+HEAD_RADIUS*2,bodyEnd.getX(),bodyEnd.getY());
            add(body);
        }
        partsVisible+=1;

    }
    /* Constants for the simple version of the picture (in pixels) */

    private static final int SCAFFOLD_HEIGHT = 360;
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

    private Font wordFont = new Font("Monospaced", Font.BOLD,40);
    private Font lettersFont = new Font("Monospaced", Font.PLAIN,25);
    private static final GPoint corner = new GPoint(75,40);
    private static final GPoint wordPos = new GPoint(corner.getX(),corner.getY()+SCAFFOLD_HEIGHT+50);
    private static final GPoint LettersPos = new GPoint(10,wordPos.getY()+30);
    private static final GPoint ropeEnd = new GPoint(corner.getX()+BEAM_LENGTH,corner.getY()+ROPE_LENGTH);
    private static final GPoint bodyEnd = new GPoint(ropeEnd.getX(),ropeEnd.getY()+HEAD_RADIUS*2+BODY_LENGTH);


}
