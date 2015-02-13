/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pongline.data;

import pongline.data.matlib.Vector2f;
import pongline.display.Asset;

/**
 *
 * @author Keith
 */
public class Paddle extends Entity {

    /**
     * Constructs a new paddle.
     * @param pos the position
     * @param vel the velocity
     */
    public Paddle(Vector2f pos, Vector2f vel) {
        super(pos, vel, 10, 60, EntityType.PADDLE);
    }
    
}
