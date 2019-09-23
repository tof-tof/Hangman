# Hangman
The game of hangman implemented in a variety of ways 


In this next part of the task we will build a hangman player that will allow a computer to play hangman and a solver to complete play the game.

Again, adapting the game to a computer solver is just a matter of adding a new view. (At this point we could add a spot a pattern can create an interface 
for views, but since the view is the point from which the program is always the point of entry for the code, it is not passed to any other class, so
it is not as important to have an interface.) This time, as well as the controller, the view will have a solver class in it, so an interface will be made 
for solvers.

## Hangman solver strategy?

But what is the best strategy to play hangman?

One's first thought is to use the vowels, then go through the constants in frequency order,
but this frequency is how often a letter appears in text, rather than isolated words. However for a computer solver, we have the added bonus of being 
able to do some data analysis with our lexicon. By passing this through, one can find out how many words a character comes up in and guess the most frequent one. Like normal 
Hangman, we can use the information of whether a character in the hidden word, as well as the hidden word's length, to narrow down our decision. This leads to the algorithm 
implemented in `HMStrategySolver`, which guesses correctly around 97% of the time.
