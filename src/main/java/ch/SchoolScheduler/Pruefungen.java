/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.SchoolScheduler;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jan
 */
@Entity
@Table(name = "pruefungen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pruefungen.findAll", query = "SELECT p FROM Pruefungen p"),
    @NamedQuery(name = "Pruefungen.findById", query = "SELECT p FROM Pruefungen p WHERE p.pruefungenPK.id = :id"),
    @NamedQuery(name = "Pruefungen.findByFaecherid", query = "SELECT p FROM Pruefungen p WHERE p.pruefungenPK.faecherid = :faecherid"),
    @NamedQuery(name = "Pruefungen.findByDatum", query = "SELECT p FROM Pruefungen p WHERE p.datum = :datum"),
    @NamedQuery(name = "Pruefungen.findByBenutzerid", query = "SELECT p FROM Pruefungen p WHERE p.pruefungenPK.benutzerid = :benutzerid"),
    @NamedQuery(name = "Pruefungen.findByNote", query = "SELECT p FROM Pruefungen p WHERE p.note = :note"),
    @NamedQuery(name = "Pruefungen.findByGewichtung", query = "SELECT p FROM Pruefungen p WHERE p.gewichtung = :gewichtung")})
public class Pruefungen implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PruefungenPK pruefungenPK;
    @Column(name = "datum")
    @Temporal(TemporalType.DATE)
    private Date datum;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "note")
    private float note = Float.valueOf(0);
    @Basic(optional = false)
    @NotNull
    @Column(name = "gewichtung")
    private float gewichtung;
    @JoinColumn(name = "Benutzer_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Benutzer benutzer;
    @JoinColumn(name = "Faecher_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Faecher faecher;

    public Pruefungen() {
    }

    public Pruefungen(PruefungenPK pruefungenPK) {
        this.pruefungenPK = pruefungenPK;
    }

    public Pruefungen(PruefungenPK pruefungenPK, String name, float note, float gewichtung) {
        this.pruefungenPK = pruefungenPK;
        this.name = name;
        this.note = note;
        this.gewichtung = gewichtung;
    }

    public Pruefungen(int id, int faecherid, int benutzerid) {
        this.pruefungenPK = new PruefungenPK(id, faecherid, benutzerid);
    }

    public PruefungenPK getPruefungenPK() {
        return pruefungenPK;
    }

    public void setPruefungenPK(PruefungenPK pruefungenPK) {
        this.pruefungenPK = pruefungenPK;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public float getGewichtung() {
        return gewichtung;
    }

    public void setGewichtung(float gewichtung) {
        this.gewichtung = gewichtung;
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
        hash += (pruefungenPK != null ? pruefungenPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pruefungen)) {
            return false;
        }
        Pruefungen other = (Pruefungen) object;
        if ((this.pruefungenPK == null && other.pruefungenPK != null) || (this.pruefungenPK != null && !this.pruefungenPK.equals(other.pruefungenPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.SchoolScheduler.Pruefungen[ pruefungenPK=" + pruefungenPK + " ]";
    }
    
}
