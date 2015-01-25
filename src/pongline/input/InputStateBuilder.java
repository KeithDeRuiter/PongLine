package pongline.input;

import java.util.HashMap;
import java.util.Map;

/**
 * A builder to help create InputState objects.
 * @author adam
 */
class InputStateBuilder {

    /** A map of input controls their active/inactive states. */
    private final Map<InputControl, Boolean> m_inputMap;

    /** Constructor. */
    InputStateBuilder() {
        m_inputMap = new HashMap<>();
        m_inputMap.put(InputControl.PADDLE_DOWN, false);
        m_inputMap.put(InputControl.PADDLE_UP, false);
        m_inputMap.put(InputControl.QUIT, false);
    }

    /**
     * Sets the state of the paddle down control in this builder and returns an updated
     * instance of itself.
     * @param state true if this control is active, false otherwise.
     * @return an updated instance of this builder.
     */
    InputStateBuilder paddleDown(boolean state) {
        m_inputMap.put(InputControl.PADDLE_DOWN, state);
        return this;
    }

    /**
     * Sets the state of the paddle up control in this builder and returns an updated
     * instance of itself.
     * @param state true if this control is active, false otherwise.
     * @return an updated instance of this builder.
     */
    InputStateBuilder paddleUp(boolean state) {
        m_inputMap.put(InputControl.PADDLE_UP, state);
        return this;
    }

    /**
     * Sets the state of the quit control in this builder and returns an updated
     * instance of itself.
     * @param state true if this control is active, false otherwise.
     * @return an updated instance of this builder.
     */
    InputStateBuilder quit(boolean state) {
        m_inputMap.put(InputControl.QUIT, state);
        return this;
    }

    /**
     * Builds and returns a complete InputState object based on the state in this builder.
     * @return a complete InputState object.
     */
    InputState build() {
        return new InputState(m_inputMap.get(InputControl.PADDLE_UP), m_inputMap.get(InputControl.PADDLE_DOWN), m_inputMap.get(InputControl.QUIT));
    }
}