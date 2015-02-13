/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pongline.engine;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import pongline.data.Ball;
import pongline.data.Entity;
import pongline.data.GameState;
import pongline.data.matlib.Vector2f;
import pongline.display.GameDisplay;

/**
 *
 * @author Keith
 */
public class GameManager {
    
    private List<Entity> entities;
    
    private final ScheduledExecutorService executor;
    
    private long previousUpdateTime;
    
    private final GameDisplay display;
    
    private final float WORLD_WIDTH = 400;
    
    private final float WORLD_HEIGHT = 300;
    
    private final Random rand;
    
    public GameManager(GameDisplay display) {
        rand = new Random();
        executor = Executors.newSingleThreadScheduledExecutor();
        previousUpdateTime = System.currentTimeMillis();
        this.display = display;
    }
    
    
    public void launch() {
        entities.add(createRandomBall());
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                long currentTime = System.currentTimeMillis();
                update(currentTime - previousUpdateTime);
                previousUpdateTime = currentTime;
            }
        }, 0, 100, TimeUnit.MILLISECONDS);
    }
    
    /**
     * 
     * @param dt The number of milliseconds since the last update;
     */
    private void update(long dt) {
        //Input
        
        //Trigger updates for every entity
        for(Entity e : entities) {
            e.update(dt);
        }
        
        //Logic
        
        //Display
        display.setState(new GameState(entities));
    }
    
    private Entity createRandomBall() {
        Vector2f pos = new Vector2f(rand.nextFloat() * WORLD_WIDTH, rand.nextFloat() * WORLD_HEIGHT);
        Vector2f vel = new Vector2f(rand.nextFloat(), rand.nextFloat());
        return new Ball(pos, vel);
    }
}
