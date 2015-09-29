/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package riaboshapka_402_lab1;

import java.util.List;

/**
 *
 * @author Lyudmila
 */
public interface IMessageDAO {
        boolean CreateMessage(Message message );
    Message ReadMessage(int id);
    List<Message> ReadMessage();
    List<Message> SearchMessage(Message message);
    boolean UpdateMessage(Message message );
    boolean DeleteMessage(Message message);
}
