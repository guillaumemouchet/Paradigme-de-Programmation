package ch.hearc.tools;

import java.util.Random;

public enum Messages {
    HI("Hi, how are you?"), //
    WEATHER("The weather is amazing today!"), //
    THANKS("Thank you for your help."), //
    GOODBYE("Goodbye, have a nice day!");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static Messages getRandomMessage() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}







