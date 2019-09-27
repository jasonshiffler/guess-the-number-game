



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
     * @param numGen
     * @param guess
     */
    //Inject the NumberGenerator with constructor injection since it's a required component
    @Autowired
    public GameImpl(NumberGenerator numGen, @Qualifier("guessCount") int guess){
        this.numberGenerator = numGen;
        this.guessCount = guess;
    }


    // == Public Methods


    /**
     *
     */
    @Override
    @PostConstruct //PostConstruct allows the method to be run after being built
    public void reset() {
        smallest = numberGenerator.getMinNumber();
        guess = numberGenerator.getMinNumber();
        remainingGuesses = guessCount;
        biggest = numberGenerator.getMaxNumber();
        number = numberGenerator.next();
    }


    /**
     *
     */
    @PreDestroy
    public void preDestroy(){
        log.info("Game closing down");
    }


    /**
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
     * @return
     */
    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    /**
     * @return
     */
    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    // == private methods ==

    /**
     *
     */
    private void checkValidNumberRange(){
        validNumberRange = (guess >= smallest) && (guess<= biggest);

    }

}
