/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava.Model.Production;

import java.util.*;

/**
 *
 * @author admin
 */
public class CalculBenefice {
    private StockProduitsFinis stockProduitsFinis;
    private StockMatierePremiere stockMatierePremiere;

    public CalculBenefice(StockProduitsFinis stockProduitsFinis, StockMatierePremiere stockMatierePremiere) {
        this.stockProduitsFinis = stockProduitsFinis;
        this.stockMatierePremiere = stockMatierePremiere;
    }

    public double Benefice() {
        double chiffreDaffaire = stockProduitsFinis.calculChiffreDaffaire();
        double coutMatierePremiere = stockMatierePremiere.calculCoutMatierePremiere();
        return chiffreDaffaire - coutMatierePremiere;
    }
}
