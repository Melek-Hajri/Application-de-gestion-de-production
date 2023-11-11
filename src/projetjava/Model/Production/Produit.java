/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava.Model.Production;

import projetjava.Model.Production.Exceptions.InvalidDeleteException;
import projetjava.Model.Production.Exceptions.InvalidNegatifException;
import projetjava.Model.Production.Exceptions.InvalidPrixException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hajri
 */
public class Produit implements AjoutMatierePremiere{
    private String nom;
    private String description;
    private double prix;
    private Map<Matiere_premiere, Integer> composants;
    public Produit(String nom, String description, double prix) throws InvalidPrixException{
        if(prix < 0)
            throw new InvalidPrixException("Prix negatif!");
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.composants = new HashMap<>();
    }
    @Override
    public void ajouterMatierePremiere(Matiere_premiere mp, int quantite) throws InvalidNegatifException{
        if(quantite < 0)
            throw new InvalidNegatifException("Quantite ne peut pas etre negative!");
        composants.put(mp, quantite);
    }
    public void retirerMatierePremiere(Matiere_premiere mp) throws InvalidDeleteException{
        if (!this.composants.containsKey(mp)) 
            throw new InvalidDeleteException(mp + " n'existe pas dans les composants de ce produit!");
        composants.remove(mp);
    }
    public int getQuantiteComposant(Matiere_premiere mp) {
        return composants.getOrDefault(mp, 0);
    }
    public Map<Matiere_premiere, Integer> getComposants() {
        return composants;
    }
    public String getNom(){
        return this.nom;
    }
    public String getDescription(){
        return this.description;
    }
    public double getPrix(){
        return this.prix;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setPrix(double prix) throws InvalidPrixException{
        if(prix < 0)
            throw new InvalidPrixException(this.getNom() + ": " + prix + " est un prix negatif!");
        this.prix = prix;
    }
    public void setComposants(HashMap<Matiere_premiere, Integer> composants){
        this.composants = composants;
    }
    
}
