/**
 * This class maintains the state of the guessing game. *
 *
 */

package com.shiffler.guessthenumbergame;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Component("guessinggame")
public class GameImpl implements Game {

    //== fields
    private NumberGenerator numberGenerator;

    @Getter
    private int guessCount; //The initial number of guesses the player started with

    @Getter
    private int number;  //The number the player is trying to guess

    @Getter
    @Setter
    private int guess;  //The guess made by the player

    @Getter
    private int smallest; //The smallest number within the range of possible answers.

    @Getter
    private int biggest; //The largest number within the range of possible answers.

    @Getter
    private int remainingGuesses; //The current number of guesses the player has

    @Getter
    private boolean validNumberRange = true; //Was the guess made within the range of possible answers.

    // ==Constructors

    /**
     * @param numGen - A numberGenerator Bean we'll use to generate random numbers
     * @param numGuess - The number of guesses the player will have to guess the random number
     */
    //Inject the NumberGenerator with constructor injection since it's a required component
    @Autowired
    public GameImpl(NumberGenerator numGen, @Qualifier("guessCount") int numGuess){
        this.numberGenerator = numGen;
        this.guessCount = numGuess;
    }

    // == Public Methods

    /**
     * Initializes the game to the beginning state
     */
    @Override
    @PostConstruct //PostConstruct allows the method to be run after being built
    public void reset() {
        smallest = numberGenerator.getMinNumber(); //Get the biggest possible number our answer could be
        guess = numberGenerator.getMinNumber();    //Initialize the guess to the lowest possible solution
        remainingGuesses = guessCount;             //Initialize remaining guesses to the total number of guesses allowed
        biggest = numberGenerator.getMaxNumber();  //Get the biggest possible number our answer could be
        number = numberGenerator.next();           //Generate our random number
    }

    /**
     *Runs when the game is closing down
     *
     */
    @PreDestroy
    private void preDestroy(){
        log.info("Game closing down");
    }

    /**
     *Checks to see if guess was in the valid number range and then recalibrates the range based on the guess
     *
     */
    @Override
    public void check() {

        checkValidNumberRange();

        if(validNumberRange){
            if(guess > number){
                biggest = guess - 1;
            }
            if(guess < number){
                smallest = guess + 1;
            }
        }
        remainingGuesses--;
    }

    /**
     * Determines if the game has been won
     *
     * @return - returns true if the game has been won, false if it hasn't.
     */
    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    /**
     * Determines if the game has been lost
     *
     * @return - returns true if the game has been lost, false if the game isn't in a lost state
     */
    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    // == private methods ==

    /**
     * Checks to see if the guess is within the bounds of the largest and smallest
     * possible solutions
     */
    private void checkValidNumberRange(){
        validNumberRange = (guess >= smallest) && (guess<= biggest);

    }

}