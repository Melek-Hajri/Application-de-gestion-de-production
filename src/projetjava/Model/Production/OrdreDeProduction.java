/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava.Model.Production;

import projetjava.Model.Production.Exceptions.DateDebFinException;
import projetjava.Model.Gestion.Date;

/**
 *
 * @author hajri
 */
public class OrdreDeProduction {
    private int numero;
    private Produit produit;
    private int quantite;
    private Date dateDeb;
    private Date dateFin;
    private LigneDeProduction ligne;
    public OrdreDeProduction(int numero, Produit produit, int quantite, Date dateDeb, Date dateFin) throws DateDebFinException{
        if(dateDeb.comparerDates(dateFin)>0)
            throw new DateDebFinException(dateDeb.toString(), dateDeb.toString()); 
        this.numero = numero;
        this.produit = produit;
        this.quantite = quantite;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
    }
    public void setNumero(int numero){
        this.numero = numero;
    }
    public int getNumero(){
        return numero;
    }
    public void setQuantite(int quantite){
        this.quantite = quantite;
    }
    public int getQuantite(){
        return quantite;
    }
    public void setProduit(Produit produit){
        this.produit = produit;
    }
    public Produit getProduit(){
        return produit;
    }
    public void setDateDeb(Date date){
        this.dateDeb = date;
    }
    public Date getDateDeb(){
        return dateDeb;
    }
    public void setDateFin(Date date){
        this.dateFin = date;
    }
    public Date getDateFin(){
        return dateFin;
    }
    public void setLigne(LigneDeProduction ligne){
        this.ligne = ligne;
    }
    public LigneDeProduction getLigne(){
        return ligne;
    }

    public Object idProperty() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
