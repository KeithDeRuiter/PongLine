/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pongline.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import pongline.data.Ball;
import pongline.data.Entity;
import pongline.data.EntityType;
import pongline.data.GameEvent;
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
    
    public static final float WORLD_WIDTH = 400;
    
    public static final float WORLD_HEIGHT = 300;
    
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
        //List to store events that happen during this loop
        List<GameEvent> events = new ArrayList<>();
        
        //Input
        
        //Trigger updates for every entity
        for(Entity e : entities) {
            e.update(dt);
        }
        checkForCollision(events);
        
        //Logic
        
        //Display
        display.setState(new GameState(entities, events));
    }
    
    private Entity createRandomBall() {
        Vector2f pos = new Vector2f(rand.nextFloat() * WORLD_WIDTH, rand.nextFloat() * WORLD_HEIGHT);
        Vector2f vel = new Vector2f(rand.nextFloat(), rand.nextFloat());
        return new Ball(pos, vel);
    }
    
    private void checkForCollision(List<GameEvent> events) {
        for(Entity e : entities) {
            Vector2f pos = e.getPosition();
            Vector2f vel = e.getVelocity();
            float width = e.getWidth();
            float height = e.getHeight();
            
            if(e.getType() == EntityType.BALL) {
                if(pos.x < 0 || pos.x + width > WORLD_WIDTH) {
                    e.setVelocity(new Vector2f(-vel.x, vel.y));
                    events.add(GameEvent.WALL_HIT);
                }
                if(pos.y < 0 || pos.y + height > WORLD_HEIGHT) {
                    e.setVelocity(new Vector2f(vel.x, -vel.y));
                    events.add(GameEvent.WALL_HIT);
                }
            } else if(e.getType() == EntityType.PADDLE) {
                if(pos.y < 0) {
                    e.setYPosition(0.0f);
                } else if(pos.y + height > WORLD_HEIGHT) {
                    e.setYPosition(WORLD_HEIGHT - height);
                }
            }
        }
    }
}
