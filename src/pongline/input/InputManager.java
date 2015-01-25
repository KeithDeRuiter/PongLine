package pongline.input;

import java.awt.event.KeyListener;

/**
 * A component which manages, maintains and makes available the current InputState.
 * @author adam
 */
public interface InputManager extends KeyListener {

    /**
     * Returns the current input state held by this input manager.
     * @return the current input state held by this input manager.
     */
    public InputState getInputState();

}
