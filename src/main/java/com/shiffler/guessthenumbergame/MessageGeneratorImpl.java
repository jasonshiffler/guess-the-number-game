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
     * @param game
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
     * @return
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
     * @return
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
