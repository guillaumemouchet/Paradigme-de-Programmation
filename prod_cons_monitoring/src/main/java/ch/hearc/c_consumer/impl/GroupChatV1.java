package ch.hearc.c_consumer.impl;

import ch.hearc.c_consumer.GroupChat;
import ch.hearc.d_monitoring.PerformanceMeasures;
import ch.hearc.tools.Factory;
import ch.hearc.tools.Message;
import ch.hearc.tools.Settings;

public class GroupChatV1 implements GroupChat {
    private int nbMessagesConsumed = 0;

    @Override
    public synchronized int getNbMessagesConsumed() {
        return nbMessagesConsumed;
    }

    @Override
    public void run()
    {
        nbMessagesConsumed = 0; // reset counter to make sure we don't count messages from previous runs
        while(nbMessagesConsumed < Settings.getNbMessages()) {
            Message message = Factory.getDataBufferInstance().get();
            if(message != null) {
                System.out.println(message);
                // The message has been successfully shown, so we can log its "shown" time
                PerformanceMeasures.getInstance().addMessageShownTime();
                PerformanceMeasures.getInstance().addMessageShown(message.toString());
                nbMessagesConsumed++;
            }
        }
    }
}
