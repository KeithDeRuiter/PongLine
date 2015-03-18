package pongline;

import pongline.display.SetupDisplay;
import pongline.display.SetupListener;
import pongline.input.InputManager;

/**
 *
 * @author Adam
 */
public class PongLine {

    /** The version number of this build. */
    public static final String VERSION = "v0.1";


    /**
     * The main class of this application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameInitializer gi = new GameInitializer();
        gi.startClient();
    }
}
