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
public final class Operateur extends Employe {
    private int nbreHeures; // Ajout de l'attribut nbreHeures
    private double prixParHeure; // Ajout de l'attribut prixParHeure

    public Operateur(String nom,String prenom, String id, String adresseEmail, String numeroTelephone ,double age,int nbreHeures, double prixParHeure) throws NbreHeuresNegatifException {
        super(nom,prenom,id,adresseEmail,numeroTelephone,age);
         if (nbreHeures < 0) {
            throw new NbreHeuresNegatifException("Le nombre d'heures ne peut pas être négatif.");
        }
        this.nbreHeures = nbreHeures;
        this.prixParHeure = prixParHeure;
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

