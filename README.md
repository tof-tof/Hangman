# Hangman

In this banch we will attempt to read the lexicon from a file to allow more words to be included in the game.

Notice that by using the MVC architure we can create a whole new lexicon and only make a one word change to the whole game code to ulitise it.
This is because the new lexicon, `HMFileLexicon` has the same two methods as `HangmanLexicon`, `getWordCount()` and `getWord()`. Since The program works so well 
because of this structure, a simple interface `HMLexicon` has been made for lexicons to fufill , replacing `HangmanLexicon` as the static type.

To run the program, be sure to change the file name variable to "HangmanLexicon.txt" or your full disk location if needed. 
*******************

