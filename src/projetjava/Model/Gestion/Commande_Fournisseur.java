/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava.Model.Gestion;

import projetjava.Model.Production.AjoutMatierePremiere;
import projetjava.Model.Production.Exceptions.InvalidNegatifException;
import projetjava.Model.Production.Matiere_premiere;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author hajri
 */
public class Commande_Fournisseur extends Commande implements AjoutMatierePremiere, CalculMontant{
    private Map<Matiere_premiere, Integer> matieres;
    private Fournisseur fournisseur;
    public Commande_Fournisseur(int numero, int jour, int mois, int annee, Fournisseur fournisseur){
        super(numero, jour, mois, annee);
        this.matieres = new HashMap<>();
        this.fournisseur = fournisseur;
    }
    @Override
    public void ajouterMatierePremiere(Matiere_premiere matiere, int quantite) throws InvalidNegatifException{
        if(quantite < 0)
            throw new InvalidNegatifException("Quantite ne peut pas etre negative!");
        matieres.put(matiere, quantite);
    }

    public Map<Matiere_premiere, Integer> getMatieres() {
        return matieres;
    }
    
    public void setMatieres(Map<Matiere_premiere, Integer> matieres){
        this.matieres = matieres;
    }
    public Fournisseur getFournisseur(){
        return this.fournisseur;
    }
    public void setFournisseur(Fournisseur fournisseur){
        this.fournisseur = fournisseur;
    }
    @Override
    public void calculMontant(){
        for (Entry<Matiere_premiere, Integer> entry : matieres.entrySet()) {
            double montant = this.getMontantTotal();
            montant += entry.getKey().prix_unitaire() * entry.getValue();
            this.setMontantTotal(montant);
        }
    }
}
