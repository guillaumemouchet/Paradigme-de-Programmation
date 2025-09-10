package ch.hearc.tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private String message;
    private String sender;
    private String time;
    public Message(String sender) {
        this.message = Messages.getRandomMessage().getMessage();
        this.sender = sender;
        this.time = getCurrentTime();
    }
    @Override
    public String toString() {
        return "[" + time + "] " + sender + ": " + message;
    }
    private String getCurrentTime() {
        return DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now());
    }

}

