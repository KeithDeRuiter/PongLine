/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pongline.engine;

import java.util.ArrayList;
import java.util.Collection;
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
import pongline.data.Paddle;
import pongline.data.matlib.Vector2f;
import pongline.display.GameDisplay;
import pongline.input.InputControl;
import pongline.input.InputManager;
import pongline.input.InputState;

/**
 *
 * @author Keith
 */
public class GameManager {
    
    private List<Entity> entities;
    
    private final ScheduledExecutorService executor;
    
    private long previousUpdateTime;
    
    private final GameDisplay display;
    
    private final InputManager inputManager;
    
    Paddle playerOnePaddle;
    
    private int playerOneScore;
    
    private int playerTwoScore;
    
    public static final float WORLD_WIDTH = 400;
    
    public static final float WORLD_HEIGHT = 300;
    
    private final Random rand;
    
    public GameManager(GameDisplay display, InputManager inputManager) {
        rand = new Random();
        executor = Executors.newSingleThreadScheduledExecutor();
        previousUpdateTime = System.currentTimeMillis();
        this.display = display;
        this.inputManager = inputManager;
        entities = new ArrayList<>();
        playerOneScore = 0;
        playerTwoScore = 0;
    }
    
    
    public void launch() {
        entities.add(createRandomBall());
        entities.add(createPaddleOne());
        entities.add(createPaddleTwo());
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                long currentTime = System.currentTimeMillis();
                update(currentTime - previousUpdateTime);
                previousUpdateTime = currentTime;
            }
        }, 0, 10, TimeUnit.MILLISECONDS);
    }
    
    /**
     * 
     * @param dt The number of milliseconds since the last update;
     */
    private void update(long dt) {
        //List to store events that happen during this loop
        List<GameEvent> events = new ArrayList<>();
        
        //Input
        InputState inputState = inputManager.getInputState();
        
        boolean paddleUp = inputState.getControlState(InputControl.PADDLE_UP);
        boolean paddleDown = inputState.getControlState(InputControl.PADDLE_DOWN);
        
        Vector2f playerOnePaddleVelocity = new Vector2f(0.0f, 0.0f);
        if(paddleUp) {
            playerOnePaddleVelocity.add(new Vector2f(0.0f, 10.0f));
        }
        if(paddleDown) {
            playerOnePaddleVelocity.add(new Vector2f(0.0f, -10.0f));
        }
        playerOnePaddle.setVelocity(playerOnePaddleVelocity);
        
        Collection<Entity> entitiesToRemove = new ArrayList<>();
        
        //Trigger updates for every entity
        for(Entity e : entities) {
            Vector2f originalPosition = e.getPosition();
            e.update(dt);
            checkForCollisions(e, originalPosition, events, entitiesToRemove, dt);
        }
        entities.removeAll(entitiesToRemove);
        
        //Logic
        if(events.contains(GameEvent.PLAYER_ONE_POINT_SCORED)) {
            playerOneScore++;
            entities.add(createRandomBall());
        }
        else if(events.contains(GameEvent.PLAYER_TWO_POINT_SCORED)) {
            playerTwoScore++;
            entities.add(createRandomBall());
        }
        
        //Display
        display.setState(new GameState(entities, events, playerOneScore, playerTwoScore));
    }
    
    private Entity createRandomBall() {
        Vector2f pos = new Vector2f(rand.nextFloat() * WORLD_WIDTH, rand.nextFloat() * WORLD_HEIGHT);
        Vector2f vel = new Vector2f(50.0f + (rand.nextFloat() * 100.0f), 50.0f + (rand.nextFloat() * 100.0f));
        return new Ball(pos, vel);
    }
    
    private Entity createPaddleOne() {
        Vector2f pos = new Vector2f((WORLD_WIDTH * 0.1f) - Paddle.DEFAULT_WIDTH, WORLD_HEIGHT * 0.5f);
        Vector2f vel = new Vector2f(0.0f, 0.0f);
        return new Paddle(pos, vel);
    }
    
    private Entity createPaddleTwo() {
        Vector2f pos = new Vector2f((WORLD_WIDTH * 0.9f) - Paddle.DEFAULT_WIDTH, WORLD_HEIGHT * 0.5f);
        Vector2f vel = new Vector2f(0.0f, 0.0f);
        return new Paddle(pos, vel);
    }
    
    /**
     * Checks for collisions between the given entity and the other entities in the world.
     * Flips the x velocity and moves the entity back to the given original position if a collision occurred.
     * @param e The entity we are checking for collisions on.
     * @param originalPosition The original position to move the entity back to if a collision occurred.
     * @param events List of events to add a collision event to if applicable.
     * @param entitiesToRemove List where entities slated for removel can be added.
     * @param dt The delta time in milliseconds.
     */
    private void checkForCollisions(Entity e, Vector2f originalPosition, List<GameEvent> events,
                                    Collection<Entity> entitiesToRemove, long dt) {  
        Vector2f pos = e.getPosition();
        Vector2f vel = e.getVelocity();
        float width = e.getWidth();
        float height = e.getHeight();

        if(e.getType() == EntityType.BALL) {
            if(pos.x < 0) {
                entitiesToRemove.add(e);
                events.add(GameEvent.PLAYER_TWO_POINT_SCORED);
            }
            if(pos.x + width > WORLD_WIDTH) {
                entitiesToRemove.add(e);
                events.add(GameEvent.PLAYER_ONE_POINT_SCORED);
            }
            if(pos.y < 0 || pos.y + height > WORLD_HEIGHT) {
                e.setVelocity(new Vector2f(vel.x, -vel.y));
                e.setPosition(originalPosition);
                events.add(GameEvent.WALL_HIT);
            }
            //Check for paddle collisions with ball
            for(Entity other : entities) {
                if(e.intersects(other) && other.getType() == EntityType.PADDLE) {
                    //There was a collision, move back and bounce according to what direction you collided
                    e.setPosition(originalPosition);
                    if(e.getPosition().y + height <= other.getPosition().y) {
                        //TOP
                        e.setVelocity(new Vector2f(vel.x, -vel.y));
                    } else if(e.getPosition().y >= other.getPosition().y + other.getHeight()) {
                        //BOTTOM
                        e.setVelocity(new Vector2f(vel.x, -vel.y));
                    } else if(e.getPosition().x + width <= other.getPosition().x) {
                        //LEFT
                        e.setVelocity(new Vector2f(-vel.x, vel.y));
                    } else if(e.getPosition().x >= other.getPosition().x + other.getWidth()) {
                        //RIGHT
                        e.setVelocity(new Vector2f(-vel.x, vel.y));
                    }
                    
                    events.add(GameEvent.PADDLE_HIT);
                }
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
