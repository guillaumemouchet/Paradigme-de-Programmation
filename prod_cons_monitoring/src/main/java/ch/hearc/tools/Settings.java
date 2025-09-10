package ch.hearc.tools;

public class Settings {
    private static int nbPersons = 4;
    private static int bufferSize = 10;
    private static int nbMessages = 100;

    public static int getNbPersons() {
        return nbPersons;
    }

    public static void setNbPersons(int nbPersons) {
        Settings.nbPersons = nbPersons;
    }

    public static int getBufferSize() {
        return bufferSize;
    }

    public static void setBufferSize(int bufferSize) {
        Settings.bufferSize = bufferSize;
    }

    public static int getNbMessages() {
        return nbMessages;
    }

    public static void setNbMessages(int nbMessages) {
        Settings.nbMessages = nbMessages;
    }

}
