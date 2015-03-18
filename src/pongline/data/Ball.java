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
public class Ball extends Entity {

    public static final float DEFAULT_WIDTH = 20;
    public static final float DEFAULT_HEIGHT = 20;
    
    /**
     * Constructs a new Ball.
     * @param pos the position
     * @param vel the Velocity
     */
    public Ball(Vector2f pos, Vector2f vel) {
        super(pos, vel, DEFAULT_WIDTH, DEFAULT_HEIGHT, EntityType.BALL);
    }

    @Override
    public String toString() {
        return "BALL pos <" + this.position + ">   vel <" + this.velocity + ">";
    }
    
}
