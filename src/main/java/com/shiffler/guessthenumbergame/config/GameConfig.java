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

    @Bean
    public int maxNumber(){
        return maxNumber;
    }

    @Bean
    public int minNumber(){
        return minNumber;
    }

    @Bean
    public int guessCount(){
        return guessCount;

    }

}
