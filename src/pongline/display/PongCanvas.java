package pongline.display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Main canvas for display.
 * @author LoTB
 */
public class PongCanvas extends JComponent {
    List<Renderable> m_renderables;
    
    /** Creates a new instance of PongCanvas. */
    public PongCanvas() {
        initComponents();
    }
    
    /** Initializes the components of the display. */
    private void initComponents() {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(640, 480));
        this.setBackground(Color.BLACK);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /** {@inheritDoc} */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        for (Renderable renderable : m_renderables) {
            renderable.draw(g2d);
        }
        
    }
    
    /** 
     * Updates the current state of the display by setting the current
     * renderables to be drawn.
     * @param renderables The renderables to draw.
     */
    public void updateDisplay(List<Renderable> renderables) {
        m_renderables = renderables;
        repaint();
    }
    
}
