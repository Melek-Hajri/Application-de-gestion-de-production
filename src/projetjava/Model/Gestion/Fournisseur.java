/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava.Model.Gestion;

/**
 *
 * @author hajri
 */
public final class Fournisseur extends Personne {
    private String societe;
    public Fournisseur(String nom, String tel, String societe){
        super(nom, tel);
        this.societe = societe;
    }
    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }
}
