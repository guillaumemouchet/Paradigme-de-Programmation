package ch.hearc.tools;

import ch.hearc.a_producer.Person;
import ch.hearc.a_producer.impl.PersonV1;
import ch.hearc.b_buffer.DataBuffer;
import ch.hearc.b_buffer.impl.DataBufferV1;
import ch.hearc.c_consumer.GroupChat;
import ch.hearc.c_consumer.impl.GroupChatV1;

public class Factory {
    public static Message createMessage(String name) {
        return new Message(name);
    }

    public static Person createPerson(String name) {
        return new PersonV1(name);
    }

    public static GroupChat createGroupChat() {
        return new GroupChatV1();
    }

    public static synchronized DataBuffer getDataBufferInstance() {
        return DataBufferV1.getInstance();
    }
}
