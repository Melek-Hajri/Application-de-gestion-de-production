/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava.Model.Gestion;

/**
 *
 * @author hajri
 */
public class Commande {
    private int numero;
    private Date date;
    private double montantTotal;
     public Commande(int numero, int jour, int mois, int annee) {
        this.numero = numero;
        this.date = new Date(jour, mois, annee);
        this.montantTotal = 0;
    }

    // Méthodes publiques pour accéder et modifier les attributs

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }
}
