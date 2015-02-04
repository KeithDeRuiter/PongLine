package pongline.display;

import pongline.data.GameState;

/**
 * Interface for a game display.
 * @author Keith
 */
public interface GameDisplay {
    
    /** 
     * Sets the current state of how the game display should look
     * @param state 
     */
    public void setState(GameState state);
}
