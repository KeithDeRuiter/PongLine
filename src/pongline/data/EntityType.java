/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pongline.data;

import pongline.display.Asset;

/**
 *
 * @author Keith
 */
public enum EntityType {
    BALL,
    PADDLE,
    WALL;
    
    public static Asset getAssetForEntityType(EntityType type) {
        Asset asset = null;
        switch(type) {
            case BALL:
                asset = Asset.BALL;
                break;
            case PADDLE:
                asset = Asset.BALL;
                break;
        }
        return asset;
    }
}
