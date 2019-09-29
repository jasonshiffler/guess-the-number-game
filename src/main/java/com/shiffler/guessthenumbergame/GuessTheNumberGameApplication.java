/**
 * This is the main application class that starts the app. It relies on Beans to listen for ApplicationContext startup
 * and then execute the appropriate method
 *
 */


package com.shiffler.guessthenumbergame;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

//This is a Lombok annotation that keeps us from having to manually define a logger
@Slf4j
@SpringBootApplication
public class GuessTheNumberGameApplication {

    public static void main(String[] args) {

        // Get the Application context
        ApplicationContext ctx = SpringApplication.run(GuessTheNumberGameApplication.class, args);
        log.info("****Guess the Number Game****");

        ((ConfigurableApplicationContext)ctx).close();

    }

}
