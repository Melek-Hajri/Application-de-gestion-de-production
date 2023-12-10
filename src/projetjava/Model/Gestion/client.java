/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava.Model.Gestion;

/**
 *
 * @author admin
 */
public final class client extends Personne implements validationCommande {
    double budget;
    public client(String nom, String tel,double budget) {
        super(nom, tel);
        this.budget = budget;
    }
    public void passerCommande(Commande commande, double montantTotal) {
    commande.setMontantTotal(montantTotal);
    System.out.println("Commande passée avec succès !");
}
    // Méthode pour payer une commande
    public void payerCommande(Commande commande) {
        double montantTotal = commande.getMontantTotal();
            double budgetRestant = budget - montantTotal;
            budget = budgetRestant;
            System.out.println("Commande payée avec succès. Nouveau solde : " + budgetRestant);
        
    }
    @Override
   public boolean valider(double montantTotal) {
    return (budget >= montantTotal) ? true : false;
}



    
}
