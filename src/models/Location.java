/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author yassi
 */
public class Location {
     private int idl;
    private String model;
    private String prix;
    private String dateloc;
    private String duree;

    public Location(int idl, String model, String prix, String dateloc, String duree) {
        this.idl = idl;
        this.model = model;
        this.prix = prix;
        this.dateloc = dateloc;
        this.duree = duree;
    }

    public Location() {
    }

    public Location(String model, String prix, String dateloc, String duree) {
        this.model = model;
        this.prix = prix;
        this.dateloc = dateloc;
        this.duree = duree;
    }

    public int getIdl() {
        return idl;
    }

    public void setIdl(int idl) {
        this.idl = idl;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getDateloc() {
        return dateloc;
    }

    public void setDateloc(String dateloc) {
        this.dateloc = dateloc;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    @Override
    public String toString() {
        return "Location{" + "idl=" + idl + ", model=" + model + ", prix=" + prix + ", dateloc=" + dateloc + ", duree=" + duree + '}';
    }
    

    
}
