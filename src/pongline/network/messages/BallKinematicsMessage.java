/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pongline.network.messages;

import pongline.data.matlib.Vector2f;
import pongline.network.MessageType;

/**
 * The ball kinematic message. This message is sent by the last player to touch the ball.
 * @author Yeaman
 */
public class BallKinematicsMessage extends AbstractMessage {
    
    /** The position of the ball. */
    private final Vector2f my_position;
    
    /** The speed and vector of the ball. */
    private final Vector2f my_velocity;
    
    /**
     * Public Constructor.
     * @param position The position of the ball.
     * @param velocity The speed and vector of the ball.
     */
    public BallKinematicsMessage(Vector2f position, Vector2f velocity) {
        super();
                
        if (position == null) {
            throw new IllegalArgumentException("Position should not be null.");
        }
        if (velocity == null) {
            throw new IllegalArgumentException("Velocity should not be null.");
        }
        my_position = position;
        my_velocity = velocity;
    }

    /**
     * Returns the position of the ball.
     * @return The ball's position.
     */
    public Vector2f getPosition() {
        return my_position;
    }
    
    /** 
     * Returns the velocity of the ball.
     * @return The ball's velocity.
     */
    public Vector2f getVelocity() {
        return my_velocity;
    }
    
    @Override
    public MessageType getType() {
        return MessageType.BALL_KINEMATICS;
    }
}
