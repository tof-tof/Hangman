# Hangman
This project focuses around implementing the game of hangman in a variety of ways in java

## Approach 1: The "all-in-one" method

What do you do when you have an hour or so to spare? `mainController.java` has all the components needed to create and run a text based game of hangman from a console or shell. This version uses what I call, "textbook rules" (the specific rules that don't change the core gameplay and create the least the least number of code paths/exceptions to the rules). In Hangman, these are:

* If the user guesses a correct letter more than once, your program should simply do nothing.
* Guessing an incorrect letter a second time should be counted as another wrong guess.

## Approach 2: Model-View-Controller
The all-in-one method will play a decent game as it is, must what if one wanted change some of the textbook rules, for example, adapt the more popular rule that repeat guesses are not counted as incorrect? The all in one method does not allow one to adapt easily to changing requirements, hence the need for the MVC model.
This splits the game across three files, one which holds the program logic and game state, one which handles user intraction, 
and one which decides what to do with user input.

The game runs from the main method in the `HMview.java` file, which depends on the `HMcontroller.java` file, which depends on the `HMmodel.java` file

Note: If notebooks aren't rendering correctly, download and run, or use an [Online Notebook Viewer](http://nbviewer.jupyter.org/)
