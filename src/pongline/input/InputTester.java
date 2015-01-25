package pongline.input;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * A test application for the InputManager.
 * @author adam
 */
public class InputTester {

    /** A frame.*/
    private JFrame m_frame;

    /** A text output area for the events. */
    private final JTextArea m_outputArea;

    /** The input manager. */
    private final InputManager m_inputManager;

    /** Constructor. */
    public InputTester() {
        m_outputArea = new JTextArea(10, 20);
        m_inputManager = new KeyboardInputManager();
        initialize();
    }

    /** Starts the application. */
    public void launch() {
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        Runnable checker = new Runnable() {
            @Override
            public void run() {
                InputState state = m_inputManager.getInputState();
                if (state.getHasAnyInput()) {
                    StringBuilder output = new StringBuilder("Quit:");
                    output.append(state.getControlState(InputControl.QUIT));
                    output.append(", paddle-down:");
                    output.append(state.getControlState(InputControl.PADDLE_DOWN));
                    output.append(", paddle-up:");
                    output.append(state.getControlState(InputControl.PADDLE_UP));
                    m_outputArea.append(output.toString() + System.lineSeparator());
                }
            }
        };
        // Scheduled to run 100 times per second
        exec.scheduleAtFixedRate(checker, 0L, 10, TimeUnit.MILLISECONDS);
        m_frame.pack();
        m_frame.setVisible(true);
    }

    /** Initializes the UI and stuff for the testing of the input. */
    private void initialize() {
        // Frame
        m_frame = new JFrame("Input Test Application");
        m_frame.setPreferredSize(new Dimension(320, 480));
        m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Output Area
        m_outputArea.setWrapStyleWord(true);
        m_outputArea.setLineWrap(true);
        m_outputArea.setEditable(false);
        m_outputArea.addKeyListener(m_inputManager);

        // Scroll pane
        JScrollPane jsp = new JScrollPane(m_outputArea);

        // Layout
        m_frame.setLayout(new BorderLayout());
        m_frame.add(jsp, BorderLayout.CENTER);
    }

    /** The main. */
    public static void main(String args[]) {
        InputTester tester = new InputTester();
        tester.launch();
    }
}