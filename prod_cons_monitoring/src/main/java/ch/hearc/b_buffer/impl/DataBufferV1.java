package ch.hearc.b_buffer.impl;

import ch.hearc.b_buffer.DataBuffer;
import ch.hearc.d_monitoring.PerformanceMeasures;
import ch.hearc.tools.Message;
import ch.hearc.tools.Settings;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * DataBuffer Singleton
 */
public class DataBufferV1 implements DataBuffer {
    private static DataBufferV1 instance = null;
    private final BlockingQueue<Message> buffer;

    private DataBufferV1() {
        buffer = new ArrayBlockingQueue<Message>(Settings.getBufferSize());
    }

    public static synchronized DataBufferV1 getInstance() {
        if (instance == null) {
            instance = new DataBufferV1();
        }
        return instance;
    }

    @Override
    public void put(Message message) throws InterruptedException {
        buffer.put(message);
        // The message has been successfully stored in the buffer, so we can log its "received" time
        PerformanceMeasures.getInstance().addMessageReceivedTime();
    }

    @Override
    public Message get() {
        return buffer.poll();
    }
}
