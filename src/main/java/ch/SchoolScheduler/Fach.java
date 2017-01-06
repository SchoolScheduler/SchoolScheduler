/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.SchoolScheduler;

/**
 *
 * @author Jan
 */
public class Fach {
    private int id;
    private String name;
    private String lehrperson;

    public void Fach(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLehrperson() {
        return lehrperson;
    }

    public void setLehrperson(String lehrperson) {
        this.lehrperson = lehrperson;
    }
    
}
