/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pongline.data;

import pongline.data.matlib.Vector2f;



/**
 * Class representing a moveable entity in the game world.
 * @author Keith
 */
public abstract class Entity {
    /**
     * The position, represented using floats.
     */
    protected Vector2f position;

    /**
     * The velocity in units per second, represented using floats.  Not a unit vector.
     */
    protected Vector2f velocity;
    
    /**
     * The type of entity.
     */
    protected EntityType type;

    
    public Entity(Vector2f pos, Vector2f vel, EntityType type) {
        this.position = pos;
        this.velocity = vel;
        this.type = type;
    }
    
    
    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public Vector2f getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2f velocity) {
        this.velocity = velocity;
    }

    public EntityType getType() {
        return type;
    }

    public void setAsset(EntityType type) {
        this.type = type;
    }
    
    public void translate(Vector2f delta) {
        this.position.add(delta);
    }
    
    /**
     * Updates the state of this method.
     * @param dt The time since last update, in milliseconds.
     */
    public void update(long dt) {
        Vector2f delta = velocity.multiply((float)dt / 1000.0f);
        translate(delta);
    }
}

