package pongline.network;

import java.util.List;
import java.util.Queue;

/**
 * The CommunicationEngine interface defines methods used for network interaction.
 * @author Yeaman
 */
public interface CommunicationEngine {
    
    /** 
     * Establishes the connection between users. 
     * @param ip The ip address to connect to.
     * @param port The port number to connect to.
     */
    public void connect(String ip, int port);
    
    /** Ends network connection for the game. */
    public void disconnect();
    
    /**
     * Publishes Messages across the network to the opponent.
     * @param message The message to publish.
     */
    public void publishMessage(Message message);
    
    /**
     * Returns the queue of messages recieved across the network in order of reception.
     * When this method is called the Queue will be emptied.
     * @return A queue of messages.
     */
    public Queue<Message> getMessageQueue();
}
