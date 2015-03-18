package pongline;

import pongline.display.DefaultGameDisplay;
import pongline.display.GameDisplay;
import pongline.display.SetupDisplay;
import pongline.display.SetupListener;
import pongline.engine.GameManager;
import pongline.input.InputManager;
import pongline.input.KeyboardInputManager;
import pongline.network.CommunicationEngine;

/**
 * A class that manages the initialization of the game.
 * @author adam
 */
public class GameInitializer implements SetupListener {

    /** The setup display. */
    private final SetupDisplay m_setupDisplay;

    /** An input manager. */
    private final InputManager m_inputManager;

    /** A communication engine. */
    private final CommunicationEngine m_communicationEngine;

    /** A GameManager for processing the game state. */
    private final GameManager m_manager;

    /** A display for rendering the current game state. */
    private final GameDisplay m_display;

    /** Creates a new GameInitializer */
    public GameInitializer() {
        m_setupDisplay = new SetupDisplay();
        m_setupDisplay.addListener(this);

        m_inputManager = new KeyboardInputManager();
        m_communicationEngine = null;
        m_display = new DefaultGameDisplay(m_inputManager);
        
        m_manager = new GameManager(m_display, m_inputManager, m_communicationEngine);
    }

    /** Starts the client, and launches the configuration window. */
    public void startClient() {
        m_setupDisplay.launch();
    }

    /** {@inheritDoc} */
    @Override
    public void hostSelected() {
        // Handle 'host' mode being selected
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /** {@inheritDoc} */
    @Override
    public void connectSelected() {
        // Handle 'connect' mode being selected
        throw new UnsupportedOperationException("Not supported yet.");
    }
}