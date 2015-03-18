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
    
    /**
     * Events that occurred this game loop.
     */
    private final List<GameEvent> events;
    
    private final int playerOneScore;
    
    private final int playerTwoScore;
    
    public GameState(List<Entity> entities, List<GameEvent> events, int playerOneScore, int playerTwoScore) {
        this.entities = entities;
        this.events = events;
        this.playerOneScore = playerOneScore;
        this.playerTwoScore = playerTwoScore;
    }
    
    public List<Entity> getEntities() {
        return entities;
    }
    
    public List<GameEvent> getEvents() {
        return events;
    }
    
    public int getPlayerOneScore() {
        return playerOneScore;
    }
    
    public int getPlayerTwoScore() {
        return playerTwoScore;
    }
    
}
