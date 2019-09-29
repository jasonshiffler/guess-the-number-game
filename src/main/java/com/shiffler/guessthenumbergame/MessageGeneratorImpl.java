/**
 * This class is used to generate messages that will be used throughout the game.
 *
 */

package com.shiffler.guessthenumbergame;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component("messagegenerator")
public class MessageGeneratorImpl implements MessageGenerator {

    // == fields

    private Game game;

   // == Constructors

    /**
     * The object constructor
     *
     * @param game - The game bean we're injecting into this bean
     */
    @Autowired
    public MessageGeneratorImpl(Game game) {
        this.game = game;
    }

    // == Init Methods

    /**
     *
     */
    @PostConstruct
    private void init(){
        log.info("Game is {}", game);
    }

    // == Public Methods

    /**
     * Creates a String that prompts the player to guess a number between a certain range
     *
     * @return - The String that should be presented to the player
     */
    @Override
    public String getMainMessage() {
        return "Number is between " +
                game.getSmallest() +
                " and " +
                game.getBiggest() +
                ". Can you guess it?";
    }

    /**
     * Creates a String that reacts to the players guess based on the state of the game
     *
     * @return - This String gives the player feedback on what's happening in the game.
     */
    @Override
    public String getResultMessage() {

        if(game.isGameWon()){
            return "You guessed it! The number was " + game.getNumber();
        } else if (game.isGameLost()){
            return "You lost. the number was " + game.getNumber();
        } else if(!game.isValidNumberRange()){
            return "Invalid number range!";
        } else if(game.getRemainingGuesses() == game.getGuessCount()){
            return "What is the first guess?";
          }
          else {
            String direction = "Lower";

            if(game.getGuess() < game.getNumber()){
                direction = "Higher";
            }
             return direction + "! You have  " + game.getRemainingGuesses() + " guesses left";

        }
    } //close method
}
