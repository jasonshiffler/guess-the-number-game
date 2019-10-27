package com.shiffler.guessthenumbergame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageGeneratorImplTest {

    NumberGenerator generator;
    GameImpl theGame;
    MessageGenerator messGen;

    @BeforeEach
    void setUp() {
        //Create a game where the possible answers are between 0 and 100 and
        //the player gets 10 guesses

        generator = new NumberGeneratorImpl(100,0);
        theGame = new GameImpl(generator,10);
        theGame.reset();          //reset initializes the game state, it should run here
        messGen = new MessageGeneratorImpl(theGame);
    }

    @Test
    void getMainMessage() {
        assertEquals("Number is between 0 and 100. Can you guess it?"
                ,messGen.getMainMessage());
    }

    @Test
    void getResultMessage() {
        assertEquals("What is the first guess?"
                ,messGen.getResultMessage());

        theGame.setGuess(150);
        theGame.check();

        assertEquals("Invalid number range!"
                ,messGen.getResultMessage());

        theGame.setGuess(theGame.getNumber());
        theGame.check();

        assertEquals("You guessed it! The number was " + theGame.getNumber()
                ,messGen.getResultMessage());
    }
}