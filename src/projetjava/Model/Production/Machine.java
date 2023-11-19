/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava.Model.Production;

/**
 *
 * @author hajri
 */
public class Machine {
    private int id;
    private String nom; 
    private String type; 
    private boolean enMaintenance;

    public Machine(int id, String nom, String type, boolean enMaintenance) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.enMaintenance = enMaintenance;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getEnMaintenance() {
        return enMaintenance;
    }

    public void setEnMaintenance(boolean enMaintenance) {
        this.enMaintenance = enMaintenance;
    }

    @Override
    public String toString() {
        return "Machine{" + "id=" + id + ", nom=" + nom + ", type=" + type + ", enMaintenance=" + enMaintenance + '}';
    }
}
