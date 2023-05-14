package com.example.zdarzenia_w_springu_1_2.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Random;

@Slf4j
@Component
public class RandomStringGenerator {
    private final Random random;

    public RandomStringGenerator() {
        this.random = new Random();
    }

    public String generateRandomString(int length){
        String randomString = "";
        for(int i = 0; i < length; i++){
            randomString = randomString.concat(String.valueOf(generateRandomCharacter()));
        }
        log.info("Generated random string {}", randomString);
        return randomString;
    }

    private char generateRandomCharacter(){
        int randomNumber = random.nextInt(3);
        switch (randomNumber) {
            case 0:
                return generateRandomLowerCase();
            case 1:
                return generateRandomUpperCase();
            case 2:
                return generateRandomNumberBasedOnASCII();
            default:
                return ' ';
        }
    }

    private char generateRandomLowerCase(){
        final int startingASCIICode = 97;
        final int endingASCIICode = 122;

        return randomCharInRange(startingASCIICode, endingASCIICode);
    }

    private char generateRandomUpperCase(){
        final int startingASCIICode = 65;
        final int endingASCIICode = 90;

        return randomCharInRange(startingASCIICode, endingASCIICode);
    }

    private char generateRandomNumberBasedOnASCII(){
        final int startingASCIICode = 48;
        final int endingASCIICode = 57;

        return randomCharInRange(startingASCIICode, endingASCIICode);
    }

    private char randomCharInRange(int startingASCII, int endingASCII){
        return (char) (random.nextInt(endingASCII - startingASCII) + startingASCII);
    }
}
