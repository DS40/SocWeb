/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package riaboshapka_402_lab1;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lyudmila
 */
public class Riaboshapka_402_lab1 {

    /**
     * @param args the command line arguments
     */
    
     private static ControllerSql controller=null;
    public static void main(String[] args) {
        // TODO code application logic here
        My_conn con = new My_conn("org.sqlite.JDBC", "jdbc:sqlite:Lab1.sqlite");
        try {
            controller = new ControllerSql(con.getCon());
           
            User userTest = new User(2,"Nata" , "yas");
            ((IUserDAO)controller).CreateUser(userTest);
            ((IUserDAO)controller).ReadUser(userTest.id);
            userTest.name = "Natalia";
            userTest.surname = "Yasenko";
            ((IUserDAO)controller).UpdateUser(userTest);
            ((IUserDAO)controller).ReadUsers();
            
            
            Message testMessage = new Message(2, 2, "Hello Olga");
            ((IMessageDAO)controller).CreateMessage(testMessage);
            ((IMessageDAO)controller).ReadMessage(testMessage.id);
            ((IMessageDAO)controller).SearchMessage(new Message(0, 2, ""));
            testMessage.message="Olga!!!";
            ((IMessageDAO)controller).UpdateMessage(testMessage);
            ((IMessageDAO)controller).ReadMessage();
            
            ((IUserDAO)controller).DeleteUser(userTest);
            ((IMessageDAO)controller).DeleteMessage(testMessage);
        } catch (SQLException ex) {
            Logger.getLogger(Riaboshapka_402_lab1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
