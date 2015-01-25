package pongline.input;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the input state at a given moment in time.
 * @author adam
 */
public class InputState {

    /** The Map of current input controls. */
    private final Map<InputControl, Boolean> m_inputMap;

    /**
     * Creates a new instance of InputState.
     * @param isUpPressed true if the up control was pressed, false otherwise.
     * @param isDownPressed true if the down control was pressed, false otherwise.
     * @param isQuitPressed true if the quit control was pressed, false otherwise.
     */
    InputState(boolean isUpPressed, boolean isDownPressed, boolean isQuitPressed) {
        m_inputMap = new HashMap<>();
        m_inputMap.put(InputControl.QUIT, isQuitPressed);
        m_inputMap.put(InputControl.PADDLE_DOWN, isDownPressed);
        m_inputMap.put(InputControl.PADDLE_UP, isUpPressed);
    }

    /**
     * Returns true if any is recorded in this InputState.
     * @return true if any input is recorded in this state, false otherwise.
     */
    public boolean getHasAnyInput() {
        return m_inputMap.values().contains(true);
    }

    /**
     * Returns the control state (active/true or not active/false) of the requested control.
     * @param control the InputControl state that is desired.
     * @return true if the control is active, or false if it is not active.
     */
    public boolean getControlState(InputControl control) {
        if (m_inputMap.containsKey(control)){
            return m_inputMap.get(control);
        }
        return false;
    }
}