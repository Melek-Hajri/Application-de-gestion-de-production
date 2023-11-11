/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava.Model.Production;

/**
 *
 * @author admin
 */
import java.util.*;
public class StockProduitsFinis extends Stock{
    private Map<Produit, Double> produitsFinis;

    public StockProduitsFinis(String nomStock,double capaciteMaximale) {
        super(nomStock,capaciteMaximale);
        produitsFinis = new HashMap<>();
    }

    public void ajouterProduit(Produit produit, double quantite) {
        produitsFinis.put(produit, produitsFinis.getOrDefault(produit, 0.0) + quantite);
    }

    public void retirerProduit(Produit produit, double quantite) {
        if (produitsFinis.containsKey(produit)) {
            double ancienneQuantite = produitsFinis.get(produit);
            if (quantite >= ancienneQuantite) {
                produitsFinis.remove(produit);
            } else {
                produitsFinis.put(produit, ancienneQuantite - quantite);
            }
        }
    }
    //lambda sur collection map

    public double calculChiffreDaffaire() {
        return produitsFinis.entrySet()
                .stream()
                .mapToDouble(entry -> entry.getKey().getPrix() * entry.getValue())
                .sum();
    }

}