/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pongline.network;

import java.util.UUID;

/**
 *
 * @author Yeaman
 */
public abstract class AbstractMessage implements Message {
    
    public UUID my_id = UUID.randomUUID();
    
    /** Constructor. */
    public AbstractMessage() {
    }
    
    /** Returns the id of the message. */
    public UUID getID() {
        return my_id;
    }
}
