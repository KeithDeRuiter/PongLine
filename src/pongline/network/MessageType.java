/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pongline.network;

/**
 *
 * @author Yeaman
 */
public enum MessageType {
    /** Ball kinematics message contains the attributes of the ball. */
    BALL_KINEMATICS,
    /** Paddle position messages is for the sender's paddle. */
    PADDLE_POSITiON,
    /** score update message indicates when the sender has been scored upon. */
    SCORE_UPDATE;
    
    
}
