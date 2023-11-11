/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava.Model.Gestion;

/**
 *
 * @author hajri
 */
public class Date {
    private int jour;
    private int mois;
    private int annee;

    public Date(int jour, int mois, int annee) {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
    }

    public int comparerDates(Date date) {
        if (this.annee != date.annee) {
            return Integer.compare(this.annee, date.annee);
        } else if (this.mois != date.mois) {
            return Integer.compare(this.mois, date.mois);
        } else {
            return Integer.compare(this.jour, date.jour);
        }
    }
    
    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", jour, mois, annee);
    }
}
