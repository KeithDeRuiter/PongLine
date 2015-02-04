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

    /**
     * Constructs a new Ball.
     * @param pos the position
     * @param vel the Velocity
     */
    public Ball(Vector2f pos, Vector2f vel) {
        super(pos, vel, EntityType.BALL);
    }
}
