package pongline.input;

import java.awt.event.KeyEvent;

/**
 * An implementation of the InputState interface for getting user input from the keyboard.
 * @author adam
 */
public class KeyboardInputManager implements InputManager {

    /** The key states. */
    private final boolean[] m_keysDown = new boolean[256];

    /** The input configuration for the keyboard input manager. */
    private InputConfiguration m_config;

    /** Creates a new instance of KeyboardInputManager. */
    public KeyboardInputManager() {
        m_config = new InputConfiguration(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_ESCAPE);
    }

    /** {@inheritDoc}*/
    @Override
    public InputState getInputState() {
        // Create a new input state
        InputStateBuilder bldr = new InputStateBuilder();
        synchronized(m_keysDown) {
            // Get the key states and set them
            Integer quitCode = m_config.getVirtualKeyCode(InputControl.QUIT);
            if (quitCode != null) {
                bldr.quit(m_keysDown[quitCode]);
            }

            Integer downCode = m_config.getVirtualKeyCode(InputControl.PADDLE_DOWN);
            if (downCode != null) {
                bldr.paddleDown(m_keysDown[downCode]);
            }

            Integer upCode = m_config.getVirtualKeyCode(InputControl.PADDLE_UP);
            if (upCode != null) {
                bldr.paddleUp(m_keysDown[upCode]);
            }
        }

        // Return assembled input state
        return bldr.build();
    }

    /** {@inheritDoc} */
    @Override
    public void keyPressed(KeyEvent ke) {
        synchronized(m_keysDown) {
            m_keysDown[ke.getKeyCode()] = true;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void keyReleased(KeyEvent ke) {
        synchronized(m_keysDown) {
            m_keysDown[ke.getKeyCode()] = false;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void keyTyped(KeyEvent ke) {
        // ignored
    }
}