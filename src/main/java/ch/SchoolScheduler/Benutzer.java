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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "benutzer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Benutzer.findAll", query = "SELECT b FROM Benutzer b"),
    @NamedQuery(name = "Benutzer.findById", query = "SELECT b FROM Benutzer b WHERE b.id = :id"),
    @NamedQuery(name = "Benutzer.findByName", query = "SELECT b FROM Benutzer b WHERE b.name = :name"),
    @NamedQuery(name = "Benutzer.findByPasswort", query = "SELECT b FROM Benutzer b WHERE b.passwort = :passwort")})
public class Benutzer implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "passwort")
    private String passwort;
    @JoinTable(name = "benutzer_faecher", joinColumns = {
        @JoinColumn(name = "Benutzer_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "Faecher_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Faecher> faecherCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "benutzer")
    private Collection<Hausaufgaben> hausaufgabenCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "benutzer")
    private Collection<Pruefungen> pruefungenCollection;

    public Benutzer() {
    }

    public Benutzer(Integer id) {
        this.id = id;
    }

    public Benutzer(Integer id, String name, String passwort) {
        this.id = id;
        this.name = name;
        this.passwort = passwort;
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

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    @XmlTransient
    public Collection<Faecher> getFaecherCollection() {
        return faecherCollection;
    }

    public void setFaecherCollection(Collection<Faecher> faecherCollection) {
        this.faecherCollection = faecherCollection;
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
        if (!(object instanceof Benutzer)) {
            return false;
        }
        Benutzer other = (Benutzer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.SchoolScheduler.Benutzer[ id=" + id + " ]";
    }
    
}
