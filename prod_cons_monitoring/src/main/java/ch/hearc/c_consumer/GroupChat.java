package ch.hearc.c_consumer;

public interface GroupChat extends Runnable{

    public int getNbMessagesConsumed();
    public void run();
}


