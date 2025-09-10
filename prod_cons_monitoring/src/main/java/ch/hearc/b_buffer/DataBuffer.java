package ch.hearc.b_buffer;

import ch.hearc.tools.Message;

/**
 * DataBuffer Singleton
 */
public interface DataBuffer {
    void put(Message message) throws InterruptedException;
    Message get();
}
