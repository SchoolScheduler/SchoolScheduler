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
    @NamedQuery(name = "Pruefungen.findByNote", query = "SELECT p FROM Pruefungen p WHERE p.note = :note"),
    @NamedQuery(name = "Pruefungen.findByDatum", query = "SELECT p FROM Pruefungen p WHERE p.datum = :datum"),
    @NamedQuery(name = "Pruefungen.findByBenutzerid", query = "SELECT p FROM Pruefungen p WHERE p.pruefungenPK.benutzerid = :benutzerid")})
public class Pruefungen implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PruefungenPK pruefungenPK;
    @Column(name = "note")
    private Integer note;
    @Column(name = "datum")
    @Temporal(TemporalType.DATE)
    private Date datum;
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

    public Pruefungen(int id, int faecherid, int benutzerid) {
        this.pruefungenPK = new PruefungenPK(id, faecherid, benutzerid);
    }

    public PruefungenPK getPruefungenPK() {
        return pruefungenPK;
    }

    public void setPruefungenPK(PruefungenPK pruefungenPK) {
        this.pruefungenPK = pruefungenPK;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
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
