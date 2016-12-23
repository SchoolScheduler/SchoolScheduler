/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.SchoolScheduler;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jan
 */
@Entity
@Table(name = "faecher")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Faecher.findAll", query = "SELECT f FROM Faecher f"),
    @NamedQuery(name = "Faecher.findById", query = "SELECT f FROM Faecher f WHERE f.id = :id"),
    @NamedQuery(name = "Faecher.findByName", query = "SELECT f FROM Faecher f WHERE f.name = :name")})
public class Faecher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "faecherCollection")
    private Collection<Benutzer> benutzerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "faecher")
    private Collection<Hausaufgaben> hausaufgabenCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "faecher")
    private Collection<Pruefungen> pruefungenCollection;

    public Faecher() {
    }

    public Faecher(Integer id) {
        this.id = id;
    }

    public Faecher(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Benutzer> getBenutzerCollection() {
        return benutzerCollection;
    }

    public void setBenutzerCollection(Collection<Benutzer> benutzerCollection) {
        this.benutzerCollection = benutzerCollection;
    }

    @XmlTransient
    public Collection<Hausaufgaben> getHausaufgabenCollection() {
        return hausaufgabenCollection;
    }

    public void setHausaufgabenCollection(Collection<Hausaufgaben> hausaufgabenCollection) {
        this.hausaufgabenCollection = hausaufgabenCollection;
    }

    @XmlTransient
    public Collection<Pruefungen> getPruefungenCollection() {
        return pruefungenCollection;
    }

    public void setPruefungenCollection(Collection<Pruefungen> pruefungenCollection) {
        this.pruefungenCollection = pruefungenCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Faecher)) {
            return false;
        }
        Faecher other = (Faecher) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.SchoolScheduler.Faecher[ id=" + id + " ]";
    }
    
}
