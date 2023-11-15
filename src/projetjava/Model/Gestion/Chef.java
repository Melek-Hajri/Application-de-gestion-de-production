/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava.Model.Gestion;

/**
 *
 * @author admin
 */
import java.util.*;
import java.lang.String;
public final class Chef extends Employe {
    private int nbreHeures; // Ajout de l'attribut nbreHeures
    private double prixParHeure; // Ajout de l'attribut prixParHeure
    String mdp;

    public Chef(String nom,String prenom, String id, String adresseEmail, String numeroTelephone ,double age, int nbreHeures, double prixParHeure,String mdp) {
        super(nom,prenom,id,adresseEmail,numeroTelephone,age);
        this.nbreHeures = nbreHeures;
        this.prixParHeure = prixParHeure;
        this.mdp=mdp;
    }

    @Override
    public String toString() {
return "Chef{" +
            "nom='" + nom + '\'' +
            ", prenom='" + prenom + '\'' +
            ", id='" + id + '\'' +
            ", adresseEmail='" + adresseEmail + '\'' +
            ", numeroTelephone='" + numeroTelephone + '\'' +
            ", age=" + age +
            ", nbreHeures=" + nbreHeures +
            ", prixParHeure=" + prixParHeure +
            ", mdp='" + mdp + '\'' +
            '}';    }
    
     public String getMdp(){
        return mdp;
    }
     
    public void setMdp(String mdp){
        this.mdp=mdp;
    }

    public int getNbreHeures() {
        return nbreHeures;
    }

    public double getPrixParHeure() {
        return prixParHeure;
    }
    public void setNbreHeures(int nbreHeures) {
        this.nbreHeures = nbreHeures;
    }


    public void setPrixParHeure(double prixParHeure) {
        this.prixParHeure = prixParHeure;
    }
    @Override
    public double calculSalaire() {
        // Utilisation d'une lambda pour calculer le salaire
        calculsalaire salaireCalcul = () -> nbreHeures * prixParHeure;
        return salaireCalcul.calculSalaire();
       
    }
}
