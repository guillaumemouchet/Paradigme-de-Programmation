package ch.hearc.a_producer.impl;

import ch.hearc.a_producer.Person;
import ch.hearc.b_buffer.impl.DataBufferV1;
import ch.hearc.d_monitoring.PerformanceMeasures;
import ch.hearc.tools.Factory;
import ch.hearc.tools.Message;

public class PersonV1 implements Person {
    private String name;

    public PersonV1(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            while(true) {
                Message message = Factory.createMessage(name);
                // The message was created, so we can log its "sent" time
                PerformanceMeasures.getInstance().addMessageSentTime();
                Factory.getDataBufferInstance().put(message);
            }
        } catch (InterruptedException e) {
            // Closing thread
            // System.out.println("Closing thread " + name);
            // I don't want to clutter the console with this message
        }
    }
}
