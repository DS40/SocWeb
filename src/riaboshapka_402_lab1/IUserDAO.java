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
public interface IUserDAO {
    boolean CreateUser(User user);
    User ReadUser(int id);
    List<User> ReadUsers();
    boolean UpdateUser(User user);
    boolean DeleteUser(User user);
}
