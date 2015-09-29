/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package riaboshapka_402_lab1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lyudmila
 */
public class ControllerSql implements IUserDAO,IMessageDAO{
  private Connection con=null;
    private Statement stmt=null;
   public  ControllerSql(Connection con) throws SQLException {
        this.con = con;
        this.stmt=this.con.createStatement();
    }

    public ResultSet  searchKlientSql(String query) throws SQLException{
        return stmt.executeQuery(query);
    }

    @Override
    public boolean CreateUser(User user) {
        String query="INSERT INTO \"main\".\"Users\" (\"id\",\"Name\",\"Surname\") VALUES  "
                + " (\""+ user.id+"\",\""+user.name+"\",\""+user.surname+"\") ";

        try {
            return this.stmt.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerSql.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.toString());
            return false;
        }
    }

    @Override
    public User ReadUser(int id) {
        String query="SELECT * FROM \"main\".\"Users\" where id = "+id;
        try {
            ResultSet q =this.stmt.executeQuery(query);
            q.next();
            User rez = new User(q.getInt(1),q.getString(2),q.getString(3));
            System.out.println(rez.toString());
            return (rez);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerSql.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.toString());
            return null;
        }
    }

    @Override
    public List<User> ReadUsers() {
        String query="SELECT * FROM \"main\".\"Users\"";
        try {
            ResultSet q =this.stmt.executeQuery(query);
            List<User> user = new LinkedList(); 
            String rezString = new String();
            while(q.next())
            {
                User tmp =new User(q.getInt(1),q.getString(2),q.getString(3));
                user.add(tmp);
                rezString+=tmp.toString();
            }
            System.out.println(rezString);
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(ControllerSql.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.toString());
            return null;
        }
    }

    @Override
    public boolean UpdateUser(User user) {
        try {
            String query = "UPDATE \"main\".\"Users\" "
                    + "SET "
                    + "   \"id\" = \""+ user.id+"\", "
                    + "   \"Name\" = \""+user.name+"\", "
                    + "\"Surname\" = \""+user.surname+"\" "
                    + "WHERE  \"id\"= "+user.id ;
            return this.stmt.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerSql.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean DeleteUser(User user) {
        String query="DELETE FROM \"main\".\"Users\" where id = "+user.id;
        try {
            return this.stmt.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerSql.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.toString());
            return false;
        }
    }

    @Override
    public boolean CreateMessage(Message message) {
        String query="INSERT INTO \"main\".\"Messages\" (\"Id\",\"UserId\",\"Message\") VALUES "
                + " ( "+message.id+" , "+message.userId+" , \""+message.message+"\") ";
        try {
            return this.stmt.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerSql.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.toString());
            return false;
        }
    }

    @Override
    public Message ReadMessage(int id) {
        String query="SELECT * FROM \"main\".\"Messages\" where Id = "+id+"";
        try {
            ResultSet q =this.stmt.executeQuery(query);
            q.next();
            Message rez = new Message(q.getInt(1),q.getInt(2),q.getString(3));
            System.out.println(rez.toString());
            return (rez);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerSql.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.toString());
            return null;
        }
    }

    @Override
    public List<Message> ReadMessage() {
        String query="SELECT * FROM \"main\".\"Messages\"";
        try {
            ResultSet q =this.stmt.executeQuery(query);
            List<Message> messages = new LinkedList(); 
            String rezString = new String();
            while(q.next())
            {
                Message tmp =new Message(q.getInt(1),q.getInt(2),q.getString(3));
                messages.add(tmp);
                rezString+=tmp.toString();
            }
            System.out.println(rezString);
            return messages;
        } catch (SQLException ex) {
            Logger.getLogger(ControllerSql.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.toString());
            return null;
        }
    }

    @Override
    public List<Message> SearchMessage(Message message) {
        boolean first = true;
        String query="SELECT * FROM \"main\".\"Messages\" where ";
        if(message.id>0)
        {
            if(first)
            {
                first=false;
                query+="Id = "+message.id+" ";
            }
        }
        if(message.userId>0)
        {
            if(first)
            {
                first=false;
                query+="UserId = "+message.userId+" ";
            }
            else
            {
                query+="or UserId = "+message.userId+" ";
            }
            
        }
        if(message.message!=null||!"".equals(message.message))
        {
            if(first)
            {
                first=false;
                query+="Message = \""+message.message+"\" ";
            }
            else
            {
                query+="or Message = \""+message.message+"\" ";
            }
            
        }
        if(first)
        {
           return ReadMessage();     
        }
        
        try {
            ResultSet q =this.stmt.executeQuery(query);
            List<Message> messages = new LinkedList(); 
            String rezString = new String();
            while(q.next())
            {
                Message tmp =new Message(q.getInt(1),q.getInt(2),q.getString(3));
                messages.add(tmp);
                rezString+=tmp.toString();
            }
            System.out.println(rezString);
            return messages;
        } catch (SQLException ex) {
            Logger.getLogger(ControllerSql.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.toString());
            return null;
        }
    }

    @Override
    public boolean UpdateMessage(Message message) {
        try {
            String query = "UPDATE \"main\".\"Messages\" "
                    + "SET "
                    + "\"Id\" = "+message.id+","
                    + " \"UserId\" = "+message.userId+", "
                    + "\"Message\" = \""+message.message+"\" "
                    + "WHERE  Id = "+message.id+"";
            return this.stmt.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerSql.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean DeleteMessage(Message message) {
        String query="DELETE FROM \"main\".\"Messages\" where Id = "+message.id+"";
        try {
            return this.stmt.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerSql.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.toString());
            return false;
        }
    }
    
}
