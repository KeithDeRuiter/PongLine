package pongline.display;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import pongline.data.Entity;
import pongline.engine.GameManager;

/**
 * Contains an entity, the image, and a method to draw the renderable.
 * @author LoTB
 */
public class Renderable {
    
    /** Entity containing kinematic data. */
    private Entity m_entity;
    
    /** The image representation of the entity. */
    private BufferedImage m_image;
    
    public static final float WIDTH_SCALE = PongCanvas.CANVAS_WIDTH / GameManager.WORLD_WIDTH;
    
    public static final float HEIGHT_SCALE = PongCanvas.CANVAS_HEIGHT / GameManager.WORLD_HEIGHT;
    
    /**
     * Creates a new instance of Renderable.
     * @param entity Entity containing kinematic data
     * @param image The image representation of the entity
     */
    public Renderable(Entity entity, BufferedImage image) {
        m_entity = entity;
        m_image = image;
    }
    
    /** 
     * Draws the renderable on the provided Graphics2D.
     * @param graphics The graphics to draw this renderable on.
     */
    public void draw(Graphics2D graphics) {
        graphics.drawImage(m_image, 
                (int)(m_entity.getPosition().x / GameManager.WORLD_WIDTH * PongCanvas.CANVAS_WIDTH), 
                (int)(m_entity.getPosition().y / GameManager.WORLD_HEIGHT * PongCanvas.CANVAS_HEIGHT), 
                null);
        
        graphics.setColor(Color.RED);

        graphics.drawRect((int)(m_entity.getPosition().x * WIDTH_SCALE), 
                (int)(m_entity.getPosition().y * HEIGHT_SCALE), 
                (int)(m_entity.getWidth() * WIDTH_SCALE), 
                (int)(m_entity.getHeight() * HEIGHT_SCALE));
    }
    
    
}
