/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pongline.display;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import pongline.data.Ball;
import pongline.data.Paddle;

/**
 * Manages images and sounds and maps them to their assets. 
 * @author LoTB
 */
public class AssetManager {
    
    /** Map of Assets to their images. */
    private final Map<Asset, BufferedImage> m_imageAssetMap;
    
    /** Map of Assets to their sounds. */
    private final Map<Asset, AudioInputStream> m_soundAssetMap;
    
    /** Loads the assets images and sounds. */
    public AssetManager() {
        m_imageAssetMap = new HashMap<>();
        m_soundAssetMap = new HashMap<>();
        try {
            BufferedImage ballImage = ImageIO.read(new File("res/ball.png"));
            float ballTargetWidth = Ball.DEFAULT_WIDTH * Renderable.WIDTH_SCALE;
            float ballTargetHeight = Ball.DEFAULT_HEIGHT * Renderable.HEIGHT_SCALE;
            BufferedImage scaledBallImage = scale(ballImage, (int)ballTargetWidth, (int)ballTargetHeight, (int)ballTargetWidth / (float)ballImage.getWidth(), (int)ballTargetHeight / (float)ballImage.getHeight()); 
            m_imageAssetMap.put(Asset.BALL, scaledBallImage);
            
            BufferedImage paddleImage = ImageIO.read(new File("res/paddle.png"));
            float paddleTargetWidth = Paddle.DEFAULT_WIDTH * Renderable.WIDTH_SCALE;
            System.out.println("paddle target width: " + paddleTargetWidth);
            System.out.println("paddle target width: (int)" + (int)paddleTargetWidth);
            float paddleTargetHeight = Paddle.DEFAULT_HEIGHT * Renderable.HEIGHT_SCALE; 
            BufferedImage scaledPaddleImage = scale(paddleImage, (int)paddleTargetWidth, (int)paddleTargetHeight, (int)paddleTargetWidth / (float)paddleImage.getWidth(), (int)paddleTargetHeight / (float)paddleImage.getHeight());
            m_imageAssetMap.put(Asset.PADDLE, scaledPaddleImage);

        } catch (IOException ex) {
            Logger.getLogger(PongCanvas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * scale image
     *
     * @param sbi image to scale
     * @param imageType type of image
     * @param dWidth width of destination image
     * @param dHeight height of destination image
     * @param fWidth x-factor for transformation / scaling
     * @param fHeight y-factor for transformation / scaling
     * @return scaled image
     */
    private BufferedImage scale(BufferedImage sbi, int dWidth, int dHeight, double fWidth, double fHeight) {
        BufferedImage dbi = null;
        if (sbi != null) {
            dbi = new BufferedImage(dWidth, dHeight, sbi.getType());
            Graphics2D g = dbi.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
            g.drawRenderedImage(sbi, at);
        }
        return dbi;
    }
    
    /**
     * Returns the image associated with the asset, or null if the asset
     * is not associated with an image.
     * @param asset The asset to retrieve the image for
     * @return 
     */
    public BufferedImage getImage(Asset asset) {
        return m_imageAssetMap.get(asset);
    }
    
    /**
     * Returns the sound associated with the asset, or null if the asset
     * is not associated with a sound.
     * @param asset The asset to retrieve the image for
     * @return 
     */
    public AudioInputStream getSound(Asset asset) {
        return m_soundAssetMap.get(asset);
    }
    
}
