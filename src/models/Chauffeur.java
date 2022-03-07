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
public class Chauffeur {
    private int id;
    private String nom;
    private String prenom;
    private String sexe;
    private String numtel;

    public Chauffeur(int id, String nom, String prenom, String sexe, String numtel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.numtel = numtel;
    }

    public Chauffeur(String nom, String prenom, String sexe, String numtel) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.numtel = numtel;
    }
    

    public Chauffeur() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    @Override
    public String toString() {
        return "Chauffeur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", numtel=" + numtel + '}';
    }

    
}
