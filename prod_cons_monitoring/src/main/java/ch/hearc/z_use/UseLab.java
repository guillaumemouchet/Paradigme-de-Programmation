package ch.hearc.z_use;

import ch.hearc.c_consumer.GroupChat;
import ch.hearc.d_monitoring.PerformanceCalculator;
import ch.hearc.d_monitoring.PerformanceMeasures;
import ch.hearc.tools.Factory;
import ch.hearc.tools.Settings;

import java.util.ArrayList;
import java.util.stream.IntStream;


/**
 * UseLab
 * <p>
 * This class is used to run the laboratory. In this laboratory we examine the effect of various parameters on the
 * performance
 * of a producer/consumer system. The parameters are:
 * - the number of persons that write messages in the group chat
 * - the size of the buffer that stores the messages to be consumed by the group chat
 * - the total number of messages that need to be written
 */
public class UseLab {

    public static void settings(int nbPerson, int bufferSize, int nbMessages) {
        Settings.setBufferSize(bufferSize);
        Settings.setNbPersons(nbPerson);
        Settings.setNbMessages(nbMessages);
    }

    public static void run() {
        GroupChat groupChat = Factory.createGroupChat();
        ArrayList<Thread> threads = new ArrayList<>();
        Thread groupChatThread = new Thread(groupChat);
        threads.add(groupChatThread);
        groupChatThread.start();
        // This marks the official start of the programm
        PerformanceMeasures.getInstance().markStartTime();
        IntStream.range(0, Settings.getNbPersons()).forEach(i -> {
            Thread thread = new Thread(Factory.createPerson("Person " + i));
            threads.add(thread);
            thread.start();
        });

        try {
            groupChatThread.join();
            // This marks the official end of the programm
            PerformanceMeasures.getInstance().markEndTime();
        } catch (InterruptedException e) {
            System.out.println("UseLab interrupted while joining");
        }

        threads.forEach(Thread::interrupt);
        System.out.println("Total number of messages consumed: " + groupChat.getNbMessagesConsumed());
        System.out.println(PerformanceCalculator.getReport());
    }
}
