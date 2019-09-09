# Hangman
the game of hangman implemented in a variety of ways 

In this banch we will attempt to read the lexicon from a file to allow more words to be included in the game.

Notice that by using the MVC architure we can create a whole new lexicon and only make a one word change to the whole game code to ulitise it.
This is because the new lexicon, `HMFileLexicon` has the same two methods as `HangmanLexicon`, `getWordCount()` and `getWord`. Since The program works so well 
because of this structure, a simple interface `HMLexicon` has been made for lexicons to fufill , replacing `HangmanLexicon` as the static type.

*******************

hangman solver strategy
In this next part of the task we will build a hangman player that will allow a computer to play hangman and a solver to complete it

the player will..

But what is the best strategy to play hangman?
One's first thought is to use the vowels, then go through the constants in frequency order,
but this frequency is how often a letter appears in text, rather than isolated words
