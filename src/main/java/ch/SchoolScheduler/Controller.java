/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.SchoolScheduler;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Jan
 */
@Named(value = "controller")
@SessionScoped
public class Controller implements Serializable {

    private EntityManager em;
    /**
     * Creates a new instance of Controller
     */
    public Controller() {
    }

    //Diese methode bietet die Anbindung an die Datenbank
    private Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/schoolschedulerdb";
        String username = "root";
        String password = "";
        try {
            Connection connect = DriverManager.getConnection(url, username, password);
            return connect;
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean createNewUser(String user, String passwort) {
        ResultSet rs;
        Connection con = this.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT name FROM benutzer");
            rs = ps.executeQuery();
            while(rs.next()){
                if(user.equals(rs.getString(1))){
                    return false;
                }
            }
            Benutzer neu = new Benutzer();
            neu.setName(user);
            neu.setPasswort(passwort);
            em.getTransaction().begin();
            em.persist(neu);
            em.getTransaction().commit();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
