package mro.arcade.game.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Logback {


    private static final Logger logger
            = LoggerFactory.getLogger(Logback.class);

    public static void main(String[] args) {
        logger.info("Position (6|9) is already filled" , Logback.class.getSimpleName());
    }


}
