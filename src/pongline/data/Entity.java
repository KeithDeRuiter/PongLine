/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pongline.data;

import java.awt.geom.Rectangle2D;
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
    
    /**
     * The width of the entity.
     */
    protected float width;
    
    /**
     * The height of the entity.
     */
    protected float height;

    
    public Entity(Vector2f pos, Vector2f vel, float width, float height, EntityType type) {
        this.position = pos;
        this.velocity = vel;
        this.width = width;
        this.height = height;
        this.type = type;
    }
    
    
    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }
    
    public void setXPosition(float x) {
        position.x = x;
    }
    
    public void setYPosition(float y) {
        position.y = y;
    }

    public Vector2f getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2f velocity) {
        this.velocity = velocity;
    }

    public float getWidth() {
        return this.width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return this.height;
    }

    public void setHeight(float height) {
        this.height = height;
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
    
    public Rectangle2D getBoundingBox() {
        return new Rectangle2D.Float(position.x, position.y, width, height);
    }
    
    public boolean intersects(Entity e) {
        return getBoundingBox().intersects(e.getBoundingBox());
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

