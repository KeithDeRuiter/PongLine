package pongline.display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Main canvas for display.
 * @author LoTB
 */
public class PongCanvas extends JComponent {
    /** List of renderables that can draw themselves. */
    private List<Renderable> m_renderables;
    
    /** The width of the drawable canvas area. */
    public static final int CANVAS_WIDTH = 640;
    
    /** The height of the drawable canvas area. */
    public static final int CANVAS_HEIGHT = 480;
    
    /** Creates a new instance of PongCanvas. */
    public PongCanvas() {
        m_renderables = new ArrayList<>();
        initComponents();
    }
    
    /** Initializes the components of the display. */
    private void initComponents() {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        this.setBackground(Color.BLACK);
        frame.setResizable(false);
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
        g2d.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        for (Renderable renderable : m_renderables) {
            renderable.draw(g2d);
        }
//        g2d.setColor(Color.GREEN.darker().darker());
//        for (int i = 0; i < CANVAS_WIDTH; i+=20) {
//            g2d.drawLine(i, 0, i, CANVAS_HEIGHT);
//        }
//        for (int i = 0; i < CANVAS_HEIGHT; i+=20) {
//            g2d.drawLine(0, i, CANVAS_WIDTH, i);
//        }
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
