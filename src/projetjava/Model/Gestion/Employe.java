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
public sealed class Employe extends Salarie permits Chef, Operateur {
     List<String> taches;
     private double salaire;

    public Employe(String nom,String prenom, String id, String adresseEmail, String numeroTelephone,double age) {
        super(nom,prenom,id,adresseEmail,numeroTelephone,age);
        this.taches = new ArrayList<>();
    }
    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public double getSalaire() {
        return salaire;
    }

    public List<String> getTaches() {
        return taches;
    }
    public void setTaches(List<String> taches) {
    this.taches = taches;
}
    public void testajouterTachesupp() {
    AutreTache autreTache = () -> System.out.println("Autre tâche ajoutée");
    tacheSupplementaire tacheSupplementaire = new tacheSupplementaire() {
        @Override
        public void faireTache() {
            System.out.println(autreTache);
        }
    };
    // Appel de la méthode "faireTache" de l'interface fonctionnelle
    tacheSupplementaire.faireTache();
}

    @Override
    public String toString() {
        return super.toString() + " taches=" + taches + ", salaire=" + salaire;
    }

   

    
}






