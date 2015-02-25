/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pongline.data;

import pongline.data.matlib.Vector2f;

/**
 *
 * @author Keith
 */
public class Paddle extends Entity {

    public static final float DEFAULT_WIDTH = 10;
    public static final float DEFAULT_HEIGHT = 60;
    
    /**
     * Constructs a new paddle.
     * @param pos the position
     * @param vel the velocity
     */
    public Paddle(Vector2f pos, Vector2f vel) {
        super(pos, vel, DEFAULT_WIDTH, DEFAULT_HEIGHT, EntityType.PADDLE);
    }
    
}
