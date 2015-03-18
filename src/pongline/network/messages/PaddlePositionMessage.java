/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pongline.network.messages;

import pongline.data.matlib.Vector2f;
import pongline.network.MessageType;

/**
 * The paddle position message. This message is sent by the owner of the paddle.
 * @author Yeaman
 */
public class PaddlePositionMessage extends AbstractMessage {

    /** The Position of the paddle. */
    public final Vector2f my_position;
    
    /**
     * Public constructor.
     * @param position The position of the paddle.
     */
    public PaddlePositionMessage(Vector2f position) {
        super();
        
        if (position == null) {
            throw new IllegalArgumentException("Position may not be null.");
        }
        my_position = position;
    }
    
    /**
     * Returns The position of the paddle.
     * @return the position of the paddle.
     */
    public Vector2f getPosition() {
        return my_position;
    }
    
    @Override
    public MessageType getType() {
        
        return MessageType.PADDLE_POSITION;
    }
    
}
