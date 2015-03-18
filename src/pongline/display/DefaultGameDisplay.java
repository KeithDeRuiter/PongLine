/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pongline.display;

import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import pongline.data.Entity;
import pongline.data.EntityType;
import pongline.data.GameState;
import pongline.engine.GameManager;
import pongline.input.InputManager;
import pongline.input.KeyboardInputManager;

/**
 * Default implementation of the GameDisplay interface. Creates the PongCanvas
 * and AssetManager objects. This class can also play sound.
 * @author LoTB
 */
public class DefaultGameDisplay implements GameDisplay {
    
    /** The main canvas for display. */
    private PongCanvas m_pongCanvas;
    
    /** The manager of assets. Maintains of mapping of assets to their images or sounds. */
    private AssetManager m_assMan;
    
    /** 
     * Creates and initializes a new instance of DefaultGameDisplay. 
     * @param keyListener Listener to notify of key events
     */
    public DefaultGameDisplay(KeyListener keyListener) {
        m_assMan = new AssetManager();
        m_pongCanvas = new PongCanvas();
        m_pongCanvas.addKeyListener(keyListener);
    }

    /**
     * Creates a renderables using the provided states and hands
     * those renderables off to the pong canvas.
     * @param state 
     */
    @Override
    public void setState(GameState state) {
        List<Renderable> renderables = new ArrayList<>();
        for (Entity entity : state.getEntities()) {
            renderables.add(new Renderable(entity,
                    m_assMan.getImage(EntityType.getAssetForEntityType(entity.getType()))));
        }
        m_pongCanvas.updateDisplay(renderables);
        //play renderable sounds
    }
    /**
     * Plays a wav file.
     * @param wavFile the wav to play.
     */
    @SuppressWarnings("CallToThreadDumpStack")
    private static void playSound(String wavFile) {
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(wavFile).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (LineUnavailableException ex) {
            System.out.println("Error playing sound file:  " + wavFile);
            ex.printStackTrace();
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("Unsupported sound file type.  File was:  " + wavFile);
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("IO Exception loading sound file:  " + wavFile);
            ex.printStackTrace();
        } finally {
            try {
                audioInputStream.close();
            } catch (IOException ex) {
                System.out.println("Error closing sound file:  " + wavFile);
                ex.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        InputManager input = new KeyboardInputManager();
        DefaultGameDisplay dfg = new DefaultGameDisplay(input);
        GameManager manager = new GameManager(dfg, input);
        manager.launch();
    }
    
}
