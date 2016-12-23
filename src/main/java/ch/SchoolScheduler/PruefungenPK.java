/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.SchoolScheduler;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jan
 */
@Embeddable
public class PruefungenPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Faecher_id")
    private int faecherid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Benutzer_id")
    private int benutzerid;

    public PruefungenPK() {
    }

    public PruefungenPK(int id, int faecherid, int benutzerid) {
        this.id = id;
        this.faecherid = faecherid;
        this.benutzerid = benutzerid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFaecherid() {
        return faecherid;
    }

    public void setFaecherid(int faecherid) {
        this.faecherid = faecherid;
    }

    public int getBenutzerid() {
        return benutzerid;
    }

    public void setBenutzerid(int benutzerid) {
        this.benutzerid = benutzerid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) faecherid;
        hash += (int) benutzerid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PruefungenPK)) {
            return false;
        }
        PruefungenPK other = (PruefungenPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.faecherid != other.faecherid) {
            return false;
        }
        if (this.benutzerid != other.benutzerid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.SchoolScheduler.PruefungenPK[ id=" + id + ", faecherid=" + faecherid + ", benutzerid=" + benutzerid + " ]";
    }
    
}
