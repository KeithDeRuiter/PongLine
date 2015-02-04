/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pongline.data;

import java.util.List;

/**
 * Represents the total gamestate of Pong.
 * @author Keith
 */
public class GameState {
    /**
     * All entities in the game.
     */
    private final List<Entity> entities;
    
    public GameState(List<Entity> entities) {
        this.entities = entities;
    }
    
    public List<Entity> getEntities() {
        return entities;
    }
    
}
