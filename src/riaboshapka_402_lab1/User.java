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
public class User {
        public int id;
    public String name,  surname;
    
    @Override
    public String toString()
    {
        return id+" " +name+" " +surname;
    }

    public User(int id, String name,  String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
}
