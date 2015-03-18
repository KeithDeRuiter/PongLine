package pongline.network;

/**
 * The interface for a communication engine.
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
}
