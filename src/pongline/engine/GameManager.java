/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pongline.engine;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import pongline.data.Entity;

/**
 *
 * @author Keith
 */
public class GameManager {
    
    private List<Entity> entities;
    
    private ScheduledExecutorService executor;
    
    private long previousUpdateTime;
    
    public GameManager() {
        executor = Executors.newSingleThreadScheduledExecutor();
    }
    
    
    public void launch() {
        
    }
}
