package com.shiffler.guessthenumbergame.console;

import com.shiffler.guessthenumbergame.Game;
import com.shiffler.guessthenumbergame.MessageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
public class ConsoleNumberGuess  {

    private Game game;
    private MessageGenerator mg;

    /**
     * @param game - This is an instance of the game bean that we're injecting into our object
     * @param mg - This is an instance of the message generator bean that we're injecting into our object
     */
    @Autowired
    public ConsoleNumberGuess(@Qualifier("guessinggame") Game game,
                              @Qualifier("messagegenerator") MessageGenerator mg) {
        this.game = game;
        this.mg = mg;
    }

    /**
     * This method starts and runs the console game using the game and message generator beans.
     *
     * @param contextRefreshedEvent - Method is run when a ContextRefreshedEvent is triggered. This occurs when the
     *                              application context is initialized
     */
    @EventListener
    public void start(ContextRefreshedEvent contextRefreshedEvent) {

        Scanner scanner = new Scanner(System.in); // Create a scanner to gather user input

        while(true){

            log.info("{}", mg.getMainMessage()); // Output the messages for the game
            log.info("{}", mg.getResultMessage());

            int guess = scanner.nextInt(); //get the players guess
            scanner.nextLine();            //consume the newline character
            game.setGuess(guess);          //pass the guess to the game
            game.check();                  //adjust the guessing range

            if(game.isGameWon() || game.isGameLost()){                //check to see if the game is over
                log.info("{}",mg.getResultMessage());                 //output the appropriate message
                log.info("Play again y/n?");
                String playAgainString = scanner.nextLine().trim();   //grab the response
                if(!playAgainString.equalsIgnoreCase("y")){        //if they didn't say yes
                    break;                                            //stop the game
                }
                game.reset();              //reinitialize the game to play again
            } //close if

        } //close while

    } //close method

}
