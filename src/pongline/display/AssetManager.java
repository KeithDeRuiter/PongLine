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
//            m_imageAssetMap.put(Asset.BALL, ImageIO.read(new File("res/ball.png")));
//            m_imageAssetMap.put(Asset.PADDLE, ImageIO.read(new File("res/paddle.png")));
            m_imageAssetMap.put(Asset.BALL, scale(ImageIO.read(new File("res/ball.png")), BufferedImage.TYPE_INT_RGB, (int)(Ball.DEFAULT_WIDTH * Renderable.WIDTH_SCALE), (int)(Ball.DEFAULT_HEIGHT * Renderable.HEIGHT_SCALE), .5, .5));
            m_imageAssetMap.put(Asset.PADDLE, scale(ImageIO.read(new File("res/paddle.png")), BufferedImage.TYPE_INT_RGB, (int)(Paddle.DEFAULT_WIDTH * Renderable.WIDTH_SCALE), (int)(Paddle.DEFAULT_HEIGHT * Renderable.HEIGHT_SCALE), .5, .5));

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
    private BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
        BufferedImage dbi = null;
        System.out.println("fWidth : " + fWidth);
        System.out.println("fHeight : " + fHeight);
        if (sbi != null) {
            dbi = new BufferedImage(dWidth, dHeight, imageType);
            Graphics2D g = dbi.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
            g.drawRenderedImage(sbi, at);
        }
        System.out.println("image dimensions: " + dbi.getWidth() + ", " + dbi.getHeight());
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
