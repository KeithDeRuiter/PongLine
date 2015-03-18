/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pongline.network.messages;

import pongline.network.MessageType;

/**
 * The score update message. This message indicates that a score has just happened
 * against the sender. This message has no fields and will only indicate that a
 * score has happened.
 * @author Yeaman
 */
public class ScoreUpdateMessage extends AbstractMessage {

    /** Public Constructor. */
    public ScoreUpdateMessage() {    
        super();
    }
    
    @Override
    public MessageType getType() {
        return MessageType.SCORE_UPDATE;
    }
    
}
