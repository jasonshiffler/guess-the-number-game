package com.shiffler.guessthenumbergame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameImplTest {

    NumberGenerator generator;
    GameImpl theGame;


    @BeforeEach
    void setup(){
        //Create a game where the possible answers are between 0 and 100 and
        //the player gets 10 guesses

        generator = new NumberGeneratorImpl(100,0);
        theGame = new GameImpl(generator,10);
        theGame.reset();          //reset initializes the game state, it should run here
    }

    @org.junit.jupiter.api.Test
    void resetTest() {

        assertEquals(0,theGame.getSmallest());
        assertEquals(100,theGame.getBiggest());
        assertEquals(0,theGame.getGuess());
        assertEquals(10,theGame.getRemainingGuesses());
    }

    @org.junit.jupiter.api.Test
    void checkTest() {
        theGame.check();
        assertEquals(9, theGame.getRemainingGuesses());

        theGame.setGuess(110);
        theGame.check();
        assertFalse(theGame.isValidNumberRange());
        assertEquals(8,theGame.getRemainingGuesses());

        theGame.setGuess(-5);
        theGame.check();
        assertFalse(theGame.isValidNumberRange());
        assertEquals(7,theGame.getRemainingGuesses());

    }

    @org.junit.jupiter.api.Test
    void isGameWonTest() {
        assertFalse(theGame.isGameWon());
        theGame.setGuess(theGame.getNumber());
        assertTrue(theGame.isGameWon());
    }

    @org.junit.jupiter.api.Test
    void isGameLostTest() {
        assertFalse(theGame.isGameLost());
        theGame.setGuess(150);
        for (int i =0;i < 11;i++){
            theGame.check();
        }
        assertTrue(theGame.isGameLost());
    }

    @org.junit.jupiter.api.Test
    void setGuess() {

    }

    @org.junit.jupiter.api.Test
    void getGuessCount() {
        assertEquals(10,theGame.getGuessCount());
    }

    @org.junit.jupiter.api.Test
    void getNumber() {
        assertTrue(theGame.getNumber() >= theGame.getSmallest()
                && theGame.getNumber() <= theGame.getBiggest());
    }

    @org.junit.jupiter.api.Test
    void getGuess() {
    }

    @org.junit.jupiter.api.Test
    void getSmallest() {
        assertEquals(0,theGame.getSmallest());
    }

    @org.junit.jupiter.api.Test
    void getBiggest() {
        assertEquals(100,theGame.getBiggest());
    }

    @org.junit.jupiter.api.Test
    void getRemainingGuessesTest() {
        assertEquals(10,theGame.getRemainingGuesses());
    }

    @org.junit.jupiter.api.Test
    void isValidNumberRangeTest() {
        assertTrue(theGame.isValidNumberRange());
    }




}