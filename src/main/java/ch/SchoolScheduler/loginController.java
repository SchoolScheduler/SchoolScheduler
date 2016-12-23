/*
 * Datei:          DATEINAME
 * Datum:          DATUM
 * Ersteller:      @author Jan Säuberli
 * Version:        @version 1.0
 * Änderungen:     DATUM WER WAS
 * Beschreibung:   BESCHREIBUNG
 */
package ch.SchoolScheduler;

import java.io.Serializable;
import java.security.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Jan
 */
@Named(value = "loginController")
@SessionScoped
public class loginController implements Serializable {

    @Pattern(regexp = "^[a-z]*$", message = "PW oder User nicht korrekt.")
    private String username;

    @Pattern(regexp = "^[a-zA-Z0-9]*$^-_:;,.+#@%&/()=", message = "PW oder User nicht korrekt.")
    private String passwort;

    @Pattern(regexp = "^[a-z]*$", message = "PW oder User nicht korrekt.")
    private String userNew;

    @Pattern(regexp = "^[a-zA-Z0-9]*$^-_:;,.+#@%&/()=", message = "PW oder User nicht korrekt.")
    private String passwortNew;

    @Pattern(regexp = "^[a-zA-Z0-9]*$^-_:;,.+#@%&/()=", message = "PW oder User nicht korrekt.")
    private String passwortNewCheck;

    private boolean loggedIn = false;

    @Inject
    private Controller c;

    /**
     * Creates a new instance of LoginController
     */
    public loginController() {
    }

    public String login() {

        if (username != null && username.equals("admin") && passwort != null && passwort.equals("admin")) {
            loggedIn = true;
            //SessionID verändern um Sessionfixation vorzubeugen
            //((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).changeSessionId();
            return "menu?faces-redirect=true";
        }
        return "login";
    }

    public String register() {
        if (!passwortNew.equals(passwortNewCheck)) {
            
            return "register";
            
        } else if (c.createNewUser(userNew, passwortNew)) {
            
        }
        return "login";
    }

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public String getUserNew() {
        return userNew;
    }

    public void setUserNew(String userNew) {
        this.userNew = userNew;
    }

    public String getPasswortNew() {
        return passwortNew;
    }

    public void setPasswortNew(String passwortNew) {
        this.passwortNew = passwortNew;
    }

    public String getPasswortNewCheck() {
        return passwortNewCheck;
    }

    public void setPasswortNewCheck(String passwortNewCheck) {
        this.passwortNewCheck = passwortNewCheck;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

}
