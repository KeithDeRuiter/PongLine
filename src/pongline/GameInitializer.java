package pongline;

import java.util.logging.Logger;
import pongline.display.DefaultGameDisplay;
import pongline.display.GameDisplay;
import pongline.display.SetupDisplay;
import pongline.display.SetupListener;
import pongline.engine.GameManager;
import pongline.input.InputManager;
import pongline.input.KeyboardInputManager;

/**
 * A class that manages the initialization of the game.
 * @author adam
 */
public class GameInitializer implements SetupListener {

    /** Logger. */
    private static final Logger LOGGER = Logger.getLogger(SetupListener.class.getName());

    /** The setup display. */
    private final SetupDisplay m_setupDisplay;

    /** An input manager. */
    private final InputManager m_inputManager;

    /** A GameManager for processing the game state. */
    private final GameManager m_manager;

    /** A display for rendering the current game state. */
    private final GameDisplay m_display;

    /** A communication engine.  For communicating. */
    //private final CommunicationEngine m_commEngine;

    /** Creates a new GameInitializer */
    public GameInitializer() {
        m_setupDisplay = new SetupDisplay();
        m_setupDisplay.addListener(this);
        m_inputManager = new KeyboardInputManager();
        m_display = new DefaultGameDisplay(m_inputManager);
        // BUILD COMM ENGINE HERE
        // m_commEngine = new CommunicationEngine();
        m_manager = new GameManager(m_display);
    }

    /** Starts the client, and launches the configuration window. */
    public void startClient() {
        m_setupDisplay.launch();
    }

    /** {@inheritDoc} */
    @Override
    public void hostSelected() {
        // Handle 'host' mode being selected
        LOGGER.fine("Hosting Game;  IP/Host:  " + m_setupDisplay.getHostIpAddress() + ", port:  " + m_setupDisplay.getLocalPort());
        //m_commEngine.host(m_setupDisplay.getHostIpAddress(), m_setupDisplay.getLocalPort());
    }

    /** {@inheritDoc} */
    @Override
    public void connectSelected() {
        // Handle 'connect' mode being selected
        LOGGER.fine("Connect to Game;  IP/Host:  " + m_setupDisplay.getHostIpAddress() + ", port:  " + m_setupDisplay.getRemotePort());
        //m_commEngine.host(m_setupDisplay.getHostIpAddress(), m_setupDisplay.getRemotePort());
    }
}