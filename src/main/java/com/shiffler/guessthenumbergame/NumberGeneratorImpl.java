package com.shiffler.guessthenumbergame;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Random;

//Allow this to turned into a bean and name the bean numGen
@Slf4j
@Component("numGen")
public class NumberGeneratorImpl implements NumberGenerator {

    // == fields
    private final Random random = new Random();

    // These are used to determine the range of our random number
    @Getter
    private int maxNumber;
    @Getter
    private int minNumber;

    // == Constructor

    /**
     * @param max
     * @param min
     */
    //Using constructor injection along with a qualifier that allows us to match on the bean name and not just the type
    @Autowired
    public NumberGeneratorImpl(@Qualifier("maxNumber") int max, @Qualifier("minNumber") int min){
        this.maxNumber = max;
        this.minNumber = min;
    }

    // == public methods

    /**
     * @return
     */
    @Override
    public int next() {
        return random.nextInt(maxNumber - minNumber) + minNumber;
    }
}
