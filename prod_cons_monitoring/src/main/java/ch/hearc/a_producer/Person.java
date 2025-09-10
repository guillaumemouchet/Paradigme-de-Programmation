package ch.hearc.a_producer;

public interface Person extends Runnable{
    public String getName();
    public void run();
    public default void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
