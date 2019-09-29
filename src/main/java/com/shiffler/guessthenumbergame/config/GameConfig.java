/**
 * This class is our game configuration it takes the info from the game.properties file
 * and then injects the settings into the game. Allows the game to be configured without having to be
 * recompiled. *
 */

package com.shiffler.guessthenumbergame.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/game.properties")
public class GameConfig {

    // == fields

    //pull in properties from the config file using 20 as a default value.
    @Value("${game.maxnumber:20}")
    private int maxNumber;

    @Value("${game.minnumber:1}")
    private int minNumber;


    @Value("${game.guesscount:5}")
    private int guessCount;

    // == bean methods

    //Beans will be injected with the same name as the method.

    /**
     * Injects the maximum possible random number into a Bean called maxNumber
     *
     * @return - maximum possible random number
     */
    @Bean
    public int maxNumber(){
        return maxNumber;
    }

    /**
     * Injects the minimum possible random number into a Bean called minNumber
     *
     * @return - minimum possible random number
     */
    @Bean
    public int minNumber(){
        return minNumber;
    }

    /**
     * Injects the number of guesses the player will have into a bean called guessCount     *
     *
     * @return  - the number of guesses the player will have
     */
    @Bean
    public int guessCount(){
        return guessCount;

    }

}