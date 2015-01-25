package pongline.input;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that contains the current input configuration.
 * @author adam
 */
class InputConfiguration {

    /** Map of InputControls to Java's virtual key value. */
    private Map<InputControl, Integer> m_keyMap;

    /**
     * Creates a new instance of input configuration.
     * @param upKey the key that moves the paddle up.
     * @param downKey the key that moves the paddle down.
     * @param quitKey the key to quit the game.
     */
    InputConfiguration(int upKey, int downKey, int quitKey) {
        m_keyMap = new HashMap<>();
        m_keyMap.put(InputControl.PADDLE_UP, upKey);
        m_keyMap.put(InputControl.PADDLE_DOWN, downKey);
        m_keyMap.put(InputControl.QUIT, quitKey);
    }

    /**
     * Returns the Virtual Key ID (VK_E for example) for the key associated with the input
     * control supplied.  NOTE:  THIS METHOD MAY RETURN NULL, callers should handle that case.
     * @param control the input control for which the key ID is desired.
     * @return
     */
    Integer getVirtualKeyCode(InputControl control) {
        return m_keyMap.get(control);
    }
}