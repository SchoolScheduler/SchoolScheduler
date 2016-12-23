/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.SchoolScheduler;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jan
 */
@Entity
@Table(name = "hausaufgaben")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hausaufgaben.findAll", query = "SELECT h FROM Hausaufgaben h"),
    @NamedQuery(name = "Hausaufgaben.findById", query = "SELECT h FROM Hausaufgaben h WHERE h.hausaufgabenPK.id = :id"),
    @NamedQuery(name = "Hausaufgaben.findByBeschreibung", query = "SELECT h FROM Hausaufgaben h WHERE h.beschreibung = :beschreibung"),
    @NamedQuery(name = "Hausaufgaben.findByFaecherid", query = "SELECT h FROM Hausaufgaben h WHERE h.hausaufgabenPK.faecherid = :faecherid"),
    @NamedQuery(name = "Hausaufgaben.findByDatum", query = "SELECT h FROM Hausaufgaben h WHERE h.datum = :datum"),
    @NamedQuery(name = "Hausaufgaben.findByBenutzerid", query = "SELECT h FROM Hausaufgaben h WHERE h.hausaufgabenPK.benutzerid = :benutzerid")})
public class Hausaufgaben implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HausaufgabenPK hausaufgabenPK;
    @Size(max = 100)
    @Column(name = "Beschreibung")
    private String beschreibung;
    @Column(name = "datum")
    @Temporal(TemporalType.DATE)
    private Date datum;
    @JoinColumn(name = "Benutzer_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Benutzer benutzer;
    @JoinColumn(name = "Faecher_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Faecher faecher;

    public Hausaufgaben() {
    }

    public Hausaufgaben(HausaufgabenPK hausaufgabenPK) {
        this.hausaufgabenPK = hausaufgabenPK;
    }

    public Hausaufgaben(int id, int faecherid, int benutzerid) {
        this.hausaufgabenPK = new HausaufgabenPK(id, faecherid, benutzerid);
    }

    public HausaufgabenPK getHausaufgabenPK() {
        return hausaufgabenPK;
    }

    public void setHausaufgabenPK(HausaufgabenPK hausaufgabenPK) {
        this.hausaufgabenPK = hausaufgabenPK;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public Faecher getFaecher() {
        return faecher;
    }

    public void setFaecher(Faecher faecher) {
        this.faecher = faecher;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hausaufgabenPK != null ? hausaufgabenPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hausaufgaben)) {
            return false;
        }
        Hausaufgaben other = (Hausaufgaben) object;
        if ((this.hausaufgabenPK == null && other.hausaufgabenPK != null) || (this.hausaufgabenPK != null && !this.hausaufgabenPK.equals(other.hausaufgabenPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.SchoolScheduler.Hausaufgaben[ hausaufgabenPK=" + hausaufgabenPK + " ]";
    }
    
}
