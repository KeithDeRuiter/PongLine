package pongline.network;

import pongline.network.messages.Message;
import java.util.Queue;

/**
 * The CommunicationEngine interface defines methods used for network interaction.
 * @author Yeaman
 */
public interface CommunicationEngine {

    /**
     * When this call is made, it starts the Communication in 'host mode.'  This causes the comm engine to
     * create its socket and initialize and wait for a Communication Engine in 'connect mode' to connect to it.
     * @param ipOrHostname the IP or Hostname of the host (your local computer).
     * @param port the port to connect to on the host (your local computer).
     */
    public void host(String ipOrHostname, int port);


    /**
     * When this call is made, it starts the Communication in 'host mode.'  This causes the comm engine to
     * create its socket and initialize and wait for a Communication Engine in 'connect mode' to connect to it.
     * @param ipOrHostname the IP or Hostname of the host (the computer to connect to).
     * @param port the port to connect to on the host (the computer to connect to).
     */
    public void connect(String ipOrHostname, int port);

    /** Ends network connection for the game. */
    public void disconnect();

    /**
     * Publishes Messages across the network to the opponent.
     * @param message The message to publish.
     */
    public void publishMessage(Message message);

    /**
     * Returns the queue of messages received across the network in order of reception.
     * When this method is called the Queue will be emptied.
     * @return A queue of messages.
     */
    public Queue<Message> getMessageQueue();
}
