/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava.Model.Production;

/**
 *
 * @author admin
 */
public class Stock {
    private String nomStock;
    private double capaciteMaximale;
    public Stock(String nomStock,double capaciteMaximale){
        this.nomStock=nomStock;
        this.capaciteMaximale=capaciteMaximale;
    }

    // Autres attributs et méthodes de la classe

    public String getNomStock() {
        return nomStock;
    }

    public void setNomStock(String nomStock) {
        this.nomStock = nomStock;
    }

    public double getCapaciteMaximale() {
        return capaciteMaximale;
    }

    public void setCapaciteMaximale(double capaciteMaximale) {
        this.capaciteMaximale = capaciteMaximale;
    }

    // Autres méthodes de la classe
}





