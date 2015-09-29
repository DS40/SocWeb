/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package riaboshapka_402_lab1;

/**
 *
 * @author Lyudmila
 */
public class Message {
      public String message;
    public int id, userId;
    
    @Override
    public String toString()
    {
        return id+" " +userId+" " +message;
    }

    public Message(int id, int userId,String message) {
        this.message = message;
        this.id = id;
        this.userId = userId;
    }  
}
