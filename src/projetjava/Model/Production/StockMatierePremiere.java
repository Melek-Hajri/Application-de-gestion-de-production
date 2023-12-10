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
public final class StockMatierePremiere extends Stock{
    private Map<Matiere_premiere, Double> matieresPremieres;

    public StockMatierePremiere(String nomStock,double capaciteMaximale) {
         super(nomStock,capaciteMaximale);
        matieresPremieres = new HashMap<>();
    }

    public void ajouterMatierePremiere(Matiere_premiere matiere, double quantite) {
        matieresPremieres.put(matiere, matieresPremieres.getOrDefault(matiere, 0.0) + quantite);
    }

    public void retirerMatierePremiere(Matiere_premiere matiere, double quantite) {
        if (matieresPremieres.containsKey(matiere)) {
            double ancienneQuantite = matieresPremieres.get(matiere);
            if (quantite >= ancienneQuantite) {
                matieresPremieres.remove(matiere);
            } else {
                matieresPremieres.put(matiere, ancienneQuantite - quantite);
            }
        }
    }
    //stream sur collection map
//Dans cette version, nous utilisons Stream pour parcourir les entrées de la map matieresPremieres. 
//Pour chaque entrée, nous calculons le coût en utilisant mapToDouble et sum pour obtenir la somme totale du coût
   public double calculCoutMatierePremiere() {
        return matieresPremieres.entrySet()
                .stream()
                .mapToDouble(entry -> entry.getKey().prix_unitaire() * entry.getValue())
                .sum();
    }
}
