/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava.Model.Gestion;

import projetjava.Model.Production.Produit;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author hajri
 */
public class Commande_Client extends Commande implements CalculMontant{
    private Map<Produit, Integer> produits;
    client client;
    public Commande_Client(int numero, int jour, int mois, int annee, client client){
        super(numero, jour, mois, annee);
        this.produits = new HashMap<>();
        this.client = client;
    }
    public void ajouterProduit(Produit produit, int quantite) {
        produits.put(produit, quantite);
    }

    public Map<Produit, Integer> getProduits() {
        return produits;
    }
    
    public void setProduits(Map<Produit, Integer> produits){
        this.produits = produits;
    }
    public client getClient(){
        return this.client;
    }
    public void setClient(client client){
        this.client = client;
    }
    @Override
    public void calculMontant(){
        for (Entry<Produit, Integer> entry : produits.entrySet()) {
            double montant = this.getMontantTotal();
            montant += entry.getKey().getPrix() * entry.getValue();
            this.setMontantTotal(montant);
        }
    }
}
