package ch.hearc.d_monitoring;

import java.util.ArrayList;

/**
 * Singleton
 */
public class PerformanceMeasures {
    private static PerformanceMeasures instance = null;
    private final ArrayList<Long> messageSentTimes = new ArrayList<>();
    private final ArrayList<Long> messageReceivedTimes = new ArrayList<>();
    private final ArrayList<Long> messageShownTimes = new ArrayList<>();
    private final ArrayList<String> messagesShown = new ArrayList<>();
    private long startTime;
    private long endTime;

    private PerformanceMeasures() {
    }

    public static synchronized PerformanceMeasures getInstance() {
        if (instance == null) {
            instance = new PerformanceMeasures();
        }
        return instance;
    }

    public void markStartTime() {
        startTime = System.nanoTime();
    }

    public void markEndTime() {
        endTime = System.nanoTime();
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void addMessageSentTime() {
        messageSentTimes.add(System.nanoTime());
    }

    public void addMessageReceivedTime() {
        messageReceivedTimes.add(System.nanoTime());
    }

    public void addMessageShownTime() {
        messageShownTimes.add(System.nanoTime());
    }

    public void addMessageShown(String message) {
        messagesShown.add(message);
    }

    public ArrayList<Long> getMessageSentTimes() {
        return messageSentTimes;
    }

    public ArrayList<Long> getMessageReceivedTimes() {
        return messageReceivedTimes;
    }

    public ArrayList<Long> getMessageShownTimes() {
        return messageShownTimes;
    }

    public ArrayList<String> getMessagesShown() {
        return messagesShown;
    }

}
