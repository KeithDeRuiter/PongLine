package pongline.display;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JButton;
import pongline.PongLine;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * The display for setting up a game.
 * @author adam
 */
public class SetupDisplay {

    private JFrame m_frame;
    private JTextField m_remoteIpField;
    private JTextField m_remotePortField;
    private JTextField m_localPortField;

    private static final String DEFAULT_REMOTE_PORT = "56070";
    private static final String DEFAULT_LOCAL_PORT = "57070";

    private JButton m_hostButton;
    private JButton m_connectButton;

    private final Set<SetupListener> m_listeners;

    /** Creates a new instance of SetupDisplay. */
    public SetupDisplay() {
        m_listeners = new HashSet<>();
        initialize();
    }

    /** Launches this display. */
    public void launch() {
        m_frame.pack();
        updateButtonState();
        m_frame.setVisible(true);
    }

    /**
     * Adds a new SetupListener to be alerted when the user selects Host or Connect.
     * @param sl the listener to add.  Null listeners are ignored.
     */
    public void addListener(SetupListener sl) {
        if (sl != null) {
            m_listeners.add(sl);
        }
    }

    /**
     * Returns the local port value.
     * @return the local port value.
     */
    public int getLocalPort() {
        return Integer.valueOf(m_localPortField.getText());
    }

    /**
     * Returns the remote port value.
     * @return the remote port value.
     */
    public int getRemotePort() {
        return Integer.valueOf(m_remotePortField.getText());
    }

    /**
     * Checks to see if the content of this display is valid.
     * @return true if the content is valid.
     */
    private boolean isContentValid() {
        if (Integer.valueOf(m_localPortField.getText()) > 65535) {
            return false;
        }
        if (Integer.valueOf(m_remotePortField.getText()) > 65535) {
            return false;
        }
        // THIS IS HORRIBLE.  DO THIS BETTER.
//        try {
//            InetAddress.getByName(m_remoteIpField.getText());
//        } catch (UnknownHostException ex) {
//            // ignored
//            return false;
//        }

        return true;
    }

    /** Updates the enabled state of the buttons, based on currently entered values. */
    private void updateButtonState() {
        m_connectButton.setEnabled(isContentValid());
        m_hostButton.setEnabled(isContentValid());
    }

    /** Initialize the display components */
    private void initialize() {
        m_frame = new JFrame("PongLine Setup - " + PongLine.VERSION);
        m_frame.setLayout(new GridBagLayout());
        m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DocumentListener docListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                updateButtonState();
            }
            @Override
            public void removeUpdate(DocumentEvent de) {
                updateButtonState();
            }
            @Override
            public void changedUpdate(DocumentEvent de) {
                updateButtonState();
            }
        };

        m_remoteIpField = new JTextField(15);
        m_remoteIpField.getDocument().addDocumentListener(docListener);

        m_remotePortField = new JTextField(5);
        m_remotePortField.setDocument(new LimitedLengthIntegerDocument(5));
        m_remotePortField.setText(DEFAULT_REMOTE_PORT);
        m_remotePortField.getDocument().addDocumentListener(docListener);

        m_localPortField = new JTextField(5);
        m_localPortField.setDocument(new LimitedLengthIntegerDocument(5));
        m_localPortField.setText(DEFAULT_LOCAL_PORT);
        m_localPortField.getDocument().addDocumentListener(docListener);

        m_hostButton = new JButton("Host Game");
        m_hostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                for (SetupListener sl : m_listeners) {
                    sl.hostSelected();
                }
            }
        });
        m_connectButton = new JButton("Connet to Game");
        m_connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                for (SetupListener sl : m_listeners) {
                    sl.connectSelected();
                }
            }
        });

        // TITLE
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0f;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3, 3, 3, 3);
        JLabel title = new JLabel("Welcome to PongLine");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setHorizontalAlignment(JLabel.CENTER);
        m_frame.add(title, gbc);

        // COL ONE - Labels
        // Remote IP Label
        gbc.gridwidth = 1;
        gbc.weightx = 0.0f;
        gbc.gridy = 1;
        m_frame.add(new JLabel("Remote IP:"), gbc);

        // Remote port label
        gbc.gridy = 2;
        m_frame.add(new JLabel("Remote Port:"), gbc);

        // local port label
        gbc.gridy = 3;
        m_frame.add(new JLabel("Local Port:"), gbc);


        // COL TWO - Values
        // Remote IP Field
        gbc.gridx = 1;
        gbc.gridy = 1;
        m_frame.add(m_remoteIpField, gbc);

        // Remote Port field
        gbc.gridy = 2;
        m_frame.add(m_remotePortField, gbc);

        // Local port field
        gbc.gridy = 3;
        m_frame.add(m_localPortField, gbc);

        // BUTTONS
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0f;
        m_frame.add(new JSeparator(SwingConstants.HORIZONTAL));

        // Buttons
        gbc.gridwidth = 1;
        m_frame.add(m_hostButton, gbc);

        gbc.gridx = 1;
        m_frame.add(m_connectButton, gbc);
    }


    /** Test Main */
    public static void main(String[] args) {
        SetupDisplay sd = new SetupDisplay();
        sd.launch();
    }
}