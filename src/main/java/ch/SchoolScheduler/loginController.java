/*
 * Datei:          DATEINAME
 * Datum:          DATUM
 * Ersteller:      @author Jan Säuberli
 * Version:        @version 1.0
 * Änderungen:     DATUM WER WAS
 * Beschreibung:   BESCHREIBUNG
 */
package ch.schoolscheduler;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
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

    /**
     * Creates a new instance of LoginController
     */
    public loginController() {
    }

    public String login() {
        if (username.equals("admin") && passwort.equals("1234")) {
            this.loggedIn = true;
            //SessionID verändern um Sessionfixation vorzubeugen
//            ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).changeSessionId();
            return "menu?faces-redirect=true";
        }
        return "/login";
    }

    public String register() {
        return "";
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
