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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private int fachId;
    private String fachName;
    private String lehrperson = null;
    private float note;
    private int pruefungsId;
    private java.util.Date pruefungsDatum;
    private String pruefungsName;

    private int currentlyLoggedIn;
    private float gewichtung;

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
            while (rs.next()) {
                if (user.equals(rs.getString(1))) {
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

    public boolean login(String user, String passwort) {
        ResultSet rs;
        Connection con = this.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT id, name, passwort FROM benutzer");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (user.equals(rs.getString(2)) && passwort.equals(rs.getString(3))) {
                    this.currentlyLoggedIn = rs.getInt(1);
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String createNewFach() {
        ResultSet rs;
        Connection con = this.getConnection();
        PreparedStatement ps;

        int fachIdnew;

        Faecher neu = new Faecher();
        neu.setName(fachName);
        neu.setLehrperson(lehrperson);

        em.getTransaction().begin();
        em.persist(neu);
        em.getTransaction().commit();

        try {
            ps = con.prepareStatement("SELECT MAX(id) FROM faecher");
            rs = ps.executeQuery();
            rs.next();
            fachIdnew = rs.getInt(1);

            ps = con.prepareStatement("INSERT INTO benutzer_faecher (Benuter_id, Faecher_id) VALUES (?,?)");
            ps.setInt(1, this.currentlyLoggedIn);
            ps.setInt(2, fachId);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "grades";
    }

    public List<Fach> getAllFaecher() {
        List<Fach> faecher = new ArrayList();
        ResultSet rs;
        Connection con = this.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT f.id, f.name, f.lehrperson FROM faecher f JOIN benutzer_faecher bf ON f.id=bf.Faecher_id JOIN benutzer b ON bf.Benutzer_id=b.id WHERE b.id=?;");
            ps.setInt(1, currentlyLoggedIn);
            rs = ps.executeQuery();
            while (rs.next()) {
                Fach fach = new Fach();
                fach.setId(rs.getInt(1));
                fach.setName(rs.getString(2));
                fach.setLehrperson(rs.getString(3));
                faecher.add(fach);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return faecher;
    }

    public List<pruefung> allPendingPruefungen() {
        List<pruefung> pruefungen = new ArrayList();
        ResultSet rs;
        Connection con = this.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT p.name, f.name fach, p.note, p.gewichtung, p.datum FROM pruefungen p JOIN benutzer b ON p.Benutzer_id=b.id JOIN faecher f ON p.Faecher_id=f.id WHERE b.id=? AND p.note IS NULL;");
            ps.setInt(1, currentlyLoggedIn);
            rs = ps.executeQuery();
            while (rs.next()) {
                pruefung test = new pruefung();
                test.setName(rs.getString(1));
                test.setFach(rs.getString(2));
                test.setGewichtung(rs.getFloat(3));
                test.setNote(rs.getFloat(4));
                test.setDatum(rs.getDate(5));           //AUFPASSEN DATUM SQL UND DATUM JAVA.UTIL
                pruefungen.add(test);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pruefungen;
    }

    public List<pruefung> allPruefungen() {
        List<pruefung> pruefungen = new ArrayList();
        ResultSet rs;
        Connection con = this.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT p.name, f.name fach, p.note, p.gewichtung, p.datum FROM pruefungen p JOIN benutzer b ON p.Benutzer_id=b.id JOIN faecher f ON p.Faecher_id=f.id WHERE b.id=? AND p.note>0");
            ps.setInt(1, currentlyLoggedIn);
            rs = ps.executeQuery();
            while (rs.next()) {
                pruefung test = new pruefung();
                test.setName(rs.getString(1));
                test.setFach(rs.getString(2));
                test.setGewichtung(rs.getFloat(3));
                test.setNote(rs.getFloat(4));
                test.setDatum(rs.getDate(5));           //AUFPASSEN DATUM SQL UND DATUM JAVA.UTIL
                pruefungen.add(test);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pruefungen;
    }

    public String noteEintragen() {
        ResultSet rs;
        Connection con = this.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("UPDATE pruefungen SET note=? WHERE id=? AND Benutzer_id=? AND note=0;");
            ps.setFloat(1, this.note);
            ps.setInt(2, pruefungsId);
            ps.setInt(3, this.currentlyLoggedIn);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "grades";
    }

    public String createNewPruefung() {
        Connection con = this.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("INSERT INTO pruefungen (Faecher_id, datum, Benutzer_id, name, gewichtung) VALUES (?,?,?,?,?)");
            ps.setInt(1, this.fachId);
            ps.setDate(2, new java.sql.Date(pruefungsDatum.getTime()));
            ps.setInt(3, this.currentlyLoggedIn);
            ps.setString(4, this.pruefungsName);
            ps.setFloat(5, this.gewichtung);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "menu";
    }

    public String getFachName() {
        return fachName;
    }

    public void setFachName(String fachName) {
        this.fachName = fachName;
    }

    public String getLehrperson() {
        return lehrperson;
    }

    public void setLehrperson(String lehrperson) {
        this.lehrperson = lehrperson;
    }

    public float getNote() {
        return note;
    }

    public void setNote(String note) {
        float grade = Float.valueOf(note);
        this.note = grade;
    }

    public int getPruefungsId() {
        return pruefungsId;
    }

    public void setPruefungsId(int pruefungsId) {
        this.pruefungsId = pruefungsId;
    }

    public Date getPruefungsDatum() {
        return pruefungsDatum;
    }

    public void setPruefungsDatum(Date pruefungsDatum) {
        this.pruefungsDatum = pruefungsDatum;
    }

    public int getFachId() {
        return fachId;
    }

    public void setFachId(int fachId) {
        this.fachId = fachId;
    }

    public String getPruefungsName() {
        return pruefungsName;
    }

    public void setPruefungsName(String pruefungsName) {
        this.pruefungsName = pruefungsName;
    }

    public float getGewichtung() {
        return gewichtung;
    }

    public void setGewichtung(float gewichtung) {
        this.gewichtung = gewichtung;
    }

}
