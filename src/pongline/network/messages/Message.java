/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pongline.network.messages;

import java.io.Serializable;
import pongline.network.MessageType;

/**
 *
 * @author Yeaman
 */
 public interface Message extends Serializable {
    public MessageType getType();
}
